package de.saschafeldmann.adesso.master.thesis.detection.algorithm.fillintheblank;

import com.google.common.base.Strings;
import de.saschafeldmann.adesso.master.thesis.detection.algorithm.DetectionAlgorithm;
import de.saschafeldmann.adesso.master.thesis.detection.algorithm.DetectionOptions;
import de.saschafeldmann.adesso.master.thesis.detection.model.FillInTheBlankTextConcept;
import de.saschafeldmann.adesso.master.thesis.detection.util.DetectionProperties;
import de.saschafeldmann.adesso.master.thesis.detection.util.ValidateUtil;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.util.linguistic.NamedEntityTagAdapter;
import de.saschafeldmann.adesso.master.thesis.util.linguistic.NlpAnnotationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  29.06.2016
 * Author:         Sascha Feldmann (sascha.feldmann@gmx.de)
 * <br /><br />
 * University:
 * Hochschule für Technik und Wirtschaft, Berlin
 * Fachbereich 4
 * Studiengang Internationale Medieninformatik (Master)
 * <br /><br />
 * Company:
 * adesso AG
 * <br /><br />
 * Filltext concept detection.<br />
 * Filltexts are simple concepts. <br />
 * Those are sentences that contain an object that is a named entity and at least one more noun.<br />
 * For the question generation part, the learning student will be asked to fill the object.<br />
 * E.g. if the original sentence was "Berlin is the capital of Germany."
 * the fill text will be "Berlin is the capital of ___?"
 */
@Component
@Scope("prototype")
public class FillInTheBlankConceptDetection implements DetectionAlgorithm<FillInTheBlankTextConcept> {
    private static final Logger LOGGER = LoggerFactory.getLogger(FillInTheBlankConceptDetection.class);

    private static final String REGEX_TO_MATCH_NAMED_ENTITIES = "<%1$s>.*?</%1$s>";
    private static final String REGEX_TO_MATCH_PART_OF_SPEECH_TAGS = "<%1$s>.*?</%1$s>";

    private final DetectionProperties detectionProperties;

    /**
     * Creates a new detection implementation.
     * @param detectionProperties the properties
     */
    @Autowired
    public FillInTheBlankConceptDetection(final DetectionProperties detectionProperties) {
        this.detectionProperties = detectionProperties;
    }

    @Override
    public List<FillInTheBlankTextConcept> execute(final LearningContent learningContent, final DetectionOptions detectionOptions) {
        ValidateUtil.validate(learningContent, detectionOptions);

        return findFillTextCandidates(learningContent);
    }

    private List<FillInTheBlankTextConcept> findFillTextCandidates(final LearningContent learningContent) {
        final List<FillInTheBlankTextConcept> foundConceptsList = new ArrayList<>();

        // identify sentences with at least one named entity or part-of-speech-tag that are candidates (see detection.properties)
        int index = 0;
        for (final String partOfSpeechSentence: learningContent.getPartOfSpeechAnnotatedText()) {
            final String namedEntitySentence = learningContent.getNamedEntityAnnotatedText().get(index);
            final List<String> matchedPartOfSpeechTags = matchesConfiguredPartOfSpeech(learningContent, partOfSpeechSentence);
            final List<String> matchedNamedEntities = matchesConfiguredNamedEntity(learningContent, namedEntitySentence);

            if (matchedNamedEntities.size() > 0) {
                // priority 1: at least one named entity candidate was found in the sentence, so either take it (if count of NER candidates is 1) or pick a random one
                String fillTextCandidate = findRandomCandidate(matchedNamedEntities);

                if (!Strings.isNullOrEmpty(fillTextCandidate)) {
                    addConcept(foundConceptsList, learningContent, namedEntitySentence, fillTextCandidate);
                }
            } else if (matchedPartOfSpeechTags.size() > 0) {
                // priority 2: at least one part-of-speech candidate was found in the sentence, so either take it (if count of POS candidates is 1) or pick a random one
                String fillTextCandidate = findRandomCandidate(matchedPartOfSpeechTags);

                if (!Strings.isNullOrEmpty(fillTextCandidate)) {
                    addConcept(foundConceptsList, learningContent, namedEntitySentence, fillTextCandidate);
                }
            }

            index++;
        }

        return foundConceptsList;
    }

    private void addConcept(final List<FillInTheBlankTextConcept> foundConceptsList, final LearningContent learningContent, final String namedEntitySentence, final String fillTextCandidate) {
        final String originalSentence = NlpAnnotationUtil.removeAllTokenAnnotations(namedEntitySentence);
        final FillInTheBlankTextConcept fillInTheBlankTextConcept = createFillTextConcept(learningContent, originalSentence, fillTextCandidate);

        foundConceptsList.add(fillInTheBlankTextConcept);
    }

    private FillInTheBlankTextConcept createFillTextConcept(LearningContent learningContent, String originalSentence, String fillTextCandidate) {
        return new FillInTheBlankTextConcept.FillTextConceptBuilder()
                .withLearningContent(learningContent)
                .withCorrectAnswer(fillTextCandidate)
                .withFillSentence(buildFillSentence(originalSentence, fillTextCandidate))
                .withOriginalSentence(originalSentence)
                .build();
    }

    private String buildFillSentence(String originalSentence, String fillTextCandidate) {
        return originalSentence.replaceAll(fillTextCandidate, detectionProperties.getFillTextReplacementCharacter());
    }

    private String findRandomCandidate(final List<String> matchedTags) {
        final Set<String> candidates = new HashSet<>();

        for (String matchedTag: matchedTags) {
            String token = NlpAnnotationUtil.removeAllTokenAnnotations(matchedTag);

            candidates.add(token);
        }

        Random random = new Random();

        if (candidates.size() > 0) {
            // returns a random element within the candiates set / array
            return candidates.toArray(new String[candidates.size()])[random.nextInt(candidates.size())];
        } else {
            return null;
        }
    }

    private List<String> matchesConfiguredNamedEntity(LearningContent learningContent, final String namedEntitySentence) {
        final List<String> matchedNamedEntities = new ArrayList<>();

        for (String configuredNamedEntity: detectionProperties.getFillTextFillNamedEntities()) {
            try {
                List<String> namedEntities = NamedEntityTagAdapter.valueOf(configuredNamedEntity).getTagsForLanguage(learningContent.getDeterminedLanguage());

                for (String namedEntity: namedEntities) {
                    Pattern pattern = Pattern.compile(String.format(REGEX_TO_MATCH_NAMED_ENTITIES, namedEntity));
                    Matcher matcher = pattern.matcher(namedEntitySentence);

                    while (matcher.find()) {
                        String foundNamedEntity = matcher.group();
                        matchedNamedEntities.add(foundNamedEntity);
                    }
                }
            } catch (IllegalArgumentException e) {
                LOGGER.error("matchesConfiguredNamedEntity(): properties configuration error: configured named entity does not match one of the enumeration values in NamedEntityTagAdapter. " +
                        "Exception: {}", e);
                throw new FillInTheBlankConceptDetectionException(e);
            }
        }

        return matchedNamedEntities;
    }


    private List<String> matchesConfiguredPartOfSpeech(LearningContent learningContent, final String partOfSpeechSentence) {
        final List<String> partOfSpeechTags = new ArrayList<>();

        for (String namedEntity: detectionProperties.getFillTextFillPartOfSpeechTags()) {
            Pattern pattern = Pattern.compile(String.format(REGEX_TO_MATCH_PART_OF_SPEECH_TAGS, namedEntity));
            Matcher matcher = pattern.matcher(partOfSpeechSentence);

            while (matcher.find()) {
                String foundPartOfSpeechTag = matcher.group();
                partOfSpeechTags.add(foundPartOfSpeechTag);
            }
        }

        return partOfSpeechTags;
    }
}

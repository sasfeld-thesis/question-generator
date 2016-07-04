package de.saschafeldmann.adesso.master.thesis.detection.algorithm.filltext;

import static com.google.common.base.Preconditions.*;

import com.google.common.base.Strings;
import de.saschafeldmann.adesso.master.thesis.detection.algorithm.DetectionAlgorithm;
import de.saschafeldmann.adesso.master.thesis.detection.algorithm.DetectionOptions;
import de.saschafeldmann.adesso.master.thesis.detection.model.FillTextConcept;
import de.saschafeldmann.adesso.master.thesis.detection.model.api.Concept;
import de.saschafeldmann.adesso.master.thesis.detection.util.DetectionProperties;
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
public class FillTextConceptDetection implements DetectionAlgorithm<FillTextConcept> {
    private static final Logger LOGGER = LoggerFactory.getLogger(FillTextConceptDetection.class);

    private static final String REGEX_TO_MATCH_NAMED_ENTITIES = "<%1$s>.*?</%1$s>";
    private static final String REGEX_TO_MATCH_PART_OF_SPEECH_TAGS = "<%1$s>.*?</%1$s>";

    private final DetectionProperties detectionProperties;

    /**
     * Creates a new detection implementation.
     * @param detectionProperties the properties
     */
    @Autowired
    public FillTextConceptDetection(final DetectionProperties detectionProperties) {
        this.detectionProperties = detectionProperties;
    }

    @Override
    public List<FillTextConcept> execute(final LearningContent learningContent, final DetectionOptions detectionOptions) {
        List<Concept> sentencesWithNamedEntityAndAdditionalNoun = findSentencesWithNamedEntitiesAndAnAdditionalNoun(learningContent);
        return null;
    }

    private List<Concept> findSentencesWithNamedEntitiesAndAnAdditionalNoun(final LearningContent learningContent) {
        final List<Concept> foundConceptsList = new ArrayList<>();

        // identify sentences with at least one named entity and one addtional noun / named entity
        int index = 0;
        for (final String partOfSpeechSentence: learningContent.getPartOfSpeechAnnotatedText()) {
            final String namedEntitySentence = learningContent.getNamedEntityAnnotatedText().get(index);
            final List<String> matchedPartOfSpeechTags = matchesConfiguredPartOfSpeech(learningContent, partOfSpeechSentence);
            final List<String> matchedNamedEntities = matchesConfiguredNamedEntity(learningContent, namedEntitySentence);

            if (matchedNamedEntities.size() > 0 && matchedPartOfSpeechTags.size() > 1) {
                // add concept if and only if there are at least two of the supported configured part-of-speech tags in the sentence and at least one of the configured named entities
                String fillTextCandidate = findRandomCandiateThatIsConfiguredPartOfSpeechTagAndNamedEntity(matchedPartOfSpeechTags, matchedNamedEntities);

                if (!Strings.isNullOrEmpty(fillTextCandidate)) {
                    addConcept(foundConceptsList, learningContent, namedEntitySentence, fillTextCandidate);
                }
            }

            index++;
        }

        return foundConceptsList;
    }

    private void addConcept(final List<Concept> foundConceptsList, final LearningContent learningContent, final String namedEntitySentence, final String fillTextCandidate) {
        final String originalSentence = NlpAnnotationUtil.removeAllTokenAnnotations(namedEntitySentence);
        final Concept fillTextConcept = createFillTextConcept(learningContent, originalSentence, fillTextCandidate);

        foundConceptsList.add(fillTextConcept);
    }

    private Concept createFillTextConcept(LearningContent learningContent, String originalSentence, String fillTextCandidate) {
        return new FillTextConcept.FillTextConceptBuilder()
                .withLearningContent(learningContent)
                .withCorrectAnswer(fillTextCandidate)
                .withFillSentence(buildFillSentence(originalSentence, fillTextCandidate))
                .withOriginalSentence(originalSentence)
                .build();
    }

    private String buildFillSentence(String originalSentence, String fillTextCandidate) {
        return originalSentence.replaceAll(fillTextCandidate, detectionProperties.getFillTextReplacementCharacter());
    }

    private String findRandomCandiateThatIsConfiguredPartOfSpeechTagAndNamedEntity(final List<String> matchedPartOfSpeechTags, final List<String> matchedNamedEntities) {
        final Set<String> candidates = new HashSet<>();

        for (String matchedPartOfSpeechTag: matchedPartOfSpeechTags) {
            String partOfSpeechToken = NlpAnnotationUtil.removeAllTokenAnnotations(matchedPartOfSpeechTag);

            for (String matchedNamedEntityTag: matchedNamedEntities) {
                // finds the named entity which is the potential fill text candidate
                String namedEntityToken = NlpAnnotationUtil.removeAllTokenAnnotations(matchedNamedEntityTag);

                if (partOfSpeechToken.equals(namedEntityToken)) {
                    candidates.add(namedEntityToken);
                }
            }
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
                throw new FillTextConceptDetectionException(e);
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

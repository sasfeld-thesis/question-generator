package de.saschafeldmann.adesso.master.thesis.detection.algorithm;

import static com.google.common.base.Preconditions.*;

import com.google.common.base.Strings;
import de.saschafeldmann.adesso.master.thesis.detection.model.FillTextConcept;
import de.saschafeldmann.adesso.master.thesis.detection.util.DetectionProperties;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
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
        List<String> sentencesWithNamedEntityAndAdditionalNoun = findSentencesWithNamedEntitiesAndAnAdditionalNoun(learningContent);
        return null;
    }

    private List<String> findSentencesWithNamedEntitiesAndAnAdditionalNoun(final LearningContent learningContent) {
        final List<String> foundSentencesList = new ArrayList<>();

        // identify sentences with at least one named entity and one addtional noun / named entity
        int index = 0;
        for (final String partOfSpeechSentence: learningContent.getPartOfSpeechAnnotatedText()) {
            final String namedEntitySentence = learningContent.getNamedEntityAnnotatedText().get(index);
            final List<String> matchedPartOfSpeechTags = matchesConfiguredPartOfSpeech(partOfSpeechSentence);
            final List<String> matchedNamedEntities = matchesConfiguredNamedEntity(namedEntitySentence);

            if (matchedNamedEntities.size() > 0 && matchedPartOfSpeechTags.size() > 1) {
                String fillTextCandidate = findRandomCandiateThatIsConfiguredPartOfSpeechTagAndNamedEntity(matchedPartOfSpeechTags, matchedNamedEntities);
            }

            index++;
        }

        return foundSentencesList;
    }

    private String findRandomCandiateThatIsConfiguredPartOfSpeechTagAndNamedEntity(final List<String> matchedPartOfSpeechTags, final List<String> matchedNamedEntities) {
        for (String matchedPartOfSpeechTag: matchedPartOfSpeechTags) {
            
        }
        return null;
    }

    private List<String> matchesConfiguredNamedEntity(final String namedEntitySentence) {
        final List<String> matchedNamedEntities = new ArrayList<>();

        for (String namedEntity: detectionProperties.getFillTextFillNamedEntities()) {
            Pattern pattern = Pattern.compile(String.format(REGEX_TO_MATCH_NAMED_ENTITIES, namedEntity));
            Matcher matcher = pattern.matcher(namedEntitySentence);

            while (matcher.find()) {
                String foundNamedEntity = matcher.group();
                matchedNamedEntities.add(foundNamedEntity);
            }
        }

        return matchedNamedEntities;
    }


    private List<String> matchesConfiguredPartOfSpeech(final String partOfSpeechSentence) {
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

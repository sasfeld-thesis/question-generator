package de.saschafeldmann.adesso.master.thesis.detection.algorithm.cardinalrelation;

import de.saschafeldmann.adesso.master.thesis.detection.algorithm.DetectionAlgorithm;
import de.saschafeldmann.adesso.master.thesis.detection.algorithm.DetectionOptions;
import de.saschafeldmann.adesso.master.thesis.detection.model.CardinalRelationConcept;
import de.saschafeldmann.adesso.master.thesis.detection.util.DetectionProperties;
import de.saschafeldmann.adesso.master.thesis.detection.util.ValidateUtil;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Language;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  11.07.2016
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
 * Implementation of a {@link DetectionAlgorithm}
 */
@Component
@Scope("prototype")
public class CardinalRelationConceptDetection implements DetectionAlgorithm<CardinalRelationConcept> {

    private final DetectionProperties detectionProperties;

    /**
     * Creates a new cardinal relation concept.
     * @param detectionProperties the modules properties
     */
    @Autowired
    public CardinalRelationConceptDetection(final DetectionProperties detectionProperties) {
        this.detectionProperties = detectionProperties;
    }

    @Override
    public List<CardinalRelationConcept> execute(final LearningContent learningContent, final DetectionOptions detectionOptions) {
        ValidateUtil.validate(learningContent, detectionOptions);

        return findSentencesWithCardinalRelations(learningContent);
    }

    private List<CardinalRelationConcept> findSentencesWithCardinalRelations(final LearningContent learningContent) {
        for (final String posAnnotatedSentence : learningContent.getPartOfSpeechAnnotatedText()) {
            if (sentenceMatchesConfiguredCardinalRelationPatterns(learningContent, posAnnotatedSentence)) {

            }
        }

        return null;
    }

    private boolean sentenceMatchesConfiguredCardinalRelationPatterns(LearningContent learningContent, final String posAnnotatedSentence) {
        final Language targetLanguage = learningContent.getDeterminedLanguage();

        final List<String> cardinalRelationCompositePosTags = detectionProperties.getCardinalRelationCompositePosTags(targetLanguage);
        final List<String> cardinalRelationKeyWordsPosTags = detectionProperties.getCardinalRelationKeywordsPosTags(targetLanguage);
        final List<String> cardinalRelationCompositionPosTags = detectionProperties.getCardinalRelationCompositionPosTags(targetLanguage);


        return false;
    }
}

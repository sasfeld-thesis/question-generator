package de.saschafeldmann.adesso.master.thesis.detection.algorithm;

import de.saschafeldmann.adesso.master.thesis.detection.algorithm.cardinalrelation.CardinalRelationConceptDetection;
import de.saschafeldmann.adesso.master.thesis.detection.model.CardinalRelationConcept;
import de.saschafeldmann.adesso.master.thesis.detection.util.DetectionProperties;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Language;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

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
 * Test of {@link de.saschafeldmann.adesso.master.thesis.detection.algorithm.cardinalrelation.CardinalRelationConceptDetection}
 */
public class CardinalRelationConceptDetectionTest {

    private static final String GERMAN_GEOGRAPHY_TEXT = " Deutschland hat 16 Bundesländer.";
    private static final String[] GERMAN_GEOGRAPHY_POS_TEXT = {"<NE>Deutschland</NE><VAFIN>hat</VAFIN><CARD>16</CARD><NN>Bundesländer</NN><$.>.</$.>"};
    private static final String[] GERMAN_GEOGRAPHY_NER_TEXT = {"<I-LOC>Deutschland</I-LOC><O>hat</O><NUMBER>16</NUMBER><O>Bundesländer</O><O>.</O>"};


    @Test
    public void testCardinalRelationConceptDetectsGermanGeographyRelation() throws Exception {
        // given a German geography learning content
        final LearningContent learningContent = newLearningContent(GERMAN_GEOGRAPHY_TEXT, new ArrayList<String>(Arrays.asList(GERMAN_GEOGRAPHY_POS_TEXT)),
                new ArrayList<String>(Arrays.asList(GERMAN_GEOGRAPHY_NER_TEXT)),  Language.GERMAN);
        final CardinalRelationConceptDetection detectionAlgorithm = newCardinalRelationDetectionAlgorithm();

        // when detect concepts is called
        final List<CardinalRelationConcept> detectedConcepts = detectionAlgorithm.execute(learningContent, new DetectionOptions());

        // then the list of detected concepts should not be empty
        assertTrue("the list of detected concepts should not be empty", detectedConcepts.size() > 0);
    }

    private CardinalRelationConceptDetection newCardinalRelationDetectionAlgorithm() throws Exception {
        return new CardinalRelationConceptDetection(new DetectionProperties());
    }

    private LearningContent newLearningContent(String originalText, ArrayList<String> posSentences, ArrayList<String> nerSentences, Language givenLanguage) {
        final LearningContent learningContent = new LearningContent.LearningContentBuilder()
                .withRawText(originalText)
                .withTitle("CardinalRelationConceptDetectionTest test content")
                .withType(LearningContent.Type.DIRECT_RAWTEXT)
                .build();

        learningContent.setPartOfSpeechAnnotatedText(posSentences);
        learningContent.setNamedEntityAnnotatedText(nerSentences);
        learningContent.setDeterminedLanguage(givenLanguage);

        return learningContent;
    }
}

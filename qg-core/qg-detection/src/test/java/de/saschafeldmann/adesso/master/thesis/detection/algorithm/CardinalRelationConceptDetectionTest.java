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
    private static final String GERMAN_GEOGRAPHY_TEXT = "Deutschland hat 16 Bundesländer.";
    private static final String[] GERMAN_GEOGRAPHY_POS_TEXT = {"<NE>Deutschland</NE><VAFIN>hat</VAFIN><CARD>16</CARD><NN>Bundesländer</NN><$.>.</$.>"};
    private static final String[] GERMAN_GEOGRAPHY_NER_TEXT = {"<I-LOC>Deutschland</I-LOC><O>hat</O><NUMBER>16</NUMBER><O>Bundesländer</O><O>.</O>"};

    private static final String ENGLISH_GEOGRAPHY_TEXT = "Germany has 16 federal states.";
    private static final String[] ENGLISH_GEOGRAPHY_POS_TEXT = {"<NNP>Germany</NNP><VBZ>has</VBZ><CD>16</CD><JJ>federal</JJ><NNS>states</NNS><.>.</.>"};
    private static final String[] ENGLISH_GEOGRAPHY_NER_TEXT = {"<LOCATION>Germany</LOCATION><O>has</O><NUMBER>16</NUMBER><O>federal</O><O>states</O><O>.</O>"};

    private static final String GERMAN_NON_CARDINAL_GEOGRAPHY_TEXT = "Die Bundesrepublik Deutschland liegt in Europa.\n" +
            "Die Hauptstadt von Deutschland ist Berlin.\n" +
            "Die größten Städte von Deutschland sind Berlin, Hamburg und Köln.";
    private static final String[] GERMAN_NON_CARDINAL_GEOGRAPHY_NER_TEXT = {"<ART>Die</ART><NN>Bundesrepublik</NN><NE>Deutschland</NE><VVFIN>liegt</VVFIN><APPR>in</APPR><NE>Europa</NE><$.>.</$.>",
                                                                            "<ART>Die</ART><NN>Hauptstadt</NN><APPR>von</APPR><NE>Deutschland</NE><VAFIN>ist</VAFIN><NE>Berlin</NE><$.>.</$.>",
                                                                            "<ART>Die</ART><ADJA>größten</ADJA><NN>Städte</NN><APPR>von</APPR><NE>Deutschland</NE><VAFIN>sind</VAFIN><NE>Berlin</NE><$,>,</$,><NE>Hamburg</NE><KON>und</KON><NE>Köln</NE><$.>.</$.>"};
    private static final String[] GERMAN_NON_CARDINAL_GEOGRAPHY_POS_TEXT = {"<O>Die</O><I-LOC>Bundesrepublik</I-LOC><I-LOC>Deutschland</I-LOC><O>liegt</O><O>in</O><I-LOC>Europa</I-LOC><O>.</O>",
                                                                            "<O>Die</O><O>Hauptstadt</O><O>von</O><I-LOC>Deutschland</I-LOC><O>ist</O><I-LOC>Berlin</I-LOC><O>.</O>",
                                                                            "<O>Die</O><O>größten</O><O>Städte</O><O>von</O><I-LOC>Deutschland</I-LOC><O>sind</O><I-LOC>Berlin</I-LOC><O>,</O><I-LOC>Hamburg</I-LOC><O>und</O><O>Köln</O><O>.</O>"};

    private static final String ENGLISH_NON_CARDINAL_GEOGRAPHY_TEXT = "The Bundesrepublik Germany is part of Europe. The capital of Germany is Berlin.";
    private static final String[] ENGLISH_NON_CARDINAL_GEOGRAPHY_NER_TEXT = {"<O>The</O><LOCATION>Bundesrepublik</LOCATION><LOCATION>Germany</LOCATION><O>is</O><O>part</O><O>of</O><LOCATION>Europe</LOCATION><O>.</O>",
                                                                            "<O>The</O><O>capital</O><O>of</O><LOCATION>Germany</LOCATION><O>is</O><LOCATION>Berlin</LOCATION><O>.</O>"};
    private static final String[] ENGLISH_NON_CARDINAL_GEOGRAPHY_POS_TEXT = {"<DT>The</DT><NNP>Bundesrepublik</NNP><NNP>Germany</NNP><VBZ>is</VBZ><NN>part</NN><IN>of</IN><NNP>Europe</NNP><.>.</.>",
                                                                              "<DT>The</DT><NN>capital</NN><IN>of</IN><NNP>Germany</NNP><VBZ>is</VBZ><NNP>Berlin</NNP><.>.</.>"};


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
        // and the following concept should have been detected
        assertEquals("Deutschland", detectedConcepts.get(0).getComposite());
        assertEquals(1, detectedConcepts.get(0).getCompositeCardinality());
        assertEquals("Bundesländer", detectedConcepts.get(0).getComposition());
        assertEquals(16, detectedConcepts.get(0).getCompositionCardinality());
    }

    @Test
    public void testCardinalRelationConceptDetectsEnglishGeographyRelation() throws Exception {
        // given a German geography learning content
        final LearningContent learningContent = newLearningContent(ENGLISH_GEOGRAPHY_TEXT, new ArrayList<String>(Arrays.asList(ENGLISH_GEOGRAPHY_POS_TEXT)),
                new ArrayList<String>(Arrays.asList(ENGLISH_GEOGRAPHY_NER_TEXT)),  Language.ENGLISH);
        final CardinalRelationConceptDetection detectionAlgorithm = newCardinalRelationDetectionAlgorithm();

        // when detect concepts is called
        final List<CardinalRelationConcept> detectedConcepts = detectionAlgorithm.execute(learningContent, new DetectionOptions());

        // then the list of detected concepts should not be empty
        assertTrue("the list of detected concepts should not be empty", detectedConcepts.size() > 0);
        // and the following concept should have been detected
        assertEquals("Germany", detectedConcepts.get(0).getComposite());
        assertEquals(1, detectedConcepts.get(0).getCompositeCardinality());
        assertEquals("federal states", detectedConcepts.get(0).getComposition());
        assertEquals(16, detectedConcepts.get(0).getCompositionCardinality());
    }

    @Test
    public void testCardinalRelationConceptDoesNotDetectNoCardinalRelationSentencesGerman() throws  Exception {
        // given a German non-cardinal Geography learning content
        final LearningContent learningContent = newLearningContent(GERMAN_NON_CARDINAL_GEOGRAPHY_TEXT, new ArrayList<String>(Arrays.asList(GERMAN_NON_CARDINAL_GEOGRAPHY_POS_TEXT)),
                new ArrayList<String>(Arrays.asList(GERMAN_NON_CARDINAL_GEOGRAPHY_NER_TEXT)),  Language.GERMAN);
        final CardinalRelationConceptDetection detectionAlgorithm = newCardinalRelationDetectionAlgorithm();

        // when detect concepts is called
        final List<CardinalRelationConcept> detectedConcepts = detectionAlgorithm.execute(learningContent, new DetectionOptions());

        // then the list of detected concepts should be empty
        assertEquals(0, detectedConcepts.size());
    }

    @Test
    public void testCardinalRelationConceptDoesNotDetectNoCardinalRelationSentencesEnglish() throws  Exception {
        // given a German non-cardinal Geography learning content
        final LearningContent learningContent = newLearningContent(ENGLISH_NON_CARDINAL_GEOGRAPHY_TEXT, new ArrayList<String>(Arrays.asList(ENGLISH_NON_CARDINAL_GEOGRAPHY_POS_TEXT)),
                new ArrayList<String>(Arrays.asList(ENGLISH_NON_CARDINAL_GEOGRAPHY_NER_TEXT)),  Language.ENGLISH);
        final CardinalRelationConceptDetection detectionAlgorithm = newCardinalRelationDetectionAlgorithm();

        // when detect concepts is called
        final List<CardinalRelationConcept> detectedConcepts = detectionAlgorithm.execute(learningContent, new DetectionOptions());

        // then the list of detected concepts should be empty
        assertEquals(0, detectedConcepts.size());
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

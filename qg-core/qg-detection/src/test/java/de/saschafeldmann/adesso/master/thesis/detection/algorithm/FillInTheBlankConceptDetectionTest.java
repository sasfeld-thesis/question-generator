package de.saschafeldmann.adesso.master.thesis.detection.algorithm;

import static org.junit.Assert.*;

import de.saschafeldmann.adesso.master.thesis.detection.algorithm.fillintheblank.FillInTheBlankConceptDetection;
import de.saschafeldmann.adesso.master.thesis.detection.model.FillInTheBlankTextConcept;
import de.saschafeldmann.adesso.master.thesis.detection.util.DetectionProperties;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Course;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Language;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  04.07.2016
 * Author:         Sascha Feldmann (sascha.feldmann@gmx.de)
 * <br><br>
 * University:
 * Hochschule für Technik und Wirtschaft, Berlin
 * Fachbereich 4
 * Studiengang Internationale Medieninformatik (Master)
 * <br><br>
 * Company:
 * adesso AG
 * <br><br>
 * Test of the {@link de.saschafeldmann.adesso.master.thesis.detection.algorithm.fillintheblank.FillInTheBlankConceptDetection} algorithm.
 */
public class FillInTheBlankConceptDetectionTest {

    private static final String GERMAN_GEOGRAPHY_TEXT = "Die Bundesrepublik Deutschland liegt in Europa. Die Hauptstadt von Deutschland ist Berlin.";
    private static final String[] GERMAN_GEOGRAPHY_POS_TEXT = {"<ART>Die</ART><NN>Bundesrepublik</NN><NE>Deutschland</NE><VVFIN>liegt</VVFIN><APPR>in</APPR><NE>Europa</NE><$.>.</$.>",
                                                                "<ART>Die</ART><NN>Hauptstadt</NN><APPR>von</APPR><NE>Deutschland</NE><VAFIN>ist</VAFIN><NE>Berlin</NE><$.>.</$.>"};
    private static final String[] GERMAN_GEOGRAPHY_NER_TEXT = {"<O>Die</O><I-LOC>Bundesrepublik</I-LOC><I-LOC>Deutschland</I-LOC><O>liegt</O><O>in</O><I-LOC>Europa</I-LOC><O>.</O>",
                                                                "<O>Die</O><O>Hauptstadt</O><O>von</O><I-LOC>Deutschland</I-LOC><O>ist</O><I-LOC>Berlin</I-LOC><O>.</O>"};

    private static final String ENGLISH_GEOGRAPHY_TEXT = "The Bundesrepublik Germany is part of Europe. The capital of Germany is Berlin.";
    private static final String[] ENGLISH_GEOGRAPHY_POS_TEXT = {"<DT>The</DT><NNP>Bundesrepublik</NNP><NNP>Germany</NNP><VBZ>is</VBZ><NN>part</NN><IN>of</IN><NNP>Europe</NNP><.>.</.>",
                                                                "<DT>The</DT><NN>capital</NN><IN>of</IN><NNP>Germany</NNP><VBZ>is</VBZ><NNP>Berlin</NNP><.>.</.>"};
    private static final String[] ENGLISH_GEOGRAPHY_NER_TEXT = {"<O>The</O><LOCATION>Bundesrepublik</LOCATION><LOCATION>Germany</LOCATION><O>is</O><O>part</O><O>of</O><LOCATION>Europe</LOCATION><O>.</O>",
                                                                "<O>The</O><O>capital</O><O>of</O><LOCATION>Germany</LOCATION><O>is</O><LOCATION>Berlin</LOCATION><O>.</O>"};

    @Test
    public void testFillTextConceptDetectionDetectsGermanGeographyConcepts() throws Exception {
        // given a German geography learning content and the algorithm
        LearningContent learningContent = newLearningContent(GERMAN_GEOGRAPHY_TEXT, new ArrayList<String>(Arrays.asList(GERMAN_GEOGRAPHY_POS_TEXT)),
                new ArrayList<String>(Arrays.asList(GERMAN_GEOGRAPHY_NER_TEXT)), Language.GERMAN);
        DetectionAlgorithm<FillInTheBlankTextConcept> algorithm = newFillTextConceptAlgorithm();

        // when the algorithm is called
        List<FillInTheBlankTextConcept> detectedFillInTheBlankTextConcepts = algorithm.execute(learningContent, new DetectionOptions());

        // then make sure that the expected concepts were detected
        assertTrue("At least one fill text concept should have been detected", detectedFillInTheBlankTextConcepts.size() > 0);
}

    @Test
    public void testFillTextConceptDetectionDetectsEnglishGeographyConcepts() throws Exception {
        // given an English geography learning content and the algorithm
        LearningContent learningContent = newLearningContent(ENGLISH_GEOGRAPHY_TEXT, new ArrayList<String>(Arrays.asList(ENGLISH_GEOGRAPHY_POS_TEXT)),
                new ArrayList<String>(Arrays.asList(ENGLISH_GEOGRAPHY_NER_TEXT)), Language.ENGLISH);
        DetectionAlgorithm<FillInTheBlankTextConcept> algorithm = newFillTextConceptAlgorithm();

        // when the algorithm is called
        List<FillInTheBlankTextConcept> detectedFillInTheBlankTextConcepts = algorithm.execute(learningContent, new DetectionOptions());

        // then make sure that the expected concepts were detected
        assertTrue("At least one fill text concept should have been detected", detectedFillInTheBlankTextConcepts.size() > 0);
    }

    private LearningContent newLearningContent(String germanGeographyText, List<String> germanGeographyPosText, List<String> germanGeographyNerText, Language givenLanguage) {
        final LearningContent learningContent = new LearningContent.LearningContentBuilder()
                .withRawText(germanGeographyText)
                .withTitle("FillTextConceptDetectionTest test content")
                .withType(LearningContent.Type.DIRECT_RAWTEXT)
                .withCourse(newCourse())
                .build();

        learningContent.setPartOfSpeechAnnotatedText(germanGeographyPosText);
        learningContent.setNamedEntityAnnotatedText(germanGeographyNerText);
        learningContent.setDeterminedLanguage(givenLanguage);

        return learningContent;
    }

    private DetectionAlgorithm<FillInTheBlankTextConcept> newFillTextConceptAlgorithm() throws Exception {
        DetectionProperties properties = new DetectionProperties();
        return new FillInTheBlankConceptDetection(properties);
    }

    private Course newCourse() {
        return new Course.CourseBuilder()
                .withLanguage(Language.GERMAN)
                .withTitle("Unit test course")
                .withViewUrl("http://unittest.de")
                .build();
    }
}

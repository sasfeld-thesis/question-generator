package de.saschafeldmann.adesso.master.thesis.detection.util;

import de.saschafeldmann.adesso.master.thesis.detection.algorithm.DetectionOptions;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Course;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Language;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  23.08.2016
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
 * Test of {@link ValidateUtil}
 */
public class ValidateUtilTest {

    @Test(expected = NullPointerException.class)
    public void testValidateLearningContentThrowsNullpointerIfLearningContentIsNull() {
        // given, when
        ValidateUtil.validate(null, null);
    }

    @Test(expected = NullPointerException.class)
    public void testValidateLearningContentThrowsNullpointerIfDetectionOptionstIsNull() {
        // given, when
        ValidateUtil.validate(newValidLearningContentWithoutPos(), null);
    }

    @Test(expected = NullPointerException.class)
    public void testValidateLearningContentThrowsNullpointerIfPosTextIsNull() {
        // given, when
        ValidateUtil.validate(newValidLearningContentWithoutPos(), newDetectionOptions());
    }

    @Test(expected = NullPointerException.class)
    public void testValidateLearningContentThrowsNullpointerIfNerTextIsNull() {
        // given, when
        LearningContent learningContent = newValidLearningContentWithoutPos();
        String[] partOfSpeech = {"<POS>text</POS>"};
        learningContent.setPartOfSpeechAnnotatedText(Arrays.asList(partOfSpeech));

        ValidateUtil.validate(learningContent, newDetectionOptions());
    }

    @Test(expected = NullPointerException.class)
    public void testValidateLearningContentThrowsNullpointerIfLanguageTextIsNull() {
        // given, when
        LearningContent learningContent = newValidLearningContentWithoutPos();
        String[] partOfSpeech = {"<POS>text</POS>"};
        learningContent.setPartOfSpeechAnnotatedText(Arrays.asList(partOfSpeech));
        String[] namedEntity = {"<NER>text</NER>"};
        learningContent.setNamedEntityAnnotatedText(Arrays.asList(namedEntity));

        ValidateUtil.validate(learningContent, newDetectionOptions());
    }

    private DetectionOptions newDetectionOptions() {
        DetectionOptions detectionOptions = new DetectionOptions();
        detectionOptions.setNumberOfFilltextQuestions(10);
        detectionOptions.getNumberOfFilltextQuestions();
        detectionOptions.setNumberOfCardinalityQuestions(10);
        detectionOptions.getNumberOfCardinalityQuestions();

        return detectionOptions;
    }

    @Test
    public void testValidateLearningContentHappyCase() {
        // given, when
        LearningContent learningContent = newValidLearningContentWithoutPos();
        String[] partOfSpeech = {"<POS>text</POS>"};
        learningContent.setPartOfSpeechAnnotatedText(Arrays.asList(partOfSpeech));
        String[] namedEntity = {"<NER>text</NER>"};
        learningContent.setNamedEntityAnnotatedText(Arrays.asList(namedEntity));
        learningContent.setDeterminedLanguage(Language.ENGLISH);

        try {
            ValidateUtil.validate(learningContent, newDetectionOptions());
        } catch (NullPointerException e) {
            Assert.fail("A nullpointer exception must not be thrown: the learning content should have been validated correctly: " + learningContent);
        }
    }

    private LearningContent newValidLearningContentWithoutPos() {
        return  new LearningContent.LearningContentBuilder()
                .withCourse(newCourse())
                .withType(LearningContent.Type.DIRECT_RAWTEXT)
                .withRawText("This is a test sentence")
                .withTitle("UnitTest")
                .build();
    }

    private Course newCourse() {
        return new Course.CourseBuilder()
                .withLanguage(Language.ENGLISH)
                .withTitle("Test course")
                .withViewUrl("http://test.co")
                .build();
    }

}

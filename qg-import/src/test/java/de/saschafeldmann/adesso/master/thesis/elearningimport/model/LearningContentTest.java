package de.saschafeldmann.adesso.master.thesis.elearningimport.model;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  24.05.2016
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
 * Learning content building test.
 */
public class LearningContentTest {

    @Test
    public void testCourseBuilderIsSuccessful() {
        // given a learning content build
        LearningContent learningContent = newLearningContent();

        // then assert that the expected course instane was build
        assertEquals("Test title", learningContent.getTitle());
        assertEquals("Test raw text", learningContent.getRawText());
        assertEquals(LearningContent.Type.FILE, learningContent.getType());
    }

    protected LearningContent newLearningContent() {
        return new LearningContent.LearningContentBuilder()
                    .withTitle("Test title")
                    .withRawText("Test raw text")
                    .withCourse(newCourse())
                    .withType(LearningContent.Type.FILE).build();
    }

    @Test
    public void testLearningContentBuildThrowsNullpointerExceptionOnNullTitle() {
        try {
            // given a learning content build with null arguments
            LearningContent learningContent = new LearningContent.LearningContentBuilder()
                    .withTitle(null)
                    .build();

            fail("The LearningContentBuilder should throw a nullpointer exception if a null title is given.");
        } catch (NullPointerException e) {
            // then we should run into an exception
        }
    }

    @Test
    public void testLearningContentBuildThrowsNullpointerExceptionOnEmptyTitle() {
        try {
            // given a learning content build with null arguments
            LearningContent learningContent = new LearningContent.LearningContentBuilder()
                    .withTitle(" ")
                    .build();

            fail("The LearningContentBuilder should throw a nullpointer exception if an empty title is given.");
        } catch (NullPointerException e) {
            // then we should run into an exception
        }
    }

    @Test
    public void testSetDeterminedLanguageSetsLanguageCouldNotBeDetermined() {
        // given a learning cotnent with determined language
        LearningContent learningContent = newLearningContent();

        // when a determined language is set
        learningContent.setDeterminedLanguage(Language.ENGLISH);

        // then the flag language coud not be determined should be false
        assertFalse(learningContent.isLanguageCouldNotBeDetermined());
        assertEquals(Language.ENGLISH, learningContent.getDeterminedLanguage());
    }

    @Test
    public void testSetFallbackLanguageSetsLanguageCouldNotBeDetermined() {
        // given a learning content with determined language
        LearningContent learningContent = newLearningContent();
        Course course = newCourse();

        // when fallback is called
        learningContent.setFallbackLanguage(course);

        // then the flag language coud not be determined should be false
        assertTrue(learningContent.isLanguageCouldNotBeDetermined());
    }

    private Course newCourse() {
        return new Course.CourseBuilder()
                .withTitle("Test title")
                .withLanguage(Language.GERMAN)
                .withViewUrl("Test url")
                .build();
    }

    @Test
    public void testDeletedAnnotatedTextSetsHasAnnotatedTextFlag() {
        // given a learning content with determined language
        LearningContent learningContent = newLearningContent();

        // when annotated text is deleted
        learningContent.deleteAnnotatedText();

        // then the flag has annotated text should be false
        assertFalse(learningContent.hasAnnotatedText());
 }

    @Test
    public void testSetPartOfSpeechAnnotatedTextLeadsToWasNotDeletedFlag() {
        // given a learning content with determined language
        LearningContent learningContent = newLearningContent();

        // when annotated text is set
        ArrayList<String> partOfSpeechSentences = new ArrayList<String>();
        partOfSpeechSentences.add("<NN>noun</NN>");
        learningContent.setPartOfSpeechAnnotatedText(partOfSpeechSentences);

        ArrayList<String> namedEntitySentences = new ArrayList<String>();
        namedEntitySentences.add("<DATE>2016</DATE>");
        learningContent.setNamedEntityAnnotatedText(namedEntitySentences);

        // then the flag has annotated text should be true
        assertTrue(learningContent.hasAnnotatedText());
        assertEquals(partOfSpeechSentences, learningContent.getPartOfSpeechAnnotatedText());
        assertEquals(namedEntitySentences, learningContent.getNamedEntityAnnotatedText());
    }

    @Test
    public void testResetAnnotatedTextResetsBothPosAndNerTexts() {
        LearningContent learningContent = newLearningContent();

        // when annotated text is set and afterwards reset
        ArrayList<String> partOfSpeechSentences = new ArrayList<String>();
        partOfSpeechSentences.add("<NN>noun</NN>");
        learningContent.setPartOfSpeechAnnotatedText(partOfSpeechSentences);

        ArrayList<String> namedEntitySentences = new ArrayList<String>();
        namedEntitySentences.add("<DATE>2016</DATE>");
        learningContent.setNamedEntityAnnotatedText(namedEntitySentences);
        learningContent.resetAnnotatedText();

        // then the annotated texts should be the empty string
        assertEquals(0, learningContent.getNamedEntityAnnotatedText().size());
        assertEquals(0, learningContent.getPartOfSpeechAnnotatedText().size());
    }
}

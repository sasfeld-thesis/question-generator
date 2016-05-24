package de.saschafeldmann.adesso.master.thesis.elearningimport.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  24.05.2016
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
 * Learning content building test.
 */
public class LearningContentTest {

    @Test
    public void testCourseBuilderIsSuccessful() {
        // given a learning content build
        LearningContent learningContent = new LearningContent.LearningContentBuilder()
                .withTitle("Test title")
                .withRawText("Test raw text")
                .withType(LearningContent.Type.FILE).build();

        // then assert that the expected course instane was build
        assertEquals("Test title", learningContent.getTitle());
        assertEquals("Test raw text", learningContent.getRawText());
        assertEquals(LearningContent.Type.FILE, learningContent.getType());
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
}

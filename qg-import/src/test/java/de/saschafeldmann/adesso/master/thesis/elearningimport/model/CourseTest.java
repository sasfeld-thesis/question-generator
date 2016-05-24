package de.saschafeldmann.adesso.master.thesis.elearningimport.model;

import static org.junit.Assert.*;

import com.google.common.collect.ImmutableCollection;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


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
 * Unit test of {@link Course}, {@link Language} and {@link LearningContent} instances.
 */
public class CourseTest {

    public static List<LearningContent> getLearningContentList() {
        List<LearningContent> list = new ArrayList<LearningContent>();

        list.add(new LearningContent.LearningContentBuilder()
                .withType(LearningContent.Type.FILE)
                .withTitle("Learning content title 1")
                .withRawText("Learning content 1")
                .build());
        list.add(new LearningContent.LearningContentBuilder()
                .withType(LearningContent.Type.FILE)
                .withTitle("Learning content title 2")
                .withRawText("Learning content 2")
                .build());

        return list;
    }

    @Test
    public void testCourseBuilderIsSuccessful() {
        // given a course build
        Course course = getCourse();

        // then assert that the expected course instane was build
        assertEquals("Test title", course.getTitle());
        assertEquals(Language.GERMAN, course.getLanguage());
        assertEquals("Test url", course.getViewUrl());
    }

    private Course getCourse() {
        return new Course.CourseBuilder()
                .withTitle("Test title")
                .withLanguage(Language.GERMAN)
                .withViewUrl("Test url")
                .build();
    }

    @Test
    public void testCourseBuildThrowsNullpointerExceptionOnNullTitle() {
        try {
            // given a course build with null arguments
            Course course = new Course.CourseBuilder()
                    .withTitle(null)
                    .build();

            fail("The CourseBuilder should throw a nullpointer exception if a null title is given.");
        } catch (NullPointerException e) {
            // then we should run into an exception
        }
    }

    @Test
    public void testCourseBuildThrowsNullpointerExceptionOnEmptyTitle() {
        try {
            // given a course build with null arguments
            Course course = new Course.CourseBuilder()
                    .withTitle(" ")
                    .build();

            fail("The CourseBuilder should throw a nullpointer exception if an empty title is given.");
        } catch (NullPointerException e) {
            // then we should run into an exception
        }
    }

    @Test
    public void testCourseBuildThrowsNullpointerExceptionOnNullViewUrl() {
        try {
            // given a course build with null arguments
            Course course = new Course.CourseBuilder()
                    .withTitle("Test title")
                    .withViewUrl(null)
                    .build();


            fail("The CourseBuilder should throw a nullpointer exception if a null view url is given.");
        } catch (NullPointerException e) {
            // then we should run into an exception
        }
    }

    @Test
    public void testCourseBuildThrowsNullpointerExceptionOnEmptyViewUrl() {
        try {
            // given a course build with null arguments
            Course course =  new Course.CourseBuilder()
                    .withTitle("Test title")
                    .withViewUrl(" ")
                    .build();

            fail("The CourseBuilder should throw a nullpointer exception if an empty view url is given.");
        } catch (NullPointerException e) {
            // then we should run into an exception
        }
    }

    @Test
    public void testCourseBuildThrowsNullpointerExceptionOnNullLanguage() {
        try {
            // given a course build with null arguments
            Course course = new Course.CourseBuilder()
                    .withTitle("Test title")
                    .withViewUrl("http://someurl.de")
                    .withLanguage(null)
                    .build();


            fail("The CourseBuilder should throw a nullpointer exception if a null language is given.");
        } catch (NullPointerException e) {
            // then we should run into an exception
        }
    }

    @Test
    public void testCourseGetLearningContentsAddReplacesIfExisting() {
        // given a course
        Course course = getCourse();

        // gand a list of learning contents
        List<LearningContent> learningContentList = getLearningContentList();
        int originalSize = learningContentList.size();

        // when added to the learning unit
        for (LearningContent learningContent: learningContentList) {
            course.addOrReplaceLearningContent(learningContent);
        }

        // and e learning content with same title as first in current list is added
        LearningContent changedLearningContent = new LearningContent.LearningContentBuilder()
                .withRawText(learningContentList.get(0).getRawText() + " changed")
                .withTitle(learningContentList.get(0).getTitle())
                .withType(learningContentList.get(0).getType())
                .build();

        // when added to the course
        course.addOrReplaceLearningContent(changedLearningContent);

        // then the second returned course should be the new one, not the old one
        assertEquals(changedLearningContent, course.getLearningContents().get(1));

        // and the number of learning contents should not have been increased
        assertEquals(originalSize, course.getLearningContents().size());
    }
}

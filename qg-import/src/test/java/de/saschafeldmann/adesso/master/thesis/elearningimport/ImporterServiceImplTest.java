package de.saschafeldmann.adesso.master.thesis.elearningimport;

import static org.junit.Assert.*;

import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Course;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Language;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.elearningimport.parser.ParserException;
import de.saschafeldmann.adesso.master.thesis.elearningimport.parser.TikaRawtextParserAdapter;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  28.05.2016
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
 * Test of the {@link ImporterServiceImpl} service facade
 */
public class ImporterServiceImplTest {
    private static final String COURSE_TITLE = "Testcourse";
    private static final String COURSE_VIEW_URL = "http://testcourse.de";
    private static final Language COURSE_LANGUAGE = Language.ENGLISH;
    private static final String RAWTEXT_LEARNING_CONTENT_TITLE = "Testrawtext";
    private static final String RAWTEXT_LEARNING_CONTENT_TEXT = "Test content";
    private static final String FILE_LEARNING_CONTENT = "/files/testdocument.docx";
    private static final String FILE_LEARNING_CONTENT_TITLE = "testdocument.docx";
    private static final String FILE_LEARNING_CONTENT_TEXT_FIRSTLINE =  "Test document";


    private ImporterService importerService;

    @Before
    public void setUp() {
        this.importerService = newImporterServiceImpl();
    }

    private ImporterService newImporterServiceImpl() {
        return new ImporterServiceImpl(new TikaRawtextParserAdapter());
    }

    @Test
    public void testBuildNewCourseInstanceCreatesInstance() {
        // given course data
        Course course = importerService.buildNewCourseInstance(
                COURSE_TITLE,
                COURSE_VIEW_URL,
                COURSE_LANGUAGE
        );

        // then the expected course should have been built
        assertEquals(COURSE_TITLE, course.getTitle());
        assertEquals(COURSE_VIEW_URL, course.getViewUrl());
        assertEquals(COURSE_LANGUAGE, course.getPrimaryLanguage());
    }

    @Test
    public void addOrReplaceLearningContentByRawtextAddsLearningContentToAGivenCourse() {
        // given a course
        Course course = newCourse();

        // and given learning content raw text
        // when service method is called
        importerService.addOrReplaceLearningContentByRawtext(course, RAWTEXT_LEARNING_CONTENT_TITLE, RAWTEXT_LEARNING_CONTENT_TEXT);

        // then the following learning content should have been added to the course
        assertEquals(1, course.getLearningContents().size());
        assertEquals(RAWTEXT_LEARNING_CONTENT_TITLE, course.getLearningContents().get(0).getTitle());
        assertEquals(RAWTEXT_LEARNING_CONTENT_TEXT, course.getLearningContents().get(0).getRawText());
        // and especially the type raw text should have been set automatically
        assertEquals(LearningContent.Type.DIRECT_RAWTEXT, course.getLearningContents().get(0).getType());
    }

    @Test
    public void addOrReplaceLearningContentByFileAddsLearningContentToAGivenCourse() throws URISyntaxException, ParserException {
        // given a course
        Course course = newCourse();

        // and given learning content file
        // when service method is called
        importerService.addOrReplaceLearningContentByFile(course, getFile(FILE_LEARNING_CONTENT));

        // then the following learning content should have been added to the course
        assertEquals(1, course.getLearningContents().size());
        assertEquals(FILE_LEARNING_CONTENT_TITLE, course.getLearningContents().get(0).getTitle());
        assertTrue(course.getLearningContents().get(0).getRawText().contains(FILE_LEARNING_CONTENT_TEXT_FIRSTLINE));
        // and especially the type file should have been set automatically
        assertEquals(LearningContent.Type.FILE, course.getLearningContents().get(0).getType());
    }

    @Test
    public void addOrReplaceLearningContentWithTypeAddsLearningContentToAGivenCourse() {
        // given a course
        Course course = newCourse();

        // and given learning content
        // when service method is called
        importerService.addOrReplaceLearningContentWithType(course, RAWTEXT_LEARNING_CONTENT_TITLE, RAWTEXT_LEARNING_CONTENT_TEXT, LearningContent.Type.FILE);

        // then the following learning content should have been added to the course
        assertEquals(1, course.getLearningContents().size());
        assertEquals(RAWTEXT_LEARNING_CONTENT_TITLE, course.getLearningContents().get(0).getTitle());
        assertEquals(RAWTEXT_LEARNING_CONTENT_TEXT, course.getLearningContents().get(0).getRawText());
        assertEquals(LearningContent.Type.FILE, course.getLearningContents().get(0).getType());
    }

    @Test
    public void removeLearningContentRemovesSuccessfully() {
        // given a course with attached learning content
        Course course = newCourse();
        LearningContent learningContent = newLearningContent();
        course.addOrReplaceLearningContent(learningContent);

        // when service method is called
        importerService.removeLearningContent(course, learningContent);

        // then the learning contents should have been removed
        assertEquals(0, course.getLearningContents().size());
    }

    private Course newCourse() {
        return new Course.CourseBuilder()
                .withTitle(COURSE_TITLE)
                .withViewUrl(COURSE_VIEW_URL)
                .withLanguage(COURSE_LANGUAGE)
                .build();
    }

    private File getFile(String testFile) throws URISyntaxException {
        return new File(getClass().getResource(testFile).getPath());
    }

    private LearningContent newLearningContent() {
        return new LearningContent.LearningContentBuilder()
                .withTitle(RAWTEXT_LEARNING_CONTENT_TITLE)
                .withRawText(RAWTEXT_LEARNING_CONTENT_TEXT)
                .withType(LearningContent.Type.DIRECT_RAWTEXT)
                .build();
    }
}

package de.saschafeldmann.adesso.master.thesis.elearningimport;

import static com.google.common.base.Preconditions.*;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Course;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Language;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.elearningimport.parser.ParserException;
import de.saschafeldmann.adesso.master.thesis.elearningimport.parser.RawtextParserAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;

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
 * The default implementation of the {@link ImporterService} facade.
 */
@Component
@Scope("singleton")
public class ImporterServiceImpl implements ImporterService {
    private final RawtextParserAdapter rawtextParserAdapter;

    @Autowired
    public ImporterServiceImpl(final RawtextParserAdapter rawtextParserAdapter) {
        this.rawtextParserAdapter = rawtextParserAdapter;
    }

    @Override
    public Course buildNewCourseInstance(final String courseTitle, final String courseViewUrl, final Language courseMainLanguage) {
        checkNotNull(courseTitle, "The course title must not be null.");
        checkNotNull(courseViewUrl, "The course view URL must not be null.");
        checkNotNull(courseMainLanguage, "The course main language must not be null.");

        return new Course.CourseBuilder()
                .withTitle(courseTitle)
                .withViewUrl(courseViewUrl)
                .withLanguage(courseMainLanguage)
                .build();
    }

    @Override
    public void addOrReplaceLearningContentByRawtext(final Course course, final String contentTitle, final String contentRawText) {
        checkNotNull(course, "The course must not be null.");
        checkNotNull(contentTitle, "The contentTitle must not be null.");
        checkNotNull(contentRawText, "The contentRawText must not be null.");

        addOrReplaceLearningContentToCourse(course, contentTitle, contentRawText, LearningContent.Type.DIRECT_RAWTEXT);
    }

    @Override
    public void addOrReplaceLearningContentByFile(final Course course, final File file) throws ParserException {
        checkNotNull(course, "The course must not be null.");
        checkNotNull(file, "The file must not be null.");

        final String rawText = parseFile(file);
        final String learningContentTitle = file.getName();

        addOrReplaceLearningContentToCourse(course, learningContentTitle, rawText, LearningContent.Type.FILE);
    }

    private void addOrReplaceLearningContentToCourse(Course course, String contentTitle, String contentRawText, LearningContent.Type type) {
        LearningContent learningContent = new LearningContent.LearningContentBuilder()
                .withTitle(contentTitle)
                .withRawText(contentRawText)
                .withType(type)
                .build();

        course.addOrReplaceLearningContent(learningContent);
    }

    private String parseFile(final File file) throws ParserException {
        return rawtextParserAdapter.extractRawtext(file);
    }

    @Override
    public void removeLearningContent(final Course course, final LearningContent learningContent) {
        checkNotNull(course, "The course must not be null.");
        checkNotNull(learningContent, "The learningContent must not be null.");

        course.removeLearningContent(learningContent);
    }

    @Override
    public void addOrReplaceLearningContentWithType(Course course, String contentTitle, String contentRawText, LearningContent.Type type) {
        checkNotNull(course, "The course must not be null.");
        checkNotNull(contentTitle, "The contentTitle must not be null.");
        checkNotNull(contentRawText, "The contentRawText must not be null.");
        checkNotNull(type, "The type must not be null.");

        addOrReplaceLearningContentToCourse(course, contentTitle, contentRawText, type);
    }
}

package de.saschafeldmann.adesso.master.thesis.elearningimport;

import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Course;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Language;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.elearningimport.parser.ParserException;

import java.io.File;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  28.05.2016
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
 * Service interface for the import module which works as a facade.
 */
public interface ImporterService {

    /**
     * Builds a new course instance and returns it.
     * @param courseTitle the course's title
     * @param courseViewUrl the URL to view the course
     * @param courseMainLanguage the main / primary language of the course
     * @return the new course instance
     */
    Course buildNewCourseInstance(final String courseTitle, final String courseViewUrl, final Language courseMainLanguage);

    /**
     * Adds or replaces - if a learning content with the given title already belongs to the course -  learning content to the given course.
     *
     * @param course the course that the learning content should be added to
     * @param contentTitle the unique content title. If content with this title was already attached to the course, it will be replaced.
     * @param contentRawText the raw text
     */
    void addOrReplaceLearningContentByRawtext(final Course course, final String contentTitle, final String contentRawText);

    /**
     * Adds or replaces - if a learning content with the given files name already belongs to the course -  learning content to the given course.
     *
     * @param course the course that the learning content should be added to
     * @param file the file containing learning content. The rawtexts will be extracted and automatically be added as rawtext.
     * @throws ParserException if the raw text could not be extracted from the file due to several causes
     */
    void addOrReplaceLearningContentByFile(final Course course, final File file) throws ParserException;

    /**
     * Removes the given learningContent from the given course.
     * @param course the course that the learning content will be removed from
     * @param learningContent the learning content that should get removed
     */
    void removeLearningContent(final Course course, final LearningContent learningContent);

    /**
     * Adds or replaces - if a learning content with the given title already belongs to the course -  learning content to the given course.
     *
     * @param course the course that the learning content should be added to
     * @param contentTitle the unique content title. If content with this title was already attached to the course, it will be replaced.
     * @param contentRawText the raw text
     * @param type the {@link de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent.Type}
     */
    void addOrReplaceLearningContentWithType(Course course, String contentTitle, String contentRawText, LearningContent.Type type);
}

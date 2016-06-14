package de.saschafeldmann.adesso.master.thesis.portlet.view.course.contents;

import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.portlet.view.ViewWithMenu;

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
 * The course contents view. The user can upload course documents and enter raw texts here.
 * It will attach {@link de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent} instances to the {@link de.saschafeldmann.adesso.master.thesis.elearningimport.model.Course} model in the session.
 */
public interface CourseContentsView extends ViewWithMenu {

    /**
     * Displays the learning content files in the session.
     * @param learningContentFiles the list of learning content files attached to the course in the session {@link de.saschafeldmann.adesso.master.thesis.elearningimport.model.Course}
     */
    void showContentFiles(List<LearningContent> learningContentFiles);

    /**
     * Displays the learning raw texts in the session.
     * @param learningRawTexts the list of learning raw texts attached to the course in the session
     */
    void showContentRawTexts(List<LearningContent> learningRawTexts);

    /**
     * Sets the current course title.
     * @param courseTitle the current course title
     */
    void setCourseTitle(final String courseTitle);

    /**
     * Resets / initializes the view layout.
     */
    void reset();

    /**
     * Sets the view listener which is notified on user actions.
     * @param courseContentsViewListener the view listener
     */
    void setViewListener(CourseContentsViewListener courseContentsViewListener);

    /**
     * Displays the current file upload information.
     * @param information String
     */
    void displayFileUploadInformation(String information);

    /**
     * Displays the current file upload error.
     * @param error String
     */
    void displayFileUploadError(String error);
}

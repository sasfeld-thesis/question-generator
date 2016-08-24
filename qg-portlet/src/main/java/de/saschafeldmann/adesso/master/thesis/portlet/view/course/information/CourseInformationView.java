package de.saschafeldmann.adesso.master.thesis.portlet.view.course.information;

import com.vaadin.ui.Component;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.course.information.CourseInformationPresenter;
import de.saschafeldmann.adesso.master.thesis.portlet.view.ViewWithMenu;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  23.05.2016
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
 * The course information view interface.
 * The view is managed by the {@link CourseInformationPresenter}.
 */
public interface CourseInformationView extends ViewWithMenu {

    /**
     * Initialize or reset the whole view.
     * Make sure to clear the current state.
     */
    void reset();

    /**
     * Gets the root component of this view (the view itself).
     * @return
     */
    Component getRootComponent();

    /**
     * Sets the view listener for this view.
     * @param viewListener the view listener instance.
     */
    void setViewListener(CourseInformationViewListener viewListener);

    /**
     * Sets the view mode of this view.
     * @param viewMode see {@link CourseInformationViewImpl.ViewMode}
     */
    void setViewMode(CourseInformationViewImpl.ViewMode viewMode);

    /**
     * Get the course title from the user's input.
     * @return String
     */
    String getInputTitle();

    /**
     * Get the view url from the user's input.
     * @return String
     */
    String getInputViewUrl();

    /**
     * Get the input language from the user's input.
     * @return String
     */
    String getInputLanguage();

    /**
     * Sets the course title - only relevant in the edit mode.
     * @param courseTitle String
     */
    void setCourseTitle(final String courseTitle);
}

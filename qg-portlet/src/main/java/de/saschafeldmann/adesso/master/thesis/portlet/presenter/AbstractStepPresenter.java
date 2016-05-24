package de.saschafeldmann.adesso.master.thesis.portlet.presenter;

import com.vaadin.navigator.Navigator;
import de.saschafeldmann.adesso.master.thesis.portlet.model.QuestionGenerationSession;
import de.saschafeldmann.adesso.master.thesis.portlet.view.MenuListener;
import de.saschafeldmann.adesso.master.thesis.portlet.view.course.contents.CourseContentsViewImpl;
import de.saschafeldmann.adesso.master.thesis.portlet.view.course.information.CourseInformationViewImpl;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  23.05.2016
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
 * This abstract presenter defines the basic components that each view in the application has, e.g. the current step indicator etc.
 * It is a {@link de.saschafeldmann.adesso.master.thesis.portlet.view.MenuListener} and therefore manages menu clicks.
 */
public class AbstractStepPresenter implements MenuListener, VaadinViewPresenter {
    private Navigator navigator;
    protected QuestionGenerationSession questionGenerationSession;

    /**
     * @see MenuListener#onCourseInformationClicked()
     */
    public void onCourseInformationClicked() {
        getNavigator().navigateTo(CourseInformationViewImpl.VIEW_NAME);
    }

    /**
     * @see MenuListener#onContentsClicked()
     */
    public void onContentsClicked() {
        getNavigator().navigateTo(CourseContentsViewImpl.VIEW_NAME);
    }

    /**
     * @see MenuListener#onPreprocessesClicked()
     */
    public void onPreprocessesClicked() {

    }

    /**
     * @see MenuListener#onDetectionClicked()
     */
    public void onDetectionClicked() {

    }

    /**
     * @see MenuListener#onQuestionGenerationClicked() ()
     */
    public void onQuestionGenerationClicked() {

    }

    /**
     * @see VaadinViewPresenter#setNavigator(Navigator)
     */
    public void setNavigator(Navigator navigator) {
        this.navigator = navigator;
    }

    /**
     * @see VaadinViewPresenter#getNavigator()
     */
    public Navigator getNavigator() {
        return this.navigator;
    }

    /**
     * @see VaadinViewPresenter#setQuestionGenerationSession(QuestionGenerationSession)
     */
    public void setQuestionGenerationSession(QuestionGenerationSession questionGenerationSession) {
        this.questionGenerationSession = questionGenerationSession;
    }
}

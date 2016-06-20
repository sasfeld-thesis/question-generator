package de.saschafeldmann.adesso.master.thesis.portlet.presenter;

import com.vaadin.navigator.Navigator;
import de.saschafeldmann.adesso.master.thesis.portlet.model.QuestionGenerationSession;
import de.saschafeldmann.adesso.master.thesis.portlet.view.ViewWithMenu;

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
 * Common interface for all vaadin view presenters.
 */
public interface VaadinViewPresenter {
    /**
     * Sets the vaadin view navigator.
     * @param navigator the vaadin view navigator
     */
    void setNavigator(Navigator navigator);

    /**
     * Gets the vaadin view navigator.
     * @return the navigator
     */
    Navigator getNavigator();

    /**
     * Sets the question generation session.
     * Should be done after the session was started.
     * @param questionGenerationSession the {@link QuestionGenerationSession}
     */
    void setQuestionGenerationSession(QuestionGenerationSession questionGenerationSession);

    /**
     * Gets the view.
     * @return the view
     */
    ViewWithMenu getView();
}

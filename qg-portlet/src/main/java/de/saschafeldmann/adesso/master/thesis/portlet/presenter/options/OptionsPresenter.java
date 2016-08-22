package de.saschafeldmann.adesso.master.thesis.portlet.presenter.options;

import de.saschafeldmann.adesso.master.thesis.portlet.model.QuestionGenerationSession;
import de.saschafeldmann.adesso.master.thesis.portlet.view.options.OptionsView;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  01.08.2016
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
 * Triggers and shows an option dialog where the user can do settings.
 */
public interface OptionsPresenter {

    /**
     * Displays the dialog.
     */
    void display();

    /**
     * Sets the question generation session.
     * @param questionGenerationSession
     */
    void setQuestionGenerationSession(QuestionGenerationSession questionGenerationSession);

    /**
     * Gets the options view.
     * @return view
     */
    OptionsView getView();
}

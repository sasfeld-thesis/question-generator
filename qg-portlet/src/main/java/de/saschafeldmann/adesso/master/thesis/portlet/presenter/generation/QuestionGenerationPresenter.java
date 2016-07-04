package de.saschafeldmann.adesso.master.thesis.portlet.presenter.generation;

import de.saschafeldmann.adesso.master.thesis.portlet.presenter.VaadinViewPresenter;
import de.saschafeldmann.adesso.master.thesis.portlet.view.generation.QuestionGenerationView;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  04.07.2016
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
 * Interface for the last step - the question generation presenter.
 */
public interface QuestionGenerationPresenter extends VaadinViewPresenter {

    /**
     * Initializes the view.
     * @return the view
     */
    QuestionGenerationView initializeView();
}

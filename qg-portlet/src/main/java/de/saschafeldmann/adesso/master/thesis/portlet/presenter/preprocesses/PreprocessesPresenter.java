package de.saschafeldmann.adesso.master.thesis.portlet.presenter.preprocesses;

import de.saschafeldmann.adesso.master.thesis.portlet.presenter.VaadinViewPresenter;
import de.saschafeldmann.adesso.master.thesis.portlet.view.preprocesses.PreprocessesView;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  04.06.2016
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
 * Interface for a preprocesses presenter which handles the preprocessing (syntactical and semantical annotation) logic.
 */
public interface PreprocessesPresenter extends VaadinViewPresenter {
    /**
     * Initializes the view and returns it.
     * @return PreprocessesView
     */
    PreprocessesView initializeView();
}

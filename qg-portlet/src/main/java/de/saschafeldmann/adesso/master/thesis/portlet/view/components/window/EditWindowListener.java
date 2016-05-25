package de.saschafeldmann.adesso.master.thesis.portlet.view.components.window;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  25.05.2016
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
 * Interface for listeners of an {@link EditWindow}
 */
public interface EditWindowListener {

    /**
     * Triggered if the user clicked the edit button.
     * @param textareInput the user's input
     */
    void onEditButtonClicked(final String textareInput);

    /**
     * Triggered if the user clicked the delete button.
     */
    void onDeleteButtonClicked();
}

package de.saschafeldmann.adesso.master.thesis.portlet.view.options;

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
 * Interface for listeners of {@link OptionsView}
 */
public interface OptionsViewListener {
    /**
     * Triggered after the dialog was closed.
     */
    void onClosed();

    /**
     * Triggered after the user clicked the edit button.
     */
    void onEditButtonClicked();

    /**
     * Triggered after the user clicked the reset button.
     */
    void onResetButtonClicked();
}

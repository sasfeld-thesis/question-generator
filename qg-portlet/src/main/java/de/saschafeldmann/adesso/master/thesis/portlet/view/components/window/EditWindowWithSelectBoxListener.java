package de.saschafeldmann.adesso.master.thesis.portlet.view.components.window;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  14.06.2016
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
 * Interface for listeners of an {@link EditWindowWithSelectBox}
 */
public interface EditWindowWithSelectBoxListener extends  EditWindowListener{

    /**
     * Triggered if another item in the selectbox was set.
     * @param itemChanged the item that was changed
     */
    void onSelectBoxItemChanged(Object itemChanged);

    /**
     * Triggered if the user clicked the edit button.
     * @param textareInput the user's input
     * @param secondTextAreaInput the second textarea input
     */
    void onEditButtonClicked(final String textareInput, final String secondTextAreaInput);
}

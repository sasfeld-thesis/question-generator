package de.saschafeldmann.adesso.master.thesis.portlet.model.preprocesses;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  17.06.2016
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
 * Interface for listeners of state changes of {@link ProcessActivationElement}, for example if the user changed the value of a process activation option group.
 */
public interface ProcessActivationStateChangeListener {

    /**
     * On state changed event. Fired if the {@link de.saschafeldmann.adesso.master.thesis.portlet.model.preprocesses.ProcessActivationElement.ProcessActivationElementState}
     * of the element has changed.
     */
    void onStateChanged(ProcessActivationElement changed);
}

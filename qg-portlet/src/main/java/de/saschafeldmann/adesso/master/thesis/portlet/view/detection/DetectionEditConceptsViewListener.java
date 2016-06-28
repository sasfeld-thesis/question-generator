package de.saschafeldmann.adesso.master.thesis.portlet.view.detection;

import de.saschafeldmann.adesso.master.thesis.detection.algorithm.model.api.Concept;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  28.06.2016
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
 * Interface for listeners of a {@link DetectionEditConceptsView}.
 */
public interface DetectionEditConceptsViewListener {
    /**
     * Triggered if the user clicked the edit button to edit a concept.
     * @param conceptToBeEdited the previously detected {@link Concept} to be edited
     */
    void onEditButtonClicked(final Concept conceptToBeEdited);
}

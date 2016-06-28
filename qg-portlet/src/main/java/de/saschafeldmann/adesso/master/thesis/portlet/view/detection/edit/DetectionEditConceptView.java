package de.saschafeldmann.adesso.master.thesis.portlet.view.detection.edit;

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
 * Interface for edit views for all concepts, regardless to their concrete implementation.
 */
public interface DetectionEditConceptView<ConceptType extends Concept> {
    /**
     * Displays the edit view for the given concept.
     * @param concept the concept to be edited
     */
    void displayForConcept(final ConceptType concept);

    /**
     * Closes the view / popup window.
     */
    void close();
}

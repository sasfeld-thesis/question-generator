package de.saschafeldmann.adesso.master.thesis.portlet.view.detection.edit;

import de.saschafeldmann.adesso.master.thesis.detection.model.api.Concept;

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
 * Interface for all listeners of {@link DetectionEditConceptView} instances.
 */
public interface DetectionEditConceptViewListener<ConceptType extends Concept>  {
    /**
     * Edits the given concept.
     * @param concept the given concept
     */
    void onEditButtonClicked(final ConceptType concept);

    /**
     * Deletes the given concept.
     * @param concept the given concept
     */
    void onDeleteButtonClicked(final ConceptType concept);

    /**
     * On window closed event.
     * @param concept the concept that this edit window represents
     */
    void onWindowClosed(Concept concept);
}

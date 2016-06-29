package de.saschafeldmann.adesso.master.thesis.portlet.view.detection.edit;

import de.saschafeldmann.adesso.master.thesis.detection.model.CardinalRelationConcept;

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
 * Special view that allows editing {@link CardinalRelationConcept} instances.
 */
public interface DetectionEditCardinalRelationConceptView extends DetectionEditConceptView<CardinalRelationConcept> {
    /**
     * Gets the composite edited by the user.
     * @return String
     */
    public String getCompositeUserInput();

    /**
     * Gets the composite cardinality edited by the user.
     * @return int
     */
    public int getCompositeCardinalityUserInput();

    /**
     * Gets the composition edited by the user.
     * @return String
     */
    public String getCompositionUserInput();

    /**
     * Gets the composition cardinality edited by the user.
     * @return int
     */
    public int getCompositionCardinalityUserInput();
}

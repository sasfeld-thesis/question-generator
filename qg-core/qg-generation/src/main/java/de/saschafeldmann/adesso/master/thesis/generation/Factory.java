package de.saschafeldmann.adesso.master.thesis.generation;

import de.saschafeldmann.adesso.master.thesis.detection.model.CardinalRelationConcept;
import de.saschafeldmann.adesso.master.thesis.detection.model.FillTextConcept;
import de.saschafeldmann.adesso.master.thesis.generation.specifications.CardinalRelationConceptTestQuestionSpec;
import de.saschafeldmann.adesso.master.thesis.generation.specifications.FillTextConceptTestQuestionSpec;

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
 * Class with static factory methods.
 */
public class Factory {

    /**
     * Creates a new {@link FillTextConceptTestQuestionSpec}
     * @param fillTextConcept the underlying concept
     * @return the {@link FillTextConceptTestQuestionSpec}
     */
    public static FillTextConceptTestQuestionSpec newFillTextConceptTestQuestionSpec(final FillTextConcept fillTextConcept) {
        return new FillTextConceptTestQuestionSpec(fillTextConcept);
    }

    /**
     * Creates a new {@link CardinalRelationConceptTestQuestionSpec} .
     * @param cardinalRelationConcept the underlinyg concept
     * @return the {@link CardinalRelationConceptTestQuestionSpec}
     */
    public static CardinalRelationConceptTestQuestionSpec newCardinalRelationConceptTestQuestionSpec(final CardinalRelationConcept cardinalRelationConcept) {
        return new CardinalRelationConceptTestQuestionSpec(cardinalRelationConcept);
    }

}

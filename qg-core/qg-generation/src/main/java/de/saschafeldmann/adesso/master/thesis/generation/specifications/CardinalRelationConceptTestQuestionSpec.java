package de.saschafeldmann.adesso.master.thesis.generation.specifications;

import de.saschafeldmann.adesso.master.thesis.detection.model.CardinalRelationConcept;
import de.saschafeldmann.adesso.master.thesis.detection.model.api.Concept;

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
 * Implementation of a {@link TestQuestionSpecification} based upon a {@link de.saschafeldmann.adesso.master.thesis.detection.model.CardinalRelationConcept}
 * detected upon a {@link de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent}.
 */
public class CardinalRelationConceptTestQuestionSpec implements TestQuestionSpecification<CardinalRelationConcept> {
    private final CardinalRelationConcept concept;

    /**
     * Creates the specification to create the test question based on the {@link de.saschafeldmann.adesso.master.thesis.detection.model.CardinalRelationConcept}.
     * @param concept the based concept detected within qg-detection module
     */
    public CardinalRelationConceptTestQuestionSpec(final CardinalRelationConcept concept) {
        this.concept = concept;
    }

    @Override
    public CardinalRelationConcept getUnderlyingConcept() {
        return concept;
    }

    @Override
    public String buildSpec() {
        return "";
    }
}

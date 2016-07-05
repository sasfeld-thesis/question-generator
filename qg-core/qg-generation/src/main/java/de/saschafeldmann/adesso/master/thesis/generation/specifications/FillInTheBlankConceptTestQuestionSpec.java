package de.saschafeldmann.adesso.master.thesis.generation.specifications;

import de.saschafeldmann.adesso.master.thesis.detection.model.FillInTheBlankTextConcept;
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
 * Implementation of a {@link TestQuestionSpecification} for {@link FillInTheBlankTextConcept} detected on
 * a {@link de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent}.
 */
public class FillInTheBlankConceptTestQuestionSpec implements TestQuestionSpecification {
    private final Concept concept;

    /**
     * Creates the specification based upon the given concept.
     * @param concept the concept detected within qg-detection module
     */
    public FillInTheBlankConceptTestQuestionSpec(final Concept concept) {
        this.concept = concept;
    }

    @Override
    public Concept getUnderlyingConcept() {
        return concept;
    }

    @Override
    public void buildSpec() {
        // TODO use SimpleNLG to build the spec for filltexts
    }
}

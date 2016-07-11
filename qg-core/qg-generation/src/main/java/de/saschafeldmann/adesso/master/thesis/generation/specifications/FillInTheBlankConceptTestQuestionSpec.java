package de.saschafeldmann.adesso.master.thesis.generation.specifications;

import de.saschafeldmann.adesso.master.thesis.detection.model.FillInTheBlankTextConcept;

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
public class FillInTheBlankConceptTestQuestionSpec implements TestQuestionSpecification<FillInTheBlankTextConcept> {
    private final FillInTheBlankTextConcept concept;

    /**
     * Creates the specification based upon the given concept.
     * @param concept the concept detected within qg-detection module
     */
    public FillInTheBlankConceptTestQuestionSpec(final FillInTheBlankTextConcept concept) {
        this.concept = concept;
    }

    @Override
    public FillInTheBlankTextConcept getUnderlyingConcept() {
        return concept;
    }

    @Override
    public String buildSpec() {
        return chooseRandomFillTextQuestionIntroductionFromProperties() + getUnderlyingConcept().getFillSentence();
    }

    private String chooseRandomFillTextQuestionIntroductionFromProperties() {
        return "Wie lautet der Satz richtig? ";
    }
}

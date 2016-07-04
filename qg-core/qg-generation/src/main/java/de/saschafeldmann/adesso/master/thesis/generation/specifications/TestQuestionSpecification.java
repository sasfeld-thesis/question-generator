package de.saschafeldmann.adesso.master.thesis.generation.specifications;

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
 * Following the natural language generation approach inntroduced by [Reiter], this is the interface for the second
 * step within the NLG pipeline - the microplanning which produces a text specification. <br />
 * In my case, the text specification specifies how to exactly create the final test question linguistically for the related Concept.<br />
 * This means, each concept has its own implementation of {@link TestQuestionSpecification}.
 * See: Reiter, E., Dale, R., & Feng, Z. (2000). Building natural language generation systems (Vol. 33). Cambridge: Cambridge university press.
 */
public interface TestQuestionSpecification {
    /**
     * Gets the underlying concepts for this test question specification.
     * @return the underlying concept which was detected for a given {@link de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent}.
     */
    Concept getUnderlyingConcept();

    /**
     * Builds the specification for the concept returned by getUnderlyingConcept().
     */
    void buildSpec();
}

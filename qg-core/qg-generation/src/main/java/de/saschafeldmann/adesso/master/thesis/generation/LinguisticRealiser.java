package de.saschafeldmann.adesso.master.thesis.generation;

import de.saschafeldmann.adesso.master.thesis.detection.model.api.Concept;
import de.saschafeldmann.adesso.master.thesis.generation.model.TestQuestion;

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
 * Interface for the linguistic realiser which finally triggers the natural language generation task to produce
 * a well-formed question on either English or German (depending on the {@link de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent}). <br />
 * See: Reiter, E., Dale, R., & Feng, Z. (2000). Building natural language generation systems (Vol. 33). Cambridge: Cambridge university press.
 */
public interface LinguisticRealiser {

    /**
     * Generate the question upon the given concept.
     * @param concept the concept
     * @return the generated question
     * @throws QuestionGenerationException on exceptions during question generation
     */
    public TestQuestion generateQuestion(final Concept concept);
}

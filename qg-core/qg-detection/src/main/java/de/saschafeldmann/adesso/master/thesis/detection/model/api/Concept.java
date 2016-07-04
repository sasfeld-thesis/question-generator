package de.saschafeldmann.adesso.master.thesis.detection.model.api;

import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.generation.specifications.TestQuestionSpecification;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  27.06.2016
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
 * Interface for all detection concepts.<br />
 * A concept reveals special semantics that was extracted based upon a a special sequence of named entities and / or part of speech tags
 * within a given {@link LearningContent} or based on a meaning that was obtained from external ontologies.<br />
 * Following [Reiter] in his proposal of a natural language generation system, a concept is the result of the document planner which
 * did the tasks of the content determination ("what information should be communicated?").<br />
 * See: Reiter, E., Dale, R., & Feng, Z. (2000). Building natural language generation systems (Vol. 33). Cambridge: Cambridge university press.
 */
public interface Concept {

    /**
     * Gets the text specification (step 2 in the natural language generation pipeline) from the qg-generation module to build natural language sentences for this concept (step 1 in the NLG pipeline).
     */
    TestQuestionSpecification getTestQuestionTextSpecification();

    /**
     * Gets the learning content for which this concept was detected.
     * @return the learning content
     */
    LearningContent getLearningContent();

    /**
     * Gets the original sentence that was the basis for this concept.
     * @return the original sentence that this concept is based on.
     */
    String getOriginalSentence();
}

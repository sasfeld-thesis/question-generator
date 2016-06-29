package de.saschafeldmann.adesso.master.thesis.detection.model.api;

import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;

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
 * A concept is a special sequence of named entities and / or part of speech tags
 * representing a concept or a special semantics.
 */
public interface Concept {

    /**
     * Gets the document plan from the qg-generation module to build natural language sentences for this concept.
     */
    void getDocumentPlan();

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

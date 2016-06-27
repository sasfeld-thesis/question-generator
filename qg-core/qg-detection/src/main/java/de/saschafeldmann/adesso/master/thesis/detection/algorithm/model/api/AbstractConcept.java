package de.saschafeldmann.adesso.master.thesis.detection.algorithm.model.api;

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
 * [short description]
 */
public abstract class AbstractConcept implements Concept {
    private final LearningContent learningContent;
    private final String originalSentence;

    public AbstractConcept(final LearningContent learningContent, final String originalSentence) {
        this.learningContent = learningContent;
        this.originalSentence = originalSentence;
    }

    @Override
    public LearningContent getLearningContent() {
        return learningContent;
    }

    @Override
    public String getOriginalSentence() {
        return this.originalSentence;
    }
}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AbstractConcept that = (AbstractConcept) o;

        if (learningContent != null ? !learningContent.equals(that.learningContent) : that.learningContent != null)
            return false;
        return originalSentence != null ? originalSentence.equals(that.originalSentence) : that.originalSentence == null;

    }

    @Override
    public int hashCode() {
        int result = learningContent != null ? learningContent.hashCode() : 0;
        result = 31 * result + (originalSentence != null ? originalSentence.hashCode() : 0);
        return result;
    }
}

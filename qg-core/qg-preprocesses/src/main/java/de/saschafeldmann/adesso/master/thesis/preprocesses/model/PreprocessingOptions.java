package de.saschafeldmann.adesso.master.thesis.preprocesses.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  20.06.2016
 * Author:         Sascha Feldmann (sascha.feldmann@gmx.de)
 * <br><br>
 * University:
 * Hochschule für Technik und Wirtschaft, Berlin
 * Fachbereich 4
 * Studiengang Internationale Medieninformatik (Master)
 * <br><br>
 * Company:
 * adesso AG
 * <br><br>
 * Options plain old java object that is given to an algorithm ({@link de.saschafeldmann.adesso.master.thesis.preprocesses.algorithm.PreprocessingAlgorithm}
 * in its execute() method to be thread-safe and support singleton-scoped algorithms.
 */
@Component
@Scope("prototype")
public class PreprocessingOptions {
    private Boolean activatePartOfSpeechTagging = false;
    private Boolean activateNamedEntityRecognition = false;

    /**
     * Whether named entity recognition should be done.
     * @param activateNamedEntityRecognition boolean
     */
    public void setActivateNamedEntityRecognition(boolean activateNamedEntityRecognition) {
        this.activateNamedEntityRecognition = activateNamedEntityRecognition;
    }

    /**
     * Whether named entity recognition should be done.
     * @return boolean
     */
    public Boolean getActivateNamedEntityRecognition() {
        return activateNamedEntityRecognition;
    }

    /**
     * Whether part of speech tagging should be done.
     * @param activatePartOfSpeechTagging boolean
     */
    public void setActivatePartOfSpeechTagging(boolean activatePartOfSpeechTagging) {
        this.activatePartOfSpeechTagging = activatePartOfSpeechTagging;
    }

    /**
     * Whether part of speech tagging should be done.
     * @return boolean
     */
    public Boolean getActivatePartOfSpeechTagging() {
        return activatePartOfSpeechTagging;
    }
}

package de.saschafeldmann.adesso.master.thesis.detection.algorithm;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  29.06.2016
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
 * Options to be handed to {@link DetectionAlgorithm} implementations in their execute() method to ensure thread-safetyness.
 */
@Component
@Scope("prototype")
public class DetectionOptions {
    /**
     * Option value for unlimited.
     */
    public static final int UNLIMITED = Integer.MAX_VALUE;

    private int numberOfFilltextQuestions;
    private int numberOfCardinalityQuestions;

    /**
     * Sets the number of filltext questions to be generated.
     * @param numberOfFilltextQuestions the number
     */
    public void setNumberOfFilltextQuestions(int numberOfFilltextQuestions) {
        this.numberOfFilltextQuestions = numberOfFilltextQuestions;
    }

    /**
     *
     * @return the number of filltext questions
     */
    public int getNumberOfFilltextQuestions() {
        return numberOfFilltextQuestions;
    }

    /**
     * Sets the number of cardinality questions to be generated.
     * @param numberOfCardinalityQuestions the number
     */
    public void setNumberOfCardinalityQuestions(int numberOfCardinalityQuestions) {
        this.numberOfCardinalityQuestions = numberOfCardinalityQuestions;
    }

    /**
     * Returns the number of cardinality questions to be generated.
     * @return the number
     */
    public int getNumberOfCardinalityQuestions() {
        return numberOfCardinalityQuestions;
    }
}

package de.saschafeldmann.adesso.master.thesis.preprocesses.algorithm;

import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.preprocesses.model.PreprocessingOptions;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  14.06.2016
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
 * Service interface for all preprocessing algorithms. <br />
 * Each algorithm simply takes the raw text of a given learning content, processes it and writes information back to the mutable
 * fields of the learning content object ({@link de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent} .
 */
public interface PreprocessingAlgorithm {

    /**
     * Execute the algorith. Take the raw text stored in the given learningContent as input.
     * Write back the outputs to the learning content returned.
     * @param learningContent raw learning content
     * @param preprocessingOptions options instance for the algorithm to be thread-safe and support singleton-scoped algorithms.
     * @return enriched learning content
     */
    LearningContent execute(LearningContent learningContent, PreprocessingOptions preprocessingOptions);
}

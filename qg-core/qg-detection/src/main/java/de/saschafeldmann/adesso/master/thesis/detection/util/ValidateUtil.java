package de.saschafeldmann.adesso.master.thesis.detection.util;

import de.saschafeldmann.adesso.master.thesis.detection.algorithm.DetectionOptions;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;

import static com.google.common.base.Preconditions.checkNotNull;

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
 * Utility to validate inputs of concept detection.
 */
public class ValidateUtil {

    /**
     * Validates the given learning content. Throws a null pointer exception on invalid values.
     * @param learningContent the given learning content that should be validated before handing in to a {@link de.saschafeldmann.adesso.master.thesis.detection.algorithm.DetectionAlgorithm}
     */
    public static void validate(final LearningContent learningContent, final DetectionOptions detectionOptions) {
        checkNotNull(learningContent, "Learning content must not be null");
        checkNotNull(detectionOptions, "detection options must not be null");
        checkNotNull(learningContent.getPartOfSpeechAnnotatedText(), "learningContent#getPartOfSpeechAnnotatedText() must not be null");
        checkNotNull(learningContent.getNamedEntityAnnotatedText(), "learningContent#getNamedEntityAnnotatedText() must not be null");
        checkNotNull(learningContent.getDeterminedLanguage(), "learningContent#getDeterminedLanguage() must not be null");
    }
}

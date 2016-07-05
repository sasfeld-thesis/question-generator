package de.saschafeldmann.adesso.master.thesis.detection.algorithm.fillintheblank;

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
 * [short description]
 */
public class FillInTheBlankConceptDetectionException extends RuntimeException {
    /**
     * Creates a new filltext concept detection exception.
     * @param cause the cause
     */
    public FillInTheBlankConceptDetectionException(Throwable cause) {
        super(cause);
    }
}

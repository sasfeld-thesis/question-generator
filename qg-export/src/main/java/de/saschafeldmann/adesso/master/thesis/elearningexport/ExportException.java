package de.saschafeldmann.adesso.master.thesis.elearningexport;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  10.08.2016
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
 * Exception thrown in export module.
 */
public class ExportException extends RuntimeException {

    /**
     * Creates a new export exception.
     * @param message the message
     * @param cause the causing exception
     */
    public ExportException (String message, Throwable cause) {
        super(message, cause);
    }
}

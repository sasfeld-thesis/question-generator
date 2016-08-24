package de.saschafeldmann.adesso.master.thesis.generation;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  11.07.2016
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
 * Exception thrown by {@link LinguisticRealiser}
 */
public class QuestionGenerationException extends RuntimeException {
    /**
     * Creates a new question generation exception.
     * @param message String
     */
    public QuestionGenerationException(final String message) {
        super(message);
    }
}

package de.saschafeldmann.adesso.master.thesis.elearningimport.parser;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  28.05.2016
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
 * Special parser exception.
 */
public class ParserException extends Exception
{
    /**
     * Creates a parser exception that occured due to the given cause.
     * @param cause the causing {@link Throwable}
     */
    public ParserException(Throwable cause) {
        super(cause);
    }
}

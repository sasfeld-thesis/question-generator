package de.saschafeldmann.adesso.master.thesis.preprocesses.algorithm.nlp;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  20.06.2016
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
 * Thrown by {@link NlpPreprocessingAlgorithm}
 */
public class NlpException extends RuntimeException
{
    /**
     * Creates a NLP exception.
     * @param s the message
     */
    public NlpException(String s) {
        super(s);
    }
}

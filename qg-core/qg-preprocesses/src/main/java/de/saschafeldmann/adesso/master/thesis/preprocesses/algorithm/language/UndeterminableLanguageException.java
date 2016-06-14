package de.saschafeldmann.adesso.master.thesis.preprocesses.algorithm.language;

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
 * Thrown by the {@link LanguageDetection} algorithm if the language could not be determined.<br />
 * This might have different causes: it might be that the input language is not supported (not contained in the enumeration
 * {@link de.saschafeldmann.adesso.master.thesis.elearningimport.model.Language}) or is not statistically adequate (consisting
 * of the language's most common words).
 */
public class UndeterminableLanguageException extends RuntimeException {
}

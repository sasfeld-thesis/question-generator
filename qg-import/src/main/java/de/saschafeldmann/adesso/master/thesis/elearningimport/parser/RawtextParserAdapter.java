package de.saschafeldmann.adesso.master.thesis.elearningimport.parser;

import java.io.File;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  28.05.2016
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
 * Implementations adapt to underlying fulltext parser libaries.
 */
public interface RawtextParserAdapter {

    /**
     * Extracts the rawtext from a given {@link java.io.File}
     * @return String the raw text
     * @throws ParserException if the parsing could not be done
     */
    String extractRawtext(final File file) throws ParserException;
}

package de.saschafeldmann.adesso.master.thesis.util.csv;

import java.io.File;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  12.07.2016
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
 * A CSV writer produces a CSV file.<br />
 * It is a simple text file which consists of coluuns, where each column is separated by a delimiter
 * and lines for each row.<br />
 * The default delimiter is the semicolon ';'<br />
 * A UTF-8 encoded CSV file will be generated.
 */
public interface CsvWriter {
    /**
     * Sets the delimiter and overwrites the default one (;).
     * @param delimiter the delimiter
     */
    void setDelimiter(final String delimiter);

    /**
     * Adds a row.
     * @param columns the columns (must be string values).
     */
    void addRow(final String... columns);

    /**
     * Finally writes the CSV contents to a file.
     * @param filename the filename
     * @return the CSV file
     */
    File writeToFile(String filename);
}

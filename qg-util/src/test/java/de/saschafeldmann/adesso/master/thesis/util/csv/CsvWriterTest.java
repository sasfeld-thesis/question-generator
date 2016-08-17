package de.saschafeldmann.adesso.master.thesis.util.csv;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
 * Test of {@link de.saschafeldmann.adesso.master.thesis.util.csv.CsvWriter}
 */
public class CsvWriterTest {

    @Test
    public void testCsvFileGenerationUsesDefaultSemicolonDelimiter() throws IOException {
        // given a CsvWriter
        CsvWriter csvWriter = new CsvWriterImpl();
        // given a column that contains the CSV delimiter
        String[] column = {"Column1", "Column2", "Column3"};

        // when the row is added
        csvWriter.addRow(column);

        // then ensure that the file is correct
        File f = csvWriter.writeToFile(".", "unittest.csv");
        checkFileContains(f, "Column1;Column2;Column3");
    }

    @Test
    public void testAddRowReplacesDelimiterCharactersInColumn() throws IOException {
        // given a CsvWriter
        CsvWriter csvWriter = new CsvWriterImpl();
        // given a column that contains the CSV delimiter
        csvWriter.setDelimiter(";");
        String[] column = {"Column1", "Column2 contains a forbidden character;haha", "Column3"};

        // when the row is added
        csvWriter.addRow(column);

        // then ensure that the file is correct
        File f = csvWriter.writeToFile(".", "unittest.csv");
        checkFileContains(f, "Column1;Column2 contains a forbidden characterhaha;Column3");
    }

    private void checkFileContains(File f, String s) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(f.toURI()));
        String fileContent = new String(bytes, "UTF-8");

        assertTrue("The CSV file does not contain " + s, fileContent.contains(s));
    }
}

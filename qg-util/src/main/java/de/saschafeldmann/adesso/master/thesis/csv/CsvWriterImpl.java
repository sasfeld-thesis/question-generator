package de.saschafeldmann.adesso.master.thesis.csv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
 * Implementation of {@link CsvWriter}
 */
public class CsvWriterImpl implements CsvWriter {
    private static final Logger LOGGER = LoggerFactory.getLogger(CsvWriterImpl.class);
    private String delimiter = resetDelimiter();
    private List<String> rows = resetRows();

    private String resetDelimiter() {
        return ";";
    }

    private ArrayList<String> resetRows() {
        return new ArrayList<>();
    }

    @Override
    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    @Override
    public void addRow(String... columns) {
        rows.add(buildLine(columns));
    }

    private String buildLine(String[] columns) {
        final StringBuilder columnBuilder = new StringBuilder();

        for (String column: columns) {
            final String cleanColumn = column.replaceAll(delimiter, "");

            if (columnBuilder.length() != 0) {
                // prepend the delimiter if not first column
                columnBuilder.append(delimiter);
            }

            columnBuilder.append(cleanColumn);
        }

        return columnBuilder.toString();
    }

    @Override
    public File writeToFile(final String filename) {
        File file = new File(filename);

        FileOutputStream fileOutputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        try {
            fileOutputStream =  new FileOutputStream(file);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream, "UTF-8");

            for (String row: rows) {
                outputStreamWriter.write(row);
                outputStreamWriter.write(System.lineSeparator());
            }

            outputStreamWriter.flush();
        } catch (IOException e) {
            LOGGER.error("writeToFile(): could not write to CSV file cause of: {}", e);
        } finally {
          try {
              if (null != outputStreamWriter) {
                  outputStreamWriter.close();
              }
              if (null != fileOutputStream) {
                  fileOutputStream.close();
              }
          } catch (IOException e) { /*ignore*/ }
        }

        return file;
    }
}


package de.saschafeldmann.adesso.master.thesis.portlet.properties;

import de.saschafeldmann.adesso.master.thesis.util.properties.PropertiesReader;
import de.saschafeldmann.adesso.master.thesis.util.properties.PropertiesReaderApi;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.InputStream;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  25.05.2016
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
 * The adapter to read properties from the file 'questiongenerator.properties'.
 */
@Component
@Scope("singleton")
public class QuestionGeneratorProperties extends PropertiesReader implements PropertiesReaderApi {
    private static final String PROPERTIES_FILE_NAME = "/questiongenerator.properties";

    /**
     * Create a new config reader which reads from a '.property' - file. <br />
     * A critical log entry will be executed if the given file doesn't exist or
     * isn't accessible.
     */
    public QuestionGeneratorProperties() throws Exception {
        super(QuestionGeneratorProperties.class.getResourceAsStream(PROPERTIES_FILE_NAME));
    }
}
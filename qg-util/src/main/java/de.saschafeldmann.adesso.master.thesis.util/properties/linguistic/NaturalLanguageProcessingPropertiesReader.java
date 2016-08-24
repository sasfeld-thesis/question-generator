package de.saschafeldmann.adesso.master.thesis.util.properties.linguistic;

import de.saschafeldmann.adesso.master.thesis.util.properties.PropertiesReader;
import de.saschafeldmann.adesso.master.thesis.util.properties.PropertiesReaderApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  29.06.2016
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
 * Adapter to naturallanguageprocessing.properties file.
 */
public class NaturalLanguageProcessingPropertiesReader extends PropertiesReader implements PropertiesReaderApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(NaturalLanguageProcessingPropertiesReader.class);

    private static final String PROPERTIES_FILE_NAME = "/naturallanguageprocessing.properties";
    private static NaturalLanguageProcessingPropertiesReader instance;

    static {
        try {
            instance = new NaturalLanguageProcessingPropertiesReader();
        } catch (Exception e) {
            LOGGER.error("CRITICAL error: could not initialize required NaturalLanguageProcessingPropertiesReader(), exception:\n{}", e);
        }
    }

    /**
     * Create a new config reader which reads from a '.property' - file. <br>
     * A critical log entry will be executed if the given file doesn't exist or
     * isn't accessible.
     */
    private NaturalLanguageProcessingPropertiesReader() throws Exception {
        super(NaturalLanguageProcessingPropertiesReader.class.getResourceAsStream(PROPERTIES_FILE_NAME));
    }

    /**
     * Gets the instance.
     * @return
     */
    public static NaturalLanguageProcessingPropertiesReader getInstance() {
        return instance;
    }
}

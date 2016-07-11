package de.saschafeldmann.adesso.master.thesis.generation.util;

import de.saschafeldmann.adesso.master.thesis.util.properties.PropertiesReader;
import de.saschafeldmann.adesso.master.thesis.util.properties.PropertiesReaderApi;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  11.07.2016
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
 * Implementation of a properties reader for the generation module.
 */
@Component
@Scope("singleton")
public class GenerationProperties extends PropertiesReader implements PropertiesReaderApi {
    private static final String PROPERTIES_FILE_NAME = "/generation.properties";

    /**
     * Constructs a new properties instance.
     * @throws Exception if the properties file could not be found
     */
    public GenerationProperties() throws Exception {
        super(GenerationProperties.class.getResourceAsStream(PROPERTIES_FILE_NAME));
    }
}

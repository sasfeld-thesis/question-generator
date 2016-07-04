package de.saschafeldmann.adesso.master.thesis.detection.util;

import de.saschafeldmann.adesso.master.thesis.util.properties.PropertiesReader;
import de.saschafeldmann.adesso.master.thesis.util.properties.PropertiesReaderApi;

import java.io.InputStream;
import java.util.List;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  04.07.2016
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
 * Implementation of a properties reader for the detection module.
 */
public class DetectionProperties extends PropertiesReader implements PropertiesReaderApi {
    private static final String PROPERTIES_FILE_NAME = "/detection.properties";

    /**
     * Constructs a new properties instance.
     * @throws Exception if the properties file could not be found
     */
    public DetectionProperties() throws Exception {
        super(DetectionProperties.class.getResourceAsStream(PROPERTIES_FILE_NAME));
    }

    /**
     * Gets the named entity types that are asked for within fill text concept questions.<br />
     * Example original sentence: The capital of Germany is <LOCATION>Berlin</LOCATION>. <br />
     * Berlin as location would be offered for the filltext if location is contained within the properties values.<br />
     * The resulting fill text question would be: "What is the complete sentence? The capital of Germany is ___?".
     *
     * @return a list of named entities
     */
    public List<String> getFillTextFillNamedEntities() {
        return fetchMultipleValues("de.saschafeldmann.adesso.master.thesis.detection.filltext.fill.named.entities");
    }

    /**
     * Gets the part of speech tag typesthat are asked for within fill text concept questions.<br />
     * Example original sentence: The capital of Germany is <NNP>Berlin</NNP>. <br />
     * Berlin as a proper noun singular would be offered for the filltext if location is contained within the properties values.<br />
     * The resulting fill text question would be: "What is the complete sentence? The capital of Germany is ___?".
     *
     * @return a list of named entities
     */
    public List<String> getFillTextFillPartOfSpeechTags() {
        return fetchMultipleValues("de.saschafeldmann.adesso.master.thesis.detection.filltext.fill.part.of.speech.tags");
    }
}

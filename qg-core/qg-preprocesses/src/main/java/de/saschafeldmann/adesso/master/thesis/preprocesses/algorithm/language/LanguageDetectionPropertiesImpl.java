package de.saschafeldmann.adesso.master.thesis.preprocesses.algorithm.language;

import de.saschafeldmann.adesso.master.thesis.util.properties.PropertiesReader;
import de.saschafeldmann.adesso.master.thesis.util.properties.PropertiesReaderApi;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

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
public class LanguageDetectionPropertiesImpl extends PropertiesReader implements PropertiesReaderApi, LanguageDetectionProperties {
    private static final String PROPERTIES_FILE_NAME = "/languagedetection.properties";
    private static final String PROPERTY_KEY_GERMAN_COMMON_WORDS = "de.saschafeldmann.adesso.master.thesis.preprocesses.languagedetection.common.german";
    private static final String PROPERTY_KEY_ENGLISH_COMMON_WORDS = "de.saschafeldmann.adesso.master.thesis.preprocesses.languagedetection.common.english";

    /**
     * Create a new config reader which reads from a '.property' - file. <br />
     * A critical log entry will be executed if the given file doesn't exist or
     * isn't accessible.
     */
    public LanguageDetectionPropertiesImpl() throws Exception {
        super(LanguageDetectionPropertiesImpl.class.getResourceAsStream(PROPERTIES_FILE_NAME));
    }

    @Override
    public List<String> getGermanCommonWords() {
        return fetchMultipleValues(PROPERTY_KEY_GERMAN_COMMON_WORDS);
    }

    @Override
    public List<String> getEnglishCommonWords() {
        return fetchMultipleValues(PROPERTY_KEY_ENGLISH_COMMON_WORDS);
    }

}

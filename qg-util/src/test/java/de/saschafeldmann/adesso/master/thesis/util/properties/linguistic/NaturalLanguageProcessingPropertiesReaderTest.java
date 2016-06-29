package de.saschafeldmann.adesso.master.thesis.util.properties.linguistic;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  29.06.2016
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
 * Test of {@link NaturalLanguageProcessingPropertiesReader}
 */
public class NaturalLanguageProcessingPropertiesReaderTest {

    @Test
    public void testFetchSomeProperty() {
        // given a reader and some existing property
        NaturalLanguageProcessingPropertiesReader reader = NaturalLanguageProcessingPropertiesReader.getInstance();
        String existingPropertyKey = "de.saschafeldmann.adesso.master.thesis.util.named.entity.adapter.location.english";

        // when fetchValue is called
        String resultingValue = reader.fetchValue(existingPropertyKey);

        // then resulting value should be not empty
        assertTrue("The resulting value for the property " + existingPropertyKey + " must not be empty.", resultingValue.trim().length() > 0);
    }
}

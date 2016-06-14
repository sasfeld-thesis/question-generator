package de.saschafeldmann.adesso.master.thesis.util.properties;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.File;
import java.io.InputStream;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  14.06.2016
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
 * Test of the {@link PropertiesReader}.
 */
public class PropertiesReaderTest {
    private static final String TEST_PROPERTIES_FILE = "/test.properties";

    /**
     * Since {@link PropertiesReader} is abstract, define a test implementation that extends it.
     */
    private static class PropertiesReaderTestImplementation extends PropertiesReader {
        public PropertiesReaderTestImplementation(File inputFile) {
            super(inputFile);
        }

        public PropertiesReaderTestImplementation(InputStream inputStream) throws Exception {
            super(inputStream);
        }
    }

    private static PropertiesReaderApi newTestPropertiesReader() throws Exception {
        return new PropertiesReaderTestImplementation(PropertiesReader.class.getResourceAsStream(TEST_PROPERTIES_FILE));
    }

    @Test
    public void testFetchValueReturnsExpectedSingleValue() throws Exception {
        // given a single key property and a properties reader
        final String key = "de.saschafeldmann.adesso.master.thesis.util.test.singlekey";
        PropertiesReaderApi propertiesReader = newTestPropertiesReader();

        // when fetchValue() is called
        final String resultingValue = propertiesReader.fetchValue(key);

        // then the value should be the expected one
        assertEquals("singlevalue", resultingValue);
    }

    @Test
    public void testFetchValueThrowsInvalidArgumentExceptionIfKeyDoesNotExist() throws Exception {
        // given a single key property and a properties reader
        final String key = "itdoesnotexist";
        PropertiesReaderApi propertiesReader = newTestPropertiesReader();

        try {
            // when fetchValue() is called
            propertiesReader.fetchValue(key);

            fail("An illegal argument exception should have been thrown since the properties key should not exist.");
        } catch (IllegalArgumentException e) {
            // then an invalid argument exception should be thrown
        }
    }

    @Test
    public void testFetchMultipleValuesReturnsExpectedMultipleValues() throws Exception {
        // given a multi key property and a properties reader
        final String key = "de.saschafeldmann.adesso.master.thesis.util.test.multi";
        PropertiesReaderApi propertiesReader = newTestPropertiesReader();

        // when fetchMultiple() is called
        final List<String> values = propertiesReader.fetchMultipleValues(key);

        // then the values should be the expected one
        assertEquals(5, values.size());

        assertContains("value1", values);
        assertContains("value2", values);
        assertContains("value3", values);
        assertContains("value4", values);
        assertContains("value5", values);
    }

    private void assertContains(String expectedValue, Collection<String> givenValues) {
        assertTrue(givenValues.toString() + " should contain " + expectedValue, givenValues.contains(expectedValue));
    }

    @Test
    public void testFetchMultipleValuesThrowsInvalidArgumentExceptionIfKeyDoesNotExist() throws Exception {
        // given a single key property and a properties reader
        final String key = "itdoesnotexist";
        PropertiesReaderApi propertiesReader = newTestPropertiesReader();

        try {
            // when fetchMultipleValues() is called
            propertiesReader.fetchMultipleValues(key);

            fail("An illegal argument exception should have been thrown since the properties key should not exist.");
        } catch (IllegalArgumentException e) {
            // then an invalid argument exception should be thrown
        }
    }

    @Test
    public void testFetchValuesReturnsAllValues() throws Exception {
        // given properties reader
        PropertiesReaderApi propertiesReader = newTestPropertiesReader();

        // when fetchValues() is called
        Map<String, String> propertiesMap = propertiesReader.fetchValues();

        // then the value map should have items
        assertContains("value1", propertiesMap.values());
        assertContains("value2", propertiesMap.values());
        assertContains("value3", propertiesMap.values());
        assertContains("value4", propertiesMap.values());
        assertContains("value5", propertiesMap.values());
        assertContains("singlevalue", propertiesMap.values());
    }

    @Test
    public void testGetConfiguredClassReturnsCorrectObject() throws Exception {
        // given a properties read
        PropertiesReaderApi propertiesReader = newTestPropertiesReader();

        // when getConfiguredClass() is called with existing class
        Object configuredObject = propertiesReader.getConfiguredClass("de.saschafeldmann.adesso.master.thesis.util.test.configuredclass");

        assertTrue("received object should be instance of the expected class", (configuredObject instanceof PropertiesReaderTest));
    }
}

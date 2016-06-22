package de.saschafeldmann.adesso.master.thesis.preprocesses.algorithm.language;

import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
 * Unittests of the {@link LanguageDetectionPropertiesImpl} class.
 */
public class LanguageDetectionPropertiesImplTest {

    @Test
    public void testGetEnglishCommonWordsReturnsExpectedValues() throws Exception {
        // given the language detection properties
        LanguageDetectionProperties languageDetectionProperties = newLanguageDetectionProperties();

        // when is called
        List<String> englishCommonWords = languageDetectionProperties.getEnglishCommonWords();

        // then assert expected
        assertContains("you", englishCommonWords);
        assertContains("from", englishCommonWords);
    }

    @Test
    public void testGetGermanCommonWordsReturnsExpectedValues() throws Exception {
        // given the language detection properties
        LanguageDetectionProperties languageDetectionProperties = newLanguageDetectionProperties();

        // when is called
        List<String> germanCommonWords = languageDetectionProperties.getGermanCommonWords();

        // then assert expected
        assertContains("sein", germanCommonWords);
        assertContains("von", germanCommonWords);
    }

    private LanguageDetectionProperties newLanguageDetectionProperties() throws Exception {
        return new LanguageDetectionPropertiesImpl();
    }

    private void assertContains(String expectedValue, Collection<String> givenValues) {
        assertTrue(givenValues.toString() + " should contain " + expectedValue, givenValues.contains(expectedValue));
    }

    @Test
    public void testGetCoverageInPercentIsConfiguredCorrectlyAsInteger() throws Exception {
        // given the language detection properties
        LanguageDetectionProperties languageDetectionProperties = newLanguageDetectionProperties();

        // when is called
        try {
            int delta = languageDetectionProperties.getCoverageDeltaInPercent();
        } catch (NumberFormatException e) {
            // then a number format exception must not be thrown
            fail("getCoverageDeltaInPercent(): did not return an integer since the configured properties is not a valid integer. Please fix in file languagedetection.properties.");
        }
    }

    @Test
    public void testGetCoverageInPercentMustNotBeNegativeOrZero() throws Exception {
        // given the language detection properties
        LanguageDetectionProperties languageDetectionProperties = newLanguageDetectionProperties();

        // when is called
        int delta = languageDetectionProperties.getCoverageDeltaInPercent();

        // then delta must not be negative
        assertTrue("The configured delta must not be negative or zero. Please fil in file languagedetection.properties!", delta > 0);
    }
}

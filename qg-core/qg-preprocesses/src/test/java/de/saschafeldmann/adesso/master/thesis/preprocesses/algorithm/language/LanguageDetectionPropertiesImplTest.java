package de.saschafeldmann.adesso.master.thesis.preprocesses.algorithm.language;

import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertTrue;

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
}

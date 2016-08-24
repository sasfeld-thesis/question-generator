package de.saschafeldmann.adesso.master.thesis.util.linguistic;

import static org.junit.Assert.*;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Language;
import org.junit.Test;

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
 * Test of the {@link NamedEntityTagAdapter}
 */
public class NamedEntityTagAdapterTest {
    private static final String EXPECTED_LOCATION_ENGLISH = "LOCATION";
    private static final String EXPECTED_PERSON_ENGLISH = "PERSON";
    private static final String EXPECTED_ORGANIZATION_ENGLISH = "ORGANIZATION";
    private static final String EXPECTED_DATE_ENGLISH = "DATE";
    private static final String EXPECTED_NUMERIC_ENGLISH = "NUMBER";

    private static final String EXPECTED_LOCATION_GERMAN = "I-LOC";
    private static final String EXPECTED_PERSON_GERMAN = "I-PER";
    private static final String EXPECTED_ORGANIZATION_GERMAN = "I-ORG";
    private static final String EXPECTED_DATE_GERMAN = "DATE";
    private static final String EXPECTED_NUMERIC_GERMAN = "NUMBER";

    @Test
    public void testGetLocationTagForLanguageEnglish() {
        testForLanguageAndTag(Language.ENGLISH, NamedEntityTagAdapter.LOCATION, EXPECTED_LOCATION_ENGLISH);
    }

    @Test
    public void testGetPersonTagForLanguageEnglish() {
        testForLanguageAndTag(Language.ENGLISH, NamedEntityTagAdapter.PERSON, EXPECTED_PERSON_ENGLISH);
    }

    @Test
    public void testGetOrganizationTagForLanguageEnglish() {
        testForLanguageAndTag(Language.ENGLISH, NamedEntityTagAdapter.ORGANIZATION, EXPECTED_ORGANIZATION_ENGLISH);
    }

    @Test
    public void testGetDateTagForLanguageEnglish() {
        testForLanguageAndTag(Language.ENGLISH, NamedEntityTagAdapter.DATE, EXPECTED_DATE_ENGLISH);
    }

    @Test
    public void testGetNumberTagForLanguageEnglish() {
        testForLanguageAndTag(Language.ENGLISH, NamedEntityTagAdapter.NUMERIC, EXPECTED_NUMERIC_ENGLISH);
    }

    @Test
    public void testGetLocationTagForLanguageGerman() {
        testForLanguageAndTag(Language.GERMAN, NamedEntityTagAdapter.LOCATION, EXPECTED_LOCATION_GERMAN);
    }

    @Test
    public void testGetPersonTagForLanguageGerman() {
        testForLanguageAndTag(Language.GERMAN, NamedEntityTagAdapter.PERSON, EXPECTED_PERSON_GERMAN);
    }

    @Test
    public void testGetOrganizationTagForLanguageGerman() {
        testForLanguageAndTag(Language.GERMAN, NamedEntityTagAdapter.ORGANIZATION, EXPECTED_ORGANIZATION_GERMAN);
    }

    @Test
    public void testGetDateTagForLanguageGerman() {
        testForLanguageAndTag(Language.GERMAN, NamedEntityTagAdapter.DATE, EXPECTED_DATE_GERMAN);
    }

    @Test
    public void testGetNumberTagForLanguageGerman() {
        testForLanguageAndTag(Language.GERMAN, NamedEntityTagAdapter.NUMERIC, EXPECTED_NUMERIC_GERMAN);
    }

    private void testForLanguageAndTag(Language language, NamedEntityTagAdapter tag, String expected) {
        assertEquals(expected, tag.getTagsForLanguage(language).get(0));
    }

}

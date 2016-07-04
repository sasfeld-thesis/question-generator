package util;

import de.saschafeldmann.adesso.master.thesis.detection.util.DetectionProperties;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
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
 * Test of {@link de.saschafeldmann.adesso.master.thesis.detection.util.DetectionProperties}.
 */
public class DetectionPropertiesTest {

    @Test
    public void testGetFillTextFillNamedEntitiesReturnsListOfString() throws Exception {
        // given detection properties
        DetectionProperties detectionProperties = newDetectionProperties();

        // when the method is called
        List<String> namedEntitiesToBeFilled = detectionProperties.getFillTextFillNamedEntities();

        // then a list of string should have been returned
        assertTrue("A list with at least one string value should have been returned.", namedEntitiesToBeFilled.size() > 0);
    }

    @Test
    public void testGetFillTextFillPartOfSpeechTagsReturnsListOfString() throws Exception {
        // given detection properties
        DetectionProperties detectionProperties = newDetectionProperties();

        // when the method is called
        List<String> partOfSpeechTagsToBeFilled = detectionProperties.getFillTextFillPartOfSpeechTags();

        // then a list of string should have been returned
        assertTrue("A list with at least one string value should have been returned.", partOfSpeechTagsToBeFilled.size() > 0);
    }

    @Test
    public void testGetFillTextReplacementCharacterReturnsString() throws Exception {
        // given detection properties
        DetectionProperties detectionProperties = newDetectionProperties();

        // when the method is called
        String replacementCharacter = detectionProperties.getFillTextReplacementCharacter();

        // then a string should have been returned
        assertTrue("A replacement character with at least one char should have been returned.", replacementCharacter.length() > 0);
    }

    private DetectionProperties newDetectionProperties() throws Exception {
        return new DetectionProperties();
    }
}


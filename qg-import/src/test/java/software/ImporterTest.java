package software;

import de.saschafeldmann.adesso.master.thesis.elearningimport.Importer;
import org.junit.Test;
import static org.junit.Assert.*;
/**
 *
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  14.05.2016
 * Author:         Sascha Feldmann (sascha.feldmann@gmx.de)
 *
 * University:
 * Hochschule für Technik und Wirtschaft, Berlin
 * Fachbereich 4
 * Studiengang Internationale Medieninformatik (Master)
 *
 * Company:
 * adesso AG
 *
 * Importer main class test
 */
public class ImporterTest {
    
    @Test
    public void testImporterIsAvailable() {
        // given
        Importer importer = newImporter();

        // when then
        assertNotNull("Importer may not be null - this test is only for build pipeline demonstration.", importer);
    }

    private Importer newImporter() {
        return new Importer();
    }
}

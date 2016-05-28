package de.saschafeldmann.adesso.master.thesis.elearningimport.parser;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  28.05.2016
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
 * Test of the {@link de.saschafeldmann.adesso.master.thesis.elearningimport.parser.TikaRawtextParserAdapter} {@link de.saschafeldmann.adesso.master.thesis.elearningimport.parser.RawtextParserAdapter}
 * implementation.
 */
public class TikaRawtextParserAdapterTest {
    private final static String TEST_FILE_DOCX = "/files/testdocument.docx";
    private final static String TEST_FILE_ODT = "/files/testdocument.odt";
    private final static String TEST_FILE_PDF = "/files/testdocument.pdf";

    private final static List<String> EXPECTED_STRINGS_PDF = new ArrayList<>(
            Arrays.asList(
                    "Test document",
                    "I'm writing this text here to test the import functionality of my question generator software.",
                    "The \n" +
                            "question generator itself is the central part of my master thesis at Hochschule für Technik und \n" +
                            "Wirtschaft, Berlin in 2016.",
                    "The software allows e-learning administrators to automatically create test quesion for their offered \n" +
                            "courses",
                    "Therefore they need to provide the course contents so that the concepts can be extracted by \n" +
                            "making use of natural language processing and semantical technologies.",
                    "In a last step, an natural language generation component will create – hopefully – syntactically \n" +
                            "correct questions."
            ));

    private final static List<String> EXPECTED_STRINGS_DOCX = new ArrayList<>(
            Arrays.asList(
                    "Test document",
                    "I'm writing this text here to test the import functionality of my question generator software.",
                    "The question generator itself is the central part of my master thesis at Hochschule für Technik und Wirtschaft, Berlin in 2016.",
                    "The software allows e-learning administrators to automatically create test quesion for their offered courses. ",
                    "Therefore they need to provide the course contents so that the concepts can be extracted by making use of natural language processing and semantical technologies.",
                    "In a last step, an natural language generation component will create – hopefully – syntactically correct questions."
            ));

    private RawtextParserAdapter rawtextParserAdapter;

    private static RawtextParserAdapter newTikaRawTextParserAdapter() {
        return new TikaRawtextParserAdapter();
    }

    @Before
    public void setUpTest() {
        this.rawtextParserAdapter = newTikaRawTextParserAdapter();
    }

    @Test
    public void testPdfFileParsing() throws ParserException, URISyntaxException {
        // given a PDF file
        File file = getFile(TEST_FILE_PDF);

        // when extractRawtext() is called with the given file
        String extractedRawtext = rawtextParserAdapter.extractRawtext(file);

        // then assert the following rawtext
        assertTrue("The extracted rawtext must not be empty", extractedRawtext.length() > 0);
        assertExtractedTextContainsExpectedStrings(extractedRawtext, EXPECTED_STRINGS_PDF);
    }

    @Test
    public void testDocxFileParsing() throws ParserException, URISyntaxException {
        // given a DOCX file
        File pdfFile = getFile(TEST_FILE_DOCX);

        // when extractRawtext() is called with the given file
        String extractedRawtext = rawtextParserAdapter.extractRawtext(pdfFile);

        // then assert the following rawtext
        assertTrue("The extracted rawtext must not be empty", extractedRawtext.length() > 0);
        assertExtractedTextContainsExpectedStrings(extractedRawtext, EXPECTED_STRINGS_DOCX);
    }

    @Test
    public void testOdtFileParsing() throws ParserException, URISyntaxException {
        // given a DOCX file
        File pdfFile = getFile(TEST_FILE_ODT);

        // when extractRawtext() is called with the given file
        String extractedRawtext = rawtextParserAdapter.extractRawtext(pdfFile);

        // then assert the following rawtext
        assertTrue("The extracted rawtext must not be empty", extractedRawtext.length() > 0);
        assertExtractedTextContainsExpectedStrings(extractedRawtext, EXPECTED_STRINGS_DOCX);
    }

    private void assertExtractedTextContainsExpectedStrings(String extractedRawtext, List<String> expectedStrings) {
        for (String expectedString: expectedStrings) {
            assertTrue("The extracted rawtext should contain the expected string '" + expectedString + "'", extractedRawtext.contains(expectedString));
        }
    }

    private File getFile(String testFile) throws URISyntaxException {
        return new File(getClass().getResource(testFile).getPath());
    }
}

package de.saschafeldmann.adesso.master.thesis.elearningimport.parser;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

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
    private final static String TEST_FILE_ODP = "/files/testdocument.odp";
    private final static String TEST_FILE_PPTX = "/files/testdocument.pptx";
    private static final String TEST_PDF_FILE_WITHOUT_EXTENSION = "/files/testdocument";
    private static final String TEST_TXT_FILE = "/files/testdocument.txt";
    private static final String TEST_FILE_NOT_EXISTING = "/files/notexisting";

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

    private final static List<String> EXPECTED_STRINGS_ODT = EXPECTED_STRINGS_DOCX;
    private final static List<String> EXPECTED_STRINGS_ODP = EXPECTED_STRINGS_DOCX;
    private final static List<String> EXPECTED_STRINGS_PPTX = EXPECTED_STRINGS_ODP;
    private static final List<String> EXPECTED_STRINGS_TXT = EXPECTED_STRINGS_DOCX;

    private RawtextParserAdapter rawtextParserAdapter;

    private static RawtextParserAdapter newTikaRawTextParserAdapter() {
        return new TikaRawtextParserAdapter();
    }

    @Before
    public void setUpTest() {
        this.rawtextParserAdapter = newTikaRawTextParserAdapter();
    }

    @Test
    public void testPdfFileParsingIsSuccessful() throws ParserException, URISyntaxException {
        testFile(TEST_FILE_PDF, EXPECTED_STRINGS_PDF);
    }

    @Test
    public void testDocxFileParsingIsSuccessful() throws ParserException, URISyntaxException {
        testFile(TEST_FILE_DOCX, EXPECTED_STRINGS_DOCX);
    }

    @Test
    public void testOdtFileParsingIsSuccessful() throws ParserException, URISyntaxException {
        testFile(TEST_FILE_ODT, EXPECTED_STRINGS_ODT);
    }

    @Test
    public void testOdpFileParsingIsSuccessful() throws ParserException, URISyntaxException {
        testFile(TEST_FILE_ODP, EXPECTED_STRINGS_ODP);
    }

    @Test
    public void testPptxFileParsingIsSuccessful() throws ParserException, URISyntaxException {
        testFile(TEST_FILE_PPTX, EXPECTED_STRINGS_PPTX);
    }

    @Test
    public void testTextFileParsingIsSuccessful() throws URISyntaxException, ParserException {
        testFile(TEST_TXT_FILE, EXPECTED_STRINGS_TXT);
    }

    @Test
    public void testFileWithoutExtensionIsSuccessful() throws URISyntaxException, ParserException {
        testFile(TEST_PDF_FILE_WITHOUT_EXTENSION, EXPECTED_STRINGS_PDF);
    }

    @Test
    public void testInvalidFileLeadsToParserException() throws URISyntaxException {
        try {
            // given a PDF file
            File file = new File(TEST_FILE_NOT_EXISTING);

            // when extractRawtext() is called with the given file
            rawtextParserAdapter.extractRawtext(file);

            // then an exception should have been thrown
            fail("A parser exception should have been thrown.");
        } catch (ParserException parserException) {
            // test is successful
            assertTrue("An exception message should been given", parserException.getMessage().length() > 0);
        }
    }

    private void testFile(String testFile, List<String> expectedStrings) throws URISyntaxException, ParserException {
        // given a PDF file
        File file = getFile(testFile);

        // when extractRawtext() is called with the given file
        String extractedRawtext = rawtextParserAdapter.extractRawtext(file);

        // then assert the following rawtext
        assertTrue("The extracted rawtext must not be empty", extractedRawtext.length() > 0);
        assertExtractedTextContainsExpectedStrings(extractedRawtext, expectedStrings);
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

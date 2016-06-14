package de.saschafeldmann.adesso.master.thesis.preprocesses.algorithm.language;

import static org.junit.Assert.*;

import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Language;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
 * Unit test of the {@link LanguageDetection} algorithm.
 */
public class LanguageDetectionTest {
    private static final String TEST_CONTENT_ENGLISH = "Test document\n" +
            "\n" +
            "I'm writing this text here to test the import functionality of my question generator software. The question generator itself is the central part of my master thesis at Hochschule für Technik und Wirtschaft, Berlin in 2016.\n" +
            "\n" +
            "The software allows e-learning administrators to automatically create test quesion for their offered courses. Therefore they need to provide the course contents so that the concepts can be extracted by making use of natural language processing and semantical technologies.\n" +
            "\n" +
            "In a last step, an natural language generation component will create – hopefully – syntactically correct questions.";
    private static final String TEST_CONTENT_GERMAN = "Testdokument\n" +
            "\n" +
            "Ich schreibe diesen Text hier um die Import-Funktionalität meiner Question Generator Software zu testen. Der Question Generator ist die zentrale Funktion meiner Masterthesis an der Hochschule für Technik und Wirtschaft, Berlin im Jahr 2016.\n" +
            "\n" +
            "Die Software erlaubt e-Learning-Administratoren, automatische Testfragen für deren angebotene Kurse erstellen zu lassen. Dazu müssen diese zunächst Schulungsinhalte einpflegen, sodass Konzepte mithilfe von Natural Language Processing und semantischen Technologien erkannt werden können.\n" +
            "\n" +
            "Im letzen Schritt wird die Language Generation Komponente - hoffentlich - syntaktisch korrekte Fragen erstellen.";
    private LanguageDetection languageDetectionAlgorithm;

    @Before
    public void setUp() {
        this.languageDetectionAlgorithm = new LanguageDetection();
    }

    @After
    public void tearDown() {
        this.languageDetectionAlgorithm = null;
    }

    @Test
    public void testExecuteThrowsUndeterminableExceptionForChineseText() {
        try {
            this.languageDetectionAlgorithm.execute(getChineseLearningContent());
            fail("LanguageDetection should throw an UndeterminaleLanguageException for chinese characters.");
        } catch (UndeterminableLanguageException e) {
            // expected
        }
    }

    private LearningContent getChineseLearningContent() {
        return newLearningContent("這是一個考驗。\n" +
                "這種語言不應該得到承認。");
    }

    @Test
    public void testExecuteDetectsGermanLanguage() {
        LearningContent learningContent = this.languageDetectionAlgorithm.execute(getGermanLearningContent());

        assertEquals("The LanguageDetection should have set the determined language to German", Language.GERMAN, learningContent.getDeterminedLanguage());
    }

    private LearningContent getGermanLearningContent() {
        return newLearningContent(TEST_CONTENT_GERMAN);
    }

    @Test
    public void testExecuteDetectsEnglishLanguage() {
        LearningContent learningContent = this.languageDetectionAlgorithm.execute(getEnglishLearningContent());

        assertEquals("The LanguageDetection should have set the determined language to English", Language.ENGLISH, learningContent.getDeterminedLanguage());
    }

    private LearningContent getEnglishLearningContent() {
        return newLearningContent(TEST_CONTENT_ENGLISH);
    }

    private LearningContent newLearningContent(final String content) {
        return new LearningContent.LearningContentBuilder()
                .withType(LearningContent.Type.DIRECT_RAWTEXT)
                .withTitle("Unittest")
                .withRawText(content)
                .build();
    }
}

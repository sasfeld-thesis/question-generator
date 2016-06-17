package de.saschafeldmann.adesso.master.thesis.preprocesses.algorithm.nlp;

import static org.junit.Assert.*;

import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Language;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.preprocesses.algorithm.PreprocessingAlgorithm;
import org.junit.Test;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  17.06.2016
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
 * Test of the {@link de.saschafeldmann.adesso.master.thesis.preprocesses.algorithm.nlp.NlpPreprocessingAlgorithm} algorithm.
 *
 * This test can currently be run via mvn test only.
 */
public class NlpPreprocessingAlgorithmTest {
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

    @Test
    public void testExecutePartOfSpeechTaggingForGermanLearningContent() {
        // given algorithm with activated part of speech tagging
        PreprocessingAlgorithm nlpPreprocessingAlgorithm = newInstanceForPartOfSpeechTagging();
        // given a German learning content
        LearningContent germanLearningContent = newGermanLearningContent();

        // when execute is called
        final LearningContent annotatedGermanLearningContent = nlpPreprocessingAlgorithm.execute(germanLearningContent);
        System.out.println("annotated text:");
        System.out.println(annotatedGermanLearningContent.getAnnotatedText());

        // then the annotated text should have been set correctly
        assertEquals("", annotatedGermanLearningContent.getAnnotatedText());
    }

    private LearningContent newGermanLearningContent() {
        LearningContent learningContent = new LearningContent.LearningContentBuilder()
                .withType(LearningContent.Type.DIRECT_RAWTEXT)
                .withTitle("German test")
                .withRawText(TEST_CONTENT_GERMAN)
                .build();
        learningContent.setDeterminedLanguage(Language.GERMAN);

        return learningContent;
    }

    private PreprocessingAlgorithm newInstanceForPartOfSpeechTagging() {
        NlpPreprocessingAlgorithm nlpPreprocessingAlgorithm = new NlpPreprocessingAlgorithm();
        nlpPreprocessingAlgorithm.setActivatePartOfSpeechTagging(true);

        return nlpPreprocessingAlgorithm;
    }
}

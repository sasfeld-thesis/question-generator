package de.saschafeldmann.adesso.master.thesis.preprocesses.algorithm.nlp;

import static org.junit.Assert.*;

import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Course;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Language;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.preprocesses.algorithm.PreprocessingAlgorithm;
import de.saschafeldmann.adesso.master.thesis.preprocesses.model.PreprocessingOptions;
import de.saschafeldmann.adesso.master.thesis.util.linguistic.SentenceUtil;
import org.junit.Test;

import java.util.List;

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

    private static final NlpPreprocessingAlgorithm nlpPreprocessingAlgorithm = newInstance();

    private static NlpPreprocessingAlgorithm newInstance() {
        NlpPreprocessingAlgorithm nlpPreprocessingAlgorithm = new NlpPreprocessingAlgorithm();
        nlpPreprocessingAlgorithm.initializeStanfordModels();

        return nlpPreprocessingAlgorithm;
    }

    @Test
    public void testExecutePartOfSpeechTaggingForGermanLearningContent() {
        // given algorithm with activated part of speech tagging
        PreprocessingOptions preprocessingOptions = getInstanceForPartOfSpeechTagging();

        // given a German learning content
        LearningContent germanLearningContent = newGermanLearningContent();

        // when execute is called
        final LearningContent annotatedGermanLearningContent = nlpPreprocessingAlgorithm.execute(germanLearningContent, preprocessingOptions);
        System.out.println("POS annotated text:");
        System.out.println(annotatedGermanLearningContent.getPartOfSpeechAnnotatedText());

        // then the annotated text should contain the following part of speeches
        String[] nouns = {"Text",
                "Import-Funktionalität",
                "Funktion",
                "Masterthesis",
                "Software",
                "Testfragen",
                "Kurse",
                "Schulungsinhalte",
                "Konzepte",
                "Technologien"};
        assertContainsTags(annotatedGermanLearningContent.getPartOfSpeechAnnotatedText(), "NN", nouns);
        String[] namedEntities = {"Berlin"};
        assertContainsTags(annotatedGermanLearningContent.getPartOfSpeechAnnotatedText(), "NE", namedEntities);
    }

    @Test
    public void testExecuteNamedEntityRecognitionForGermanLearningContent() {
        // given algorithm with activated named entity recognition
        PreprocessingOptions preprocessingOptions = getInstanceForNamedEntityRecognition();

        // given a German learning content
        LearningContent germanLearningContent = newGermanLearningContent();

        // when execute is called
        final LearningContent annotatedGermanLearningContent = nlpPreprocessingAlgorithm.execute(germanLearningContent, preprocessingOptions);
        System.out.println("NER annotated text:");
        System.out.println(annotatedGermanLearningContent.getNamedEntityAnnotatedText());

        // then the annotated text should contain the following part of speeches
        String[] namedLocalizations = {"Berlin"};
        assertContainsTags(annotatedGermanLearningContent.getNamedEntityAnnotatedText(), "I-LOC", namedLocalizations);
        String[] namedDates = {"2016"};
        assertContainsTags(annotatedGermanLearningContent.getNamedEntityAnnotatedText(), "DATE", namedDates);
    }

    @Test
    public void testExecutePartOfSpeechTaggingForEnglishLearningContent() {
        // given algorithm with activated part of speech tagging
        PreprocessingOptions preprocessingOptions = getInstanceForPartOfSpeechTagging();
        // given a German learning content
        LearningContent englishLearningContent = newEnglishLearningContent();

        // when execute is called
        final LearningContent annotatedEnglishLarningContent = nlpPreprocessingAlgorithm.execute(englishLearningContent, preprocessingOptions);
        System.out.println("POS annotated text:");
        System.out.println(annotatedEnglishLarningContent.getPartOfSpeechAnnotatedText());

        // then the annotated text should contain the following part of speeches
        String[] nouns = {"import",
                "functionality",
                "part",
                "master",
                "thesis",
                "software",
                "question",
                "course"};
        assertContainsTags(annotatedEnglishLarningContent.getPartOfSpeechAnnotatedText(), "NN", nouns);
    }

    @Test
    public void testExecuteNamedEntityRecognitionForEnglishLearningContent() {
        // given algorithm with activated named entity recognition
        PreprocessingOptions preprocessingOptions = getInstanceForNamedEntityRecognition();
        // given a German learning content
        LearningContent englishLearningContent = newEnglishLearningContent();

        // when execute is called
        final LearningContent annotatedEnglishLearningContent = nlpPreprocessingAlgorithm.execute(englishLearningContent, preprocessingOptions);
        System.out.println("NER annotated text:");
        System.out.println(annotatedEnglishLearningContent.getNamedEntityAnnotatedText());

        // then the annotated text should contain the following part of speeches
        String[] namedLocalizations = {"Berlin"};
        assertContainsTags(annotatedEnglishLearningContent.getNamedEntityAnnotatedText(), "LOCATION", namedLocalizations);
        String[] namedDates = {"2016"};
        assertContainsTags(annotatedEnglishLearningContent.getNamedEntityAnnotatedText(), "DATE", namedDates);
    }

    private void assertContainsTags(final List<String> annotatedTextSentences, final String partOfSpeechTag, final String[] annotatedTokens) {
        String annotatedText = SentenceUtil.buildStringForListOfSentences(annotatedTextSentences);

        for (String annotatedToken: annotatedTokens) {
            assertTrue("The annotated text should contain the tag " + partOfSpeechTag + " annotated on the token " + annotatedToken,
                    annotatedText.contains("<" + partOfSpeechTag + ">"
                    + annotatedToken
                    + "</" + partOfSpeechTag + ">"));
        }
    }

    private LearningContent newGermanLearningContent() {
        LearningContent learningContent = new LearningContent.LearningContentBuilder()
                .withType(LearningContent.Type.DIRECT_RAWTEXT)
                .withTitle("German test")
                .withCourse(newCourse())
                .withRawText(TEST_CONTENT_GERMAN)
                .build();
        learningContent.setDeterminedLanguage(Language.GERMAN);

        return learningContent;
    }

    private LearningContent newEnglishLearningContent() {
        LearningContent learningContent = new LearningContent.LearningContentBuilder()
                .withType(LearningContent.Type.DIRECT_RAWTEXT)
                .withTitle("English test")
                .withCourse(newCourse())
                .withRawText(TEST_CONTENT_ENGLISH)
                .build();
        learningContent.setDeterminedLanguage(Language.ENGLISH);

        return learningContent;
    }

    private Course newCourse() {
        return new Course.CourseBuilder()
                .withLanguage(Language.GERMAN)
                .withTitle("Unit test course")
                .withViewUrl("http://unittest.de")
                .build();
    }

    private PreprocessingOptions getInstanceForPartOfSpeechTagging() {
        PreprocessingOptions preprocessingOptions = new PreprocessingOptions();

        preprocessingOptions.setActivatePartOfSpeechTagging(true);
        preprocessingOptions.setActivateNamedEntityRecognition(false);

        return preprocessingOptions;
    }

    private PreprocessingOptions getInstanceForNamedEntityRecognition() {
        PreprocessingOptions preprocessingOptions = new PreprocessingOptions();

        preprocessingOptions.setActivatePartOfSpeechTagging(false);
        preprocessingOptions.setActivateNamedEntityRecognition(true);

        return preprocessingOptions;
    }
}

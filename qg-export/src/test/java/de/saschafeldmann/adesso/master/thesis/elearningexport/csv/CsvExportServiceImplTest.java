package de.saschafeldmann.adesso.master.thesis.elearningexport.csv;

import de.saschafeldmann.adesso.master.thesis.detection.model.CardinalRelationConcept;
import de.saschafeldmann.adesso.master.thesis.detection.model.FillInTheBlankTextConcept;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Course;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Language;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.generation.model.TestQuestion;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  10.08.2016
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
 * Test of {@link CsvExportServiceImpl}.
 */
public class CsvExportServiceImplTest {

    @Test
    public void testExportGeneratedQuestionsToCsvFile() throws IOException {
        // given learning contents and generated test questions
        Map<LearningContent, List<TestQuestion>> generatedQuestionsMap = newGeneratedQuestionsMap();
        // and given a prepared CSV exporter
        CsvExportServiceImpl csvExportService = newCsvExportImpl();

        // when service method is called
        File csvFile = csvExportService.exportGeneratedQuestionsToFile(generatedQuestionsMap);

        // then assert
        String header = "learning content title;generated question;is multiple choice;correct answer;alternative correct answer;alternative wrong answer;original sentence";
        checkFileContains(csvFile, header);

        String firstDataRow = "learningcontent1;What's the correct word? The capital of Germany is ___.;short answer question;Berlin;;;The capital of Germany is Berlin.";
        checkFileContains(csvFile, firstDataRow);

        String fourthDataRow = "learningcontent2;How many federal states does Germany have?;short answer question;16;;;Germany has 16 federal states.";
        checkFileContains(csvFile, fourthDataRow);
    }

    private void checkFileContains(File f, String s) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(f.toURI()));
        String fileContent = new String(bytes, "UTF-8");

        assertTrue("The CSV file does not contain " + s, fileContent.contains(s));
    }

    private CsvExportServiceImpl newCsvExportImpl() {
        CsvExportServiceImpl csvExportService = new CsvExportServiceImpl();

        // CSV header
        csvExportService.setLearningContentTitleLabel("learning content title");
        csvExportService.setGeneratedQuestionTitleLabel("generated question");
        csvExportService.setMultipleChoiceModeLabel("is multiple choice");
        csvExportService.setCorrectAnswerLabel("correct answer");
        csvExportService.setCorrectAnswersLabel("alternative correct answer");
        csvExportService.setWrongAnswersLabel("alternative wrong answer");
        csvExportService.setOriginalSentenceLabel("original sentence");

        csvExportService.setFileName("testfilename");
        csvExportService.setIsNoMultipleChoiceLabel("short answer question");
        csvExportService.setIsMultipleChoiceLabel("multiple choice question");

        return csvExportService;
    }

    private Map<LearningContent, List<TestQuestion>> newGeneratedQuestionsMap() {
        Map<LearningContent, List<TestQuestion>> map = new HashMap<>();

        putDummyLearningContentWithTestQuestions("learningcontent1", map);
        putDummyLearningContentWithTestQuestions("learningcontent2", map);
        putDummyLearningContentWithTestQuestions("learningcontent3", map);

        return map;
    }

    private void putDummyLearningContentWithTestQuestions(String learningContentTitle, Map<LearningContent, List<TestQuestion>> map) {
        LearningContent learningContent = new LearningContent.LearningContentBuilder()
                .withTitle(learningContentTitle)
                .withType(LearningContent.Type.DIRECT_RAWTEXT)
                .withRawText("Some raw text")
                .withCourse(newCourse())
                .build();

        List<TestQuestion> testQuestions = new ArrayList<>();

        // first test question
        FillInTheBlankTextConcept fillInTheBlankTextConcept = new FillInTheBlankTextConcept.FillTextConceptBuilder()
                .withFillSentence("The capital of Germany is ___.")
                .withCorrectAnswer("Berlin")
                .withOriginalSentence("The capital of Germany is Berlin.")
                .withLearningContent(learningContent)
                .build();

        TestQuestion fillTextTestQuestion = new TestQuestion("The capital of Germany is Berlin.", fillInTheBlankTextConcept);
        fillTextTestQuestion.setCorrectAnswer("Berlin");
        fillTextTestQuestion.setQuestion("What's the correct word? The capital of Germany is ___.");

        // second test question
        CardinalRelationConcept cardinalRelationConcept = new CardinalRelationConcept.CardinalRelationConceptBuilder()
                .withComposite("Germany")
                .withCompositeCardinality(1)
                .withComposition("federal states")
                .withCompositionCardinality(16)
                .withOriginalSentence("Germany has 16 federal states.")
                .withLearningContent(learningContent)
                .build();
        TestQuestion cardinalRelationTestQuestion = new TestQuestion("Germany has 16 federal states.", cardinalRelationConcept);
        cardinalRelationTestQuestion.setCorrectAnswer("16");
        cardinalRelationTestQuestion.setQuestion("How many federal states does Germany have?");

        testQuestions.add(fillTextTestQuestion);
        testQuestions.add(cardinalRelationTestQuestion);

        map.put(learningContent, testQuestions);
    }

    private Course newCourse() {
        return new Course.CourseBuilder()
                .withLanguage(Language.GERMAN)
                .withTitle("Unit test course")
                .withViewUrl("http://unittest.de")
                .build();
    }
}

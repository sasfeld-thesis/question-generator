package de.saschafeldmann.adesso.master.thesis.elearningexport.moodle;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;
import de.saschafeldmann.adesso.master.thesis.detection.model.CardinalRelationConcept;
import de.saschafeldmann.adesso.master.thesis.detection.model.FillInTheBlankTextConcept;
import de.saschafeldmann.adesso.master.thesis.elearningexport.ExportException;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Course;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Language;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.generation.model.TestQuestion;
import org.junit.Test;

import javax.xml.bind.JAXBException;
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
 * <br /><br />
 * University:
 * Hochschule für Technik und Wirtschaft, Berlin
 * Fachbereich 4
 * Studiengang Internationale Medieninformatik (Master)
 * <br /><br />
 * Company:
 * adesso AG
 * <br /><br />
 * Test of {@link de.saschafeldmann.adesso.master.thesis.elearningexport.moodle.MoodleXmlExportServiceImpl}
 */
public class MoodleXmlExportServiceImplTest {

    @Test
    public void testExportWithShortAnswerQuestions() throws IOException {
        // given short answer question
        Map<LearningContent, List<TestQuestion>> shortAnswerQuestions = newGeneratedShortAnswerQuestionsMap();
        // and an export service
        MoodleXmlExportServiceImpl moodleXmlExportService = newMoodleXmlExportService();

        // when the service is called
        File moodleXmlFile = moodleXmlExportService.exportGeneratedQuestionsToFile(shortAnswerQuestions);

        // then assert the following XML contents
        assertXmlHeader(moodleXmlFile);

        // then assert fill-in-the-blank-text short answer question
        checkFileContains(moodleXmlFile,
                "   <question type=\"shortanswer\">\n" +
                        "        <name>\n" +
                        "            <text>What's the correct word? The capital of Germany is ___.</text>\n" +
                        "        </name>\n" +
                        "        <questiontext format=\"plain_text\">\n" +
                        "            <text>What's the correct word? The capital of Germany is ___.</text>\n" +
                        "        </questiontext>\n" +
                        "        <answer fraction=\"100\">\n" +
                        "            <text>Berlin</text>\n" +
                        "            <feedback>\n" +
                        "                <text>Your answer is correct.</text>\n" +
                        "            </feedback>\n" +
                        "        </answer>\n" +
                        "    </question>");

        // then assert cardinal relation short answer question
        checkFileContains(moodleXmlFile,
                "    <question type=\"shortanswer\">\n" +
                        "        <name>\n" +
                        "            <text>How many federal states does Germany have?</text>\n" +
                        "        </name>\n" +
                        "        <questiontext format=\"plain_text\">\n" +
                        "            <text>How many federal states does Germany have?</text>\n" +
                        "        </questiontext>\n" +
                        "        <answer fraction=\"100\">\n" +
                        "            <text>16</text>\n" +
                        "            <feedback>\n" +
                        "                <text>Your answer is correct.</text>\n" +
                        "            </feedback>\n" +
                        "        </answer>\n" +
                        "    </question>");
    }

    @Test
    public void testExportWithMultipleChoiceQuestions() throws IOException {
        // given short answer question
        Map<LearningContent, List<TestQuestion>> multipleChoiceAnswerQuestions = newGeneratedMultipleChoiceQuestionsMap();
        // and an export service
        MoodleXmlExportServiceImpl moodleXmlExportService = newMoodleXmlExportService();

        // when the service is called
        File moodleXmlFile = moodleXmlExportService.exportGeneratedQuestionsToFile(multipleChoiceAnswerQuestions);

        // then assert the following XML contents
        assertXmlHeader(moodleXmlFile);

        // then assert fill-in-the-blank-text multiple choice question
        checkFileContains(moodleXmlFile,
                "    <question type=\"multichoice\">\n" +
                        "        <name>\n" +
                        "            <text>What's the correct word? The capital of Germany is ___.</text>\n" +
                        "        </name>\n" +
                        "        <questiontext format=\"plain_text\">\n" +
                        "            <text>What's the correct word? The capital of Germany is ___.</text>\n" +
                        "        </questiontext>\n" +
                        "        <answer fraction=\"100\">\n" +
                        "            <text>Berlin</text>\n" +
                        "            <feedback>\n" +
                        "                <text>Your answer is correct.</text>\n" +
                        "            </feedback>\n" +
                        "        </answer>\n" +
                        "        <answer fraction=\"100\">\n" +
                        "            <text>Berlino</text>\n" +
                        "            <feedback>\n" +
                        "                <text>Your answer is correct.</text>\n" +
                        "            </feedback>\n" +
                        "        </answer>\n" +
                        "        <answer fraction=\"0\">\n" +
                        "            <text>Hamburg</text>\n" +
                        "            <feedback>\n" +
                        "                <text>Your answer is incorrect.</text>\n" +
                        "            </feedback>\n" +
                        "        </answer>\n" +
                        "        <answer fraction=\"0\">\n" +
                        "            <text>München</text>\n" +
                        "            <feedback>\n" +
                        "                <text>Your answer is incorrect.</text>\n" +
                        "            </feedback>\n" +
                        "        </answer>\n" +
                        "    </question>");

        // then assert cardinal relation multiple choice question
        checkFileContains(moodleXmlFile,
                "    <question type=\"multichoice\">\n" +
                        "        <name>\n" +
                        "            <text>How many federal states does Germany have?</text>\n" +
                        "        </name>\n" +
                        "        <questiontext format=\"plain_text\">\n" +
                        "            <text>How many federal states does Germany have?</text>\n" +
                        "        </questiontext>\n" +
                        "        <answer fraction=\"100\">\n" +
                        "            <text>16</text>\n" +
                        "            <feedback>\n" +
                        "                <text>Your answer is correct.</text>\n" +
                        "            </feedback>\n" +
                        "        </answer>\n" +
                        "        <answer fraction=\"0\">\n" +
                        "            <text>10</text>\n" +
                        "            <feedback>\n" +
                        "                <text>Your answer is incorrect.</text>\n" +
                        "            </feedback>\n" +
                        "        </answer>\n" +
                        "        <answer fraction=\"0\">\n" +
                        "            <text>20</text>\n" +
                        "            <feedback>\n" +
                        "                <text>Your answer is incorrect.</text>\n" +
                        "            </feedback>\n" +
                        "        </answer>");
    }

    @Test
    public void testExportJaxbThrowsExceptionLeadsToExportException() {
        // given
        Map<LearningContent, List<TestQuestion>> testQuestions = newGeneratedShortAnswerQuestionsMap();

        MoodleXmlExportServiceImpl mock = spy(MoodleXmlExportServiceImpl.class); // "partial mock"
        mock.setFileDirectory(".");
        mock.setFileName("unittest.xml");
        when(mock.buildQuizXmlHierarchy(testQuestions)).thenThrow(JAXBException.class);

        // when export is called
        try {
            mock.exportGeneratedQuestionsToFile(testQuestions);
            fail("An export exception should have been thrown");
        } catch (ExportException e) {
           // exception as expected
        }
    }

    private void assertMultipleChoiceFields(File moodleXmlFile) {

    }

    private void assertXmlHeader(File moodleXmlFile) throws IOException {
        checkFileContains(moodleXmlFile, "<?xml version=\"1.0\" encoding=\"UTF-8\"");
    }

    private void checkFileContains(File f, String s) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(f.toURI()));
        String fileContent = new String(bytes, "UTF-8");
        System.out.println(fileContent);

        assertTrue("The CSV file does not contain " + s, fileContent.contains(s));
    }

    private MoodleXmlExportServiceImpl newMoodleXmlExportService() {
        MoodleXmlExportServiceImpl moodleXmlExportService = new MoodleXmlExportServiceImpl();

        moodleXmlExportService.setFileDirectory(".");
        moodleXmlExportService.setFileName("testfilename");
        moodleXmlExportService.setCorrectAnswerText("Your answer is correct.");
        moodleXmlExportService.setWrongAnswerText("Your answer is incorrect.");

        return moodleXmlExportService;
    }

    private Map<LearningContent, List<TestQuestion>> newGeneratedShortAnswerQuestionsMap() {
        Map<LearningContent, List<TestQuestion>> map = new HashMap<>();

        putDummyLearningContentWithShortAnswerTestQuestions("learningcontent1", map);
        putDummyLearningContentWithShortAnswerTestQuestions("learningcontent2", map);
        putDummyLearningContentWithShortAnswerTestQuestions("learningcontent3", map);

        return map;
    }

    private void putDummyLearningContentWithShortAnswerTestQuestions(String learningContentTitle, Map<LearningContent, List<TestQuestion>> map) {
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

    private Map<LearningContent, List<TestQuestion>> newGeneratedMultipleChoiceQuestionsMap() {
        Map<LearningContent, List<TestQuestion>> map = new HashMap<>();

        putDummyLearningContentWithMultipleChoiceTestQuestions("learningcontent1", map);
        putDummyLearningContentWithMultipleChoiceTestQuestions("learningcontent2", map);
        putDummyLearningContentWithMultipleChoiceTestQuestions("learningcontent3", map);

        return map;
    }

    private void putDummyLearningContentWithMultipleChoiceTestQuestions(String learningContentTitle, Map<LearningContent, List<TestQuestion>> map) {
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
        fillTextTestQuestion.getAlternativeCorrectAnswers().add("Berlino");
        fillTextTestQuestion.getAlternativeWrongAnswers().add("Hamburg");
        fillTextTestQuestion.getAlternativeWrongAnswers().add("München");

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
        cardinalRelationTestQuestion.getAlternativeWrongAnswers().add("10");
        cardinalRelationTestQuestion.getAlternativeWrongAnswers().add("20");

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

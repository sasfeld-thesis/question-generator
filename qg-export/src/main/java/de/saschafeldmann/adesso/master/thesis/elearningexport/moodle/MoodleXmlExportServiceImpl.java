package de.saschafeldmann.adesso.master.thesis.elearningexport.moodle;

import de.saschafeldmann.adesso.master.thesis.elearningexport.ExportException;
import de.saschafeldmann.adesso.master.thesis.elearningexport.ExportService;
import de.saschafeldmann.adesso.master.thesis.elearningexport.moodle.xml.MultipleChoiceQuestion;
import de.saschafeldmann.adesso.master.thesis.elearningexport.moodle.xml.Question;
import de.saschafeldmann.adesso.master.thesis.elearningexport.moodle.xml.Quiz;
import de.saschafeldmann.adesso.master.thesis.elearningexport.moodle.xml.ShortAnswerQuestion;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.generation.model.TestQuestion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;
import java.util.Map;

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
 * Exports to the Moodle XML format documented here: https://docs.moodle.org/24/en/Moodle_XML_format
 */
@Component
@Scope("prototype")
public class MoodleXmlExportServiceImpl implements ExportService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MoodleXmlExportServiceImpl.class);

    private String fileName;
    private String correctAnswerText;
    private String wrongAnswerText;
    private String fileDirectory;

    /**
     * Sets the file directory.
     * @param fileDirectory directory.
     */
    public void setFileDirectory(String fileDirectory) {
        this.fileDirectory = fileDirectory;
    }

    /**
     * Sets the filename.
     *
     * @param fileName String
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Sets the correct answer text.
     * @param correctAnswerText String
     */
    public void setCorrectAnswerText(String correctAnswerText) {
        this.correctAnswerText = correctAnswerText;
    }

    /**
     * Sets the wrong answer text.
     * @param wrongAnswerText String.
     */
    public void setWrongAnswerText(String wrongAnswerText) {
        this.wrongAnswerText = wrongAnswerText;
    }

    @Override
    public File exportGeneratedQuestionsToFile(Map<LearningContent, List<TestQuestion>> generatedQuestionsMap) {
        File file = new File(fileDirectory, fileName);

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Quiz.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            Quiz quiz = buildQuizXmlHierarchy(generatedQuestionsMap);
            jaxbMarshaller.marshal(quiz, file);
        } catch (JAXBException e) {
            LOGGER.error("exportGeneratedQuestionsToFile(): could trigger JAXB marshalling due to exception {}", e);
            throw new ExportException("exportGeneratedQuestionsToFile(): could trigger JAXB marshalling", e);
        }

        return file;
    }

    protected Quiz buildQuizXmlHierarchy(Map<LearningContent, List<TestQuestion>> generatedQuestionsMap) {
        Quiz quiz = new Quiz();

        for (LearningContent learningContent: generatedQuestionsMap.keySet()) {
            buildQuestionHierarchyForLearningContent(quiz, learningContent, generatedQuestionsMap.get(learningContent));
        }

        return quiz;
    }

    private void buildQuestionHierarchyForLearningContent(Quiz quiz, LearningContent learningContent, List<TestQuestion> testQuestions) {
        for (TestQuestion question: testQuestions) {
            Question xmlQuestion = newXmlQuestionFor(question);

            xmlQuestion.getName().setText(question.getQuestion());
            xmlQuestion.getQuestionText().setText(question.getQuestion());

            addCorrectAnswer(xmlQuestion, question.getCorrectAnswer());

            for (String alternativeCorrectAnswer: question.getAlternativeCorrectAnswers()) {
                addCorrectAnswer(xmlQuestion, alternativeCorrectAnswer);
            }

            for (String alternativeWrongAnswer: question.getAlternativeWrongAnswers()) {
                addWrongAnswer(xmlQuestion, alternativeWrongAnswer);
            }

            quiz.getQuestionList().add(xmlQuestion);
        }
    }

    private Question newXmlQuestionFor(TestQuestion question) {
        if (question.isMultipleChoice()) {
            return new MultipleChoiceQuestion();
        } else {
            return new ShortAnswerQuestion();
        }
    }

    private void addCorrectAnswer(Question xmlQuestion, String correctAnswer) {
        Question.Answer answer = new Question.Answer();

        answer.setFraction(100);
        answer.setText(correctAnswer);
        answer.getFeedback().setText(correctAnswerText);

        xmlQuestion.getAnswerList().add(answer);
    }

    private void addWrongAnswer(Question xmlQuestion, String wrongAnswer) {
        Question.Answer answer = new Question.Answer();

        answer.setFraction(0);
        answer.setText(wrongAnswer);
        answer.getFeedback().setText(wrongAnswerText);

        xmlQuestion.getAnswerList().add(answer);
    }
}

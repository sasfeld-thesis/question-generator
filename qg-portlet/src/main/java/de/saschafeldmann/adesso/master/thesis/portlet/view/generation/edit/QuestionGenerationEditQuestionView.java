package de.saschafeldmann.adesso.master.thesis.portlet.view.generation.edit;

import de.saschafeldmann.adesso.master.thesis.generation.model.TestQuestion;
import de.saschafeldmann.adesso.master.thesis.portlet.model.generation.QuestionType;

import java.util.List;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  05.07.2016
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
 * View/dialog to edit generated test questions.
 */
public interface QuestionGenerationEditQuestionView {

    /**
     * Shows the edit dialog to edit the given test question.
     * @param testQuestion the test question to be edited
     */
    void displayForTestQuestion(final TestQuestion testQuestion);

    /**
     * Closes the view / dialog / popup window.
     */
    void close();

    /**
     * Sets the view listener of this view.
     * @param viewListener the view listener notified on UI changes
     */
    void setViewListener(final QuestionGenerationEditQuestionViewListener viewListener);

    /**
     * Gets the user input for the test question input field.
     * @return String
     */
    String getQuestionInput();

    /**
     * Gets the user input for the correct answer input field.
     * @return String
     */
    String getCorrectAnswerInput();

    /**
     * Gets the user input for the question type input field.
     * @return QuestionType
     */
    QuestionType getQuestionTypeInput();

    /**
     * Gets the user inputs for the multiple field wrong answer.
     * @return List of String
     */
    List<String> getMultipleChoiceWrongAnswersInput();

    /**
     * Gets the user inputs for the multiple field correct answer.
     * @return List of String
     */
    List<String> getMultipleChoiceCorrectAnswersInput();
}

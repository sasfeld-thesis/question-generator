package de.saschafeldmann.adesso.master.thesis.portlet.presenter.generation.edit;

import de.saschafeldmann.adesso.master.thesis.generation.model.TestQuestion;
import de.saschafeldmann.adesso.master.thesis.portlet.model.QuestionGenerationSession;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  05.07.2016
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
 * Presenter which allows the user to edit automatically generated questions.
 */
public interface QuestionGenerationEditQuestionPresenter {
    /**
     * Displays the view for the given test question.
     * @param testQuestion the test question
     */
    void displayViewForTestQuestion(final TestQuestion testQuestion);

    /**
     * Sets the question generation sesson.
     * @param questionGenerationSession the session
     */
    void setQuestionGenerationSession(final QuestionGenerationSession questionGenerationSession);

    /**
     * Sets the listener of this presenter.
     * @param listener the listener
     */
    void setListener(final QuestionGenerationEditQuestionListener listener);
}

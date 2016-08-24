package de.saschafeldmann.adesso.master.thesis.portlet.presenter.generation.edit;

import de.saschafeldmann.adesso.master.thesis.generation.model.TestQuestion;
import de.saschafeldmann.adesso.master.thesis.portlet.model.QuestionGenerationSession;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.i18n.Messages;
import de.saschafeldmann.adesso.master.thesis.portlet.view.generation.edit.QuestionGenerationEditQuestionView;
import de.saschafeldmann.adesso.master.thesis.portlet.view.generation.edit.QuestionGenerationEditQuestionViewListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

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
 * Implementation of the {@link QuestionGenerationEditQuestionPresenter}
 */
@Component
@Scope("prototype")
public class QuestionGenerationEditQuestionPresenterImpl implements QuestionGenerationEditQuestionPresenter, QuestionGenerationEditQuestionViewListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionGenerationEditQuestionPresenterImpl.class);
    private final Messages messages;
    private final QuestionGenerationEditQuestionView view;
    private QuestionGenerationEditQuestionListener listener;
    private QuestionGenerationSession questionGenerationSession;

    /**
     * Creates the new presenter.
     *
     * @param messages messages
     * @param view     view
     */
    @Autowired
    public QuestionGenerationEditQuestionPresenterImpl(
            final Messages messages,
            final QuestionGenerationEditQuestionView view
    ) {
        this.messages = messages;
        this.view = view;
    }

    @PostConstruct
    private void initialize() {
        this.view.setViewListener(this);
    }

    /**
     * Sets the question generation session.
     * @param questionGenerationSession the question generation session
     */
    public void setQuestionGenerationSession(final QuestionGenerationSession questionGenerationSession) {
        this.questionGenerationSession = questionGenerationSession;
    }

    @Override
    public void setListener(QuestionGenerationEditQuestionListener listener) {
        this.listener = listener;
    }

    @Override
    public void displayViewForTestQuestion(final TestQuestion testQuestion) {
        LOGGER.info("displayViewForTestQuestion(): displaying for test question {}", testQuestion.getQuestion());
        this.view.displayForTestQuestion(testQuestion);
    }

    @Override
    public void onEditButtonClicked(TestQuestion testQuestionToBeEdited) {
        LOGGER.info("onEditButtonClicked(): editing test question {}", testQuestionToBeEdited.getQuestion());

        testQuestionToBeEdited.setQuestion(view.getQuestionInput());
        testQuestionToBeEdited.setCorrectAnswer(view.getCorrectAnswerInput());

        handleQuestionTypeAndEdit(testQuestionToBeEdited);

        view.close();
    }

    private void handleQuestionTypeAndEdit(TestQuestion testQuestionToBeEdited) {
        testQuestionToBeEdited.getAlternativeWrongAnswers().clear();
        testQuestionToBeEdited.getAlternativeWrongAnswers().addAll(view.getMultipleChoiceWrongAnswersInput());

        testQuestionToBeEdited.getAlternativeCorrectAnswers().clear();
        testQuestionToBeEdited.getAlternativeCorrectAnswers().addAll(view.getMultipleChoiceCorrectAnswersInput());
    }

    @Override
    public void onDeleteButtonClicked(TestQuestion testQuestionToBeDeleted) {
        LOGGER.info("onDeleteButtonClicked(): deleting test question {}", testQuestionToBeDeleted.getQuestion());

        questionGenerationSession.getGeneratedQuestionsContentsMap().get(testQuestionToBeDeleted.getSourceConcept().getLearningContent()).remove(testQuestionToBeDeleted);

        view.close();
    }

    @Override
    public void onClosed(TestQuestion testQuestion) {
        LOGGER.info("onEditQuestionDialogClosed(): notifying listener...");
        listener.onEditQuestionDialogClosed(testQuestion);
    }
}

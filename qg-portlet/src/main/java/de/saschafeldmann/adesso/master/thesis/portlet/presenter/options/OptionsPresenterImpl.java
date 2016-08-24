package de.saschafeldmann.adesso.master.thesis.portlet.presenter.options;

import com.vaadin.ui.Notification;
import de.saschafeldmann.adesso.master.thesis.detection.algorithm.DetectionOptions;
import de.saschafeldmann.adesso.master.thesis.portlet.QuestionGeneratorPortletVaadinUi;
import de.saschafeldmann.adesso.master.thesis.portlet.model.QuestionGenerationSession;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.QuestionGeneratorProperties;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.i18n.Messages;
import de.saschafeldmann.adesso.master.thesis.portlet.view.options.OptionsView;
import de.saschafeldmann.adesso.master.thesis.portlet.view.options.OptionsViewListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  01.08.2016
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
 * The implementation of {@link OptionsPresenter}
 */
@Component
@Scope("prototype")
public class OptionsPresenterImpl implements OptionsPresenter, OptionsViewListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(OptionsPresenterImpl.class);

    private final OptionsView optionsView;
    private final Messages messages;
    private final QuestionGeneratorProperties questionGeneratorProperties;

    private QuestionGenerationSession session;

    /**
     * Creates a new presenter impl.
     * @param optionsView the view
     * @param messages the messages
     */
    @Autowired
    public OptionsPresenterImpl(final OptionsView optionsView, final Messages messages, final QuestionGeneratorProperties questionGeneratorProperties) {
        this.optionsView = optionsView;
        this.messages = messages;
        this.questionGeneratorProperties = questionGeneratorProperties;
    }

    @PostConstruct
    private void initialize() {
        optionsView.setViewListener(this);
        resetSession();
    }

    @Override
    public void display() {
        resetViewValues();
        optionsView.show();
    }

    @Override
    public void setQuestionGenerationSession(QuestionGenerationSession questionGenerationSession) {
        this.session = questionGenerationSession;
    }

    @Override
    public OptionsView getView() {
        return optionsView;
    }

    @Override
    public void onClosed() {
        LOGGER.info("onClosed(): the options dialog was closed");
    }

    @Override
    public void onEditButtonClicked() {
        updateSession();

        optionsView.close();
    }

    private void updateSession() {
        QuestionGenerationSession session = QuestionGeneratorPortletVaadinUi.getCurrentPortletVaadinUi().getQuestionGenerationSession();
        LOGGER.debug("updateSession(): setting options session values...");

        try {
            session.getConceptDetectionOptions().setNumberOfFilltextQuestions(optionsView.getFillTextQuestionsNumberInputValue());
        } catch (Exception e) {
            Notification.show(
                    messages.getOptionsViewErrorNotificationTitle(),
                    messages.getOptionsViewErrorNotificationCaption(messages.getOptionsViewNumberFillTextQuestionsLabel()),
                    Notification.Type.ERROR_MESSAGE
            );
        }

        try {
            session.getConceptDetectionOptions().setNumberOfCardinalityQuestions(optionsView.getCardinalityQuestionsNumberInputValue());
        } catch (Exception e) {
            Notification.show(
                    messages.getOptionsViewErrorNotificationTitle(),
                    messages.getOptionsViewErrorNotificationCaption(messages.getOptionsViewNumberCardinalityQuestionsLabel()),
                    Notification.Type.ERROR_MESSAGE
            );
        }

        try {
            session.getConceptDetectionOptions().setMaxNumberOfTokensForFillInTheBlankSentences(optionsView.getMaxNumberOfTokensInFilltextQuestionsInputValue());
        } catch (Exception e) {
            Notification.show(
                    messages.getOptionsViewErrorNotificationTitle(),
                    messages.getOptionsViewErrorNotificationCaption(messages.getOptionsViewMaxNumberTokensFillTextQuestionsLabel()),
                    Notification.Type.ERROR_MESSAGE
            );
        }
    }

    @Override
    public void onResetButtonClicked() {
        resetSession();
        resetViewValues();

        optionsView.close();
    }

    private void resetSession() {
        QuestionGenerationSession session = QuestionGeneratorPortletVaadinUi.getCurrentPortletVaadinUi().getQuestionGenerationSession();

        session.getConceptDetectionOptions().setNumberOfCardinalityQuestions(DetectionOptions.UNLIMITED);
        session.getConceptDetectionOptions().setNumberOfFilltextQuestions(DetectionOptions.UNLIMITED);
        session.getConceptDetectionOptions().setMaxNumberOfTokensForFillInTheBlankSentences(questionGeneratorProperties.getConceptDetectionFilltextMaxTokensDefault());
    }

    private void resetViewValues() {
        QuestionGenerationSession session = QuestionGeneratorPortletVaadinUi.getCurrentPortletVaadinUi().getQuestionGenerationSession();

        optionsView.reset();
        optionsView.setFillTextQuestionsNumberInputValue(session.getConceptDetectionOptions().getNumberOfFilltextQuestions());
        optionsView.setCardinalityQuestionsNumberInputValue(session.getConceptDetectionOptions().getNumberOfCardinalityQuestions());
        optionsView.setMaxNumberOfTokensInFilltextQuestionsInputValue(session.getConceptDetectionOptions().getMaxNumberOfTokensForFillInTheBlankSentences());
    }
}

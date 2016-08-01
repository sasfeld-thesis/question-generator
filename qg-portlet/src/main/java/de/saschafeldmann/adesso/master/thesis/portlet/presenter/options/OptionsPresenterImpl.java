package de.saschafeldmann.adesso.master.thesis.portlet.presenter.options;

import com.vaadin.ui.Notification;
import de.saschafeldmann.adesso.master.thesis.detection.algorithm.DetectionOptions;
import de.saschafeldmann.adesso.master.thesis.portlet.model.QuestionGenerationSession;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.i18n.Messages;
import de.saschafeldmann.adesso.master.thesis.portlet.view.options.OptionsView;
import de.saschafeldmann.adesso.master.thesis.portlet.view.options.OptionsViewListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  01.08.2016
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
 * The implementation of {@link OptionsPresenter}
 */
@Component
@Scope("prototype")
public class OptionsPresenterImpl implements OptionsPresenter, OptionsViewListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(OptionsPresenterImpl.class);

    @Autowired
    private OptionsView optionsView;
    @Autowired
    private Messages messages;

    private QuestionGenerationSession session;

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
    public void onClosed() {
        LOGGER.info("onClosed(): the options dialog was closed");
    }

    @Override
    public void onEditButtonClicked() {
        updateSession();

        optionsView.close();
    }

    private void updateSession() {
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
    }

    @Override
    public void onResetButtonClicked() {
        resetSession();
        resetViewValues();

        optionsView.close();
    }

    private void resetSession() {
        session.getConceptDetectionOptions().setNumberOfCardinalityQuestions(DetectionOptions.UNLIMITED);
        session.getConceptDetectionOptions().setNumberOfFilltextQuestions(DetectionOptions.UNLIMITED);
    }

    private void resetViewValues() {
        optionsView.reset();
        optionsView.setFillTextQuestionsNumberInputValue(session.getConceptDetectionOptions().getNumberOfFilltextQuestions());
        optionsView.setCardinalityQuestionsNumberInputValue(session.getConceptDetectionOptions().getNumberOfCardinalityQuestions());
    }
}

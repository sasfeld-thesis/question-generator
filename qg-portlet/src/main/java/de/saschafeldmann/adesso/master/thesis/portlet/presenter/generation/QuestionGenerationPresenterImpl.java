package de.saschafeldmann.adesso.master.thesis.portlet.presenter.generation;

import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.generation.model.TestQuestion;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.AbstractStepPresenter;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.i18n.Messages;
import de.saschafeldmann.adesso.master.thesis.portlet.view.ViewWithMenu;
import de.saschafeldmann.adesso.master.thesis.portlet.view.detection.DetectionViewImpl;
import de.saschafeldmann.adesso.master.thesis.portlet.view.generation.QuestionGenerationView;
import de.saschafeldmann.adesso.master.thesis.portlet.view.generation.QuestionGenerationViewListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  04.07.2016
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
 * [short description]
 */
@Component
@Scope("prototype")
public class QuestionGenerationPresenterImpl extends AbstractStepPresenter implements QuestionGenerationPresenter, QuestionGenerationViewListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionGenerationPresenterImpl.class);
    private final QuestionGenerationView questionGenerationView;

    @Autowired
    private Messages messages;
    /**
     * Creates a new view.
     * @param view
     */
    @Autowired
    public QuestionGenerationPresenterImpl(final QuestionGenerationView view) {
        this.questionGenerationView = view;
    }

    @Override
    public QuestionGenerationView initializeView() {
        this.questionGenerationView.setViewListener(this);
        this.questionGenerationView.setMenuListener(this);
        this.questionGenerationView.setCurrentSessionStatus(questionGenerationSession.getStatus());

        this.questionGenerationView.reset();
        return this.questionGenerationView;
    }

    @Override
    public ViewWithMenu getView() {
        return this.questionGenerationView;
    }

    @Override
    public void onStartQuestionGenerationButtonClicked() {
        LOGGER.info("onStartQuestionGenerationButtonClicked()");

        // TODO delegate
    }

    @Override
    public void onCompletedLearningContentSelected(final LearningContent learningContent) {
        LOGGER.info("onCompletedLearningContentSelected()");

        // TODO show generated questions for the given learning content
    }

    @Override
    public void onGeneratedQuestionSelected(TestQuestion testQuestion) {
        LOGGER.info("onGeneratedQuestionSelected()");

        // TODO delegate to edit presenter
    }

    @Override
    public void onBackButtonClicked() {
        LOGGER.info("onBackButtonClicked()");

        getNavigator().navigateTo(DetectionViewImpl.VIEW_NAME);
    }

    @Override
    public void onExportButtonClicked() {
        LOGGER.info("onExportButtonClicked()");

        // TODO start export
    }

    @Override
    public void onViewFocus() {
        questionGenerationView.setCurrentSessionStatus(questionGenerationSession.getStatus());
        questionGenerationView.reset();
    }
}

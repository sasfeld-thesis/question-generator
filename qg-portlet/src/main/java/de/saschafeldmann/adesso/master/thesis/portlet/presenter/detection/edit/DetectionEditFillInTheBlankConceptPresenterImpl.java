package de.saschafeldmann.adesso.master.thesis.portlet.presenter.detection.edit;

import de.saschafeldmann.adesso.master.thesis.detection.model.FillInTheBlankTextConcept;
import de.saschafeldmann.adesso.master.thesis.detection.model.api.Concept;
import de.saschafeldmann.adesso.master.thesis.portlet.QuestionGeneratorPortletVaadinUi;
import de.saschafeldmann.adesso.master.thesis.portlet.model.QuestionGenerationSession;
import de.saschafeldmann.adesso.master.thesis.portlet.view.detection.edit.DetectionEditConceptViewListener;
import de.saschafeldmann.adesso.master.thesis.portlet.view.detection.edit.DetectionEditFillInTheBlankConceptView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  28.06.2016
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
public class DetectionEditFillInTheBlankConceptPresenterImpl implements DetectionEditConceptPresenter<FillInTheBlankTextConcept>, DetectionEditConceptViewListener<FillInTheBlankTextConcept> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DetectionEditFillInTheBlankConceptPresenterImpl.class);
    private final DetectionEditFillInTheBlankConceptView view;

    /**
     * Constructs a new presenter for the {@link FillInTheBlankTextConcept}
     * @param view the view
     */
    @Autowired
    public DetectionEditFillInTheBlankConceptPresenterImpl(final DetectionEditFillInTheBlankConceptView view) {
        this.view = view;
    }

    @PostConstruct
    private void initialize() {
        this.view.setViewListener(this);
    }

    @Override
    public void displayEditViewForConcept(final FillInTheBlankTextConcept concept) {
        LOGGER.info("displayEditViewForConcept(): displaying for concept {}", concept.getOriginalSentence());

        view.displayForConcept(concept);
    }

    @Override
    public void onEditButtonClicked(FillInTheBlankTextConcept conceptToBeEdited) {
        LOGGER.info("onEditButtonClicked(): editing concept {}", conceptToBeEdited.getOriginalSentence());

        conceptToBeEdited.setFillSentence(view.getFillTextSentenceInput());
        conceptToBeEdited.setCorrectAnswer(view.getCorrectAnswerInput());

        closeView(conceptToBeEdited);
    }

    @Override
    public void onDeleteButtonClicked(FillInTheBlankTextConcept concept) {
        LOGGER.info("onDeleteButtonClicked(): deleting concept {}", concept.getOriginalSentence());

        getQuestionGeneratorSession().deleteDetectedConcept(concept.getLearningContent(), concept);

        closeView(concept);
    }

    @Override
    public void onWindowClosed(Concept concept) {
        showDetectedConceptsView(concept);
    }

    private void closeView(FillInTheBlankTextConcept concept) {
        view.close();

        showDetectedConceptsView(concept);
    }

    private void showDetectedConceptsView(Concept concept) {
        QuestionGeneratorPortletVaadinUi.getCurrentPortletVaadinUi().getDetectionEditConceptsPresenter().displayDetectedConcepts(
                concept.getLearningContent(),
                getQuestionGeneratorSession().getDetectedConceptsContentsMap().get(concept.getLearningContent()));
    }

    private QuestionGenerationSession getQuestionGeneratorSession() {
        return QuestionGeneratorPortletVaadinUi.getCurrentPortletVaadinUi().getQuestionGenerationSession();
    }
}

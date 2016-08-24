package de.saschafeldmann.adesso.master.thesis.portlet.presenter.detection.edit;

import de.saschafeldmann.adesso.master.thesis.detection.model.api.Concept;
import de.saschafeldmann.adesso.master.thesis.portlet.QuestionGeneratorPortletVaadinUi;
import de.saschafeldmann.adesso.master.thesis.portlet.model.QuestionGenerationSession;
import de.saschafeldmann.adesso.master.thesis.portlet.view.detection.edit.DetectionEditConceptView;
import de.saschafeldmann.adesso.master.thesis.portlet.view.detection.edit.DetectionEditConceptViewListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  12.07.2016
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
 * Abstract presenter for all specific {@link de.saschafeldmann.adesso.master.thesis.detection.model.api.Concept} edit dialogs.
 */
public abstract class AbstractDetectionEditConceptPresenter<ConceptType extends Concept> implements DetectionEditConceptPresenter<ConceptType>, DetectionEditConceptViewListener<ConceptType> {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractDetectionEditConceptPresenter.class);

    private final DetectionEditConceptView<ConceptType> view;

    /**
     * Creates the presenter.
     * @param view the view
     */
    public AbstractDetectionEditConceptPresenter(final DetectionEditConceptView<ConceptType> view) {
        this.view = view;
    }

    @Override
    public void displayEditViewForConcept(final ConceptType concept) {
        LOGGER.info("displayEditViewForConcept(): displaying for concept {}", concept.getOriginalSentence());

        view.displayForConcept(concept);
    }

    @Override
    public void onDeleteButtonClicked(final ConceptType concept) {
        LOGGER.info("onDeleteButtonClicked(): deleting concept {}", concept.getOriginalSentence());

        getQuestionGeneratorSession().deleteDetectedConcept(concept.getLearningContent(), concept);

        closeView(concept);
    }

    @Override
    public void onWindowClosed(Concept concept) {
        showDetectedConceptsView(concept);
    }

    protected void closeView(ConceptType concept) {
        view.close();

        showDetectedConceptsView(concept);
    }

    private void showDetectedConceptsView(Concept concept) {
        QuestionGeneratorPortletVaadinUi.getCurrentPortletVaadinUi().getDetectionEditConceptsPresenter().displayDetectedConcepts(
                concept.getLearningContent(),
                getQuestionGeneratorSession().getDetectedConceptsContentsMap().get(concept.getLearningContent()));
    }

    protected QuestionGenerationSession getQuestionGeneratorSession() {
        return QuestionGeneratorPortletVaadinUi.getCurrentPortletVaadinUi().getQuestionGenerationSession();
    }
}

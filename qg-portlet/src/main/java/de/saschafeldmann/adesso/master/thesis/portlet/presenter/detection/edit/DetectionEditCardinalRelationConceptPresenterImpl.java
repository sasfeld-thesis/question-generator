package de.saschafeldmann.adesso.master.thesis.portlet.presenter.detection.edit;

import de.saschafeldmann.adesso.master.thesis.detection.model.CardinalRelationConcept;
import de.saschafeldmann.adesso.master.thesis.detection.model.FillInTheBlankTextConcept;
import de.saschafeldmann.adesso.master.thesis.detection.model.api.Concept;
import de.saschafeldmann.adesso.master.thesis.portlet.QuestionGeneratorPortletVaadinUi;
import de.saschafeldmann.adesso.master.thesis.portlet.model.QuestionGenerationSession;
import de.saschafeldmann.adesso.master.thesis.portlet.view.detection.edit.DetectionEditCardinalRelationConceptView;
import de.saschafeldmann.adesso.master.thesis.portlet.view.detection.edit.DetectionEditConceptViewListener;
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
 * <br><br>
 * University:
 * Hochschule für Technik und Wirtschaft, Berlin
 * Fachbereich 4
 * Studiengang Internationale Medieninformatik (Master)
 * <br><br>
 * Company:
 * adesso AG
 * <br><br>
 * The implementation of {@link DetectionEditConceptPresenter}
 */
@Component
@Scope("prototype")
public class DetectionEditCardinalRelationConceptPresenterImpl extends AbstractDetectionEditConceptPresenter<CardinalRelationConcept> implements DetectionEditConceptPresenter<CardinalRelationConcept>, DetectionEditConceptViewListener<CardinalRelationConcept> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DetectionEditCardinalRelationConceptPresenterImpl.class);
    private final DetectionEditCardinalRelationConceptView view;

    /**
     * Constructs a new presenter for the {@link FillInTheBlankTextConcept}
     * @param view the view
     */
    @Autowired
    public DetectionEditCardinalRelationConceptPresenterImpl(final DetectionEditCardinalRelationConceptView view) {
        super(view);

        this.view = view;
    }

    @PostConstruct
    private void initialize() {
        view.setViewListener(this);
    }

    @Override
    public void onEditButtonClicked(CardinalRelationConcept conceptToBeEdited) {
        LOGGER.info("onEditButtonClicked(): editing concept {}", conceptToBeEdited.getOriginalSentence());

        conceptToBeEdited.setComposition(view.getCompositionUserInput());
        conceptToBeEdited.setCompositionCardinality(view.getCompositionCardinalityUserInput());
        conceptToBeEdited.setComposite(view.getCompositeUserInput());
        conceptToBeEdited.setCompositeCardinality(view.getCompositeCardinalityUserInput());

        closeView(conceptToBeEdited);
    }

}

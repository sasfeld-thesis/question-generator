package de.saschafeldmann.adesso.master.thesis.portlet.presenter.detection.edit;

import de.saschafeldmann.adesso.master.thesis.detection.algorithm.model.CardinalRelationConcept;
import de.saschafeldmann.adesso.master.thesis.detection.algorithm.model.FillTextConcept;
import de.saschafeldmann.adesso.master.thesis.portlet.view.detection.edit.DetectionEditCardinalRelationConceptView;
import de.saschafeldmann.adesso.master.thesis.portlet.view.detection.edit.DetectionEditConceptsViewListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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
public class DetectionEditCardinalRelationConceptPresenterImpl implements DetectionEditConceptPresenter<CardinalRelationConcept>, DetectionEditConceptsViewListener<CardinalRelationConcept> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DetectionEditCardinalRelationConceptPresenterImpl.class);
    private final DetectionEditCardinalRelationConceptView view;

    /**
     * Constructs a new presenter for the {@link FillTextConcept}
     * @param view the view
     */
    @Autowired
    public DetectionEditCardinalRelationConceptPresenterImpl(final DetectionEditCardinalRelationConceptView view) {
        this.view = view;
    }

    @Override
    public void displayEditViewForConcept(final CardinalRelationConcept concept) {
        LOGGER.info("displayEditViewForConcept(): displaying for concept {}", concept.getOriginalSentence());

        view.displayForConcept(concept);
    }

    @Override
    public void onEditButtonClicked(CardinalRelationConcept conceptToBeEdited) {
        LOGGER.info("onEditButtonClicked(): editing concept {}", conceptToBeEdited.getOriginalSentence());

        conceptToBeEdited.setComposition(view.getCompositionUserInput());
        conceptToBeEdited.setCompositionCardinality(view.getCompositionCardinalityUserInput());
        conceptToBeEdited.setComposite(view.getCompositeUserInput());
        conceptToBeEdited.setCompositeCardinality(view.getCompositeCardinalityUserInput());

        view.close();
    }
}

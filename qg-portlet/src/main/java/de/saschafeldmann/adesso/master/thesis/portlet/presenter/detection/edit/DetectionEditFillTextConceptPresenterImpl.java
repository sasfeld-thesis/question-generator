package de.saschafeldmann.adesso.master.thesis.portlet.presenter.detection.edit;

import de.saschafeldmann.adesso.master.thesis.detection.algorithm.model.FillTextConcept;
import de.saschafeldmann.adesso.master.thesis.portlet.view.detection.edit.DetectionEditConceptViewListener;
import de.saschafeldmann.adesso.master.thesis.portlet.view.detection.edit.DetectionEditConceptsViewListener;
import de.saschafeldmann.adesso.master.thesis.portlet.view.detection.edit.DetectionEditFillTextConceptView;
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
public class DetectionEditFillTextConceptPresenterImpl implements DetectionEditConceptPresenter<FillTextConcept>, DetectionEditConceptViewListener<FillTextConcept> {
    private static final Logger LOGGER = LoggerFactory.getLogger(DetectionEditFillTextConceptPresenterImpl.class);
    private final DetectionEditFillTextConceptView view;

    /**
     * Constructs a new presenter for the {@link FillTextConcept}
     * @param view the view
     */
    @Autowired
    public DetectionEditFillTextConceptPresenterImpl(final DetectionEditFillTextConceptView view) {
        this.view = view;
    }

    @PostConstruct
    private void initialize() {
        this.view.setViewListener(this);
    }

    @Override
    public void displayEditViewForConcept(final FillTextConcept concept) {
        LOGGER.info("displayEditViewForConcept(): displaying for concept {}", concept.getOriginalSentence());

        view.displayForConcept(concept);
    }

    @Override
    public void onEditButtonClicked(FillTextConcept conceptToBeEdited) {
        LOGGER.info("onEditButtonClicked(): editing concept {}", conceptToBeEdited.getOriginalSentence());

        conceptToBeEdited.setFillSentence(view.getFillTextSentenceInput());
        conceptToBeEdited.setCorrectAnswer(view.getCorrectAnswerInput());

        view.close();
    }

    @Override
    public void onDeleteButtonClicked(FillTextConcept concept) {
        // TODO
    }
}

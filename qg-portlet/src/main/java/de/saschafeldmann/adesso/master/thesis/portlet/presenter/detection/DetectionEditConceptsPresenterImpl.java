package de.saschafeldmann.adesso.master.thesis.portlet.presenter.detection;

import de.saschafeldmann.adesso.master.thesis.detection.algorithm.model.api.Concept;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.portlet.view.detection.DetectionEditConceptsViewListener;
import de.saschafeldmann.adesso.master.thesis.portlet.view.detection.DetectionEditConceptsView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

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
 * The implementation of {@link DetectionEditConceptsPresenter}.
 */
@Component
@Scope("prototype")
public class DetectionEditConceptsPresenterImpl implements DetectionEditConceptsPresenter, DetectionEditConceptsViewListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(DetectionEditConceptsPresenterImpl.class);
    private final DetectionEditConceptsView view;

    /**
     * Creates a new presenter.
     * @param detectionEditConceptsView the view which this presenter handles
     */
    @Autowired
    public DetectionEditConceptsPresenterImpl(final DetectionEditConceptsView detectionEditConceptsView) {
        this.view = detectionEditConceptsView;
    }

    @Override
    public void displayDetectedConcepts(final LearningContent learningContent, final List<Concept> detectedConcepts) {
        LOGGER.info("displayDetectedConcepts(): displaying detected concepts.");

        view.displayDetectedConcepts(detectedConcepts);
    }

    @Override
    public DetectionEditConceptsView getView() {
        return view;
    }

    @Override
    public void onEditButtonClicked(final Concept conceptToBeEdited) {
        LOGGER.info("displayDetectedConcepts(): onEditButtonClicked");

        // TODO delegate to DetectionEditConceptPresenter when present
    }
}

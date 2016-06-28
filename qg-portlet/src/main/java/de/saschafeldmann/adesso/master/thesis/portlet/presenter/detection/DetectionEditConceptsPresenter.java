package de.saschafeldmann.adesso.master.thesis.portlet.presenter.detection;

import com.vaadin.ui.Window;
import de.saschafeldmann.adesso.master.thesis.detection.algorithm.model.api.Concept;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.portlet.view.detection.DetectionEditConceptsView;

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
 * The {@link DetectionEditConceptsPresenter} handles a dialog that is shown if the user selected a {@link de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent}
 * within the {@link DetectionPresenter} logic. Here, the user can edit identified {@link de.saschafeldmann.adesso.master.thesis.detection.algorithm.model.api.Concept}
 */
public interface DetectionEditConceptsPresenter {

    /**
     * Handles the logic to display the given concepts that were detected on the given {@link LearningContent}.
     * @param learningContent the learning content for which the concepts were detected.
     * @param detectedConcepts the concepts
     */
    void displayDetectedConcepts(final LearningContent learningContent, List<Concept> detectedConcepts);

    /**
     * Gets the view.
     * @return DetectionEditConceptsView
     */
    DetectionEditConceptsView getView();

}

package de.saschafeldmann.adesso.master.thesis.portlet.view.detection;

import de.saschafeldmann.adesso.master.thesis.detection.model.api.Concept;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.portlet.model.detection.DetectionActivationElement;
import de.saschafeldmann.adesso.master.thesis.portlet.view.ViewWithMenu;

import java.util.List;
import java.util.Map;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  24.06.2016
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
 * Interface for the concept detection view.
 */
public interface DetectionView extends ViewWithMenu {

    /**
     * Sets detection activation elements to the view and allows to en- or disable the underlying concept detection algorithm
     * @param detectionActivationElements the detectionActivationElements to be rendered
     */
    void setDetectionActivationElements(Iterable<DetectionActivationElement> detectionActivationElements);

    /**
     * Shows the detection activation success message.
     */
    void showDetectionActivationSuccessMessage();

    /**
     * Adds and displays a log entry informing about the concept detection chain progress.
     * @param logEntry String the entry to be shown.
     */
    void addDetectionChainLogEntry(String time, String logEntry);

    /**
     * Shows the given processed learning contents for which concepts where detected.
     * @param detectedConcepts a map of the learning content as key and a list of detected concepts (from the qg-detection submodule).
     */
    void showProcessedLearningContents(Map<LearningContent, List<Concept>> detectedConcepts);

    /**
     * Resets / initializes the view layout.
     */
    void reset();

    /**
     * Sets the view listener for this view.
     * @param detectionViewListener the listener
     */
    void setViewListener(final DetectionViewListener detectionViewListener);
}

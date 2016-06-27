package de.saschafeldmann.adesso.master.thesis.portlet.view.detection;

import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.portlet.model.detection.DetectionActivationElement;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  24.06.2016
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
 * Interface for listeners of {@link DetectionView} instances.
 */
public interface DetectionViewListener {

    /**
     * Triggered if the user activated or deactivated the given {@link de.saschafeldmann.adesso.master.thesis.portlet.model.detection.DetectionActivationElement.DetectionActivationElementState}.
     * @param detectionActivationElement the detectionActivationElement
     */
    void onDetectionActivationElementChange(final DetectionActivationElement.DetectionActivationElementState detectionActivationElement);

    /**
     * Triggered if the user clicked the detection chain start button.
     */
    void onDetectionChainStartButtonClicked();

    /**
     * Triggered if the user selected a {@link LearningContent}.
     * @param learningContent the selected learning content
     */
    void onFinishedLearningContentSelected(final LearningContent learningContent);

    /**
     * Called if the view gets the user's focus.
     */
    void onViewFocus();

    /**
     * On back button clicked action.
     */
    void onBackButtonClicked();

    /**
     * On next button clicked action.
     */
    void onNextButtonClicked();
}

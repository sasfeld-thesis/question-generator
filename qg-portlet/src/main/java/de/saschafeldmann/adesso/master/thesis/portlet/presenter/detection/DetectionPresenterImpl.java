package de.saschafeldmann.adesso.master.thesis.portlet.presenter.detection;

import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.portlet.model.QuestionGenerationSession;
import de.saschafeldmann.adesso.master.thesis.portlet.model.detection.DetectionActivationElement;
import de.saschafeldmann.adesso.master.thesis.portlet.model.preprocesses.ProcessActivationElement;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.AbstractStepPresenter;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.preprocesses.PreprocessesPresenterImpl;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.i18n.Messages;
import de.saschafeldmann.adesso.master.thesis.portlet.view.ViewWithMenu;
import de.saschafeldmann.adesso.master.thesis.portlet.view.detection.DetectionView;
import de.saschafeldmann.adesso.master.thesis.portlet.view.detection.DetectionViewListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
 * Implementation of the {@link DetectionPresenter}.
 */
@Component
@Scope("prototype")
public class DetectionPresenterImpl extends AbstractStepPresenter implements DetectionPresenter, DetectionViewListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(DetectionPresenterImpl.class);

    private final DetectionView detectionView;
    @Autowired
    private Messages messages;
    private List<DetectionActivationElement> detectionActivationElementList;
    private boolean detectionFinished = false;

    @Autowired
    public DetectionPresenterImpl(
            DetectionView detectionView
    ) {
        this.detectionView = detectionView;
    }

    @Override
    public ViewWithMenu getView() {
        return detectionView;
    }

    @Override
    public DetectionView initializeView() {
        this.detectionView.setMenuListener(this);
        this.detectionView.setViewListener(this);
        this.detectionView.setCurrentSessionStatus(questionGenerationSession.getStatus());

        loadDetectionActivationElements();

        this.detectionView.reset();
        return this.detectionView;
    }

    private void loadDetectionActivationElements() {
        this.detectionActivationElementList = new ArrayList<>();

        addFillSentenceDetectionActivationElement(this.detectionActivationElementList);
        addCardinalitySentencesDetectionActivationElement(this.detectionActivationElementList);

        this.detectionView.setDetectionActivationElements(detectionActivationElementList);
    }

    private void addFillSentenceDetectionActivationElement(List<DetectionActivationElement> detectionActivationElementList) {
        // TODO couple underlying algorithm from qg-detection module
        DetectionActivationElement detectionActivationElement = new DetectionActivationElement.DetectionActivationElementBuilder()
                .withActivationLabel(messages.getDetectionViewAccordionActivationOptiongroupFillsentencesLabel())
                .withIsActivatedPerDefault(false)
                .withTooltip(messages.getDetectionViewAccordionActivationOptiongroupFillsentencesTooltip())
                .withStartedLogEntry(messages.getDetectionViewAccordionDetectionChainLogChainFillsentencesStarted())
                .withFinishedLogEntry(messages.getDetectionViewAccordionDetectionChainLogChainFillsentencesFinished())
                .build();

        setDefaultProcessActivationElementState(detectionActivationElement);
        detectionActivationElementList.add(detectionActivationElement);
    }

    private void addCardinalitySentencesDetectionActivationElement(List<DetectionActivationElement> detectionActivationElementList) {
        // TODO couple underlying algorithm from qg-detection module
        DetectionActivationElement detectionActivationElement = new DetectionActivationElement.DetectionActivationElementBuilder()
                .withActivationLabel(messages.getDetectionViewAccordionActivationOptiongroupCardinalitySentencesLabel())
                .withIsActivatedPerDefault(false)
                .withTooltip(messages.getDetectionViewAccordionActivationOptiongroupCardinalitySentencesTooltip())
                .withStartedLogEntry(messages.getDetectionViewAccordionDetectionChainLogChainCardinalitySentencesStarted())
                .withFinishedLogEntry(messages.getDetectionViewAccordionDetectionChainLogChainCardinalitySentencesFinished())
                .build();

        setDefaultProcessActivationElementState(detectionActivationElement);
        detectionActivationElementList.add(detectionActivationElement);
    }

    private void setDefaultProcessActivationElementState(DetectionActivationElement detectionActivationElement) {
        if (detectionActivationElement.isActivatedPerDefault()) {
            detectionActivationElement.setDetectionActivationElementState(detectionActivationElement.getDetectionActivationElementStateActivated());
        } else {
            detectionActivationElement.setDetectionActivationElementState(detectionActivationElement.getDetectionActivationElementStateDeactivated());
        }
    }

    @Override
    public void onDetectionActivationElementChange(DetectionActivationElement.DetectionActivationElementState detectionActivationElementState) {
        LOGGER.info("onDetectionActivationElementChange(): option for {} was changed to {}",
                detectionActivationElementState.getParentProcessActivationElement().getActivationLabel(),
                detectionActivationElementState.getActivationOptionGroupItem());

        detectionActivationElementState.getParentProcessActivationElement().setDetectionActivationElementState(detectionActivationElementState);

        detectionView.showDetectionActivationSuccessMessage();
    }

    @Override
    public void onDetectionChainStartButtonClicked() {
        LOGGER.info("onDetectionChainStartButtonClicked()");
    }

    @Override
    public void onFinishedLearningContentSelected(LearningContent learningContent) {
        LOGGER.info("onFinishedLearningContentSelected()");
    }

    @Override
    public void onViewFocus() {
        LOGGER.info("onViewFocus()");

        detectionView.setCurrentSessionStatus(questionGenerationSession.getStatus());
        detectionView.reset();
    }

    @Override
    public void onBackButtonClicked() {
        LOGGER.info("onBackButtonClicked()");
    }

    @Override
    public void onNextButtonClicked() {
        LOGGER.info("onNextButtonClicked()");

        questionGenerationSession.setStatus(QuestionGenerationSession.Status.DETECTION_DONE);
    }
}

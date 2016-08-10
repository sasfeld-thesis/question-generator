package de.saschafeldmann.adesso.master.thesis.portlet.presenter.detection;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.vaadin.ui.Notification;
import de.saschafeldmann.adesso.master.thesis.detection.algorithm.DetectionOptions;
import de.saschafeldmann.adesso.master.thesis.detection.algorithm.cardinalrelation.CardinalRelationConceptDetection;
import de.saschafeldmann.adesso.master.thesis.detection.algorithm.fillintheblank.FillInTheBlankConceptDetection;
import de.saschafeldmann.adesso.master.thesis.detection.model.FillInTheBlankTextConcept;
import de.saschafeldmann.adesso.master.thesis.detection.model.api.Concept;
import de.saschafeldmann.adesso.master.thesis.util.ListUtil;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.portlet.QuestionGeneratorPortletVaadinUi;
import de.saschafeldmann.adesso.master.thesis.portlet.model.QuestionGenerationSession;
import de.saschafeldmann.adesso.master.thesis.portlet.model.detection.DetectionActivationElement;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.AbstractStepPresenter;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.i18n.Messages;
import de.saschafeldmann.adesso.master.thesis.portlet.view.ViewWithMenu;
import de.saschafeldmann.adesso.master.thesis.portlet.view.detection.DetectionView;
import de.saschafeldmann.adesso.master.thesis.portlet.view.detection.DetectionViewListener;
import de.saschafeldmann.adesso.master.thesis.portlet.view.generation.QuestionGenerationViewImpl;
import de.saschafeldmann.adesso.master.thesis.portlet.view.preprocesses.PreprocessesViewImpl;
import de.saschafeldmann.adesso.master.thesis.util.linguistic.SentenceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

import static de.saschafeldmann.adesso.master.thesis.portlet.util.FilterUtil.FILTER_DELETED_ANNOTATED_TEXTS_PREDICATE;

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
    @Autowired
    private FillInTheBlankConceptDetection fillTextConceptDetectionAlgorithm;
    @Autowired
    private CardinalRelationConceptDetection cardinalRelationConceptDetectionAlgorithm;
    private List<DetectionActivationElement> detectionActivationElementList;
    private boolean detectionFinished = false;

    private final Predicate<Concept> FILTER_FILL_TEXTS_WITH_MORE_THAN_ALLOWED = new Predicate<Concept>() {
        @Override
        public boolean apply(Concept concept) {
            if (concept instanceof FillInTheBlankTextConcept) {
                FillInTheBlankTextConcept fillInTheBlankTextConcept = (FillInTheBlankTextConcept) concept;
                return SentenceUtil.calculatesNumberOfTokens(fillInTheBlankTextConcept.getFillSentence())
                        <= questionGenerationSession.getConceptDetectionOptions().getMaxNumberOfTokensForFillInTheBlankSentences();
            }

            return true;
        }
    };

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
        DetectionActivationElement detectionActivationElement = new DetectionActivationElement.DetectionActivationElementBuilder()
                .withActivationLabel(messages.getDetectionViewAccordionActivationOptiongroupFillsentencesLabel())
                .withIsActivatedPerDefault(false)
                .withTooltip(messages.getDetectionViewAccordionActivationOptiongroupFillsentencesTooltip())
                .withStartedLogEntry(messages.getDetectionViewAccordionDetectionChainLogChainFillsentencesStarted())
                .withFinishedLogEntry(messages.getDetectionViewAccordionDetectionChainLogChainFillsentencesFinished())
                .withAlgorithm(fillTextConceptDetectionAlgorithm)
                .build();

        setDefaultProcessActivationElementState(detectionActivationElement);
        detectionActivationElementList.add(detectionActivationElement);
    }

    private void addCardinalitySentencesDetectionActivationElement(List<DetectionActivationElement> detectionActivationElementList) {
        DetectionActivationElement detectionActivationElement = new DetectionActivationElement.DetectionActivationElementBuilder()
                .withActivationLabel(messages.getDetectionViewAccordionActivationOptiongroupCardinalitySentencesLabel())
                .withIsActivatedPerDefault(false)
                .withTooltip(messages.getDetectionViewAccordionActivationOptiongroupCardinalitySentencesTooltip())
                .withStartedLogEntry(messages.getDetectionViewAccordionDetectionChainLogChainCardinalitySentencesStarted())
                .withFinishedLogEntry(messages.getDetectionViewAccordionDetectionChainLogChainCardinalitySentencesFinished())
                .withAlgorithm(cardinalRelationConceptDetectionAlgorithm)
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
        initProcessedLearningContentsMap();

        addLogEntryToView(messages.getDetectionViewAccordionDetectionChainLogChainStartedStarted());

        for (DetectionActivationElement detectionActivationElement: getUsersActivatedDetectionElements()) {
            triggerProcess(detectionActivationElement);
        }

        addLogEntryToView(messages.getDetectionViewAccordionDetectionChainFinishedLabel());
        updateProcessedLearningContents();
    }

    private void initProcessedLearningContentsMap() {
        questionGenerationSession.resetDetectedConceptsContentsMap();
    }

    private void updateProcessedLearningContents() {
        if (questionGenerationSession.getDetectedConceptsContentsMap().size() == 0) {
            questionGenerationSession.setStatus(QuestionGenerationSession.Status.PREPROCESSES_DONE);
        }

        detectionView.showProcessedLearningContents(questionGenerationSession.getDetectedConceptsContentsMap());
    }

    private void triggerProcess(DetectionActivationElement detectionActivationElement) {
        addLogEntryToView(detectionActivationElement.getStartedLogEntry());

        executeProcessForAllNlpTaggedLearningContents(detectionActivationElement);

        addLogEntryToView(detectionActivationElement.getFinishedLogEntry());
    }

    private void executeProcessForAllNlpTaggedLearningContents(DetectionActivationElement detectionActivationElement) {
        Collection<LearningContent> annotatedLearningContents = Collections2.filter(questionGenerationSession.getCourse().getLearningContents(), FILTER_DELETED_ANNOTATED_TEXTS_PREDICATE);

        for (LearningContent learningContent: annotatedLearningContents) {
            LOGGER.info("executeProcessForAllNlpTaggedLearningContents(): will run detection algorithm on the given learning content {}", learningContent.getTitle());
            List<Concept> concepts = detectionActivationElement.getProcessAlgorithm().execute(learningContent, questionGenerationSession.getConceptDetectionOptions());
            List<Concept> reducedConcepts = ListUtil.reduceListToMaximumOfElements(concepts, getUserSettingForMaximumNumberOfElements(detectionActivationElement));

            if (detectionActivationElement.getProcessAlgorithm() instanceof FillInTheBlankConceptDetection) {
                reducedConcepts = filterSentencesWithMoreTokens(reducedConcepts, questionGenerationSession.getConceptDetectionOptions().getMaxNumberOfTokensForFillInTheBlankSentences());
            }

            for (Concept detectedConcept: reducedConcepts) {
                putLearningContentToConceptsMap(learningContent, detectedConcept);
            }
        }
    }

    private List<Concept> filterSentencesWithMoreTokens(List<Concept> reducedConcepts, int maxNumberOfTokensForFillInTheBlankSentences) {
        return Lists.newArrayList(Collections2.filter(reducedConcepts, FILTER_FILL_TEXTS_WITH_MORE_THAN_ALLOWED));
    }

    private int getUserSettingForMaximumNumberOfElements(DetectionActivationElement detectionActivationElement) {
        if (detectionActivationElement.getProcessAlgorithm() instanceof FillInTheBlankConceptDetection) {
            return questionGenerationSession.getConceptDetectionOptions().getNumberOfFilltextQuestions();
        } else if (detectionActivationElement.getProcessAlgorithm() instanceof CardinalRelationConceptDetection) {
            return questionGenerationSession.getConceptDetectionOptions().getNumberOfCardinalityQuestions();
        } else {
            return DetectionOptions.UNLIMITED;
        }
    }

    private void putLearningContentToConceptsMap(LearningContent learningContent, Concept detectedConcept) {
        if (!questionGenerationSession.getDetectedConceptsContentsMap().containsKey(learningContent)) {
            questionGenerationSession.getDetectedConceptsContentsMap().put(learningContent, new ArrayList<Concept>());
        }

        questionGenerationSession.getDetectedConceptsContentsMap().get(learningContent).add(detectedConcept);
    }

    @Override
    public void onFinishedLearningContentSelected(final LearningContent learningContent) {
        LOGGER.info("onFinishedLearningContentSelected(): learning content {} was selected.", learningContent.getTitle());

        if (!questionGenerationSession.getDetectedConceptsContentsMap().containsKey(learningContent)) {
            LOGGER.error("onFinishedLearningContentSelected(): learning content {} is not contained in detected concepts map.", learningContent.getTitle());
            Notification.show(
                    messages.getDetectionViewAccordionDetectionChainEditErrorTitle(),
                    messages.getDetectionViewAccordionDetectionChainEditErrorMessage(),
                    Notification.Type.ERROR_MESSAGE
            );
        } else {
            LOGGER.info("onFinishedLearningContentSelected(): delegating to edit concepts presenter");
            QuestionGeneratorPortletVaadinUi.getCurrentPortletVaadinUi().getDetectionEditConceptsPresenter()
                    .displayDetectedConcepts(learningContent, getConceptsForLearningContent(learningContent));
        }
    }

    private List<Concept> getConceptsForLearningContent(LearningContent learningContent) {
        return questionGenerationSession.getDetectedConceptsContentsMap().get(learningContent);
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

        getNavigator().navigateTo(PreprocessesViewImpl.VIEW_NAME);
    }

    @Override
    public void onNextButtonClicked() {
        LOGGER.info("onNextButtonClicked()");

        questionGenerationSession.setStatus(QuestionGenerationSession.Status.DETECTION_DONE);
        getNavigator().navigateTo(QuestionGenerationViewImpl.VIEW_NAME);
    }

    private DetectionActivationElement[] getUsersActivatedDetectionElements() {
        List<DetectionActivationElement> activatedProcessActivationElements = new ArrayList<>();

        for (DetectionActivationElement detectionActivationElement: this.detectionActivationElementList) {
            if (detectionActivationElement.getDetectionActivationElementState().getActivationOptionGroupItem()
                    .equals(DetectionActivationElement.ActivationOptionGroupItem.YES)) {
                activatedProcessActivationElements.add(detectionActivationElement);
            }
        }

        return activatedProcessActivationElements.toArray(new DetectionActivationElement[activatedProcessActivationElements.size()]);
    }

    private void addLogEntryToView(final String logMessage) {
        detectionView.addDetectionChainLogEntry(timeUtil.buildTimeEntryForUserLogs(), logMessage);
   }
}

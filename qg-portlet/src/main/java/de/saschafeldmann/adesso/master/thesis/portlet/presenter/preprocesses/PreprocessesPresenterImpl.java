package de.saschafeldmann.adesso.master.thesis.portlet.presenter.preprocesses;

import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.portlet.model.QuestionGenerationSession;
import de.saschafeldmann.adesso.master.thesis.portlet.model.preprocesses.ProcessActivationElement;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.AbstractStepPresenter;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.i18n.Messages;
import de.saschafeldmann.adesso.master.thesis.portlet.view.preprocesses.PreprocessesView;
import de.saschafeldmann.adesso.master.thesis.portlet.view.preprocesses.PreprocessesViewListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  04.06.2016
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
 * Implementation of the {@link PreprocessesPresenter}
 */
@Component
@Scope("prototype")
public class PreprocessesPresenterImpl extends AbstractStepPresenter implements PreprocessesPresenter, PreprocessesViewListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(PreprocessesPresenterImpl.class);
    private PreprocessesView preprocessesView;
    @Autowired
    private Messages messages;
    private ArrayList<ProcessActivationElement> processActivationElements;

    /**
     * Creates a new preprocesses presenter.
     * @param preprocessesView
     */
    @Autowired
    public PreprocessesPresenterImpl(
            PreprocessesView preprocessesView
    ) {
        this.preprocessesView = preprocessesView;
    }

    @Override
    public PreprocessesView initializeView() {
        this.preprocessesView.setMenuListener(this);
        this.preprocessesView.setCurrentSessionStatus(questionGenerationSession.getStatus());
        this.preprocessesView.setViewListener(this);

        loadProcessActivationElements();

        this.preprocessesView.reset();
        return this.preprocessesView;
    }

    private void loadProcessActivationElements() {
        this.processActivationElements = new ArrayList<ProcessActivationElement>();

        addLanguageDetectionActivationElement(processActivationElements);
        addPartOfSpeechActivationElement(processActivationElements);
        addNamedEntityRecognitionActivationElement(processActivationElements);

        this.preprocessesView.addProcessActivationElements(processActivationElements);
    }

    private void addLanguageDetectionActivationElement(List<ProcessActivationElement> processActivationElements) {
        ProcessActivationElement processActivationElement = new ProcessActivationElement.ProcessActivationElementBuilder()
                .withActivationLabel(messages.getPreproccesesViewAccordionActivationOptiongroupLanguageDetectionLabel())
                .withIsActivatedPerDefault(false)
                .withTooltip(messages.getPreproccesesViewAccordionActivationOptiongroupLanguageDetectionTooltip())
                .withStartedLogEntry(messages.getPreproccesesViewAccordionProcesschainLogLanguageDetectionStarted())
                .withFinishedLogEntry(messages.getPreproccesesViewAccordionProcesschainLogLanguageDetectionFinished())
                .build();

        setDefaultProcessActivationElementState(processActivationElement);
        processActivationElements.add(processActivationElement);
    }

    private void addPartOfSpeechActivationElement(List<ProcessActivationElement> processActivationElements) {
        ProcessActivationElement processActivationElement = new ProcessActivationElement.ProcessActivationElementBuilder()
                .withActivationLabel(messages.getPreproccesesViewAccordionActivationOptiongroupPartOfSpeechDetectionLabel())
                .withIsActivatedPerDefault(false)
                .withTooltip(messages.getPreproccesesViewAccordionActivationOptiongroupPartOfSpeechDetectionTooltip())
                .withStartedLogEntry(messages.getPreproccesesViewAccordionProcesschainLogPartofspeechDetectionStarted())
                .withFinishedLogEntry(messages.getPreproccesesViewAccordionProcesschainLogPartofspeechDetectionFinished())
                .build();

        setDefaultProcessActivationElementState(processActivationElement);
        processActivationElements.add(processActivationElement);
    }

    private void addNamedEntityRecognitionActivationElement(List<ProcessActivationElement> processActivationElements) {
        ProcessActivationElement processActivationElement = new ProcessActivationElement.ProcessActivationElementBuilder()
                .withActivationLabel(messages.getPreproccesesViewAccordionActivationOptiongroupNamedEntitiesDetectionLabel())
                .withIsActivatedPerDefault(false)
                .withTooltip(messages.getPreproccesesViewAccordionActivationOptiongroupNamedEntitiesDetectionTooltip())
                .withStartedLogEntry(messages.getPreproccesesViewAccordionProcesschainLogNamedEntityRecognitionStarted())
                .withFinishedLogEntry(messages.getPreproccesesViewAccordionProcesschainLogNamedEntityRecognitionFinished())
                .build();

        setDefaultProcessActivationElementState(processActivationElement);
        processActivationElements.add(processActivationElement);
    }

    private void setDefaultProcessActivationElementState(ProcessActivationElement processActivationElement) {
        if (processActivationElement.isActivatedPerDefault()) {
            processActivationElement.setProcessActivationElementState(processActivationElement.getProcessActivationElementStateActivated());
        } else {
            processActivationElement.setProcessActivationElementState(processActivationElement.getProcessActivationElementStateDeactivated());
        }
    }


    @Override
    public void onProcessChainStartButtonClick() {
        LOGGER.info("onProcessChainStartButtonClick");

        preprocessesView.addProcessChainLogEntry(messages.getPreproccesesViewAccordionProcesschainLogChainStarted());

        for (ProcessActivationElement processActivationElement: getUsersActivatedProcesses()) {
            triggerProcess(processActivationElement);
        }

        preprocessesView.addProcessChainLogEntry(messages.getPreproccesesViewAccordionProcesschainLogChainFinished());

        // TODO update processed learning contents
        updateProcessedLearningContents();
    }

    private void updateProcessedLearningContents() {
        // TODO maybe filter the ones with errors
       preprocessesView.showProcessedLearningContents(questionGenerationSession.getCourse().getLearningContents());
    }

    private ProcessActivationElement[] getUsersActivatedProcesses() {
        List<ProcessActivationElement> activatedProcessActivationElements = new ArrayList<ProcessActivationElement>();

        for (ProcessActivationElement processActivationElement: this.processActivationElements) {
            if (processActivationElement.getProcessActivationElementState().getActivationOptionGroupItem()
                    .equals(ProcessActivationElement.ActivationOptionGroupItem.YES)) {
                activatedProcessActivationElements.add(processActivationElement);
            }
        }

        return activatedProcessActivationElements.toArray(new ProcessActivationElement[activatedProcessActivationElements.size()]);
    }

    private void triggerProcess(ProcessActivationElement processActivationElement) {
        preprocessesView.addProcessChainLogEntry(processActivationElement.getStartedLogEntry());

        // TODO call service facade in qg-preprocesses module

        preprocessesView.addProcessChainLogEntry(processActivationElement.getFinishedLogEntry());
    }

    @Override
    public void onEditLearningContentClick(LearningContent learningContentToBeEdited, String textareaInput) {
        LOGGER.info("onEditLearningContentClick(): will edit {}", learningContentToBeEdited.getTitle());
    }

    @Override
    public void onDeleteLearningContentClick(LearningContent selectedContent) {
        LOGGER.info("onDeleteLearningContentClick(): will delete {}", selectedContent.getTitle());
    }

    @Override
    public void onActivationElementChange(ProcessActivationElement.ProcessActivationElementState processActivationElementState) {
        LOGGER.info("onActivationElementChange(): option for {} was changed to {}",
                processActivationElementState.getParentProcessActivationElement().getActivationLabel(),
                processActivationElementState.getActivationOptionGroupItem()
                );


        processActivationElementState.getParentProcessActivationElement().setProcessActivationElementState(processActivationElementState);
        preprocessesView.showProcessActivationSuccessMessage();
    }

    @Override
    public void onViewFocus() {
        preprocessesView.setCurrentSessionStatus(questionGenerationSession.getStatus());
        preprocessesView.reset();
    }
}

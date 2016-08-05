package de.saschafeldmann.adesso.master.thesis.portlet.presenter.preprocesses;

import com.google.common.base.Joiner;
import com.google.common.collect.Collections2;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Language;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.portlet.model.LanguageWrapper;
import de.saschafeldmann.adesso.master.thesis.portlet.model.QuestionGenerationSession;
import de.saschafeldmann.adesso.master.thesis.portlet.model.preprocesses.ProcessActivationElement;
import de.saschafeldmann.adesso.master.thesis.portlet.model.preprocesses.ProcessActivationStateChangeListener;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.AbstractStepPresenter;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.i18n.Messages;
import de.saschafeldmann.adesso.master.thesis.portlet.view.ViewWithMenu;
import de.saschafeldmann.adesso.master.thesis.portlet.view.course.contents.CourseContentsViewImpl;
import de.saschafeldmann.adesso.master.thesis.portlet.view.detection.DetectionViewImpl;
import de.saschafeldmann.adesso.master.thesis.portlet.view.preprocesses.PreprocessesView;
import de.saschafeldmann.adesso.master.thesis.portlet.view.preprocesses.PreprocessesViewListener;
import de.saschafeldmann.adesso.master.thesis.preprocesses.algorithm.language.LanguageDetectionAlgorithm;
import de.saschafeldmann.adesso.master.thesis.preprocesses.algorithm.language.UndeterminableLanguageException;
import de.saschafeldmann.adesso.master.thesis.preprocesses.model.PreprocessingOptions;
import de.saschafeldmann.adesso.master.thesis.preprocesses.algorithm.nlp.NlpException;
import de.saschafeldmann.adesso.master.thesis.preprocesses.algorithm.nlp.NlpPreprocessingAlgorithm;
import de.saschafeldmann.adesso.master.thesis.util.linguistic.SentenceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.*;

import static de.saschafeldmann.adesso.master.thesis.portlet.util.FilterUtil.FILTER_DELETED_ANNOTATED_TEXTS_PREDICATE;
import static de.saschafeldmann.adesso.master.thesis.portlet.util.FilterUtil.FILTER_NOT_DELETED_ANNOTATED_TEXTS_PREDICATE;

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
    private final PreprocessesView preprocessesView;
    @Autowired
    private Messages messages;
    private ArrayList<ProcessActivationElement> processActivationElements;

    @Autowired
    private LanguageDetectionAlgorithm languageDetectionAlgorithmAlgorithm;
    @Autowired
    private NlpPreprocessingAlgorithm nlpPreprocessingAlgorithm;
    @Autowired
    private PreprocessingOptions preprocessingOptions;

    private static final Joiner DETECTED_LANGUAGES_JOINER = Joiner.on(", ").skipNulls();

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

        initPreprocessAlgorithms();
        loadProcessActivationElements();

        this.preprocessesView.reset();
        return this.preprocessesView;
    }

    private void initPreprocessAlgorithms() {
        preprocessingOptions.setActivateNamedEntityRecognition(false);
        preprocessingOptions.setActivatePartOfSpeechTagging(false);
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
                .withAlgorithm(languageDetectionAlgorithmAlgorithm)
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
                .withAlgorithm(nlpPreprocessingAlgorithm)
                .withTooltip(messages.getPreproccesesViewAccordionActivationOptiongroupPartOfSpeechDetectionTooltip())
                .withStartedLogEntry(messages.getPreproccesesViewAccordionProcesschainLogPartofspeechDetectionStarted())
                .withFinishedLogEntry(messages.getPreproccesesViewAccordionProcesschainLogPartofspeechDetectionFinished())
                .withStateChangeListener(new ProcessActivationStateChangeListener() {
                    @Override
                    public void onStateChanged(ProcessActivationElement changed) {
                        if (changed.getProcessActivationElementState().equals(changed.getProcessActivationElementStateActivated())) {
                            LOGGER.info("stateChangeListener: activating part of speech tagging...");
                            preprocessingOptions.setActivatePartOfSpeechTagging(true);
                        } else {
                            LOGGER.info("stateChangeListener: disabling part of speech tagging...");
                            preprocessingOptions.setActivatePartOfSpeechTagging(false);
                        }
                    }
                })
                .build();

        setDefaultProcessActivationElementState(processActivationElement);
        processActivationElements.add(processActivationElement);
    }

    private void addNamedEntityRecognitionActivationElement(List<ProcessActivationElement> processActivationElements) {
        ProcessActivationElement processActivationElement = new ProcessActivationElement.ProcessActivationElementBuilder()
                .withActivationLabel(messages.getPreproccesesViewAccordionActivationOptiongroupNamedEntitiesDetectionLabel())
                .withIsActivatedPerDefault(false)
                .withAlgorithm(nlpPreprocessingAlgorithm)
                .withTooltip(messages.getPreproccesesViewAccordionActivationOptiongroupNamedEntitiesDetectionTooltip())
                .withStartedLogEntry(messages.getPreproccesesViewAccordionProcesschainLogNamedEntityRecognitionStarted())
                .withFinishedLogEntry(messages.getPreproccesesViewAccordionProcesschainLogNamedEntityRecognitionFinished())
                .withStateChangeListener(new ProcessActivationStateChangeListener() {
                    @Override
                    public void onStateChanged(ProcessActivationElement changed) {
                        if (changed.getProcessActivationElementState().equals(changed.getProcessActivationElementStateActivated())) {
                            LOGGER.info("stateChangeListener: activating named entity recognition...");
                            preprocessingOptions.setActivateNamedEntityRecognition(true);
                        } else {
                            LOGGER.info("stateChangeListener: disabling named entity recognition...");
                            preprocessingOptions.setActivateNamedEntityRecognition(false);
                        }
                    }
                })
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

        addLogEntryToView(messages.getPreproccesesViewAccordionProcesschainLogChainStarted());

        for (ProcessActivationElement processActivationElement: getUsersActivatedProcesses()) {
            triggerProcess(processActivationElement);
        }

        addLogEntryToView(messages.getPreproccesesViewAccordionProcesschainLogChainFinished());

        updateProcessedLearningContents();
    }

    private void addLogEntryToView(final String logMessage) {
        preprocessesView.addProcessChainLogEntry(timeUtil.buildTimeEntryForUserLogs(), logMessage);
    }

    private void updateProcessedLearningContents() {
        Collection<LearningContent> notDeletedLearningContents = Collections2.filter(questionGenerationSession.getCourse().getLearningContents(), FILTER_DELETED_ANNOTATED_TEXTS_PREDICATE);

        if (notDeletedLearningContents.size() == 0) {
            questionGenerationSession.setStatus(QuestionGenerationSession.Status.CONTENTS_ADDED);
        }

        preprocessesView.showProcessedLearningContents(notDeletedLearningContents);
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
        addLogEntryToView(processActivationElement.getStartedLogEntry());

        executeProcessForAllLearningContents(processActivationElement);

        if (processActivationElement.getProcessAlgorithm().equals(this.languageDetectionAlgorithmAlgorithm)) {
            addLogEntryToView(String.format(processActivationElement.getFinishedLogEntry(), getDeterminedLanguages()));
        } else {
            addLogEntryToView(processActivationElement.getFinishedLogEntry());
        }

    }

    private void executeProcessForAllLearningContents(ProcessActivationElement processActivationElement) {
        for (LearningContent learningContent: questionGenerationSession.getCourse().getLearningContents()) {
            try {
                processActivationElement.getProcessAlgorithm().execute(learningContent, preprocessingOptions);
            } catch (UndeterminableLanguageException undeterminalLanguageException) {
                LOGGER.error("executeProcessForAllLearningContents(): language detection failed due to exception\n {}. Will fallback to course's primary language {}.",
                        undeterminalLanguageException, questionGenerationSession.getCourse().getPrimaryLanguage());
                // language detection: the language could not be detected
                addLogEntryToView(messages.getPreproccesesViewAccordionProcesschainLogLanguageDetectionFailed(learningContent.getTitle(), getLanguageLabel(questionGenerationSession.getCourse().getPrimaryLanguage())));
                // use the course's primary language instead
                learningContent.setFallbackLanguage(questionGenerationSession.getCourse());
            } catch (NlpException nlpException) {
                LOGGER.error("executeProcessForAllLearningContents(): natural language processing failed due to exception\n {}", nlpException);
                // NER or POS didn't work
                addLogEntryToView(messages.getPreprocessesViewAccordionProcesschainLogNlpFailed());
            }
        }
    }

    private String getLanguageLabel(final Language primaryLanguage) {
        return LanguageWrapper.forLanguage(primaryLanguage).toString();
    }

    private String getDeterminedLanguages() {
        Set<Language> detectedLanguages = getDeterminedLanguagesSet();
        return buildDeterminedLanguagesString(detectedLanguages);
    }

    private String buildDeterminedLanguagesString(Set<Language> detectedLanguages) {
        return DETECTED_LANGUAGES_JOINER.join(LanguageWrapper.forLanguages(detectedLanguages));
    }

    private Set<Language> getDeterminedLanguagesSet() {
        Set<Language> detectedLanguages = new HashSet<>();

        for (LearningContent learningContent: questionGenerationSession.getCourse().getLearningContents()) {
            if (null != learningContent.getDeterminedLanguage()) {
                detectedLanguages.add(learningContent.getDeterminedLanguage());
            }
        }

        return detectedLanguages;
    }

    @Override
    public void onEditLearningContentClick(LearningContent learningContentToBeEdited, String partOfSpeechInput, String namedEntitiesInput) {
        LOGGER.info("onEditLearningContentClick(): will edit {}", learningContentToBeEdited.getTitle());

        learningContentToBeEdited.setPartOfSpeechAnnotatedText(SentenceUtil.buildListOfSentencesForString(partOfSpeechInput));
        learningContentToBeEdited.setNamedEntityAnnotatedText(SentenceUtil.buildListOfSentencesForString(namedEntitiesInput));
    }

    @Override
    public void onEditLearningContentLanguageClick(LearningContent selectedContent, Language newLanguage) {
        LOGGER.info("onEditLearningContentLanguageClick(): will edit {} and set language {}", selectedContent.getTitle(), newLanguage);

        selectedContent.setDeterminedLanguage(newLanguage);
    }

    @Override
    public void onBackButtonClicked() {
        LOGGER.info("onBackButtonClicked()");

        getNavigator().navigateTo(CourseContentsViewImpl.VIEW_NAME);
    }

    @Override
    public void onNextButtonClicked() {
        LOGGER.info("onNextButtonClicked()");

        questionGenerationSession.setStatus(QuestionGenerationSession.Status.PREPROCESSES_DONE);
        
        getNavigator().navigateTo(DetectionViewImpl.VIEW_NAME);
    }

    @Override
    public void onDeleteLearningContentClick(LearningContent selectedContent) {
        LOGGER.info("onDeleteLearningContentClick(): will delete {}", selectedContent.getTitle());

        selectedContent.deleteAnnotatedText();
        updateAnnotatedTexts();
    }

    private void updateAnnotatedTexts() {
        updateProcessedLearningContents();
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

        resetDeletedLearningContents();
    }

    /**
     * Resets the {@link LearningContent} instances deleted by the user so that he/she can reprocess them after content changes.
     */
    private void resetDeletedLearningContents() {
        Collection<LearningContent> deletedLearningContents = Collections2.filter(questionGenerationSession.getCourse().getLearningContents(), FILTER_NOT_DELETED_ANNOTATED_TEXTS_PREDICATE);

        for (LearningContent deletedLearningContent: deletedLearningContents) {
            deletedLearningContent.resetAnnotatedText();
        }
    }

    @Override
    public ViewWithMenu getView() {
        return preprocessesView;
    }
}

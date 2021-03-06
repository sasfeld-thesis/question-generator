package de.saschafeldmann.adesso.master.thesis.portlet.presenter.generation;

import de.saschafeldmann.adesso.master.thesis.elearningexport.csv.CsvExportServiceImpl;
import de.saschafeldmann.adesso.master.thesis.detection.model.CardinalRelationConcept;
import de.saschafeldmann.adesso.master.thesis.detection.model.FillInTheBlankTextConcept;
import de.saschafeldmann.adesso.master.thesis.detection.model.api.Concept;
import de.saschafeldmann.adesso.master.thesis.elearningexport.moodle.MoodleXmlExportServiceImpl;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.generation.LinguisticRealiser;
import de.saschafeldmann.adesso.master.thesis.generation.model.TestQuestion;
import de.saschafeldmann.adesso.master.thesis.portlet.QuestionGeneratorPortletVaadinUi;
import de.saschafeldmann.adesso.master.thesis.portlet.model.QuestionGenerationSession;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.AbstractStepPresenter;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.generation.edit.QuestionGenerationEditQuestionListener;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.QuestionGeneratorProperties;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.i18n.Messages;
import de.saschafeldmann.adesso.master.thesis.portlet.view.ViewWithMenu;
import de.saschafeldmann.adesso.master.thesis.portlet.view.detection.DetectionViewImpl;
import de.saschafeldmann.adesso.master.thesis.portlet.view.generation.QuestionGenerationView;
import de.saschafeldmann.adesso.master.thesis.portlet.view.generation.QuestionGenerationViewListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  04.07.2016
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
 * The implementation of a {@link QuestionGenerationPresenter}
 */
@Component
@Scope("prototype")
public class QuestionGenerationPresenterImpl extends AbstractStepPresenter implements QuestionGenerationPresenter, QuestionGenerationViewListener, QuestionGenerationEditQuestionListener {
    private static final String CSV_EXPORT_FILENAME_TEMPLATES = "generated_test_questions_%s.csv";
    private static final String MOODLE_XML_EXPORT_FILENAME_TEMPLATES = "moodle_xml_generated_test_questions_%s.xml";
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionGenerationPresenterImpl.class);

    private final QuestionGenerationView questionGenerationView;

    @Autowired
    private LinguisticRealiser linguisticRealiser;
    @Autowired
    private Messages messages;
    @Autowired
    private CsvExportServiceImpl csvExportService;
    @Autowired
    private MoodleXmlExportServiceImpl moodleXmlExportService;
    @Autowired
    private QuestionGeneratorProperties questionGeneratorProperties;

    /**
     * Creates a new view.
     *
     * @param view
     */
    @Autowired
    public QuestionGenerationPresenterImpl(final QuestionGenerationView view) {
        this.questionGenerationView = view;
    }

    @PostConstruct
    private void initialize() {
        initializeCsvExportService();
        initializeMoodleXmlExportService();
    }

    private void initializeCsvExportService() {
        csvExportService.setFileDirectory(questionGeneratorProperties.getFileTempFolder());

        csvExportService.setLearningContentTitleLabel(messages.getQuestionGenerationViewExportCsvHeaderColumnLearningContent());
        csvExportService.setGeneratedQuestionTitleLabel(messages.getQuestionGenerationViewExportCsvHeaderColumnTestquestion());
        csvExportService.setMultipleChoiceModeLabel(messages.getQuestionGenerationViewExportCsvHeaderColumnMultipleChoice());
        csvExportService.setCorrectAnswerLabel(messages.getQuestionGenerationViewExportCsvHeaderColumnCorrectAnswer());
        csvExportService.setCorrectAnswersLabel(messages.getQuestionGenerationViewExportCsvHeaderColumnAlternativeCorrectAnswers());
        csvExportService.setWrongAnswersLabel(messages.getQuestionGenerationViewExportCsvHeaderColumnAlternativeWrongAnswerst());
        csvExportService.setOriginalSentenceLabel(messages.getQuestionGenerationViewExportCsvHeaderColumnOriginalSentence());
        csvExportService.setIsMultipleChoiceLabel(messages.getQuestionGenerationViewExportCsvHeaderRowMultipleChoiceYes());
        csvExportService.setIsNoMultipleChoiceLabel(messages.getQuestionGenerationViewExportCsvHeaderRowMultipleChoiceNo());
    }


    private void initializeMoodleXmlExportService() {
        moodleXmlExportService.setFileDirectory(questionGeneratorProperties.getFileTempFolder());

        moodleXmlExportService.setCorrectAnswerText(messages.getQuestionGenerationViewExportMoodleXmlCorrectAnswerText());
        moodleXmlExportService.setWrongAnswerText(messages.getQuestionGenerationViewExportMoodleXmlWrongAnswerText());
    }

    @Override
    public QuestionGenerationView initializeView() {
        this.questionGenerationView.setViewListener(this);
        this.questionGenerationView.setMenuListener(this);
        this.questionGenerationView.setCurrentSessionStatus(questionGenerationSession.getStatus());

        this.questionGenerationView.reset();
        return this.questionGenerationView;
    }

    @Override
    public ViewWithMenu getView() {
        return this.questionGenerationView;
    }

    @Override
    public void onStartQuestionGenerationButtonClicked() {
        LOGGER.info("onStartQuestionGenerationButtonClicked()");

        resetStatisticsInformation();
        generateTestQuestions();
        addStatisticsLogEntryIfConfigured();

        generateExportFiles();
        refreshGeneratedQuestionsLearningContents();
    }

    private void generateExportFiles() {
        triggerCsvExportGeneration();
        triggerMoodleXmlExportGeneration();
    }

    private void resetStatisticsInformation() {
        questionGenerationSession.getCourse().getStatistics().resetQuestionGenerationtatistics();
    }

    private void addStatisticsLogEntryIfConfigured() {
        if (questionGeneratorProperties.showStatisticInformation()) {
            questionGenerationView.showStatistics(
                    questionGenerationSession.getCourse().getStatistics().getNumberOfDetectedConcepts(),
                    questionGenerationSession.getCourse().getStatistics().getQuestionGenerationRuntime()
            );
        }
    }

    private void generateTestQuestions() {
        questionGenerationSession.resetGeneratedQuestionsContentsMap();

        for (LearningContent learningContent : questionGenerationSession.getDetectedConceptsContentsMap().keySet()) {
            List<TestQuestion> testQuestionsList = new ArrayList<>();
            questionGenerationSession.getGeneratedQuestionsContentsMap()
                    .put(learningContent, testQuestionsList);

            for (Concept concept : questionGenerationSession.getDetectedConceptsContentsMap().get(learningContent)) {
                TestQuestion testQuestion = linguisticRealiser.generateQuestion(concept);
                testQuestion.setLabel(buildTestQuestionLabel(testQuestion));
                testQuestionsList.add(
                        testQuestion
                );
            }
        }
    }

    private String buildTestQuestionLabel(TestQuestion testQuestion) {
        String label = "";

        // differentiate the label
        if (testQuestion.getSourceConcept() instanceof FillInTheBlankTextConcept) {
            label += messages.getQuestionGenerationViewFinishedQuestionsFillsentencesPrefix();
        } else if (testQuestion.getSourceConcept() instanceof CardinalRelationConcept) {
            label += messages.getQuestionGenerationViewFinishedQuestionsCardinalSentencesPrefix();
        }

        label += " - " + testQuestion.getQuestion();

        return label;
    }

    @Override
    public void onCompletedLearningContentSelected(final LearningContent learningContent) {
        LOGGER.info("onCompletedLearningContentSelected(): learning content {}", learningContent);

        questionGenerationView.displayGeneratedQuestions(
                questionGenerationSession.getGeneratedQuestionsContentsMap().get(learningContent)
        );
    }

    @Override
    public void onGeneratedQuestionSelected(TestQuestion testQuestion) {
        LOGGER.info("onGeneratedQuestionSelected(): test question {}", testQuestion);

        QuestionGeneratorPortletVaadinUi.getCurrentPortletVaadinUi().getQuestionGenerationEditQuestionPresenter().displayViewForTestQuestion(testQuestion);
    }

    @Override
    public void onBackButtonClicked() {
        LOGGER.info("onBackButtonClicked()");

        getNavigator().navigateTo(DetectionViewImpl.VIEW_NAME);
    }

    private void triggerCsvExportGeneration() {
        try {
            LOGGER.debug("triggerCsvExportGeneration(): starting CSV export...");
            csvExportService.setFileName(generateExportFileName(CSV_EXPORT_FILENAME_TEMPLATES));
            File exportFile = csvExportService.exportGeneratedQuestionsToFile(questionGenerationSession.getGeneratedQuestionsContentsMap());
            questionGenerationView.setCsvExportFile(exportFile);
        } catch (Exception e) {
            LOGGER.error("triggerCsvExportGeneration(): could not generate CSV due to {}", e);
        }
    }

    private String generateExportFileName(String csvExportFilenameTemplates) {
        String currentTimestamp = String.valueOf(System.currentTimeMillis());
        return String.format(csvExportFilenameTemplates, questionGenerationSession.getCourse().getTitle() + currentTimestamp);
    }

    private void triggerMoodleXmlExportGeneration() {
        try {
            LOGGER.debug("triggerMoodleXmlExportGeneration(): starting Moodle XML export...");
            moodleXmlExportService.setFileName(generateExportFileName(MOODLE_XML_EXPORT_FILENAME_TEMPLATES));
            File exportFile = moodleXmlExportService.exportGeneratedQuestionsToFile(questionGenerationSession.getGeneratedQuestionsContentsMap());
            questionGenerationView.setMoodleXmlExportFile(exportFile);
        } catch (Exception e) {
            LOGGER.error("triggerMoodleXmlExportGeneration(): could not generate Moodle XML file due to {}", e);
        }
    }

    @Override
    public void onViewFocus() {
        questionGenerationView.setCurrentSessionStatus(questionGenerationSession.getStatus());
        questionGenerationView.reset();

        refreshGeneratedQuestionsLearningContents();
    }

    @Override
    public void onEditQuestionDialogClosed(TestQuestion testQuestion) {
        LOGGER.info("onEditQuestionDialogClosed(): refreshing generated questions for question {}", testQuestion);

        // rebuild test question label
        testQuestion.setLabel(buildTestQuestionLabel(testQuestion));
        refreshGeneratedQuestion(testQuestion);

        // refresh export files
        generateExportFiles();
    }

    private void refreshGeneratedQuestion(TestQuestion testQuestion) {
        onCompletedLearningContentSelected(testQuestion.getSourceConcept().getLearningContent());
    }

    private void refreshGeneratedQuestionsLearningContents() {
        List<LearningContent> learningContents = toList(questionGenerationSession.getGeneratedQuestionsContentsMap().keySet());
        questionGenerationView.displayCompletedLearningContents(
                learningContents
        );

        if (learningContents.size() > 0) {
            questionGenerationSession.setStatus(QuestionGenerationSession.Status.QUESTIONS_GENERATED);
        } else {
            questionGenerationSession.setStatus(QuestionGenerationSession.Status.DETECTION_DONE);
        }
    }

    private List<LearningContent> toList(Set<LearningContent> learningContents) {
        List<LearningContent> learningContentList = new ArrayList<>();

        learningContentList.addAll(learningContents);

        return learningContentList;
    }
}

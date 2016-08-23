package de.saschafeldmann.adesso.master.thesis.portlet.view.generation;

import com.vaadin.data.Property;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.*;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Notification;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.generation.model.TestQuestion;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.i18n.Messages;
import de.saschafeldmann.adesso.master.thesis.portlet.view.AbstractStepView;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.*;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.Button;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.HorizontalLayout;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.Label;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.ListSelect;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.VerticalLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.List;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  04.07.2016
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
 * Implementation of the {@link QuestionGenerationView}
 */
@Component
@Scope("prototype")
public class QuestionGenerationViewImpl extends AbstractStepView implements QuestionGenerationView {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionGenerationViewImpl.class);

    public static final String VIEW_NAME = "QuestionGenerationView";
    public static final String CSS_STYLE_NAME_HORICONTAL_LAYOUT = "question-generation-horicontal-layout";
    public static final String CSS_STYLE_NAME_EXPORT_SELECT = "question-generation-export-select";

    private QuestionGenerationViewListener viewListener;

    private final InfoBox infoBox;
    private Label introductionLabel;
    private final Button btnStartQuestionGeneration;
    private final HorizontalLayout horizontalLayout;
    private final VerticalLayout leftVerticalLayout;
    private final Label completedLearningContentsListLabel;
    private final ListSelect completedLearningContentsList;
    private final VerticalLayout rightVerticalLayout;
    private final Label completedQuestionsListLabel;
    private final ListSelect completedQuestionsList;

    private final HorizontalLayout buttonGroupLayout;
    private final Button btnPrevious;
    private final Button btnExport;
    private final ListSelect exportListSelect;
    private String defaultExportValue = messages.getQuestionGenerationViewListselectExportCsv();
    private File moodleXmlExportFile;
    private File csvExportFile;

    /**
     * Creates the new view.
     * @param messages messages
     * @param versionLabel the version label
     */

    @Autowired
    public QuestionGenerationViewImpl(Messages messages, VersionLabel versionLabel,
                                      final InfoBox infoBox,
                                      final Button btnStartQuestionGeneration,
                                      final HorizontalLayout horizontalLayout,
                                      final VerticalLayout leftVerticalLayout,
                                      final Label completedLearningContentsListLabel,
                                      final ListSelect completedLearningContentsList,
                                      final VerticalLayout rightVerticalLayout,
                                      final Label completedQuestionsListLabel,
                                      final ListSelect completedQuestionsList,
                                      final HorizontalLayout buttonGroupLayout,
                                      final Button btnPrevious,
                                      final Button btnExport,
                                      final ListSelect exportListSelect
                                      ) {
        super(messages, versionLabel);

        this.infoBox = infoBox;
        this.btnStartQuestionGeneration = btnStartQuestionGeneration;
        this.horizontalLayout = horizontalLayout;
        this.leftVerticalLayout = leftVerticalLayout;
        this.completedLearningContentsListLabel = completedLearningContentsListLabel;
        this.completedLearningContentsList = completedLearningContentsList;
        this.rightVerticalLayout = rightVerticalLayout;
        this.completedQuestionsListLabel = completedQuestionsListLabel;
        this.completedQuestionsList = completedQuestionsList;
        this.buttonGroupLayout = buttonGroupLayout;
        this.btnPrevious = btnPrevious;
        this.btnExport = btnExport;
        this.exportListSelect = exportListSelect;

        setIntroductionText();
    }

    private void setIntroductionText() {
        this.introductionLabel = new Label(messages.getQuestionGenerationViewIntroductionText(), ContentMode.HTML);
    }

    @PostConstruct
    private void initialize() {
        initializeHorizontalLayout();
        initializeExportSelect();
        registerListeners();
        disableActionsButtons();
        setStyles();

        setLabels();
    }

    private void setLabels() {
        setIntroductionText();

        this.btnStartQuestionGeneration.setCaption(messages.getQuestionGenerationViewButtonStartLabel());
        setCompletedLearningContentListLabel();
        this.btnPrevious.setCaption(messages.getButtonBackTitle());
        this.btnExport.setCaption(messages.getQuestionGenerationViewButtonExport());
        setCompletedQuestionsListLabel();
    }

    private void setCompletedQuestionsListLabel() {
        this.completedQuestionsListLabel.setCaption(messages.getQuestionGenerationViewFinishedQuestionsLabel());
    }

    private void setCompletedLearningContentListLabel() {
        this.completedLearningContentsListLabel.setCaption(messages.getQuestionGenerationViewFinishedContentsLabels());
    }

    private void initializeHorizontalLayout() {
        leftVerticalLayout.addComponent(completedLearningContentsListLabel);
        leftVerticalLayout.addComponent(completedLearningContentsList);
        horizontalLayout.addComponent(leftVerticalLayout);

        rightVerticalLayout.addComponent(completedQuestionsListLabel);
        rightVerticalLayout.addComponent(completedQuestionsList);
        horizontalLayout.addComponent(rightVerticalLayout);
    }

    private void setStyles() {
        horizontalLayout.setStyleName(CSS_STYLE_NAME_HORICONTAL_LAYOUT);
        exportListSelect.addStyleName(CSS_STYLE_NAME_EXPORT_SELECT);
    }

    private void registerListeners() {
        btnPrevious.addClickListener(new com.vaadin.ui.Button.ClickListener() {
            @Override
            public void buttonClick(com.vaadin.ui.Button.ClickEvent clickEvent) {
                viewListener.onBackButtonClicked();
            }
        });

        btnStartQuestionGeneration.addClickListener(new com.vaadin.ui.Button.ClickListener() {
            @Override
            public void buttonClick(com.vaadin.ui.Button.ClickEvent clickEvent) {
                viewListener.onStartQuestionGenerationButtonClicked();
            }
        });

        completedLearningContentsList.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                if (null != valueChangeEvent.getProperty()) {
                    if (valueChangeEvent.getProperty().getValue() instanceof LearningContent) {
                        LearningContent selectedContent = (LearningContent) valueChangeEvent.getProperty().getValue();

                        if (null != selectedContent) {
                            viewListener.onCompletedLearningContentSelected(selectedContent);
                        }
                    }
                }

                // reset selection
                completedLearningContentsList.select(completedLearningContentsList.getNullSelectionItemId());
            }
        });

        completedQuestionsList.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                if (null != valueChangeEvent.getProperty()) {
                    if (valueChangeEvent.getProperty().getValue() instanceof TestQuestion) {
                        TestQuestion testQuestion = (TestQuestion) valueChangeEvent.getProperty().getValue();

                        if (null != testQuestion) {
                            viewListener.onGeneratedQuestionSelected(testQuestion);
                        }
                    }
                }

                // reset selection
                completedQuestionsList.select(completedQuestionsList.getNullSelectionItemId());
            }
        });

        exportListSelect.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                resetExportFileDownloaderExtension();
            }
        });
    }

    private void resetExportFileDownloaderExtension() {
        removeExtensionsOfExportFileButton();

        FileDownloader exportDownloader = new FileDownloader(getExportDownloadStream());
        exportDownloader.extend(btnExport);
    }

    private void removeExtensionsOfExportFileButton() {
        try {
            for (Extension extension : btnExport.getExtensions()) {
                btnExport.removeExtension(extension);
            }
        } catch (ConcurrentModificationException e) {
            LOGGER.error("removeExtensionsOfExportFileButton(): concurrent modification exception");
        }
    }

    private FileResource getExportDownloadStream() {
        return new FileResource(getExportFile());
    }

    private File getExportFile() {
        if (getExportMethodSelection().equals(messages.getQuestionGenerationViewListselectExportMoodleXml())) {
            return moodleXmlExportFile;
        } else {
            return csvExportFile;
        }
    }

    private void initializeExportSelect() {
        exportListSelect.addItem(messages.getQuestionGenerationViewListselectExportCsv());
        exportListSelect.addItem(messages.getQuestionGenerationViewListselectExportMoodleXml());

        exportListSelect.setRows(1);
        setDefaultExportValue();
    }

    private void setDefaultExportValue() {
        exportListSelect.select(defaultExportValue);
    }

    @Override
    public void displayCompletedLearningContents(List<LearningContent> learningContents) {
        completedLearningContentsList.removeAllItems();

        completedLearningContentsList.addItems(learningContents);

        displayNumberOfProcessedLearningContents(learningContents);

        triggerActionButtonsEnabledState();
    }

    private void displayNumberOfProcessedLearningContents(Collection<LearningContent> learningContents) {
        setCompletedLearningContentListLabel();

        completedLearningContentsListLabel.setCaption(
                completedLearningContentsListLabel.getCaption()
                        + " ("
                        + learningContents.size()
                        + ")"
        );
    }


    private void triggerActionButtonsEnabledState() {
        if (completedLearningContentsList.size() > 0) {
            enableActionButtons();
        } else {
            disableActionsButtons();
        }
    }

    private void enableActionButtons() {
        btnExport.setEnabled(true);
    }

    private void disableActionsButtons() {
        btnExport.setEnabled(false);
    }

    @Override
    public void displayGeneratedQuestions(List<TestQuestion> testQuestions) {
        completedQuestionsList.removeAllItems();

        completedQuestionsList.addItems(testQuestions);

        displayNumberOfGeneratedQuestions(testQuestions);

        triggerActionButtonsEnabledState();
    }

    private void displayNumberOfGeneratedQuestions(List<TestQuestion> learningContents) {
        setCompletedQuestionsListLabel();

        completedQuestionsListLabel.setCaption(
                completedQuestionsListLabel.getCaption()
                        + " ("
                        + learningContents.size()
                        + ")"
        );
    }

    @Override
    public void reset() {
        // add menu and set the generation item to be active
        super.reset(messages.getMenuItemQuestionGenerationLabel());

        setInfoBox();
        addComponent(infoBox);
        addComponent(introductionLabel);
        addComponent(btnStartQuestionGeneration);
        addComponent(horizontalLayout);

        addFooterWithButtonGroup();
    }

    private void setInfoBox() {
        this.infoBox.setInfo();
        setInfoBoxCaption();
    }

    private void setInfoBoxCaption() {
        this.infoBox.setCaption(messages.getQuestionGenerationViewInfoText());
    }

    private void addFooterWithButtonGroup() {
        addButtonsAtBottom(buttonGroupLayout, btnPrevious, btnExport);
        buttonGroupLayout.addComponent(exportListSelect);

        addComponent(buttonGroupLayout);

        addFooter();
    }

    @Override
    public void setViewListener(QuestionGenerationViewListener viewListener) {
        this.viewListener = viewListener;
    }

    @Override
    public String getExportMethodSelection() {
        if (null == exportListSelect.getValue()) {
            return defaultExportValue;
        }

        return (String) exportListSelect.getValue();
    }

    @Override
    public void showStatistics(long numberOfGeneratedQuestions, long questionGenerationRuntime) {
        setLabels();

        Notification.show("",
                        messages.getNumberOfGeneratedQuestions(String.valueOf(numberOfGeneratedQuestions))
                        + ", "
                        + messages.getRuntimeQuestionGeneration(String.valueOf(questionGenerationRuntime))
                        + ")",
                Notification.Type.ASSISTIVE_NOTIFICATION
        );
    }

    @Override
    public void setCsvExportFile(final File csvExportFile) {
        this.csvExportFile = csvExportFile;
        resetExportFileDownloaderExtension();
    }

    @Override
    public void setMoodleXmlExportFile(final File moodleXmlExportFile) {
        this.moodleXmlExportFile = moodleXmlExportFile;
        resetExportFileDownloaderExtension();
    }

    @Override
    public void refreshView() {
        this.completedLearningContentsList.removeAllItems();
        this.completedQuestionsList.removeAllItems();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        viewListener.onViewFocus();
    }
}

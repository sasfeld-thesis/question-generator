package de.saschafeldmann.adesso.master.thesis.portlet.view.generation;

import com.vaadin.data.Property;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FileDownloader;
import com.vaadin.server.FileResource;
import com.vaadin.shared.ui.label.ContentMode;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
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
    public static final String VIEW_NAME = "QuestionGenerationView";
    public static final String CSS_STYLE_NAME_HORICONTAL_LAYOUT = "question-generation-horicontal-layout";
    public static final String CSS_STYLE_NAME_EXPORT_SELECT = "question-generation-export-select";

    private QuestionGenerationViewListener viewListener;

    private final InfoBox infoBox;
    private final Label introductionLabel;
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

        this.introductionLabel = new Label(messages.getQuestionGenerationViewIntroductionText(), ContentMode.HTML);
    }

    @PostConstruct
    private void initialize() {
        this.btnStartQuestionGeneration.setCaption(messages.getQuestionGenerationViewButtonStartLabel());
        this.completedLearningContentsListLabel.setCaption(messages.getQuestionGenerationViewFinishedContentsLabels());
        this.completedQuestionsListLabel.setCaption(messages.getQuestionGenerationViewFinishedQuestionsLabel());

        this.btnPrevious.setCaption(messages.getButtonBackTitle());
        this.btnExport.setCaption(messages.getQuestionGenerationViewButtonExport());

        initializeHorizontalLayout();
        initializeExportSelect();
        registerListeners();
        disableActionsButtons();
        setStyles();
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

        btnExport.addClickListener(new com.vaadin.ui.Button.ClickListener() {
            @Override
            public void buttonClick(com.vaadin.ui.Button.ClickEvent clickEvent) {
                viewListener.onExportButtonClicked();
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
    }

    private void initializeExportSelect() {
        exportListSelect.addItem(messages.getQuestionGenerationViewListselectExportCsv());
        exportListSelect.addItem(messages.getQuestionGenerationViewListselectExportValamis());

        exportListSelect.setRows(1);
    }

    @Override
    public void displayCompletedLearningContents(List<LearningContent> learningContents) {
        completedLearningContentsList.removeAllItems();

        completedLearningContentsList.addItems(learningContents);

        triggerActionButtonsEnabledState();
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

        triggerActionButtonsEnabledState();
    }

    @Override
    public void offerCsvFileForDownload(File csvFile) {
        FileDownloader fileDownloader = new FileDownloader(new FileResource(csvFile));
        fileDownloader.extend(btnExport);
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
    public void resetInputs() {
        this.completedLearningContentsList.removeAllItems();
        this.completedQuestionsList.removeAllItems();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        viewListener.onViewFocus();
    }
}

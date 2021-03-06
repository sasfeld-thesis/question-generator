package de.saschafeldmann.adesso.master.thesis.portlet.view.preprocesses;

import com.vaadin.data.Property;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Language;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.portlet.model.LanguageWrapper;
import de.saschafeldmann.adesso.master.thesis.portlet.model.preprocesses.ProcessActivationElement;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.i18n.Messages;
import de.saschafeldmann.adesso.master.thesis.portlet.util.VaadinUtil;
import de.saschafeldmann.adesso.master.thesis.portlet.view.AbstractStepView;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.*;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.AutowirableAccordion;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.AutowirableButton;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.AutowirableFormLayout;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.AutowirableHorizontalLayout;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.AutowirableLabel;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.AutowirableListSelect;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.AutowirableOptionGroup;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.AutowirableTextArea;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.AutowirableVerticalLayout;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.window.*;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.window.AutowirableTable;
import de.saschafeldmann.adesso.master.thesis.util.linguistic.SentenceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import java.util.Collection;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  04.06.2016
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
 * Vaadin view implementation of the {@link PreprocessesView}.
 */
@org.springframework.stereotype.Component
@Scope("prototype")
public class PreprocessesViewImpl extends AbstractStepView implements PreprocessesView{
    private static final Logger LOGGER = LoggerFactory.getLogger(PreprocessesViewImpl.class);

    public static final String VIEW_NAME = "PreprocessesView";
    private static final String CSS_STYLE_NAME_HORICONTAL_OPTION_GROUP = "horicontal-option-group";
    private static final String CSS_STYLE_NAME_PROCESSCHAIN_TABLE = "processchain-table";
    private static final Object PROCESS_CHAIN_TABLE_CONTAINER_PROPERTY_LEFT = "accordion-process-left-column";
    private static final Object PROCESS_CHAIN_TABLE_CONTAINER_PROPERTY_RIGHT = "accordion-process-right-column";
    private static final String CSS_STYLE_NAME_LOG_TEXTAREA = "log-text-area";
    private static final String CSS_STYLE_NAME_PROCESSED_SELECT = "processed-select-list";
    private static final int PROCESS_CHAIN_TABLE_LEFT_COLUMN_WIDTH_PIXELS = 600;
    private static final int PROCESS_CHAIN_TABLE_RIGHT_COLUMN_WIDTH_PIXELS = 300;

    private final InfoBox infoBox;
    private AutowirableLabel introductionLabel;
    private final AutowirableAccordion accordion;

    private final AutowirableVerticalLayout accordionActivationLayout;
    private final AutowirableFormLayout accordionActivationFormLayout;
    private final InfoBox accordionActivationLayoutInfoBox;

    private final AutowirableTable accordionProcessChainLayoutTable;
    private final AutowirableTextArea accordionProcessChainLogTextarea;
    private final AutowirableListSelect accordionProcessChainFinishedSelect;

    private final AutowirableHorizontalLayout bottomButtonGroupLayout;
    private final AutowirableButton btnNext;
    private final AutowirableButton btnPrevious;
    private final AutowirableButton btnStartProcessChain;

    private final EditWindowWithSelectBox editWindow;

    private PreprocessesViewListener viewListener;
    private AutowirableLabel finishedLabel;

    @Autowired
    public PreprocessesViewImpl(
            final Messages messages,
            final VersionLabel versionLabel,
            final InfoBox infoBox,
            final AutowirableAccordion accordion,
            final AutowirableVerticalLayout accordionActivationLayout,
            final AutowirableFormLayout accordionActivationFormLayout,
            final InfoBox accordionActivationLayoutInfoBox,
            final AutowirableTable accordionProcessChainLayoutTable,
            final AutowirableTextArea accordionProcessChainLogTextarea,
            final AutowirableListSelect accordionProcessChainFinishedSelect,
            final AutowirableHorizontalLayout bottomButtonGroupLayout,
            final AutowirableButton btnNext,
            final AutowirableButton btnPrevious,
            final AutowirableButton btnStartProcessChain,
            final EditWindowWithSelectBox editWindow
    ) {
        super(messages, versionLabel);

        this.infoBox = infoBox;
        this.accordion = accordion;
        this.accordionActivationLayout = accordionActivationLayout;
        this.accordionActivationFormLayout = accordionActivationFormLayout;
        this.accordionActivationLayoutInfoBox = accordionActivationLayoutInfoBox;
        this.accordionProcessChainLayoutTable = accordionProcessChainLayoutTable;
        this.accordionProcessChainLogTextarea = accordionProcessChainLogTextarea;
        this.accordionProcessChainFinishedSelect = accordionProcessChainFinishedSelect;
        this.bottomButtonGroupLayout = bottomButtonGroupLayout;
        this.btnNext = btnNext;
        this.btnPrevious = btnPrevious;
        this.btnStartProcessChain = btnStartProcessChain;
        this.editWindow = editWindow;

        setIntroductionText();
    }

    private void setIntroductionText() {
        this.introductionLabel = new AutowirableLabel(messages.getPreproccesesViewIntroductionText(), ContentMode.HTML);
    }

    @PostConstruct
    private void initialize() {
        initializeAccordion();

        registerListeners();
        disableActionsButtons();

        setStyles();
        setLabels();
    }

    private void setStyles() {
        accordionProcessChainLogTextarea.addStyleName(CSS_STYLE_NAME_LOG_TEXTAREA);
        accordionProcessChainFinishedSelect.addStyleName(CSS_STYLE_NAME_PROCESSED_SELECT);
        accordionProcessChainLayoutTable.addStyleName(CSS_STYLE_NAME_PROCESSCHAIN_TABLE);
    }

    private void initializeAccordion() {
        initializeActivationPart();

        initializeProcessChainPart();
    }

    private void enableActionButtons() {
        btnNext.setEnabled(true);
    }

    private void disableActionsButtons() {
        btnNext.setEnabled(false);
    }

    private void setLabels() {
        setIntroductionText();

        this.btnPrevious.setCaption(messages.getButtonBackTitle());
        this.btnNext.setCaption(messages.getButtonNextTitle());

        btnStartProcessChain.setCaption(messages.getPreproccesesViewAccordionProcesschainButtonStartLabel());
        setFinishedLabelCaption();

        accordion.getTab(0).setCaption(messages.getPreproccesesViewAccordionActivationLabel());
        accordion.getTab(1).setCaption(messages.getPreproccesesViewAccordionProcesschainLabel());
    }

    private void setFinishedLabelCaption() {
        this.finishedLabel.setCaption(messages.getPreproccesesViewAccordionProcesschainFinishedLabel());
    }

    private void initializeActivationPart() {
        accordionActivationLayout.addComponent(accordionActivationLayoutInfoBox);
        accordionActivationLayout.addComponent(accordionActivationFormLayout);

        accordion.addTab(accordionActivationLayout, messages.getPreproccesesViewAccordionActivationLabel());
    }

    private void initializeProcessChainPart() {
        initializeProcessChainTable();

        accordion.addTab(accordionProcessChainLayoutTable, messages.getPreproccesesViewAccordionProcesschainLabel());
    }

    private void initializeProcessChainTable() {
        accordionProcessChainLayoutTable.setColumnHeaderMode(com.vaadin.ui.Table.ColumnHeaderMode.HIDDEN);

        // define columns
        accordionProcessChainLayoutTable.addContainerProperty(PROCESS_CHAIN_TABLE_CONTAINER_PROPERTY_LEFT, Component.class, null);
        accordionProcessChainLayoutTable.addContainerProperty(PROCESS_CHAIN_TABLE_CONTAINER_PROPERTY_RIGHT, Component.class, null);

        this.finishedLabel = new AutowirableLabel(messages.getPreproccesesViewAccordionProcesschainFinishedLabel());

        // add first row (left cell: start process button; right cell: label)
        accordionProcessChainLayoutTable.addItem(
                new Object[] {
                    btnStartProcessChain,
                    finishedLabel
                },
                1
        );

        // add second row (left cell: log textarea, right cell: drop down)
        accordionProcessChainLayoutTable.addItem(
                new Object[] {
                        accordionProcessChainLogTextarea,
                        accordionProcessChainFinishedSelect
                },
                2
        );

        // set column width
        accordionProcessChainLayoutTable.setColumnWidth(PROCESS_CHAIN_TABLE_CONTAINER_PROPERTY_LEFT, PROCESS_CHAIN_TABLE_LEFT_COLUMN_WIDTH_PIXELS);
        accordionProcessChainLayoutTable.setColumnWidth(PROCESS_CHAIN_TABLE_CONTAINER_PROPERTY_RIGHT, PROCESS_CHAIN_TABLE_RIGHT_COLUMN_WIDTH_PIXELS);
    }


    private void registerListeners() {
        btnPrevious.addClickListener(new com.vaadin.ui.Button.ClickListener() {
            @Override
            public void buttonClick(com.vaadin.ui.Button.ClickEvent clickEvent) {
                viewListener.onBackButtonClicked();
            }
        });

        btnNext.addClickListener(new com.vaadin.ui.Button.ClickListener() {
            @Override
            public void buttonClick(com.vaadin.ui.Button.ClickEvent clickEvent) {
                viewListener.onNextButtonClicked();
            }
        });

        btnStartProcessChain.addClickListener(new com.vaadin.ui.Button.ClickListener() {
            @Override
            public void buttonClick(com.vaadin.ui.Button.ClickEvent clickEvent) {
                // reset the log shown to the user
                accordionProcessChainLogTextarea.setValue("");

                viewListener.onProcessChainStartButtonClick();
            }
        });

        accordionProcessChainFinishedSelect.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                if (null != valueChangeEvent.getProperty()) {
                    if (valueChangeEvent.getProperty().getValue() instanceof LearningContent) {
                        LearningContent selectedContent = (LearningContent) valueChangeEvent.getProperty().getValue();

                        if (null != selectedContent) {
                            showProcessedTextEditWindow(selectedContent);
                        }
                    }
                }

                // reset selection
                accordionProcessChainFinishedSelect.select(accordionProcessChainFinishedSelect.getNullSelectionItemId());
            }
        });
    }

    private void showProcessedTextEditWindow(final LearningContent selectedContent) {
        String title = selectedContent.getTitle();
        String posAnnotatedText = SentenceUtil.buildStringForListOfSentences(selectedContent.getPartOfSpeechAnnotatedText());
        String nerAnnotatedText = SentenceUtil.buildStringForListOfSentences(selectedContent.getNamedEntityAnnotatedText());

        editWindow.setTextareaLabel(messages.getPreproccesesViewAccordionProcesschainEditWindowTextareaPosTitle());
        editWindow.setSecondTextareaLabel(messages.getPreproccesesViewAccordionProcesschainEditWindowTextareaNerTitle());
        editWindow.setTitle(messages.getPreproccesesViewAccordionProcesschainEditWindowTitle() + " - " + title);
        editWindow.setTextareaInput(posAnnotatedText);
        editWindow.setSecondTextareaInput(nerAnnotatedText);
        editWindow.setEditButtonTooltip(messages.getPreproccesesViewAccordionProcesschainEditWindowEditButtonTooltip());
        editWindow.setDeleteButtonTooltip(messages.getPreproccesesViewAccordionProcesschainEditWindowDeleteButtonTooltip());
        editWindow.setListSelectLabel(messages.getPreproccesesViewAccordionProcesschainEditWindowLanguageTitle());

        editWindow.resetListSelectItems();
        editWindow.addListSelectItem(LanguageWrapper.DEFAULT_SELECTION);

        for (LanguageWrapper item: LanguageWrapper.getAllLanguageItems()) {
            editWindow.addListSelectItem(item);
        }
        editWindow.setListSelectRows(3);

        if (!selectedContent.isLanguageCouldNotBeDetermined()) {
            Language determinedLearningContentLanguage = selectedContent.getDeterminedLanguage();

            try {
                editWindow.setListSelectSelection(LanguageWrapper.forLanguage(determinedLearningContentLanguage));
            } catch (Exception e) {
                LOGGER.error("showProcessedTextEditWindow(): could not set language list selection to {}", determinedLearningContentLanguage);
            }

            editWindow.setInfoBoxText(null);
        } else {
            // user needs to manually select the language since it could not be determined
            editWindow.setInfoBoxText(messages.getPreproccesesViewAccordionProcesschainEditWindowLanguageInfoBox());
        }

        // set window listener which delegates to the view listener
        editWindow.setEditWindowListener(new EditWindowWithSelectBoxListener() {
            @Override
            public void onSelectBoxItemChanged(Object itemChanged) {
                // see onEditButtonClicked() event handler
            }

            @Override
            public void onEditButtonClicked(String textareInput, String secondTextAreaInput) {
                if (null != editWindow.getListSelectValue() && editWindow.getListSelectValue() instanceof LanguageWrapper && !editWindow.getListSelectValue().equals(LanguageWrapper.DEFAULT_SELECTION)) {
                    // language is not the default one
                    viewListener.onEditLearningContentLanguageClick(selectedContent, ((LanguageWrapper) editWindow.getListSelectValue()).getLanguage());
                }

                viewListener.onEditLearningContentClick(selectedContent, textareInput, secondTextAreaInput);
            }

            @Override
            public void onEditButtonClicked(String textareaInput) {
                // do not do anything, onEditButtonClicked(String textareInput, String secondTextAreaInput) should be called, too.
            }

            @Override
            public void onDeleteButtonClicked() {
                viewListener.onDeleteLearningContentClick(selectedContent);
            }
        });

        editWindow.reset();   // initializes / resets the windows layout
        editWindow.center();  // displays the window on the screen center

        // displays the window
        VaadinUtil.addWindow(editWindow);
    }

    @Override
    public void addProcessActivationElements(final Iterable<ProcessActivationElement> elements) {
        for (ProcessActivationElement activationElement: elements) {
            addProcessActivationElement(activationElement);
        }

        reset();
    }

    private void addProcessActivationElement(final ProcessActivationElement activationElement) {
        // add yes-no option group
        AutowirableOptionGroup activationOptionGroup = new AutowirableOptionGroup();

        activationOptionGroup.addStyleName(CSS_STYLE_NAME_HORICONTAL_OPTION_GROUP);
        activationOptionGroup.setCaption(activationElement.getActivationLabel());
        activationOptionGroup.setDescription(activationElement.getTooltip());

        activationOptionGroup.addItem(activationElement.getProcessActivationElementStateActivated());
        activationOptionGroup.addItem(activationElement.getProcessActivationElementStateDeactivated());

        // set default selection
        if (activationElement.isActivatedPerDefault()) {
            activationOptionGroup.setValue(activationElement.getProcessActivationElementStateActivated());
        } else {
            activationOptionGroup.setValue(activationElement.getProcessActivationElementStateDeactivated());
        }

        // immediatly fire an AJAX event to notify the server about option changes
        activationOptionGroup.setImmediate(true);
        activationOptionGroup.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                ProcessActivationElement.ProcessActivationElementState state = (ProcessActivationElement.ProcessActivationElementState) valueChangeEvent.getProperty().getValue();

                viewListener.onActivationElementChange(state);
            }
        });

        accordionActivationFormLayout.addComponent(activationOptionGroup);
    }

    @Override
    public void showProcessActivationSuccessMessage() {
        accordionActivationLayoutInfoBox.setInfo();
        accordionActivationLayoutInfoBox.setCaption(messages.getPreproccesesViewAccordionActivationSetSuccessInfo());
    }

    @Override
    public void addProcessChainLogEntry(final String time, final String logEntry) {
        accordionProcessChainLogTextarea.setValue(
                accordionProcessChainLogTextarea.getValue()
                + (accordionProcessChainLogTextarea.getValue().trim().length() > 0 ? "\n" : "")
                + time + ": "
                + logEntry
        );
    }

    @Override
    public void showProcessedLearningContents(final Collection<LearningContent> learningContents) {
        accordionProcessChainFinishedSelect.removeAllItems();
        accordionProcessChainFinishedSelect.addItems(learningContents);

        displayNumberOfProcessedLearningContents(learningContents);
        triggerActionButtonsEnabledState();
    }

    private void displayNumberOfProcessedLearningContents(Collection<LearningContent> learningContents) {
        setFinishedLabelCaption();
        this.finishedLabel.setCaption(finishedLabel.getCaption() + " (" + learningContents.size() + ")");
    }

    private void triggerActionButtonsEnabledState() {
        if (accordionProcessChainFinishedSelect.size() > 0 || accordionProcessChainFinishedSelect.size() > 0) {
            enableActionButtons();
        } else {
            disableActionsButtons();
        }
    }

    @Override
    public void reset() {
        // add menu and set the preprocessing item to be active
        super.reset(messages.getMenuItemPreprocessesLabel());

        setInfoBox();
        addComponent(infoBox);
        addComponent(introductionLabel);
        addComponent(accordion);

        addFooterWithButtonGroup();

        resetUserInputs();
    }

    private void resetUserInputs() {
        // reset the log
        accordionProcessChainLogTextarea.setValue("");
    }

    private void addFooterWithButtonGroup() {
        addButtonsAtBottom(bottomButtonGroupLayout, btnPrevious, btnNext);
        addComponent(bottomButtonGroupLayout);

        addFooter();
    }

    private void setInfoBox() {
        this.infoBox.setInfo();
        setInfoBoxCaption();
    }

    private void setInfoBoxCaption() {
        this.infoBox.setCaption(messages.getPreproccesesViewInfoText());
    }

    @Override
    public void setViewListener(PreprocessesViewListener preprocessesViewListener) {
        this.viewListener = preprocessesViewListener;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        viewListener.onViewFocus();
    }

    @Override
    public void refreshView() {
        this.accordionProcessChainLogTextarea.setValue("");
        this.accordionProcessChainFinishedSelect.removeAllItems();
    }
}

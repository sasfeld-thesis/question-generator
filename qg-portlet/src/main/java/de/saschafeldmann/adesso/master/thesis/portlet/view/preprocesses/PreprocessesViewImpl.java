package de.saschafeldmann.adesso.master.thesis.portlet.view.preprocesses;

import com.vaadin.data.Property;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.portlet.model.preprocesses.ProcessActivationElement;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.i18n.Messages;
import de.saschafeldmann.adesso.master.thesis.portlet.view.AbstractStepView;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.*;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.Accordion;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.Button;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.HorizontalLayout;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.Label;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.ListSelect;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.OptionGroup;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.TextArea;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.VerticalLayout;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.window.EditWindow;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.window.EditWindowListener;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.window.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import java.util.Collection;

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
 * Vaadin view implementation of the {@link PreprocessesView}.
 */
@org.springframework.stereotype.Component
@Scope("prototype")
public class PreprocessesViewImpl extends AbstractStepView implements PreprocessesView{
    public static final String VIEW_NAME = "PreprocessesView";
    private static final String CSS_STYLE_NAME_HORICONTAL_OPTION_GROUP = "horicontal-option-group";
    private static final String CSS_STYLE_NAME_PROCESSCHAIN_TABLE = "processchain-table";
    private static final Object PROCESS_CHAIN_TABLE_CONTAINER_PROPERTY_LEFT = "accordion-process-left-column";
    private static final Object PROCESS_CHAIN_TABLE_CONTAINER_PROPERTY_RIGHT = "accordion-process-right-column";
    private static final int PROCESS_CHAIN_TABLE_CELL_WIDTH = 300;

    private final InfoBox infoBox;
    private final Label introductionLabel;
    private final Accordion accordion;

    private final VerticalLayout accordionActivationLayout;
    private final InfoBox accordionActivationLayoutInfoBox;

    private final Table accordionProcessChainLayoutTable;
    private final TextArea accordionProcessChainLogTextarea;
    private final ListSelect accordionProcessChainFinishedSelect;

    private final HorizontalLayout bottomButtonGroupLayout;
    private final Button btnNext;
    private final Button btnPrevious;
    private final Button btnStartProcessChain;

    private final EditWindow editWindow;

    private final Label finishedLabel;

    private PreprocessesViewListener viewListener;

    @Autowired
    public PreprocessesViewImpl(
            final Messages messages,
            final VersionLabel versionLabel,
            final InfoBox infoBox,
            final Label introductionLabel,
            final Accordion accordion,
            final VerticalLayout accordionActivationLayout,
            final InfoBox accordionActivationLayoutInfoBox,
            final Table accordionProcessChainLayoutTable,
            final TextArea accordionProcessChainLogTextarea,
            final ListSelect accordionProcessChainFinishedSelect,
            final HorizontalLayout bottomButtonGroupLayout,
            final Button btnNext,
            final Button btnPrevious,
            final Button btnStartProcessChain,
            final EditWindow editWindow,
            final Label finishedLabel
    ) {
        super(messages, versionLabel);

        this.infoBox = infoBox;
        this.introductionLabel = introductionLabel;
        this.accordion = accordion;
        this.accordionActivationLayout = accordionActivationLayout;
        this.accordionActivationLayoutInfoBox = accordionActivationLayoutInfoBox;
        this.accordionProcessChainLayoutTable = accordionProcessChainLayoutTable;
        this.accordionProcessChainLogTextarea = accordionProcessChainLogTextarea;
        this.accordionProcessChainFinishedSelect = accordionProcessChainFinishedSelect;
        this.bottomButtonGroupLayout = bottomButtonGroupLayout;
        this.btnNext = btnNext;
        this.btnPrevious = btnPrevious;
        this.btnStartProcessChain = btnStartProcessChain;
        this.editWindow = editWindow;
        this.finishedLabel = finishedLabel;
    }

    @PostConstruct
    private void initialize() {
        initializeIntroductionLabel();
        initializeAccordion();

        initializeBottomButtonGroup();
        registerListeners();
    }

    private void initializeIntroductionLabel() {
        this.introductionLabel.setContentMode(ContentMode.HTML);
        this.introductionLabel.setCaption(messages.getPreproccesesViewIntroductionText());

    }

    private void initializeAccordion() {
        initializeActivationPart();

        initializeProcessChainPart();
    }

    private void initializeBottomButtonGroup() {
        this.btnPrevious.setCaption(messages.getButtonBackTitle());
        this.btnNext.setCaption(messages.getButtonNextTitle());
    }

    private void initializeActivationPart() {
        accordionActivationLayout.addComponent(accordionActivationLayoutInfoBox);

        accordion.addTab(accordionActivationLayout, messages.getPreproccesesViewAccordionActivationLabel());
    }

    private void initializeProcessChainPart() {
        initializeProcessChainTable();

        accordion.addTab(accordionProcessChainLayoutTable, messages.getPreproccesesViewAccordionProcesschainLabel());
    }

    private void initializeProcessChainTable() {
        accordionProcessChainLayoutTable.addStyleName(CSS_STYLE_NAME_PROCESSCHAIN_TABLE);
        accordionProcessChainLayoutTable.setColumnHeaderMode(com.vaadin.ui.Table.ColumnHeaderMode.HIDDEN);

        // define columns
        accordionProcessChainLayoutTable.addContainerProperty(PROCESS_CHAIN_TABLE_CONTAINER_PROPERTY_LEFT, Component.class, null);
        accordionProcessChainLayoutTable.addContainerProperty(PROCESS_CHAIN_TABLE_CONTAINER_PROPERTY_RIGHT, Component.class, null);

        btnStartProcessChain.setCaption(messages.getPreproccesesViewAccordionProcesschainButtonStartLabel());
        finishedLabel.setCaption(messages.getPreproccesesViewAccordionProcesschainFinishedLabel());

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
        accordionProcessChainLayoutTable.setColumnWidth(PROCESS_CHAIN_TABLE_CONTAINER_PROPERTY_LEFT, PROCESS_CHAIN_TABLE_CELL_WIDTH);
        accordionProcessChainLayoutTable.setColumnWidth(PROCESS_CHAIN_TABLE_CONTAINER_PROPERTY_RIGHT, PROCESS_CHAIN_TABLE_CELL_WIDTH);
    }


    private void registerListeners() {
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
        String annotatedText = selectedContent.getAnnotatedText();
        editWindow.setTextareaLabel(messages.getPreproccesesViewAccordionProcesschainEditWindowTextareaTitle());
        editWindow.setTitle(messages.getPreproccesesViewAccordionProcesschainEditWindowTitle() + " - " + title);
        editWindow.setTextareaInput(annotatedText);

        // set window listener which delegates to the view listener
        editWindow.setEditWindowListener(new EditWindowListener() {
            /**
             * @see EditWindowListener#onEditButtonClicked(String)
             */
            public void onEditButtonClicked(String textareaInput) {
                viewListener.onEditLearningContentClick(selectedContent, textareaInput);
            }

            /**
             * @see EditWindowListener#onDeleteButtonClicked()
             */
            public void onDeleteButtonClicked() {
                viewListener.onDeleteLearningContentClick(selectedContent);
            }
        });

        editWindow.reset();   // initializes / resets the windows layout
        editWindow.center();  // displays the window on the screen center

        // displays the window
        UI.getCurrent().addWindow(editWindow);
    }

    @Override
    public void addProcessActivationElements(final Iterable<ProcessActivationElement> elements) {
        for (ProcessActivationElement activationElement: elements) {
            addProcessActivationElement(activationElement);
        }

        reset();
    }

    private void addProcessActivationElement(final ProcessActivationElement activationElement) {
        HorizontalLayout horizontalLayout = new HorizontalLayout();

        // add label
        Label activationElementLabel = new Label(activationElement.getActivationLabel());
        activationElementLabel.setDescription(activationElement.getTooltip());

        horizontalLayout.addComponent(activationElementLabel);

        // add yes-no option group
        OptionGroup activationOptionGroup = new OptionGroup();

        activationOptionGroup.addStyleName(CSS_STYLE_NAME_HORICONTAL_OPTION_GROUP);
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

        horizontalLayout.addComponent(activationOptionGroup);

        accordionActivationLayout.addComponent(horizontalLayout);
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
    }

    private void addFooterWithButtonGroup() {
        addButtonsAtBottom(bottomButtonGroupLayout, btnPrevious, btnNext);
        addComponent(bottomButtonGroupLayout);

        addFooter();
    }

    private void setInfoBox() {
        this.infoBox.setInfo();
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
}

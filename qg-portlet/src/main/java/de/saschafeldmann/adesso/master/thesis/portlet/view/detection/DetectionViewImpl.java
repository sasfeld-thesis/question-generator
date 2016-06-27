package de.saschafeldmann.adesso.master.thesis.portlet.view.detection;

import com.vaadin.data.Property;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.portlet.model.detection.DetectionActivationElement;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.i18n.Messages;
import de.saschafeldmann.adesso.master.thesis.portlet.view.AbstractStepView;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.*;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.window.EditWindowWithSelectBox;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.window.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;

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
 * An implementation of the {@link DetectionView}
 */
@org.springframework.stereotype.Component
@Scope("prototype")
public class DetectionViewImpl extends AbstractStepView implements DetectionView {
    private static final Logger LOGGER = LoggerFactory.getLogger(DetectionViewImpl.class);

    public static final String VIEW_NAME = "DetectionView";
    // TODO duplicate styles for detection view
    private static final String CSS_STYLE_NAME_HORICONTAL_OPTION_GROUP = "horicontal-option-group";
    private static final String CSS_STYLE_NAME_PROCESSCHAIN_TABLE = "processchain-table";
    private static final Object PROCESS_CHAIN_TABLE_CONTAINER_PROPERTY_LEFT = "accordion-process-left-column";
    private static final Object PROCESS_CHAIN_TABLE_CONTAINER_PROPERTY_RIGHT = "accordion-process-right-column";
    private static final String CSS_STYLE_NAME_LOG_TEXTAREA = "log-text-area";
    private static final String CSS_STYLE_NAME_PROCESSED_SELECT = "processed-select-list";
    private static final int PROCESS_CHAIN_TABLE_LEFT_COLUMN_WIDTH_PIXELS = 600;
    private static final int PROCESS_CHAIN_TABLE_RIGHT_COLUMN_WIDTH_PIXELS = 300;

    private final InfoBox infoBox;
    private final Label introductionLabel;
    private final Accordion accordion;

    private final VerticalLayout accordionActivationLayout;
    private final FormLayout accordionActivationFormLayout;
    private final InfoBox accordionActivationLayoutInfoBox;

    private final Table accordionDetectionChainLayoutTable;
    private final TextArea accordionDetectionChainLogTextarea;
    private final ListSelect accordionDetectionChainFinishedSelect;

    private final HorizontalLayout bottomButtonGroupLayout;
    private final Button btnNext;
    private final Button btnPrevious;
    private final Button btnStartProcessChain;
    private DetectionViewListener viewListener;

    @Autowired
    public DetectionViewImpl (
            final Messages messages,
            final VersionLabel versionLabel,
            final InfoBox infoBox,
            final Accordion accordion,
            final VerticalLayout accordionActivationLayout,
            final FormLayout accordionActivationFormLayout,
            final InfoBox accordionActivationLayoutInfoBox,
            final Table accordionProcessChainLayoutTable,
            final TextArea accordionProcessChainLogTextarea,
            final ListSelect accordionProcessChainFinishedSelect,
            final HorizontalLayout bottomButtonGroupLayout,
            final Button btnNext,
            final Button btnPrevious,
            final Button btnStartProcessChain,
            final EditWindowWithSelectBox editWindow
    ) {
        super(messages, versionLabel);

        this.infoBox = infoBox;
        this.accordion = accordion;
        this.accordionActivationLayout = accordionActivationLayout;
        this.accordionActivationFormLayout = accordionActivationFormLayout;
        this.accordionActivationLayoutInfoBox = accordionActivationLayoutInfoBox;
        this.accordionDetectionChainLayoutTable = accordionProcessChainLayoutTable;
        this.accordionDetectionChainLogTextarea = accordionProcessChainLogTextarea;
        this.accordionDetectionChainFinishedSelect = accordionProcessChainFinishedSelect;
        this.bottomButtonGroupLayout = bottomButtonGroupLayout;
        this.btnNext = btnNext;
        this.btnPrevious = btnPrevious;
        this.btnStartProcessChain = btnStartProcessChain;

        // TODO
        this.introductionLabel = new Label(messages.getPreproccesesViewIntroductionText(), ContentMode.HTML);
    }

    @PostConstruct
    private void initialize() {
        initializeAccordion();

        initializeBottomButtonGroup();
        registerListeners();
        disableActionsButtons();

        setStyles();
    }

    private void initializeAccordion() {
        initializeDetectionActivationPart();

        initializeDetectionChainPart();
    }

    private void initializeDetectionChainPart() {
        accordionActivationLayout.addComponent(accordionActivationLayoutInfoBox);
        accordionActivationLayout.addComponent(accordionActivationFormLayout);

        accordion.addTab(accordionActivationLayout, messages.getPreproccesesViewAccordionActivationLabel());
    }

    private void initializeDetectionActivationPart() {
        initializeDetectionChainTable();

        accordion.addTab(accordionDetectionChainLayoutTable, messages.getPreproccesesViewAccordionProcesschainLabel());

    }

    private void initializeDetectionChainTable() {
        accordionDetectionChainLayoutTable.setColumnHeaderMode(com.vaadin.ui.Table.ColumnHeaderMode.HIDDEN);

        // define columns
        accordionDetectionChainLayoutTable.addContainerProperty(PROCESS_CHAIN_TABLE_CONTAINER_PROPERTY_LEFT, Component.class, null);
        accordionDetectionChainLayoutTable.addContainerProperty(PROCESS_CHAIN_TABLE_CONTAINER_PROPERTY_RIGHT, Component.class, null);

        btnStartProcessChain.setCaption(messages.getPreproccesesViewAccordionProcesschainButtonStartLabel());

        Label finishedLabel = new Label(messages.getPreproccesesViewAccordionProcesschainFinishedLabel());

        // add first row (left cell: start process button; right cell: label)
        accordionDetectionChainLayoutTable.addItem(
                new Object[] {
                        btnStartProcessChain,
                        finishedLabel
                },
                1
        );

        // add second row (left cell: log textarea, right cell: drop down)
        accordionDetectionChainLayoutTable.addItem(
                new Object[] {
                        accordionDetectionChainLogTextarea,
                        accordionDetectionChainFinishedSelect
                },
                2
        );

        // set column width
        accordionDetectionChainLayoutTable.setColumnWidth(PROCESS_CHAIN_TABLE_CONTAINER_PROPERTY_LEFT, PROCESS_CHAIN_TABLE_LEFT_COLUMN_WIDTH_PIXELS);
        accordionDetectionChainLayoutTable.setColumnWidth(PROCESS_CHAIN_TABLE_CONTAINER_PROPERTY_RIGHT, PROCESS_CHAIN_TABLE_RIGHT_COLUMN_WIDTH_PIXELS);
    }

    private void initializeBottomButtonGroup() {
        this.btnPrevious.setCaption(messages.getButtonBackTitle());
        this.btnNext.setCaption(messages.getButtonNextTitle());
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
                accordionDetectionChainLogTextarea.setValue("");

                viewListener.onDetectionChainStartButtonClicked();
            }
        });

        accordionDetectionChainFinishedSelect.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                if (null != valueChangeEvent.getProperty()) {
                    if (valueChangeEvent.getProperty().getValue() instanceof LearningContent) {
                        LearningContent selectedContent = (LearningContent) valueChangeEvent.getProperty().getValue();

                        if (null != selectedContent) {
                            viewListener.onFinishedLearningContentSelected(selectedContent);
                        }
                    }
                }

                // reset seletction
                accordionDetectionChainFinishedSelect.select(accordionDetectionChainFinishedSelect.getNullSelectionItemId());
            }
        });
    }

    private void enableActionButtons() {
        btnNext.setEnabled(true);
    }

    private void disableActionsButtons() {
        btnNext.setEnabled(false);
    }


    private void setStyles() {
        accordionDetectionChainLogTextarea.addStyleName(CSS_STYLE_NAME_LOG_TEXTAREA);
        accordionDetectionChainFinishedSelect.addStyleName(CSS_STYLE_NAME_PROCESSED_SELECT);
        accordionDetectionChainLayoutTable.addStyleName(CSS_STYLE_NAME_PROCESSCHAIN_TABLE);
    }

    @Override
    public void setDetectionActivationElements(Iterable<DetectionActivationElement> detectionActivationElements) {
        reset();
    }

    @Override
    public void showDetectionActivationSuccessMessage() {

    }

    @Override
    public void addDetectionChainLogEntry(final String time, final String logEntry) {
        accordionDetectionChainLogTextarea.setValue(
                accordionDetectionChainLogTextarea.getValue()
                        + (accordionDetectionChainLogTextarea.getValue().trim().length() > 0 ? "\n" : "")
                        + time + ": "
                        + logEntry
        );
    }

    @Override
    public void showProcessedLearningContents(Map<LearningContent, List<Object>> detectedConcepts) {
        accordionDetectionChainFinishedSelect.removeAllItems();
        accordionDetectionChainFinishedSelect.addItems(detectedConcepts.keySet());

        triggerActionButtonsEnabledState();
    }

    private void triggerActionButtonsEnabledState() {
        if (accordionDetectionChainFinishedSelect.size() > 0 || accordionDetectionChainFinishedSelect.size() > 0) {
            enableActionButtons();
        } else {
            disableActionsButtons();
        }
    }

    @Override
    public void reset() {
        // add menu and set the detection item to be active
        super.reset(messages.getMenuItemDetectionLabel());

        setInfoBox();
        addComponent(infoBox);
        addComponent(introductionLabel);
        addComponent(accordion);

        addFooterWithButtonGroup();

        resetUserInputs();
    }

    private void resetUserInputs() {
        // reset the log
        accordionDetectionChainLogTextarea.setValue("");
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
    public void setViewListener(DetectionViewListener detectionViewListener) {
        this.viewListener = detectionViewListener;
    }

    @Override
    public void resetInputs() {
        accordionDetectionChainLogTextarea.setValue("");
        accordionDetectionChainFinishedSelect.removeAllItems();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        viewListener.onViewFocus();
    }
}
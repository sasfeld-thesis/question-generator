package de.saschafeldmann.adesso.master.thesis.portlet.view.course.contents;

import com.google.common.base.Strings;
import com.vaadin.data.Property;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.UI;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.i18n.Messages;
import de.saschafeldmann.adesso.master.thesis.portlet.view.AbstractStepView;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.*;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.Accordion;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.Button;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.FormLayout;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.HorizontalLayout;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.Label;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.ListSelect;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.TextArea;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.TextField;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.upload.FileUpload;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.upload.FileUploadListener;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.window.EditWindow;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.window.EditWindowListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.List;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  24.05.2016
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
 * [short description]
 */
@org.springframework.stereotype.Component
@Scope("prototype")
public class CourseContentsViewImpl extends AbstractStepView implements CourseContentsView, FileUploadListener {
    public static final String VIEW_NAME = "CourseContentsView";
    private static final String CSS_BUTTON_GROUP_STYLENAME = "course-contents-view-button-group";
    private static final String CSS_ACCORDION_STYLENAME = "course-contents-accordion";
    private static final String CSS_ACCORDION_DOCUMENTS_STYLENAME = "course-contents-accordion-documents";
    private static final String CSS_RAW_TEXTS_RIGHT_SIDE_STYLENAME = "course-contents-raw-texts-right";
    private static final String CSS_FILE_UPLOAD_PROGRESSBAR_STYLENAME = "course-contents-file-upload-progress-bar";

    private final InfoBox infoBox;
    private final Label introductionLabel;
    private final Accordion accordion;

    private final HorizontalLayout accordionDocumentsLayout;
    private final FormLayout accordionDocumentsLeftSideFormLayout;
    private final FileUpload accordionDocumentsLeftSideFileUpload;
    private final ListSelect accordionDocumentsLeftSideUploadedList;
    private final VerticalLayout accordionDocumentsRightSideLayout;
    private final InfoBox accordionDocumentsRightSideInfoBox;

    private final HorizontalLayout accordionRawTextsLayout;
    private final FormLayout accordionRawTextsLeftSideFormLayout;
    private final TextField accordionRawTextsLeftSideTitleInput;
    private final TextArea accordionRawTextsLeftSideRawTextInput;
    private final Button btnAccordionRawTextsLeftSideAddRawText;

    private final FormLayout accordionRawTextsRightSideFormLayout;
    private final ListSelect accordionRawTextsRightSideAddedList;

    private final EditWindow editWindow;

    private final HorizontalLayout buttonGroupLayout;
    private final Button btnPrevious;
    private final Button btnNext;
    private CourseContentsViewListener viewListener;
    private String courseTitle;

    @Autowired
    public CourseContentsViewImpl(final Messages messages, final VersionLabel versionLabel, final InfoBox infoBox,
                                  final Accordion accordion,
                                  final HorizontalLayout buttonGroupLayout,
                                  final HorizontalLayout accordionDocumentsLayout,
                                  final FormLayout accordionDocumentsLeftSideFormLayout,
                                  final FileUpload accordionDocumentsLeftSideFileUpload,
                                  final ListSelect accordionDocumentsLeftSideUploadedList,
                                  final VerticalLayout accordionDocumentsRightSideLayout,
                                  final InfoBox accordionDocumentsRightSideInfoBox,
                                  final HorizontalLayout accordionRawTextsLayout,
                                  final FormLayout accordionRawTextsLeftSideFormLayout,
                                  final TextField accordionRawTextsLeftSideTitleInput,
                                  final TextArea accordionRawTextsLeftSideRawTextInput,
                                  final Button btnAccordionRawTextsLeftSideAddRawText,
                                  final FormLayout accordionRawTextsRightSideFormLayout,
                                  final ListSelect accordionRawTextsRightSideAddedList,
                                  final EditWindow editWindow,
                                  final Button btnPrevious,
                                  final Button btnNext
    ) {
        super(messages, versionLabel);

        this.infoBox = infoBox;
        this.accordion = accordion;
        this.buttonGroupLayout = buttonGroupLayout;
        this.accordionDocumentsLayout = accordionDocumentsLayout;
        this.accordionDocumentsLeftSideFormLayout = accordionDocumentsLeftSideFormLayout;
        this.accordionDocumentsLeftSideFileUpload = accordionDocumentsLeftSideFileUpload;
        this.accordionDocumentsLeftSideUploadedList = accordionDocumentsLeftSideUploadedList;
        this.accordionDocumentsRightSideLayout = accordionDocumentsRightSideLayout;
        this.accordionDocumentsRightSideInfoBox = accordionDocumentsRightSideInfoBox;
        this.accordionRawTextsLayout = accordionRawTextsLayout;
        this.accordionRawTextsLeftSideFormLayout = accordionRawTextsLeftSideFormLayout;
        this.accordionRawTextsLeftSideTitleInput = accordionRawTextsLeftSideTitleInput;
        this.accordionRawTextsLeftSideRawTextInput = accordionRawTextsLeftSideRawTextInput;
        this.btnAccordionRawTextsLeftSideAddRawText = btnAccordionRawTextsLeftSideAddRawText;
        this.accordionRawTextsRightSideFormLayout = accordionRawTextsRightSideFormLayout;
        this.accordionRawTextsRightSideAddedList = accordionRawTextsRightSideAddedList;
        this.editWindow = editWindow;
        this.btnPrevious = btnPrevious;
        this.btnNext = btnNext;

        this.introductionLabel = new Label(messages.getCourseContentsViewIntroductionText(), ContentMode.HTML);
    }

    @PostConstruct
    private void initialize() {
        initializeAccordion();

        setStyles();

        this.btnPrevious.setCaption(messages.getCourseInformationViewBtnPreviousLabel());
        this.btnNext.setCaption(messages.getCourseInformationViewBtnNextLabel());

        registerListeners();

        disableActionsButtons();
    }

    private void enableActionButtons() {
        btnNext.setEnabled(true);
    }

    private void disableActionsButtons() {
        btnNext.setEnabled(false);
    }

    private void setStyles() {
        this.buttonGroupLayout.addStyleName(CSS_BUTTON_GROUP_STYLENAME);
        this.accordionDocumentsLeftSideFileUpload.getProgressBar().addStyleName(CSS_FILE_UPLOAD_PROGRESSBAR_STYLENAME);
    }

    private void initializeAccordion() {
        accordion.addStyleName(CSS_ACCORDION_STYLENAME);

        // documents
        accordionDocumentsLayout.addStyleName(CSS_ACCORDION_DOCUMENTS_STYLENAME);

        this.accordionDocumentsLeftSideFileUpload.setCaption(messages.getCourseContentsViewAccordionDocumentsFileUploadLabel());
        this.accordionDocumentsLeftSideUploadedList.setCaption(messages.getCourseContentsViewAccordionDocumentsUploadedListLabel());

        accordionDocumentsLeftSideFileUpload.setImmediate(true);
        accordionDocumentsLeftSideFormLayout.addComponent(accordionDocumentsLeftSideFileUpload);
        accordionDocumentsLeftSideFormLayout.addComponent(accordionDocumentsLeftSideUploadedList);

        accordionDocumentsLayout.addComponent(accordionDocumentsLeftSideFormLayout);

        accordionDocumentsRightSideLayout.addComponent(accordionDocumentsRightSideInfoBox);
        // adds the progress bar of the file upload component
        accordionDocumentsRightSideLayout.addComponent(accordionDocumentsLeftSideFileUpload.getProgressBar());
        accordionDocumentsLayout.addComponent(accordionDocumentsRightSideLayout);

        accordion.addTab(accordionDocumentsLayout, messages.getCourseContentsViewAccordionDocumentsTitleLabel());

        // raw texts
        this.accordionRawTextsLeftSideTitleInput.setCaption(messages.getCourseContentsViewAccordionRawTextsTitleInputLabel());
        this.accordionRawTextsLeftSideRawTextInput.setCaption(messages.getCourseContentsViewAccordionRawTextsRawTextInputLabel());
        this.btnAccordionRawTextsLeftSideAddRawText.setCaption(messages.getCourseContentsViewAccordionRawTextsAddButtonLabel());

        accordionRawTextsLeftSideFormLayout.addComponent(accordionRawTextsLeftSideTitleInput);
        accordionRawTextsLeftSideFormLayout.addComponent(accordionRawTextsLeftSideRawTextInput);
        accordionRawTextsLeftSideFormLayout.addComponent(btnAccordionRawTextsLeftSideAddRawText);
        accordionRawTextsLayout.addComponent(accordionRawTextsLeftSideFormLayout);

        accordionRawTextsRightSideFormLayout.addStyleName(CSS_RAW_TEXTS_RIGHT_SIDE_STYLENAME);
        accordionRawTextsRightSideAddedList.setCaption(messages.getCourseContentsViewAccordionRawTextsAddedLabel());
        accordionRawTextsRightSideFormLayout.addComponent(accordionRawTextsRightSideAddedList);
        accordionRawTextsLayout.addComponent(accordionRawTextsRightSideFormLayout);

        accordion.addTab(accordionRawTextsLayout, messages.getCourseContentsViewAccordionRawTextsTitleLabel());
    }

    private void registerListeners() {
        btnPrevious.addClickListener(new com.vaadin.ui.Button.ClickListener() {
            @Override
            public void buttonClick(com.vaadin.ui.Button.ClickEvent clickEvent) {
                viewListener.onBackButtonClicked();
            }
        });

        btnNext.addClickListener(new com.vaadin.ui.Button.ClickListener() {
            public void buttonClick(com.vaadin.ui.Button.ClickEvent clickEvent) {
                viewListener.onNextButtonClicked();
            }
        });

        btnAccordionRawTextsLeftSideAddRawText.addClickListener(new com.vaadin.ui.Button.ClickListener() {
            public void buttonClick(com.vaadin.ui.Button.ClickEvent clickEvent) {
                viewListener.onContentRawTextAddClick(accordionRawTextsLeftSideTitleInput.getValue(), accordionRawTextsLeftSideRawTextInput.getValue());
                clearRawTextInputs();
            }
        });

        accordionRawTextsRightSideAddedList.addValueChangeListener(new Property.ValueChangeListener() {
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                if (null != valueChangeEvent.getProperty()) {
                    LearningContent selectedContent = (LearningContent) valueChangeEvent.getProperty().getValue();

                    if (null != selectedContent) {
                        showRawTextEditWindow(selectedContent);
                    }
                }

                // reset selection
                accordionRawTextsRightSideAddedList.select(accordionRawTextsRightSideAddedList.getNullSelectionItemId());
            }
        });

        accordionDocumentsLeftSideUploadedList.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                if (null != valueChangeEvent.getProperty()) {
                    LearningContent selectedContent = (LearningContent) valueChangeEvent.getProperty().getValue();

                    if (null != selectedContent) {
                        showFileEditWindow(selectedContent);
                    }
                }

                // reset selection
                accordionDocumentsLeftSideUploadedList.select(accordionDocumentsLeftSideUploadedList.getNullSelectionItemId());
            }
        });

        accordionDocumentsLeftSideFileUpload.setFileUploadListener(this);
    }

    private void triggerActionButtonsEnabledState() {
        if (accordionDocumentsLeftSideUploadedList.size() > 0 || accordionRawTextsRightSideAddedList.size() > 0) {
            enableActionButtons();
        } else {
            disableActionsButtons();
        }
    }

    private void clearRawTextInputs() {
        accordionRawTextsLeftSideTitleInput.setValue("");
        accordionRawTextsLeftSideRawTextInput.setValue("");
    }

    private void showRawTextEditWindow(final LearningContent selectedContent) {
        String title = selectedContent.getTitle();
        String rawText = selectedContent.getRawText();
        editWindow.setTextareaLabel(messages.getCourseContentsViewRawtextEditWindowTextareaLabel());
        editWindow.setTitle(messages.getCourseContentsViewRawtextEditWindowTitleText() + " - " + title);
        editWindow.setTextareaInput(rawText);

        // set window listener which delegates to the view listener
        editWindow.setEditWindowListener(new EditWindowListener() {
            /**
             * @see EditWindowListener#onEditButtonClicked(String)
             */
            public void onEditButtonClicked(String textareaInput) {
                viewListener.onContentRawTextChangeClick(selectedContent, textareaInput);
            }

            /**
             * @see EditWindowListener#onDeleteButtonClicked()
             */
            public void onDeleteButtonClicked() {
                viewListener.onContentRawTextDeleteClick(selectedContent);
            }
        });

        editWindow.reset();   // initializes / resets the windows layout
        editWindow.center();  // displays the window on the screen center

        // displays the window
        UI.getCurrent().addWindow(editWindow);
    }

    private void showFileEditWindow(final LearningContent selectedContent) {
        String title = selectedContent.getTitle();
        String rawText = selectedContent.getRawText();
        editWindow.setTextareaLabel(messages.getCourseContentsViewFileEditWindowTextareaLabel());
        editWindow.setTitle(messages.getCourseContentsViewFileEditWindowTitleText() + " - " + title);
        editWindow.setTextareaInput(rawText);

        // set window listener which delegates to the view listener
        editWindow.setEditWindowListener(new EditWindowListener() {
            /**
             * @see EditWindowListener#onEditButtonClicked(String)
             */
            public void onEditButtonClicked(String textareaInput) {
                viewListener.onContentFileChangeClick(selectedContent, textareaInput);
            }

            /**
             * @see EditWindowListener#onDeleteButtonClicked()
             */
            public void onDeleteButtonClicked() {
                viewListener.onContentFileDeleteClick(selectedContent);
            }
        });

        editWindow.reset();   // initializes / resets the windows layout
        editWindow.center();  // displays the window on the screen center

        // displays the window
        UI.getCurrent().addWindow(editWindow);
    }

    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        viewListener.onViewFocus();
    }

    @Override
    public void reset() {
        // add menu and set the course contents item to be active
        super.reset(messages.getMenuItemContentsLabel());

        setInfoBox();
        addComponent(infoBox);
        addComponent(introductionLabel);

        addComponent(accordion);

        addFooterWithButtonGroup();
    }

    private void addFooterWithButtonGroup() {
        addButtonsAtBottom(buttonGroupLayout, btnPrevious, btnNext);
        addComponent(buttonGroupLayout);

        addFooter();
    }

    private void setInfoBox() {
        this.infoBox.setInfo();

        if (!Strings.isNullOrEmpty(this.courseTitle)) {
            this.infoBox.setCaption(messages.getCourseContentsInfoBoxCourseAddedText(this.courseTitle));
        }
    }

    @Override
    public void showContentFiles(List<LearningContent> learningContentFiles) {
        accordionDocumentsLeftSideUploadedList.removeAllItems();
        accordionDocumentsLeftSideUploadedList.addItems(learningContentFiles);

        triggerActionButtonsEnabledState();
    }

    @Override
    public void showContentRawTexts(List<LearningContent> learningRawTexts) {
        accordionRawTextsRightSideAddedList.removeAllItems();
        accordionRawTextsRightSideAddedList.addItems(learningRawTexts);

        triggerActionButtonsEnabledState();
    }

    @Override
    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    @Override
    public void setViewListener(CourseContentsViewListener courseContentsViewListener) {
        this.viewListener = courseContentsViewListener;
    }

    @Override
    public void uploadFailed() {
        this.displayFileUploadError(messages.getCourseContentsViewUploadFileErrorNotificationText());
    }

    @Override
    public void uploadSucceeded(final File file) {
        // notify listener
        viewListener.onContentFileUploaded(file);
    }

    @Override
    public void displayFileUploadInformation(final String information) {
        // display info box
        this.accordionDocumentsRightSideInfoBox.setInfo();
        this.accordionDocumentsRightSideInfoBox.setCaption(information);
    }

    @Override
    public void displayFileUploadError(String error) {
        // display error box
        this.accordionDocumentsRightSideInfoBox.setError();
        this.accordionDocumentsRightSideInfoBox.setCaption(error);
    }

    @Override
    public void resetInputs() {
        this.accordionDocumentsLeftSideUploadedList.removeAllItems();
        this.accordionRawTextsRightSideAddedList.removeAllItems();
    }
}

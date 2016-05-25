package de.saschafeldmann.adesso.master.thesis.portlet.view.course.contents;

import com.google.common.base.Strings;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.label.ContentMode;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.Messages;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
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
public class CourseContentsViewImpl extends AbstractStepView implements CourseContentsView {
    public static final String VIEW_NAME = "CourseContentsView";
    private static final String CSS_BUTTON_GROUP_STYLENAME = "course-contents-view-button-group";
    private static final String CSS_ACCORDION_STYLENAME = "course-contents-accordion";
    private static final String CSS_ACCORDION_DOCUMENTS_STYLENAME = "course-contents-accordion-documents";
    private static final String CSS_RAW_TEXTS_RIGHT_SIDE_STYLENAME = "course-contents-raw-texts-right";

    private final InfoBox infoBox;
    private final Label introductionLabel;
    private final Accordion accordion;

    private final HorizontalLayout accordionDocumentsLayout;
    private final FormLayout accordionDocumentsLeftSideFormLayout;
    private final FileUpload accordionDocumentsLeftSideFileUpload;
    private final ListSelect accordionDocumentsLeftSideUploadedList;
    private final HorizontalLayout accordionRawTextsLayout;

    private final FormLayout accordionRawTextsLeftSideFormLayout;
    private final TextField accordionRawTextsLeftSideTitleInput;
    private final TextArea accordionRawTextsLeftSideRawTextInput;
    private final Button btnAccordionRawTextsLeftSideAddRawText;

    private final FormLayout accordionRawTextsRightSideFormLayout;
    private final ListSelect accordionRawTextsRightSideAddedList;

    private final HorizontalLayout buttonGroupLayout;
    private final Button btnNext;
    private CourseContentsViewListener viewListener;
    private String courseTitle;

    @Autowired
    public CourseContentsViewImpl(final Messages messages, final VersionLabel versionLabel, final InfoBox infoBox,
                                  final Label introductionLabel,
                                  final Accordion accordion,
                                  final HorizontalLayout buttonGroupLayout,
                                  final HorizontalLayout accordionDocumentsLayout,
                                  final FormLayout accordionDocumentsLeftSideFormLayout,
                                  final FileUpload accordionDocumentsLeftSideFileUpload,
                                  final ListSelect accordionDocumentsLeftSideUploadedList,
                                  final HorizontalLayout accordionRawTextsLayout,
                                  final FormLayout accordionRawTextsLeftSideFormLayout,
                                  final TextField accordionRawTextsLeftSideTitleInput,
                                  final TextArea accordionRawTextsLeftSideRawTextInput,
                                  final Button btnAccordionRawTextsLeftSideAddRawText,
                                  final FormLayout accordionRawTextsRightSideFormLayout,
                                  final ListSelect accordionRawTextsRightSideAddedList,
                                  final Button btnNext) {
        super(messages, versionLabel);

        this.infoBox = infoBox;
        this.introductionLabel = introductionLabel;
        this.accordion = accordion;
        this.buttonGroupLayout = buttonGroupLayout;
        this.accordionDocumentsLayout = accordionDocumentsLayout;
        this.accordionDocumentsLeftSideFormLayout = accordionDocumentsLeftSideFormLayout;
        this.accordionDocumentsLeftSideFileUpload = accordionDocumentsLeftSideFileUpload;
        this.accordionDocumentsLeftSideUploadedList = accordionDocumentsLeftSideUploadedList;
        this.accordionRawTextsLayout = accordionRawTextsLayout;
        this.accordionRawTextsLeftSideFormLayout = accordionRawTextsLeftSideFormLayout;
        this.accordionRawTextsLeftSideTitleInput = accordionRawTextsLeftSideTitleInput;
        this.accordionRawTextsLeftSideRawTextInput = accordionRawTextsLeftSideRawTextInput;
        this.btnAccordionRawTextsLeftSideAddRawText = btnAccordionRawTextsLeftSideAddRawText;
        this.accordionRawTextsRightSideFormLayout = accordionRawTextsRightSideFormLayout;
        this.accordionRawTextsRightSideAddedList = accordionRawTextsRightSideAddedList;
        this.btnNext = btnNext;
    }

    @PostConstruct
    private void initialize() {
        this.introductionLabel.setContentMode(ContentMode.HTML);
        this.introductionLabel.setCaption(messages.getCourseContentsViewIntroductionText());

        initializeAccordion();

        this.buttonGroupLayout.addStyleName(CSS_BUTTON_GROUP_STYLENAME);
        this.btnNext.setCaption(messages.getCourseInformationViewBtnNextLabel());

        registerListeners();
    }

    private void initializeAccordion() {
        accordion.addStyleName(CSS_ACCORDION_STYLENAME);

        // documents
        accordionDocumentsLayout.addStyleName(CSS_ACCORDION_DOCUMENTS_STYLENAME);
        accordionDocumentsLeftSideFileUpload.setImmediate(true);

        this.accordionDocumentsLeftSideFileUpload.setCaption(messages.getCourseContentsViewAccordionDocumentsFileUploadLabel());
        this.accordionDocumentsLeftSideUploadedList.setCaption(messages.getCourseContentsViewAccordionDocumentsUploadedListLabel());

        accordionDocumentsLeftSideFormLayout.addComponent(accordionDocumentsLeftSideFileUpload);
        accordionDocumentsLeftSideFormLayout.addComponent(accordionDocumentsLeftSideUploadedList);

        accordionDocumentsLayout.addComponent(accordionDocumentsLeftSideFormLayout);
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
        btnNext.addClickListener(new com.vaadin.ui.Button.ClickListener() {
            public void buttonClick(com.vaadin.ui.Button.ClickEvent clickEvent) {
                viewListener.onNextButtonClicked();
            }
        });

        btnAccordionRawTextsLeftSideAddRawText.addClickListener(new com.vaadin.ui.Button.ClickListener() {
            public void buttonClick(com.vaadin.ui.Button.ClickEvent clickEvent) {
                viewListener.onContentRawTextAddClick(accordionRawTextsLeftSideTitleInput.getValue(), accordionRawTextsLeftSideRawTextInput.getValue());
            }
        });
    }

    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        viewListener.onViewFocus();
    }

    public void reset() {
        // add menu and set the course contents item to be active
        super.reset(messages.getMenuItemContentsLabel());

        setInfoBox();
        addComponent(infoBox);
        addComponent(introductionLabel);

        addComponent(accordion);

        addComponent(btnNext);

        addVersionLabel();
    }

    private void setInfoBox() {
        this.infoBox.setInfo();

        if (!Strings.isNullOrEmpty(this.courseTitle)) {
            this.infoBox.setCaption(messages.getCourseContentsInfoBoxCourseAddedText(this.courseTitle));
        }
    }

    /**
     * @see CourseContentsView#showContentFiles(List)
     */
    public void showContentFiles(List<LearningContent> learningContentFiles) {
        accordionDocumentsLeftSideUploadedList.addItems(learningContentFiles);
    }

    /**
     * @see CourseContentsView#showContentRawTexts(List)
     */
    public void showContentRawTexts(List<LearningContent> learningRawTexts) {
        accordionRawTextsRightSideAddedList.addItems(learningRawTexts);
    }

    /**
     * @see CourseContentsView#setCourseTitle(String)
     */
    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    /**
     * @see CourseContentsView#setViewListener(CourseContentsViewListener)
     */
    public void setViewListener(CourseContentsViewListener courseContentsViewListener) {
        this.viewListener = courseContentsViewListener;
    }

}

package de.saschafeldmann.adesso.master.thesis.portlet.view.course.information;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.Messages;
import de.saschafeldmann.adesso.master.thesis.portlet.view.AbstractStepView;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  23.05.2016
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
 * Course information dialog view.
 */
@Component
@Scope("prototype")
public class CourseInformationViewImpl extends AbstractStepView implements CourseInformationView {
    private static final java.lang.String CSS_BUTTON_GROUP_STYLENAME = "course-information-view-button-group";

    /**
     * The view mode for this view.
     */
    public enum ViewMode {
        NEW_COURSE,
        EDIT_COURSE
    }

    public static final String VIEW_NAME = "CourseInformationView";

    private ViewMode viewMode;
    private final InfoBox infoBox;
    private final Label introductionLabel;
    private final FormLayout formLayout;
    private final TextField inputCourseTitle;
    private final TextField inputCourseUrl;
    private final ListSelect inputCourseLanguageSelect;
    private final HorizontalLayout buttonGroupLayout;
    private final Button btnNext;
    private final Button btnNewSession;
    private CourseInformationViewListener viewListener;
    private String courseTitle;

    @Autowired
    public CourseInformationViewImpl(final Messages messages, final InfoBox infoBox, final Label introductionLabel, final FormLayout formLayout, final VersionLabel versionLabel, final TextField inputCourseTitle,
                                     final TextField inputCourseUrl,
                                     final ListSelect inputCourseLanguageSelect,
                                     final HorizontalLayout buttonGroupLayout,
                                     final Button btnNext,
                                     final Button btnNewSession ) {
        super(messages, versionLabel);

        this.infoBox = infoBox;
        this.introductionLabel = introductionLabel;
        this.formLayout = formLayout;
        this.inputCourseTitle = inputCourseTitle;
        this.inputCourseUrl = inputCourseUrl;
        this.inputCourseLanguageSelect = inputCourseLanguageSelect;
        this.buttonGroupLayout = buttonGroupLayout;
        this.btnNext = btnNext;
        this.btnNewSession = btnNewSession;
    }

    @PostConstruct
    private void initialize() {
        this.introductionLabel.setCaption(messages.getCourseInformationViewIntroductionText());
        this.inputCourseTitle.setCaption(messages.getCourseInformationViewCourseTitleLabel());
        this.inputCourseUrl.setCaption(messages.getCourseInformationViewCourseUrlLabel());

        initializeLanguageSelect();

        this.buttonGroupLayout.addStyleName(CSS_BUTTON_GROUP_STYLENAME);
        this.btnNext.setCaption(messages.getCourseInformationViewBtnNextLabel());
        this.btnNewSession.setCaption(messages.getCourseInformationViewBtnNewSessionLabel());

        registerListeners();
    }

    private void initializeLanguageSelect() {
        this.inputCourseLanguageSelect.setCaption(messages.getCourseInformationViewCourseLanguageLabel());

        String germanLanguageLabel = messages.getCourseInformationViewGermanLanguageLabel();
        this.inputCourseLanguageSelect.addItem(germanLanguageLabel);
        this.inputCourseLanguageSelect.addItem(messages.getCourseInformationViewEnglishLanguageLabel());

        this.inputCourseLanguageSelect.setRows(2);
        this.inputCourseLanguageSelect.select(germanLanguageLabel);
    }

    private void registerListeners() {
        btnNext.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent clickEvent) {
                viewListener.onNextButtonClicked();
            }
        });

        btnNewSession.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent clickEvent) {
                viewListener.onNewSessionButtonClicked();
            }
        });
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        viewListener.onViewFocus();
    }

    @Override
    public void reset() {
        // add menu and set the course information item to be active
        super.reset(messages.getMenuItemCourseInformationLabel());

        setInfoBox();
        addComponent(infoBox);
        addComponent(introductionLabel);

        formLayout.addComponent(inputCourseTitle);
        formLayout.addComponent(inputCourseUrl);
        formLayout.addComponent(inputCourseLanguageSelect);
        addComponent(formLayout);

        addButtonsAtBottom(buttonGroupLayout, btnNext, btnNewSession);
        addComponent(buttonGroupLayout);

        addVersionLabel();
    }

    private void setInfoBox() {
        this.infoBox.setInfo();

        if (viewMode.equals(ViewMode.NEW_COURSE)) {
            this.infoBox.setCaption(messages.getCourseInformationViewNewCourseInfoText());
        } else {
            // ViewMode.EDIT_COURSE
            this.infoBox.setCaption(messages.getCourseInformationViewEditCourseInfoText(courseTitle));
        }
    }

    @Override
    public com.vaadin.ui.Component getRootComponent() {
        return this;
    }

    /**
     * Sets the view listener for this view.
     * @param viewListener the view listener instance.
     */
    public void setViewListener(CourseInformationViewListener viewListener) {
        this.viewListener = viewListener;
    }

    /**
     * Sets the view mode of this view.
     * @param viewMode see {@link ViewMode}
     */
    public void setViewMode(ViewMode viewMode) {
        this.viewMode = viewMode;
    }

    @Override
    public String getInputTitle() {
        return inputCourseTitle.getValue();
    }

    @Override
    public String getInputViewUrl() {
        return inputCourseUrl.getValue();
    }

    @Override
    public String getInputLanguage() {
        return (String) inputCourseLanguageSelect.getValue();
    }

    @Override
    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }
}

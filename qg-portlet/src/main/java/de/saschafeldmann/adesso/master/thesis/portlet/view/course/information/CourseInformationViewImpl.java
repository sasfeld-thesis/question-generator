package de.saschafeldmann.adesso.master.thesis.portlet.view.course.information;

import static com.google.common.base.Strings.*;
import com.vaadin.data.Property;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import de.saschafeldmann.adesso.master.thesis.portlet.model.LanguageWrapper;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.i18n.Messages;
import de.saschafeldmann.adesso.master.thesis.portlet.view.AbstractStepView;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.*;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.Button;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.FormLayout;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.HorizontalLayout;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.Label;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.ListSelect;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.TextField;
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
    private static final LanguageWrapper DEFAULT_LANGUAGE_ITEM = LanguageWrapper.getGermanWrapper();


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
    private final Button btnOptions;
    private CourseInformationViewListener viewListener;
    private String courseTitle;

    @Autowired
    public CourseInformationViewImpl(final Messages messages,
                                     final InfoBox infoBox,
                                     final FormLayout formLayout,
                                     final VersionLabel versionLabel,
                                     final TextField inputCourseTitle,
                                     final TextField inputCourseUrl,
                                     final ListSelect inputCourseLanguageSelect,
                                     final HorizontalLayout buttonGroupLayout,
                                     final Button btnNext,
                                     final Button btnNewSession,
                                     final Button btnOptions
                                     ) {
        super(messages, versionLabel);

        this.infoBox = infoBox;
        this.formLayout = formLayout;
        this.inputCourseTitle = inputCourseTitle;
        this.inputCourseUrl = inputCourseUrl;
        this.inputCourseLanguageSelect = inputCourseLanguageSelect;
        this.buttonGroupLayout = buttonGroupLayout;
        this.btnNext = btnNext;
        this.btnNewSession = btnNewSession;
        this.btnOptions = btnOptions;

        this.introductionLabel = new Label(messages.getCourseInformationViewIntroductionText(), ContentMode.HTML);
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
        this.btnOptions.setCaption(messages.getCourseInformationViewButtonOptionsLabel());

        registerListeners();
        disableActionsButtons();
    }

    private void enableActionButtons() {
        btnNext.setEnabled(true);
    }

    private void disableActionsButtons() {
        btnNext.setEnabled(false);
    }

    private void initializeLanguageSelect() {
        this.inputCourseLanguageSelect.setCaption(messages.getCourseInformationViewCourseLanguageLabel());

        this.inputCourseLanguageSelect.addItems(LanguageWrapper.getAllLanguageItems());

        this.inputCourseLanguageSelect.setRows(2);
        this.inputCourseLanguageSelect.select(DEFAULT_LANGUAGE_ITEM);
    }

    private void registerListeners() {
        inputCourseTitle.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                if (isNullOrEmpty(inputCourseTitle.getValue())) {
                    disableActionsButtons();
                } else {
                    enableActionButtons();
                }
            }
        });

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

        btnOptions.addClickListener(new com.vaadin.ui.Button.ClickListener() {
            @Override
            public void buttonClick(com.vaadin.ui.Button.ClickEvent clickEvent) {
                viewListener.onOptionsButtonClicked();
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
        buttonGroupLayout.addComponent(btnOptions);

        addComponent(buttonGroupLayout);

        addFooter();
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
        return ((LanguageWrapper) inputCourseLanguageSelect.getValue()).toString();
    }

    @Override
    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    @Override
    public void resetInputs() {
        this.inputCourseUrl.setValue("");
        this.inputCourseTitle.setValue("");
        this.inputCourseLanguageSelect.setValue(DEFAULT_LANGUAGE_ITEM);
    }
}

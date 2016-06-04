package de.saschafeldmann.adesso.master.thesis.portlet.presenter.course.information;

import com.vaadin.ui.Notification;
import de.saschafeldmann.adesso.master.thesis.elearningimport.ImporterService;
import de.saschafeldmann.adesso.master.thesis.elearningimport.ImporterServiceImpl;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Language;
import de.saschafeldmann.adesso.master.thesis.portlet.model.QuestionGenerationSession;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.AbstractStepPresenter;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.i18n.Messages;
import de.saschafeldmann.adesso.master.thesis.portlet.view.course.contents.CourseContentsViewImpl;
import de.saschafeldmann.adesso.master.thesis.portlet.view.course.information.CourseInformationView;
import de.saschafeldmann.adesso.master.thesis.portlet.view.course.information.CourseInformationViewImpl;
import de.saschafeldmann.adesso.master.thesis.portlet.view.course.information.CourseInformationViewListener;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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
 * CourseInformationPresenterImpl: the presenter implementation which manages the {@link CourseInformationViewImpl} and
 * implements the {@link CourseInformationViewListener} so that the view can notify the presenter on several actions.
 */
@Component
@Scope("prototype")
public class CourseInformationPresenterImpl extends AbstractStepPresenter implements CourseInformationPresenter, CourseInformationViewListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseInformationPresenterImpl.class);

    private final CourseInformationView courseInformationView;
    private final ImporterService importerService;
    private Messages messages;

    /**
     * Creates a new CourseInformationPresenterImpl.
     * @param courseInformationViewImpl the managed view.
     */
    @Autowired
    public CourseInformationPresenterImpl(final CourseInformationViewImpl courseInformationViewImpl, final Messages messages,
                                          final ImporterServiceImpl importerService) {
        if (null == courseInformationViewImpl) {
            throw new NullPointerException("The argument courseInformationView must not be null!");
        }

        this.courseInformationView = courseInformationViewImpl;
        this.messages = messages;
        this.importerService = importerService;
    }

    @Override
    public CourseInformationView initializeView() {
        this.courseInformationView.setMenuListener(this);
        this.courseInformationView.setCurrentSessionStatus(questionGenerationSession.getStatus());
        this.courseInformationView.setViewListener(this);
        this.courseInformationView.setViewMode(CourseInformationViewImpl.ViewMode.NEW_COURSE);
        this.courseInformationView.reset();
        return this.courseInformationView;
    }

    @Override
    public void onNextButtonClicked() {
        LOGGER.info("onNextButtonClicked()");

        try {
            updateCourseModel();

            getNavigator().navigateTo(CourseContentsViewImpl.VIEW_NAME);
        } catch (Exception e) {
            LOGGER.error("onNextButtonClicked(): could not create or update the course - exception {} occured:\n{}",
                    e.getMessage(), ExceptionUtils.getStackTrace(e));

            Notification.show(
                    messages.getCourseInformationViewErrorNotificationTitle(),
                    messages.getCourseInformationViewErrorNotificationText(),
                    Notification.Type.ERROR_MESSAGE
            );
        }
    }

    /**
     * Creates or updates the course model.
     */
    private void updateCourseModel() {
        if (null == this.questionGenerationSession.getCourse()) {
            createNewCourseModelInSession();
        } else {
            updateCourseModelInSession();
        }
    }

    private void createNewCourseModelInSession() {
        // create
        this.questionGenerationSession.setCourse(importerService.buildNewCourseInstance(
                courseInformationView.getInputTitle(),
                courseInformationView.getInputViewUrl(),
                getLanguage(courseInformationView.getInputLanguage())));
    }

    private void updateCourseModelInSession() {
        // update
        this.questionGenerationSession.getCourse().setTitle(courseInformationView.getInputTitle());
        this.questionGenerationSession.getCourse().setViewUrl(courseInformationView.getInputViewUrl());
        this.questionGenerationSession.getCourse().setLanguage(getLanguage(courseInformationView.getInputLanguage()));
    }

    private Language getLanguage(String inputLanguage) {
        if (inputLanguage.equals(messages.getCourseInformationViewEnglishLanguageLabel())) {
            return Language.ENGLISH;
        } else {
            return Language.GERMAN;
        }
    }

    @Override
    public void onNewSessionButtonClicked() {
        LOGGER.info("onNewSessionButtonClicked()");
    }

    @Override
    public void onViewFocus() {
        LOGGER.info("onViewFocus()");

        if (questionGenerationSession.getStatus().equals(QuestionGenerationSession.Status.STARTED)) {
            this.courseInformationView.setViewMode(CourseInformationViewImpl.ViewMode.NEW_COURSE);
        } else {
            this.courseInformationView.setCourseTitle(questionGenerationSession.getCourse().getTitle());
            this.courseInformationView.setViewMode(CourseInformationViewImpl.ViewMode.EDIT_COURSE);
        }

        this.courseInformationView.setCurrentSessionStatus(questionGenerationSession.getStatus());
        this.courseInformationView.reset();
    }

}

package de.saschafeldmann.adesso.master.thesis.portlet.presenter.course.contents;

import com.vaadin.ui.Notification;
import de.saschafeldmann.adesso.master.thesis.elearningimport.ImporterService;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Course;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.AbstractStepPresenter;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.i18n.Messages;
import de.saschafeldmann.adesso.master.thesis.portlet.view.course.contents.CourseContentsView;
import de.saschafeldmann.adesso.master.thesis.portlet.view.course.contents.CourseContentsViewListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
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
 * Implementation of {@link CourseContentsPresenter}.
 */
@Component
@Scope("prototype")
public class CourseContentsPresenterImpl extends AbstractStepPresenter implements CourseContentsPresenter, CourseContentsViewListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseContentsPresenterImpl.class);

    private final CourseContentsView courseContentsView;
    private Course courseModel;
    private final Messages messages;
    private final ImporterService importerService;

    /**
     * Creates a new CourseContentsPresenterImpl.
     * @param courseContentsView the view
     * @param messages the messages
     */
    @Autowired
    public CourseContentsPresenterImpl(final CourseContentsView courseContentsView,
                                       final Messages messages,
                                       final ImporterService importerService) {
        this.courseContentsView = courseContentsView;
        this.messages = messages;
        this.importerService = importerService;
    }

    @Override
    public CourseContentsView initializeView() {
        this.courseContentsView.setMenuListener(this);
        this.courseContentsView.setViewListener(this);
        this.courseContentsView.setCurrentSessionStatus(questionGenerationSession.getStatus());

        this.courseContentsView.reset();

        return this.courseContentsView;
    }

    @Override
    public void onContentFileUploaded(File contentFile) {
        LOGGER.info("onContentFileUploaded()");

        courseContentsView.displayFileUploadInformation(messages.getCourseContentsViewUploadFileSuccessNotificationText(contentFile.getName()));

        try {
            importerService.addOrReplaceLearningContentByFile(questionGenerationSession.getCourse(), contentFile);

            courseContentsView.displayFileUploadInformation(messages.getCourseContentsViewUploadFileProcessSuccessNotificationText(contentFile.getName()));
            updateLearningContentsInView();
        } catch (Exception e) {
            LOGGER.error("onContentFileUploaded(): could extract the raw text from file {} - exception occured:\n{}",
                    contentFile.getName(),
                    e);
            courseContentsView.displayFileUploadError(messages.getCourseContentsViewUploadFileProcessErrorNotificationText(contentFile.getName()));
        } finally {
            // delete the uploaded file since the contents were extracted or it can't be processed
            if (!contentFile.delete()) {
                LOGGER.error("onContentFileUploaded(): could not delete file {} after operations.",
                        contentFile.getName());
            }
        }
    }

    @Override
    public void onContentFileChangeClick(LearningContent learningContent, String newRawText) {
        LOGGER.info("onContentFileChangeClick()");

        try {
            importerService.addOrReplaceLearningContentWithType(questionGenerationSession.getCourse(),
                    learningContent.getTitle(),
                    newRawText,
                    LearningContent.Type.FILE);

            updateLearningContentsInView();
        } catch (Exception e) {
            LOGGER.error("onContentFileChangeClick(): could not change the raw text - exception occured:\n{}",
                    e);

            Notification.show(
                    messages.getCourseContentsViewAddRawTextErrorNotificationTitle(),
                    messages.getCourseContentsViewAddRawTextErrorNotificationText(),
                    Notification.Type.ERROR_MESSAGE
            );
        }
    }

    @Override
    public void onContentFileDeleteClick(LearningContent learningContent) {
        LOGGER.info("onContentFileDeleteClick()");

        try {
            removeLearningContent(learningContent);
        } catch (Exception e) {
            LOGGER.error("onContentFileDeleteClick(): could not delete the raw text - exception occured:\n{}",
                    e);

            Notification.show(
                    messages.getCourseContentsViewDeleteRawTextErrorNotificationTitle(),
                    messages.getCourseContentsViewDeleteRawTextErrorNotificationText(),
                    Notification.Type.ERROR_MESSAGE
            );
        }
    }

    @Override
    public void onContentRawTextChangeClick(LearningContent learningContent, String textareaInput) {
        LOGGER.info("onContentRawTextChangeClick()");

        try {
            importerService.addOrReplaceLearningContentByRawtext(questionGenerationSession.getCourse(),
                    learningContent.getTitle(),
                    textareaInput);

            updateLearningContentsInView();
        } catch (Exception e) {
            LOGGER.error("onContentRawTextChangeClick(): could not cbange the raw text - exception occured:\n{}",
                    e);

            Notification.show(
                    messages.getCourseContentsViewAddRawTextErrorNotificationTitle(),
                    messages.getCourseContentsViewAddRawTextErrorNotificationText(),
                    Notification.Type.ERROR_MESSAGE
            );
        }
    }

    @Override
    public void onContentRawTextDeleteClick(LearningContent learningContent) {
        LOGGER.info("onContentRawTextDeleteClick()");

        try {
            removeLearningContent(learningContent);
        } catch (Exception e) {
            LOGGER.error("onContentRawTextDeleteClick(): could not delete the raw text - exception occured:\n{}",
                    e);

            Notification.show(
                    messages.getCourseContentsViewDeleteRawTextErrorNotificationTitle(),
                    messages.getCourseContentsViewDeleteRawTextErrorNotificationText(),
                    Notification.Type.ERROR_MESSAGE
            );
        }
    }

    @Override
    public void onNextButtonClicked() {

    }

    @Override
    public void onContentRawTextAddClick(final String contentTitle, final String contentRawText) {
        try {
            importerService.addOrReplaceLearningContentByRawtext(questionGenerationSession.getCourse(),
                    contentTitle,
                    contentRawText);

            updateLearningContentsInView();
        } catch (Exception e) {
            LOGGER.error("onContentRawTextAddClick(): could not add the raw text - exception occured:\n{}",
                    e);

            Notification.show(
                    messages.getCourseContentsViewAddRawTextErrorNotificationTitle(),
                    messages.getCourseContentsViewAddRawTextErrorNotificationText(),
                    Notification.Type.ERROR_MESSAGE
            );
        }
    }

    @Override
    public void onViewFocus() {
        LOGGER.info("onViewFocus()");

        if (null != questionGenerationSession.getCourse()) {
            this.courseContentsView.setCourseTitle(questionGenerationSession.getCourse().getTitle());
            this.courseContentsView.setCurrentSessionStatus(questionGenerationSession.getStatus());
            this.courseContentsView.reset();
        }
    }

    private void removeLearningContent(LearningContent learningContent) {
        importerService.removeLearningContent(questionGenerationSession.getCourse(), learningContent);

        updateLearningContentsInView();
    }

    private void updateLearningContentsInView() {
        updateCourseFileUploadRawTexts();
        updateCourseRawTexts();
    }

    /**
     * Updates the direct raw texts.
     */
    private void updateCourseRawTexts() {
        courseContentsView.showContentRawTexts(getDirectRawTextsFromSession());
        courseContentsView.reset();
    }

    /**
     * Updates the file upload raw texts.
     */
    private void updateCourseFileUploadRawTexts() {
        courseContentsView.showContentFiles(getFileUploadRawTextsFromSession());
        courseContentsView.reset();
    }

    private List<LearningContent> getDirectRawTextsFromSession() {
        return getByType(LearningContent.Type.DIRECT_RAWTEXT);
    }

    private List<LearningContent> getFileUploadRawTextsFromSession() {
        return getByType(LearningContent.Type.FILE);
    }

    private List<LearningContent> getByType(LearningContent.Type type) {
        List<LearningContent> courseRawTexts = new ArrayList<LearningContent>();

        for (LearningContent rawTextContent: questionGenerationSession.getCourse().getLearningContents()) {
            if (rawTextContent.getType().equals(type)) {
                courseRawTexts.add(rawTextContent);
            }
        }

        return courseRawTexts;
    }

}

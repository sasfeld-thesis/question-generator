package de.saschafeldmann.adesso.master.thesis.portlet.presenter.course.contents;

import com.vaadin.ui.Notification;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Course;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.AbstractStepPresenter;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.Messages;
import de.saschafeldmann.adesso.master.thesis.portlet.view.course.contents.CourseContentsView;
import de.saschafeldmann.adesso.master.thesis.portlet.view.course.contents.CourseContentsViewListener;
import de.saschafeldmann.adesso.master.thesis.portlet.view.course.information.CourseInformationViewListener;
import org.apache.commons.lang3.exception.ExceptionUtils;
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

    /**
     * Creates a new CourseContentsPresenterImpl.
     * @param courseContentsView the view
     * @param messages the messages
     */
    @Autowired
    public CourseContentsPresenterImpl(final CourseContentsView courseContentsView,
                                       final Messages messages) {
        this.courseContentsView = courseContentsView;
        this.messages = messages;
    }

    /**
     * @see CourseContentsPresenter#initializeView()
     */
    public CourseContentsView initializeView() {
        this.courseContentsView.setMenuListener(this);
        this.courseContentsView.setViewListener(this);
        this.courseContentsView.setCurrentSessionStatus(questionGenerationSession.getStatus());

        this.courseContentsView.reset();

        return this.courseContentsView;
    }

    /**
     * @see CourseContentsViewListener#onContentFileUploaded(File)
     */
    public void onContentFileUploaded(File contentFile) {

    }

    /**
     * @see CourseContentsViewListener#onContentFileChangeClick(LearningContent)
     */
    public void onContentFileChangeClick(LearningContent learningContent) {

    }

    /**
     * @see CourseContentsViewListener#onContentFileDeleteClick(LearningContent)
     */
    public void onContentFileDeleteClick(LearningContent learningContent) {

    }

    /**
     * @see CourseContentsViewListener#onContentRawTextChangeClick(LearningContent)
     */
    public void onContentRawTextChangeClick(LearningContent learningContent) {

    }

    /**
     * @see CourseContentsViewListener#onContentRawTextDeleteClick(LearningContent)
     */
    public void onContentRawTextDeleteClick(LearningContent learningContent) {

    }

    /**
     * @see CourseContentsViewListener#onNextButtonClicked()
     */
    public void onNextButtonClicked() {

    }

    /**
     * @see CourseContentsViewListener#onContentRawTextAddClick(String, String)
     */
    public void onContentRawTextAddClick(final String contentTitle, final String contentRawText) {
        try {
            LearningContent learningContent = buildLearningContent(contentTitle, contentRawText, LearningContent.Type.DIRECT_RAWTEXT);
            questionGenerationSession.getCourse().addOrReplaceLearningContent(learningContent);

            updateCourseRawTexts();
        } catch (Exception e) {
            LOGGER.error("onContentRawTextAddClick(): could not add the raw text - exception {} occured:\n{}",
                    e.getMessage(), ExceptionUtils.getStackTrace(e));

            Notification.show(
                    messages.getCourseContentsViewAddRawTextErrorNotificationTitle(),
                    messages.getCourseContentsViewAddRawTextErrorNotificationText(),
                    Notification.Type.ERROR_MESSAGE
            );
        }
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

    private LearningContent buildLearningContent(String contentTitle, String contentRawText, LearningContent.Type type) {
        return new LearningContent.LearningContentBuilder()
                .withTitle(contentTitle)
                .withRawText(contentRawText)
                .withType(type)
                .build();
    }

    /**
     * @see CourseInformationViewListener#onViewFocus()
     */
    public void onViewFocus() {
        LOGGER.info("onViewFocus()");

        if (null != questionGenerationSession.getCourse()) {
            this.courseContentsView.setCourseTitle(questionGenerationSession.getCourse().getTitle());
            this.courseContentsView.setCurrentSessionStatus(questionGenerationSession.getStatus());
            this.courseContentsView.reset();
        }
    }
}

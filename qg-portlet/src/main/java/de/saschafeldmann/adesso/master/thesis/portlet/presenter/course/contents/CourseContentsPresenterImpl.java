package de.saschafeldmann.adesso.master.thesis.portlet.presenter.course.contents;

import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Course;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.portlet.model.QuestionGenerationSession;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.AbstractStepPresenter;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.course.information.CourseInformationPresenterImpl;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.Messages;
import de.saschafeldmann.adesso.master.thesis.portlet.view.course.contents.CourseContentsView;
import de.saschafeldmann.adesso.master.thesis.portlet.view.course.contents.CourseContentsViewListener;
import de.saschafeldmann.adesso.master.thesis.portlet.view.course.information.CourseInformationViewImpl;
import de.saschafeldmann.adesso.master.thesis.portlet.view.course.information.CourseInformationViewListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.File;

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
     * @see CourseContentsViewListener#onContentRawTextAddClick()
     */
    public void onContentRawTextAddClick() {

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

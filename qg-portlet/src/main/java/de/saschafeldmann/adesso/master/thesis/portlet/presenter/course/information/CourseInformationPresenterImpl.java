package de.saschafeldmann.adesso.master.thesis.portlet.presenter.course.information;

import com.vaadin.navigator.Navigator;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.AbstractStepPresenter;
import de.saschafeldmann.adesso.master.thesis.portlet.view.course.information.CourseInformationView;
import de.saschafeldmann.adesso.master.thesis.portlet.view.course.information.CourseInformationViewImpl;
import de.saschafeldmann.adesso.master.thesis.portlet.view.course.information.CourseInformationViewListener;
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
    private Navigator navigator;

    /**
     * Creates a new CourseInformationPresenterImpl.
     * @param courseInformationViewImpl the managed view.
     */
    @Autowired
    public CourseInformationPresenterImpl(final CourseInformationViewImpl courseInformationViewImpl) {
        if (null == courseInformationViewImpl) {
            throw new NullPointerException("The argument courseInformationView must not be null!");
        }

        this.courseInformationView = courseInformationViewImpl;
    }

    /**
     * @see CourseInformationPresenter#initializeView()
     */
    public CourseInformationView initializeView() {
        this.courseInformationView.setMenuListener(this);
        this.courseInformationView.setViewListener(this);
        this.courseInformationView.setViewMode(CourseInformationViewImpl.ViewMode.NEW_COURSE);
        this.courseInformationView.reset();
        return this.courseInformationView;
    }

    /**
     * @see CourseInformationViewListener#onNextButtonClicked()
     */
    public void onNextButtonClicked() {
        LOGGER.info("onNextButtonClicked()");
    }

    /**
     * @see CourseInformationViewListener#onNewSessionButtonClicked()
     */
    public void onNewSessionButtonClicked() {
        LOGGER.info("onNewSessionButtonClicked()");
    }

}

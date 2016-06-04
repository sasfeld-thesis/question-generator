package de.saschafeldmann.adesso.master.thesis.portlet;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinPortlet;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.WrappedPortletSession;
import com.vaadin.ui.UI;
import de.saschafeldmann.adesso.master.thesis.portlet.model.QuestionGenerationSession;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.course.contents.CourseContentsPresenter;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.course.contents.CourseContentsPresenterImpl;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.course.information.CourseInformationPresenter;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.course.information.CourseInformationPresenterImpl;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.preprocesses.PreprocessesPresenter;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.preprocesses.PreprocessesPresenterImpl;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.VaadinProperties;
import de.saschafeldmann.adesso.master.thesis.portlet.util.Factory;
import de.saschafeldmann.adesso.master.thesis.portlet.view.course.contents.CourseContentsView;
import de.saschafeldmann.adesso.master.thesis.portlet.view.course.contents.CourseContentsViewImpl;
import de.saschafeldmann.adesso.master.thesis.portlet.view.course.information.CourseInformationView;
import de.saschafeldmann.adesso.master.thesis.portlet.view.course.information.CourseInformationViewImpl;
import de.saschafeldmann.adesso.master.thesis.portlet.view.preprocesses.PreprocessesView;
import de.saschafeldmann.adesso.master.thesis.portlet.view.preprocesses.PreprocessesViewImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.portlet.context.PortletApplicationContextUtils;

import javax.portlet.PortletContext;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  17.05.2016
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
 * This class defines the (vaadin) portlet. It is the single entry point for the portlet handling logic.
 * An instance is created when the user enters the page containing the portlet for the first time or on URL redirect.
 * The UI instance is bound to a single browser tab and will be killed if a page reload is done.
 * All views belonging to this portlet are dynamically rendered (via Vaadin and its built-in AJAX-based engine) so that no
 * reload is necessary.
 *
 *
 */
@Theme(VaadinProperties.THEME)
public class QuestionGeneratorPortlet extends UI {
    private Navigator viewNavigator;
    private QuestionGenerationSession questionGenerationSession;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        initSession();

        ApplicationContext applicationContext = initializeApplicationContext(vaadinRequest);
        initializeViewNavigator();
        initializeViews(applicationContext);
    }

    private void initSession() {
        this.questionGenerationSession = Factory.newQuestionGenerationSession();
    }

    private ApplicationContext initializeApplicationContext(VaadinRequest vaadinRequest) {
        WrappedPortletSession wrappedPortletSession = (WrappedPortletSession) vaadinRequest.getWrappedSession();

         return PortletApplicationContextUtils.getRequiredWebApplicationContext(wrappedPortletSession.getPortletSession().getPortletContext());
    }

    private void initializeViewNavigator() {
        this.viewNavigator = new Navigator(this, this);
    }

    private void initializeViews(ApplicationContext applicationContext) {
        initializeCourseInformationView(applicationContext);
        initializeCourseContentsView(applicationContext);
        initializePreprocessesView(applicationContext);
    }

    private void initializeCourseInformationView(ApplicationContext applicationContext) {
        CourseInformationPresenter courseInformationPresenter = applicationContext.getBean(CourseInformationPresenterImpl.class);
        courseInformationPresenter.setQuestionGenerationSession(questionGenerationSession);
        courseInformationPresenter.setNavigator(this.viewNavigator);

        CourseInformationView courseInformationView = courseInformationPresenter.initializeView();
        this.viewNavigator.addView(CourseInformationViewImpl.VIEW_NAME, courseInformationView);
        // show the course information view initially
        this.viewNavigator.addView("", courseInformationView);

        // show the course information view initially
        setContent(courseInformationView.getRootComponent());
    }

    private void initializeCourseContentsView(ApplicationContext applicationContext) {
        CourseContentsPresenter courseContentsPresenter = applicationContext.getBean(CourseContentsPresenterImpl.class);
        courseContentsPresenter.setQuestionGenerationSession(questionGenerationSession);
        courseContentsPresenter.setNavigator(this.viewNavigator);

        CourseContentsView courseContentsView = courseContentsPresenter.initializeView();
        this.viewNavigator.addView(CourseContentsViewImpl.VIEW_NAME, courseContentsView);
    }

    private void initializePreprocessesView(ApplicationContext applicationContext) {
        PreprocessesPresenter preprocessesPresenter = applicationContext.getBean(PreprocessesPresenterImpl.class);
        preprocessesPresenter.setQuestionGenerationSession(questionGenerationSession);
        preprocessesPresenter.setNavigator(this.viewNavigator);

        PreprocessesView preprocessesView = preprocessesPresenter.initializeView();
        this.viewNavigator.addView(PreprocessesViewImpl.VIEW_NAME, preprocessesView);
    }

    /**
     * Gets the current portlet context.
     * @return
     */
    public static PortletContext getContext() {
        return VaadinPortlet.getCurrent().getPortletContext();
    }
}

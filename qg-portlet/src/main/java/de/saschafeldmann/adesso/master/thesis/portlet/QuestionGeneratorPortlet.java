package de.saschafeldmann.adesso.master.thesis.portlet;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.WrappedPortletSession;
import com.vaadin.ui.UI;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.course.contents.CourseContentsPresenter;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.course.contents.CourseContentsPresenterImpl;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.course.information.CourseInformationPresenter;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.course.information.CourseInformationPresenterImpl;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.VaadinProperties;
import de.saschafeldmann.adesso.master.thesis.portlet.view.course.contents.CourseContentsView;
import de.saschafeldmann.adesso.master.thesis.portlet.view.course.contents.CourseContentsViewImpl;
import de.saschafeldmann.adesso.master.thesis.portlet.view.course.information.CourseInformationView;
import de.saschafeldmann.adesso.master.thesis.portlet.view.course.information.CourseInformationViewImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.web.portlet.context.PortletApplicationContextUtils;

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
 */
@Theme(VaadinProperties.THEME)
public class QuestionGeneratorPortlet extends UI {
    private Navigator viewNavigator;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        ApplicationContext applicationContext = initializeApplicationContext(vaadinRequest);
        initializeViewNavigator();
        initializeViews(applicationContext);
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
    }

    private void initializeCourseInformationView(ApplicationContext applicationContext) {
        CourseInformationPresenter courseInformationPresenter = applicationContext.getBean(CourseInformationPresenterImpl.class);
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
        courseContentsPresenter.setNavigator(this.viewNavigator);

        CourseContentsView courseContentsView = courseContentsPresenter.initializeView();
        this.viewNavigator.addView(CourseContentsViewImpl.VIEW_NAME, courseContentsView);
    }
}

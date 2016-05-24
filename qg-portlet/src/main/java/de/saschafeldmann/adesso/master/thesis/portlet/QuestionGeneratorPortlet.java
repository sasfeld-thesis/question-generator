package de.saschafeldmann.adesso.master.thesis.portlet;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.WrappedPortletSession;
import com.vaadin.ui.UI;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.course.information.CourseInformationPresenter;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.course.information.CourseInformationPresenterImpl;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.VaadinProperties;
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
    private CourseInformationView courseInformationView;
    private ApplicationContext applicationContext;

    private ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        initializeApplicationContext(vaadinRequest);
        initializeViewNavigator();
        initializeViews();

        // show the course information view initially
        setContent(courseInformationView.getRootComponent());
    }

    private void initializeApplicationContext(VaadinRequest vaadinRequest) {
        WrappedPortletSession wrappedPortletSession = (WrappedPortletSession) vaadinRequest.getWrappedSession();

        this.applicationContext = PortletApplicationContextUtils.getRequiredWebApplicationContext(wrappedPortletSession.getPortletSession().getPortletContext());
    }

    private void initializeViewNavigator() {
        this.viewNavigator = new Navigator(this, this);
    }

    private void initializeViews() {
        initializeCourseInformationView();
    }

    private void initializeCourseInformationView() {
        CourseInformationPresenter courseInformationPresenter = getApplicationContext().getBean(CourseInformationPresenterImpl.class);
        courseInformationPresenter.setNavigator(this.viewNavigator);

        this.courseInformationView = courseInformationPresenter.initializeView();
        this.viewNavigator.addView(CourseInformationViewImpl.VIEW_NAME, courseInformationView);
        // show the course information view initially
        this.viewNavigator.addView("", courseInformationView);
    }
}

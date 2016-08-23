package de.saschafeldmann.adesso.master.thesis.portlet;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinPortlet;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.WrappedPortletSession;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import de.saschafeldmann.adesso.master.thesis.detection.model.CardinalRelationConcept;
import de.saschafeldmann.adesso.master.thesis.detection.model.FillInTheBlankTextConcept;
import de.saschafeldmann.adesso.master.thesis.portlet.model.QuestionGenerationSession;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.course.contents.CourseContentsPresenter;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.course.contents.CourseContentsPresenterImpl;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.course.information.CourseInformationPresenter;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.course.information.CourseInformationPresenterImpl;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.detection.edit.DetectionEditCardinalRelationConceptPresenterImpl;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.detection.edit.DetectionEditConceptPresenter;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.detection.edit.DetectionEditConceptsPresenter;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.detection.DetectionPresenter;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.detection.edit.DetectionEditFillInTheBlankConceptPresenterImpl;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.generation.QuestionGenerationPresenter;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.generation.edit.QuestionGenerationEditQuestionPresenter;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.options.OptionsPresenter;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.preprocesses.PreprocessesPresenter;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.preprocesses.PreprocessesPresenterImpl;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.VaadinProperties;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.i18n.Messages;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.i18n.MessagesBundle;
import de.saschafeldmann.adesso.master.thesis.portlet.util.Factory;
import de.saschafeldmann.adesso.master.thesis.portlet.view.course.contents.CourseContentsView;
import de.saschafeldmann.adesso.master.thesis.portlet.view.course.contents.CourseContentsViewImpl;
import de.saschafeldmann.adesso.master.thesis.portlet.view.course.information.CourseInformationView;
import de.saschafeldmann.adesso.master.thesis.portlet.view.course.information.CourseInformationViewImpl;
import de.saschafeldmann.adesso.master.thesis.portlet.view.detection.DetectionView;
import de.saschafeldmann.adesso.master.thesis.portlet.view.detection.DetectionViewImpl;
import de.saschafeldmann.adesso.master.thesis.portlet.view.generation.QuestionGenerationView;
import de.saschafeldmann.adesso.master.thesis.portlet.view.generation.QuestionGenerationViewImpl;
import de.saschafeldmann.adesso.master.thesis.portlet.view.preprocesses.PreprocessesView;
import de.saschafeldmann.adesso.master.thesis.portlet.view.preprocesses.PreprocessesViewImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.portlet.context.PortletApplicationContextUtils;

import javax.portlet.PortletContext;
import javax.portlet.PortletSession;
import java.util.Locale;
import java.util.Map;

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
public class QuestionGeneratorPortletVaadinUi extends UI {
    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionGeneratorPortletVaadinUi.class);

    private Navigator viewNavigator;
    private QuestionGenerationSession questionGenerationSession;
    private Messages messages;
    private MessagesBundle messagesBundle;
    private CourseContentsPresenter courseContentsPresenter;
    private CourseInformationPresenter courseInformationPresenter;
    private OptionsPresenter optionsPresenter;
    private PreprocessesPresenter preprocessesPresenter;
    private DetectionPresenter detectionPresenter;
    private DetectionEditConceptsPresenter detectionEditConceptsPresenter;
    private DetectionEditFillInTheBlankConceptPresenterImpl detetectionEditFillTextConceptPresenter;
    private DetectionEditCardinalRelationConceptPresenterImpl detetectionEditCardinalRelationConceptPresenter;
    private QuestionGenerationPresenter questionGenerationPresenter;
    private QuestionGenerationEditQuestionPresenter questionGenerationEditQuestionPresenter;
    private Locale currentLocale;
    private PortletSession portletSession;
    private ApplicationContext applicationContext;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        this.applicationContext = initializeApplicationContext(vaadinRequest);
        // make sure to inject dependencies before initializing views
        injectOtherDependencies(applicationContext);

        initSession(vaadinRequest, applicationContext);
        initializeViewNavigator();
        initializeViews(applicationContext);
    }

    private void initSession(VaadinRequest vaadinRequest, ApplicationContext applicationContext) {
        this.currentLocale = vaadinRequest.getLocale(); // make sure to initialize before messagesBundle!
        this.questionGenerationSession = Factory.newQuestionGenerationSession();
        initializeMessageBundle(this.currentLocale);
    }

    private void initializeMessageBundle(Locale locale) {
        this.messagesBundle = new MessagesBundle(locale);
    }

    private ApplicationContext initializeApplicationContext(VaadinRequest vaadinRequest) {
        WrappedPortletSession wrappedPortletSession = (WrappedPortletSession) vaadinRequest.getWrappedSession();
        this.portletSession = wrappedPortletSession.getPortletSession();

        return PortletApplicationContextUtils.getRequiredWebApplicationContext(portletSession.getPortletContext());
    }

    private void initializeViewNavigator() {
        this.viewNavigator = new Navigator(this, this);
    }

    private void initializeViews(ApplicationContext applicationContext) {
        initializeOptionsView(applicationContext);
        initializeCourseInformationView(applicationContext);
        initializeCourseContentsView(applicationContext);
        initializePreprocessesView(applicationContext);
        initializeDetectionView(applicationContext);
        initializeTestQuestionGenerationView(applicationContext);
    }

    private void initializeOptionsView(ApplicationContext applicationContext) {
        this.optionsPresenter = applicationContext.getBean(OptionsPresenter.class);
        optionsPresenter.setQuestionGenerationSession(questionGenerationSession);
    }


    private void initializeCourseInformationView(ApplicationContext applicationContext) {
        this.courseInformationPresenter = applicationContext.getBean(CourseInformationPresenterImpl.class);
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
        this.courseContentsPresenter = applicationContext.getBean(CourseContentsPresenterImpl.class);
        courseContentsPresenter.setQuestionGenerationSession(questionGenerationSession);
        courseContentsPresenter.setNavigator(this.viewNavigator);

        CourseContentsView courseContentsView = courseContentsPresenter.initializeView();
        this.viewNavigator.addView(CourseContentsViewImpl.VIEW_NAME, courseContentsView);
    }

    private void initializePreprocessesView(ApplicationContext applicationContext) {
        this.preprocessesPresenter = applicationContext.getBean(PreprocessesPresenterImpl.class);
        preprocessesPresenter.setQuestionGenerationSession(questionGenerationSession);
        preprocessesPresenter.setNavigator(this.viewNavigator);

        PreprocessesView preprocessesView = preprocessesPresenter.initializeView();
        this.viewNavigator.addView(PreprocessesViewImpl.VIEW_NAME, preprocessesView);
    }

    private void initializeDetectionView(ApplicationContext applicationContext) {
        this.detectionPresenter = applicationContext.getBean(DetectionPresenter.class);
        detectionPresenter.setQuestionGenerationSession(questionGenerationSession);
        detectionPresenter.setNavigator(viewNavigator);

        DetectionView detectionView = detectionPresenter.initializeView();
        viewNavigator.addView(DetectionViewImpl.VIEW_NAME, detectionView);

        initializeDetectionSubViews(applicationContext);
    }

    private void initializeDetectionSubViews(ApplicationContext applicationContext) {
        // all presenters must be initialized before detectionEditConceptsPresenter !!!
        this.detetectionEditCardinalRelationConceptPresenter = applicationContext.getBean(DetectionEditCardinalRelationConceptPresenterImpl.class);
        this.detetectionEditFillTextConceptPresenter = applicationContext.getBean(DetectionEditFillInTheBlankConceptPresenterImpl.class);

        this.detectionEditConceptsPresenter = applicationContext.getBean(DetectionEditConceptsPresenter.class);
    }

    private void initializeTestQuestionGenerationView(ApplicationContext applicationContext) {
        this.questionGenerationPresenter = applicationContext.getBean(QuestionGenerationPresenter.class);
        this.questionGenerationPresenter.setQuestionGenerationSession(questionGenerationSession);
        questionGenerationPresenter.setNavigator(viewNavigator);

        QuestionGenerationView questionGenerationView = questionGenerationPresenter.initializeView();
        viewNavigator.addView(QuestionGenerationViewImpl.VIEW_NAME, questionGenerationView);

        initializeGenerationSubViews(applicationContext);
    }

    private void initializeGenerationSubViews(ApplicationContext applicationContext) {
        this.questionGenerationEditQuestionPresenter = applicationContext.getBean(QuestionGenerationEditQuestionPresenter.class);
        this.questionGenerationEditQuestionPresenter.setQuestionGenerationSession(questionGenerationSession);
        this.questionGenerationEditQuestionPresenter.setListener(questionGenerationPresenter);
    }

    private void injectOtherDependencies(ApplicationContext applicationContext) {
        messages = applicationContext.getBean(Messages.class);
    }

    public Messages getMessages() {
        return messages;
    }

    /**
     * Gets the current portlet context.
     * @return
     */
    public static PortletContext getContext() {
        return VaadinPortlet.getCurrent().getPortletContext();
    }

    /**
     * Get the current question generator portlet
     * @return
     */
    public static QuestionGeneratorPortletVaadinUi getCurrentPortletVaadinUi() {
        return (QuestionGeneratorPortletVaadinUi) getCurrent();
    }

    /**
     * Gets the options presenter.
     * @return the options presenter.
     */
    public OptionsPresenter getOptionsPresenter() {
        return optionsPresenter;
    }

    /**
     * Gets the detection presenter instance.
     * @return detection presenter
     */
    public DetectionPresenter getDetectionPresenter() {
        return detectionPresenter;
    }

    /**
     * Gets the detection edit concepts presenter.
     * @return presenter
     */
    public DetectionEditConceptsPresenter getDetectionEditConceptsPresenter() {
        return detectionEditConceptsPresenter;
    }

    /**
     * Gets the detection edit {@link FillInTheBlankTextConcept} presenter.
     * @return presenter
     */
    public DetectionEditConceptPresenter<FillInTheBlankTextConcept> getDetectionEditFillTextConceptPresenter() {
        return detetectionEditFillTextConceptPresenter;
    }

    /**
     * Gets the detection edit {@link CardinalRelationConcept} presenter.
     * @return presenter
     */
    public DetectionEditConceptPresenter<CardinalRelationConcept> getDetectionEditCardinalRelationConceptPresenter() {
        return detetectionEditCardinalRelationConceptPresenter;
    }

    /**
     * Gets the question edit presenter.
     * @return presenter
     */
    public QuestionGenerationEditQuestionPresenter getQuestionGenerationEditQuestionPresenter() {
        return questionGenerationEditQuestionPresenter;
    }
    /**
     * Resets all inputs done by the user so far.
     */
    public void resetViews() {
        courseInformationPresenter.getView().refreshView();
        courseContentsPresenter.getView().refreshView();
        preprocessesPresenter.getView().refreshView();
        detectionPresenter.getView().refreshView();
        questionGenerationPresenter.getView().refreshView();
        optionsPresenter.getView().reset();
    }

    /**
     * Gets the session instance.
     * @return the {@link QuestionGenerationSession}
     */
    public QuestionGenerationSession getQuestionGenerationSession() {
        return questionGenerationSession;
    }

    /**
     * Gets the current locale.
     * @return the current locale
     */
    public Locale getCurrentLocale() {
        return currentLocale;
    }

    /**
     * Gets the localized message bundle.
     * @return the localized message bundle
     */
    public MessagesBundle getMessagesBundle() {
        Map<String, Object> attributeMap = portletSession.getAttributeMap();

        Object languageInSessionAttribute = portletSession.getAttribute(QuestionGeneratorPortlet.PORTLET_SESSION_ATTRIBUTE_LANGUAGE, PortletSession.APPLICATION_SCOPE);

        if (null != languageInSessionAttribute
                && !languageInSessionAttribute.equals(currentLocale)) {
            reinitializeUi((Locale) languageInSessionAttribute);
        }

        return messagesBundle;
    }

    private void reinitializeUi(Locale languageInSessionAttribute) {
        // makre sure to reinitialize message bundle if the language was changed by the user
        currentLocale = languageInSessionAttribute;

        LOGGER.debug("getMessageBundle(): will set new locale {}", currentLocale);
        initializeMessageBundle(currentLocale);

        // detach the current UI and reload the current page
        getSession().close();
    }
}

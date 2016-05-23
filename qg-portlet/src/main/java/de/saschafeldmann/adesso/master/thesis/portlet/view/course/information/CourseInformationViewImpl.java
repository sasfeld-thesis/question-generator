package de.saschafeldmann.adesso.master.thesis.portlet.view.course.information;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.Messages;
import de.saschafeldmann.adesso.master.thesis.portlet.view.AbstractStepView;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.VersionLabel;
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
 * Course information dialog view.
 */
@Component
@Scope("prototype")
public class CourseInformationViewImpl extends AbstractStepView implements View, CourseInformationView {

    public static final String VIEW_NAME = "CourseInformationView";

    @Autowired
    public CourseInformationViewImpl(final Messages messages, final VersionLabel versionLabel) {
        super(messages, versionLabel);
    }

    /**
     * @see View#enter(ViewChangeListener.ViewChangeEvent)
     */
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

    }

    /**
     * @see CourseInformationView#reset()
     */
    public void reset() {
        super.reset();
    }

    /**
     * @see CourseInformationView#getRootComponent()
     */
    public com.vaadin.ui.Component getRootComponent() {
        return this;
    }
}

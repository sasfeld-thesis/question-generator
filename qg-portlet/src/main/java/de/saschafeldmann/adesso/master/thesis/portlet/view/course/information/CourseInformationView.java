package de.saschafeldmann.adesso.master.thesis.portlet.view.course.information;

import com.vaadin.navigator.View;
import com.vaadin.ui.Component;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.course.information.CourseInformationPresenter;
import de.saschafeldmann.adesso.master.thesis.portlet.view.ViewWithMenu;

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
 * The course information view interface.
 * The view is managed by the {@link CourseInformationPresenter}.
 */
public interface CourseInformationView extends ViewWithMenu {

    /**
     * Initialize or reset the whole view.
     * Make sure to clear the current state.
     */
    void reset();

    /**
     * Gets the root component of this view (the view itself).
     * @return
     */
    Component getRootComponent();
}

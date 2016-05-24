package de.saschafeldmann.adesso.master.thesis.portlet.presenter.course.contents;

import de.saschafeldmann.adesso.master.thesis.portlet.presenter.VaadinViewPresenter;
import de.saschafeldmann.adesso.master.thesis.portlet.view.course.contents.CourseContentsView;

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
 * Interface for the course contents dialog step.
 * The user can add learning contents for his/her course using the file upload and raw text extraction service or by directly entering raw text.
 */
public interface CourseContentsPresenter extends VaadinViewPresenter {

    /**
     * Initializes and returns the view.
     * @return the {@link CourseContentsView}
     */
    CourseContentsView initializeView();
}

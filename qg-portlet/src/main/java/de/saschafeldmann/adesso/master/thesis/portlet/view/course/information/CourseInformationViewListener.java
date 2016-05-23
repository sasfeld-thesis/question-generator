package de.saschafeldmann.adesso.master.thesis.portlet.view.course.information;

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
 * Interface for the course information dialog step.
 *
 * Here, the user can enter information on the course for which he or she wants to trigger the question generation process.
 */
public interface CourseInformationViewListener {

    /**
     * On next button clicked action.
     */
    void onNextButtonClicked();

    /**
     * On new session button clicked action.
     */
    void onNewSessionButtonClicked();
}

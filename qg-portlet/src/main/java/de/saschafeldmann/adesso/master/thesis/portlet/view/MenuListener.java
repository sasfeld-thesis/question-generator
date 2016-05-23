package de.saschafeldmann.adesso.master.thesis.portlet.view;

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
 * Interface for menu listeners.
 * Menu listeners are informed on menu item clicks.
 */
public interface MenuListener {
    /**
     * Triggered if the menu item on course information was clicked.
     */
    void onCourseInformationClicked();

    /**
     * Triggered if the menu item on contents was clicked.
     */
    void onContentsClicked();

    /**
     * Triggered if the menu item on preprocesses was clicked.
     */
    void onPreprocessesClicked();

    /**
     * Triggered if the menu item on detectionn was clicked.
     */
    void onDetectionClicked();

    /**
     * Triggered if the menu item on question generation was clicked.
     */
    void onQuestionGenerationClicked();
}

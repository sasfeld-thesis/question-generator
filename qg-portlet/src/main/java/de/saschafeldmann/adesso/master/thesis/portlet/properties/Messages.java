package de.saschafeldmann.adesso.master.thesis.portlet.properties;

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
 * Messages instance.
 */
@Component
@Scope("singleton")
public class Messages {

    /**
     * Gets the menu item label.
     * @return String
     */
    public String getMenuItemCourseInformationLabel() {
        return "Schulungsinformationen";
    }
    /**
     * Gets the menu item label.
     * @return String
     */
    public String getMenuItemContentsLabel() {
        return "Inhalte einlesen";
    }

    /**
     * Gets the menu item label.
     * @return String
     */
    public String getMenuItemPreprocessesLabel() {
        return "Präprozesse";
    }
    /**
     * Gets the menu item label.
     * @return String
     */
    public String getMenuItemDetectionLabel() {
        return "Detektion";
    }

    /**
     * Gets the menu item label.
     * @return String
     */
    public String getMenuItemQuestionGenerationLabel() {
        return "Fragen-Generierung";
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getCourseInformationViewNewCourseInfoText() {
        return "Sie haben noch keine Schulung angelegt.<br />Mit diesem Dialog legen Sie eine an und starten so Ihre Sitzung.";
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getCourseInformationViewIntroductionText() {
        return "Geben Sie hier eine Schulung mit ihren Rahmendaten an.";
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getCourseInformationViewCourseTitleLabel() {
        return "Schulungstitel:";
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getCourseInformationViewCourseUrlLabel() {
        return "URL:";
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getCourseInformationViewCourseLanguageLabel() {
        return "Primäre Sprache:";
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getCourseInformationViewBtnNextLabel() {
        return "Weiter";
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getCourseInformationViewBtnNewSessionLabel() {
        return "Neue Sitzung starten";
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getCourseInformationViewEnglishLanguageLabel() {
        return "Englisch";
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getCourseInformationViewGermanLanguageLabel() {
        return "Deutsch";
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getCourseInformationViewErrorNotificationTitle() {
        return "Fehler";
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getCourseInformationViewErrorNotificationText() {
        return "Der Kurs konnte nicht erstellt werden. Bitte prüfen Sie ihre Eingaben!";
    }
}

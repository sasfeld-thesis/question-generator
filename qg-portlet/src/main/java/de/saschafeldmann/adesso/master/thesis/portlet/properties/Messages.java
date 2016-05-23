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
}

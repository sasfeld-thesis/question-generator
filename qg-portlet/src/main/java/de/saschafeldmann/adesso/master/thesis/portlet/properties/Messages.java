package de.saschafeldmann.adesso.master.thesis.portlet.properties;

import org.springframework.cglib.util.StringSwitcher;
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
    public String getCourseInformationViewEditCourseInfoText(String... args) {
        return String.format("Sie arbeiten gerade mit der Schulung '%s'.<br />Mit diesem Dialog können Sie die Schulungsinformationen " +
                "bearbeiten oder eine neue Sitzung starten.", args);
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

    /**
     * Gets the text.
     * @return String
     */
    public String getCourseContentsViewIntroductionText() {
        return "Füllen Sie die Schulung mit Inhalten.<br />" +
                "Sie können entweder Dokumente verschiedener Typen (PDF, DOC) hochladen, aus denen die Volltexte entnommen werden, oder direkt Rohtexte eingeben.";
    }


    /**
     * Gets the text.
     * @return String
     */
    public String getCourseContentsViewAccordionDocumentsTitleLabel() {
        return "Dokumente hochladen";
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getCourseContentsViewAccordionDocumentsFileUploadLabel() {
        return "Datei:";
    }
    /**
     * Gets the text.
     * @return String
     */
    public String getCourseContentsViewAccordionDocumentsUploadedListLabel() {
        return "Hochgeladen:";
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getCourseContentsViewAccordionRawTextsTitleLabel() {
        return "Rohtext eingeben";
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getCourseContentsViewAccordionRawTextsTitleInputLabel() {
        return "Titel:";
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getCourseContentsViewAccordionRawTextsRawTextInputLabel() {
        return "Rohtext:";
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getCourseContentsViewAccordionRawTextsAddButtonLabel() {
        return "Hinzufügen";
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getCourseContentsViewAccordionRawTextsAddedLabel() {
        return "Hinzugefügt:";
    }

    /**
     * Gets the text.
     * @param params parameters
     * @return String
     */
    public String getCourseContentsInfoBoxCourseAddedText(String... params) {
        return String.format("Die Schulung '%s' wurde erfolgreich angelegt.", params);
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getCourseContentsViewAddRawTextErrorNotificationTitle() {
        return "Fehler";
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getCourseContentsViewAddRawTextErrorNotificationText() {
        return "Ihr Rohtext konnte nicht hinzugefügt werden. Bitte überprüfen Sie Ihre Eingabe.";
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getCourseContentsViewRawtextEditWindowTitleText() {
        return "Eingegebener Rohtext";
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getCourseContentsViewRawtextEditWindowTextareaLabel() {
        return "Rohtext:";
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getCourseContentsViewDocumentsEditWindowTitleText() {
        return "Hochgeladenes Dokument";
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getCourseContentsViewDocumentsEditWindowTextareaLabel() {
        return "Extrahierter Inhalt:";
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getEditWindowChangeButtonLabel() {
        return "Ändern";
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getEditWindowDeleteButtonLabel() {
        return "Löschen";
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getCourseContentsViewDeleteRawTextErrorNotificationTitle() {
        return "Fehler";
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getCourseContentsViewDeleteRawTextErrorNotificationText() {
        return "Ihr Rohtext konnte nicht gelöscht werden.";
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getCourseContentsViewUploadFileErrorNotificationTitle() {
        return "Fehler";
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getCourseContentsViewUploadFileErrorNotificationText() {
        return "Ihre Datei konnte nicht hochgeladen werden.";
    }

    /**
     * Gets the text.
     * @param params
     * @return String
     */
    public String getCourseContentsViewUploadFileSuccessNotificationText(String params) {
        return String.format("'%s' wurde erfolgreich hochgeladen. Starte Verarbeitung...", params);
    }

    /**
     * Gets the text.
     * @param params
     * @return String
     */
    public String getCourseContentsViewUploadFileProcessSuccessNotificationText(String params) {
        return String.format("'%s' wurde erfolgreich hochgeladen und verarbeitet.", params);
    }

    /**
     * Gets the text
     * @param params
     * @return
     */
    public String getCourseContentsViewUploadFileProcessErrorNotificationText(String params) {
        return String.format("'%s' konnte nicht verarbeiten werden.", params);
    }
}

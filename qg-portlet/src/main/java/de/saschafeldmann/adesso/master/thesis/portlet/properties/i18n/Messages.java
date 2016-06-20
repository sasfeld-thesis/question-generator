package de.saschafeldmann.adesso.master.thesis.portlet.properties.i18n;

import de.saschafeldmann.adesso.master.thesis.portlet.QuestionGeneratorPortletVaadinUi;
import de.saschafeldmann.adesso.master.thesis.util.properties.PropertiesReader;
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
 * Messages instance.
 */
@Component
@Scope("singleton")
public class Messages {
    @Autowired
    private MessagesDeProperties messageDeProperties;

    private static final String MENU_ITEM_COURSE_INFORMATION_LABEL = "de.saschafeldmann.adesso.master.thesis.portlet.menu.item.course.information.label";
    private static final String MENU_ITEM_CONTENT_LABEL = "de.saschafeldmann.adesso.master.thesis.portlet.menu.item.contents.label";
    private static final String MENU_ITEM_PREPROCESSES_LABEL = "de.saschafeldmann.adesso.master.thesis.portlet.menu.item.preprocesses.label";
    private static final String MENU_ITEM_DETECTION_LABEL = "de.saschafeldmann.adesso.master.thesis.portlet.menu.item.detection.label";
    private static final String MENU_ITEM_QUESTION_GENERATION_LABEL = "de.saschafeldmann.adesso.master.thesis.portlet.menu.item.question.generation.label";

    /**
     * Gets the current messages instance - portlet scoped.
     * @return Messages
     */
    public static Messages getInstance() {
        return QuestionGeneratorPortletVaadinUi.getCurrentPortlet().getMessages();
    }

    /**
     * Gets the message properties for the current user's language.
     * <p>
     * TODO implement logic to determine current language and get specialised properties reader
     *
     * @return
     */
    private PropertiesReader getMessageProperties() {
        return messageDeProperties;
    }

    /**
     * Gets the message for the given message key.
     * @param displayLabelMessageKey
     * @return String
     */
    public String get(String displayLabelMessageKey) {
        return getMessageProperties().fetchValue(displayLabelMessageKey);
    }

    /**
     * Gets the locale string.
     * @return String
     */
    public String getLocaleTimeFormat() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.locale.time.format");
    }

    /**
     * Gets the locale string.
     * @return String
     */
    public String getLocaleTimePrefix() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.locale.time.prefix");
    }

    /**
     * Gets the locale string.
     * @return String
     */
    public String getLocaleTimeSuffix() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.locale.time.suffix");
    }

    /**
     * Gets the menu item label.
     *
     * @return String
     */
    public String getMenuItemCourseInformationLabel() {
        return getMessageProperties().fetchValue(MENU_ITEM_COURSE_INFORMATION_LABEL);
    }

    /**
     * Gets the menu item label.
     *
     * @return String
     */
    public String getMenuItemContentsLabel() {
        return getMessageProperties().fetchValue(MENU_ITEM_CONTENT_LABEL);
    }

    /**
     * Gets the menu item label.
     *
     * @return String
     */
    public String getMenuItemPreprocessesLabel() {
        return getMessageProperties().fetchValue(MENU_ITEM_PREPROCESSES_LABEL);
    }

    /**
     * Gets the menu item label.
     *
     * @return String
     */
    public String getMenuItemDetectionLabel() {
        return getMessageProperties().fetchValue(MENU_ITEM_DETECTION_LABEL);
    }

    /**
     * Gets the menu item label.
     *
     * @return String
     */
    public String getMenuItemQuestionGenerationLabel() {
        return getMessageProperties().fetchValue(MENU_ITEM_QUESTION_GENERATION_LABEL);
    }

    /**
     * Gets the text.
     *
     * @return String
     */
    public String getCourseInformationViewNewCourseInfoText() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.course.information.view.new.course.info.text");
    }

    /**
     * Gets the text.
     *
     * @return String
     */
    public String getCourseInformationViewEditCourseInfoText(String... args) {
        return String.format(getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.course.information.view.edit.course.info.text"), args);
    }

    /**
     * Gets the text.
     *
     * @return String
     */
    public String getCourseInformationViewIntroductionText() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.course.information.view.introduction.text");
    }

    /**
     * Gets the text.
     *
     * @return String
     */
    public String getCourseInformationViewCourseTitleLabel() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.course.information.view.course.title.label");
    }

    /**
     * Gets the text.
     *
     * @return String
     */
    public String getCourseInformationViewCourseUrlLabel() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.course.information.view.course.url.label");
    }

    /**
     * Gets the text.
     *
     * @return String
     */
    public String getCourseInformationViewCourseLanguageLabel() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.course.information.view.course.language.label");
    }

    /**
     * Gets the text.
     *
     * @return String
     */
    public String getCourseInformationViewBtnNextLabel() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.course.information.view.course.button.next.label");
    }

    /**
     * Gets the text.
     *
     * @return String
     */
    public String getCourseInformationViewBtnNewSessionLabel() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.course.information.view.course.button.new.session.label");
    }

    /**
     * Gets the text.
     *
     * @return String
     */
    public String getCourseInformationViewEnglishLanguageLabel() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.course.information.view.course.language.english.label");
    }

    /**
     * Gets the text.
     *
     * @return String
     */
    public String getCourseInformationViewGermanLanguageLabel() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.course.information.view.course.language.german.label");
    }

    /**
     * Gets the text.
     *
     * @return String
     */
    public String getCourseInformationViewErrorNotificationTitle() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.course.information.view.error.notification.title");
    }

    /**
     * Gets the text.
     *
     * @return String
     */
    public String getCourseInformationViewErrorNotificationText() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.course.information.view.error.notification.text");
    }

    /**
     * Gets the text.
     *
     * @return String
     */
    public String getCourseContentsViewIntroductionText() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.course.contents.view.introduction.text");
    }


    /**
     * Gets the text.
     *
     * @return String
     */
    public String getCourseContentsViewAccordionDocumentsTitleLabel() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.course.contents.view.accordion.documents.title.label");
    }

    /**
     * Gets the text.
     *
     * @return String
     */
    public String getCourseContentsViewAccordionDocumentsFileUploadLabel() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.course.contents.view.accordion.documents.file.upload.label");
    }

    /**
     * Gets the text.
     *
     * @return String
     */
    public String getCourseContentsViewAccordionDocumentsUploadedListLabel() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.course.contents.view.accordion.documents.uploaded.list.label");
    }

    /**
     * Gets the text.
     *
     * @return String
     */
    public String getCourseContentsViewAccordionRawTextsTitleLabel() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.course.contents.view.accordion.rawtexts.title.label");
    }

    /**
     * Gets the text.
     *
     * @return String
     */
    public String getCourseContentsViewAccordionRawTextsTitleInputLabel() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.course.contents.view.accordion.rawtexts.title.input.label");
    }

    /**
     * Gets the text.
     *
     * @return String
     */
    public String getCourseContentsViewAccordionRawTextsRawTextInputLabel() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.course.contents.view.accordion.rawtexts.rawtext.input.label");
    }

    /**
     * Gets the text.
     *
     * @return String
     */
    public String getCourseContentsViewAccordionRawTextsAddButtonLabel() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.course.contents.view.accordion.rawtexts.add.button.label");
    }

    /**
     * Gets the text.
     *
     * @return String
     */
    public String getCourseContentsViewAccordionRawTextsAddedLabel() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.course.contents.view.accordion.rawtexts.added.label");
    }

    /**
     * Gets the text.
     *
     * @param params parameters
     * @return String
     */
    public String getCourseContentsInfoBoxCourseAddedText(String... params) {
        return String.format(getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.course.contents.view.info.box.course.added.text"), params);
    }

    /**
     * Gets the text.
     *
     * @return String
     */
    public String getCourseContentsViewAddRawTextErrorNotificationTitle() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.course.contents.view.accordion.rawtexts.add.error.notification.title");
    }

    /**
     * Gets the text.
     *
     * @return String
     */
    public String getCourseContentsViewAddRawTextErrorNotificationText() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.course.contents.view.accordion.rawtexts.add.error.notification.text");
    }

    /**
     * Gets the text.
     *
     * @return String
     */
    public String getCourseContentsViewRawtextEditWindowTitleText() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.course.contents.view.accordion.rawtexts.edit.window.title.label");
    }

    /**
     * Gets the text.
     *
     * @return String
     */
    public String getCourseContentsViewRawtextEditWindowTextareaLabel() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.course.contents.view.accordion.rawtexts.edit.window.textarea.label");
    }

    /**
     * Gets the text.
     *
     * @return String
     */
    public String getCourseContentsViewDocumentsEditWindowTitleText() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.course.contents.view.accordion.documents.edit.window.title.label");
    }

    /**
     * Gets the text.
     *
     * @return String
     */
    public String getCourseContentsViewDocumentsEditWindowTextareaLabel() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.course.contents.view.accordion.documents.edit.window.textarea.label");
    }

    /**
     * Gets the text.
     *
     * @return String
     */
    public String getEditWindowChangeButtonLabel() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.edit.window.change.button.label");
    }

    /**
     * Gets the text.
     *
     * @return String
     */
    public String getEditWindowDeleteButtonLabel() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.edit.window.delete.button.label");
    }

    /**
     * Gets the text.
     *
     * @return String
     */
    public String getCourseContentsViewDeleteRawTextErrorNotificationTitle() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.course.contents.view.accordion.rawtexts.delete.error.notification.title");
    }

    /**
     * Gets the text.
     *
     * @return String
     */
    public String getCourseContentsViewDeleteRawTextErrorNotificationText() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.course.contents.view.accordion.rawtexts.delete.error.notification.text");
    }

    /**
     * Gets the text.
     *
     * @return String
     */
    public String getCourseContentsViewUploadFileErrorNotificationTitle() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.course.contents.view.accordion.documents.upload.file.error.notification.title");
    }

    /**
     * Gets the text.
     *
     * @return String
     */
    public String getCourseContentsViewUploadFileErrorNotificationText() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.course.contents.view.accordion.documents.upload.file.error.notification.text");
    }

    /**
     * Gets the text.
     *
     * @param params
     * @return String
     */
    public String getCourseContentsViewUploadFileSuccessNotificationText(String params) {
        return String.format(getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.course.contents.view.accordion.documents.upload.file.success.notification.text"), params);
    }

    /**
     * Gets the text.
     *
     * @param params
     * @return String
     */
    public String getCourseContentsViewUploadFileProcessSuccessNotificationText(String params) {
        return String.format(getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.course.contents.view.accordion.documents.upload.file.and.process.success.notification.text"), params);
    }

    /**
     * Gets the text
     *
     * @param params
     * @return
     */
    public String getCourseContentsViewUploadFileProcessErrorNotificationText(String params) {
        return String.format(getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.course.contents.view.accordion.documents.upload.file.and.process.error.notification.text"), params);
    }

    /**
     * Gets the text
     *
     * @return String
     */
    public String getCourseContentsViewFileEditWindowTextareaLabel() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.course.contents.view.accordion.documents.edit.window.textarea.label");
    }

    /**
     * Gets the text
     *
     * @return String
     */
    public String getCourseContentsViewFileEditWindowTitleText() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.course.contents.view.accordion.documents.edit.window.title.label");
    }

    /**
     * Gets the text
     *
     * @return String
     */
    public String getPreproccesesViewInfoText() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.preprocesses.view.info.text");
    }

    /**
     * Gets the text
     *
     * @return String
     */
    public String getPreproccesesViewIntroductionText() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.preprocesses.view.introduction.text");
    }

    /**
     * Gets the text
     *
     * @return String
     */
    public String getPreproccesesViewAccordionActivationLabel() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.preprocesses.view.accordion.activation.label");
    }

    /**
     * Gets the text
     *
     * @return String
     */
    public String getPreproccesesViewAccordionProcesschainLabel() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.preprocesses.view.accordion.processchain.label");
    }

    /**
     * Gets the text
     *
     * @return String
     */
    public String getPreproccesesViewAccordionActivationOptiongroupLanguageDetectionLabel() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.preprocesses.view.accordion.activation.optiongroup.language.detection.label");
    }

    /**
     * Gets the text
     *
     * @return String
     */
    public String getPreproccesesViewAccordionActivationOptiongroupLanguageDetectionTooltip() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.preprocesses.view.accordion.activation.optiongroup.language.detection.tooltip");
    }

    /**
     * Gets the text
     *
     * @return String
     */
    public String getPreproccesesViewAccordionActivationOptiongroupPartOfSpeechDetectionLabel() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.preprocesses.view.accordion.activation.optiongroup.partofspeech.detection.label");
    }

    /**
     * Gets the text
     *
     * @return String
     */
    public String getPreproccesesViewAccordionActivationOptiongroupPartOfSpeechDetectionTooltip() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.preprocesses.view.accordion.activation.optiongroup.partofspeech.detection.tooltip");
    }

    /**
     * Gets the text
     *
     * @return String
     */
    public String getPreproccesesViewAccordionActivationOptiongroupNamedEntitiesDetectionLabel() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.preprocesses.view.accordion.activation.optiongroup.namedentities.detection.label");
    }

    /**
     * Gets the text
     *
     * @return String
     */
    public String getPreproccesesViewAccordionActivationOptiongroupNamedEntitiesDetectionTooltip() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.preprocesses.view.accordion.activation.optiongroup.namedentities.detection.tooltip");
    }

    /**
     * Gets the text
     *
     * @return String
     */
    public String getPreproccesesViewAccordionActivationSetSuccessInfo() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.preprocesses.view.accordion.activation.set.success.info");
    }

    /**
     * Gets the text
     *
     * @return String
     */
    public String getPreproccesesViewAccordionProcesschainButtonStartLabel() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.preprocesses.view.accordion.processchain.button.start.label");
    }

    /**
     * Gets the text
     *
     * @return String
     */
    public String getPreproccesesViewAccordionProcesschainFinishedLabel() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.preprocesses.view.accordion.processchain.finished.label");
    }

    /**
     * Gets the text
     *
     * @return String
     */
    public String getPreproccesesViewAccordionProcesschainLogChainStarted() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.preprocesses.view.accordion.processchain.log.chain.started");
    }

    /**
     * Gets the text
     *
     * @return String
     */
    public String getPreproccesesViewAccordionProcesschainLogLanguageDetectionStarted() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.preprocesses.view.accordion.processchain.log.language.detection.started");
    }

    /**
     * Gets the text
     * @param params the params
     * @return String
     */
    public String getPreproccesesViewAccordionProcesschainLogLanguageDetectionFailed(String... params) {
        return String.format(getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.preprocesses.view.accordion.processchain.log.language.detection.failed"), params);
    }

    /**
     * Gets the text
     *
     * @return String
     */
    public String getPreproccesesViewAccordionProcesschainLogLanguageDetectionFinished() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.preprocesses.view.accordion.processchain.log.language.detection.finished");
    }

    /**
     * Gets the text
     *
     * @return String
     */
    public String getPreproccesesViewAccordionProcesschainLogPartofspeechDetectionStarted() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.preprocesses.view.accordion.processchain.log.partofspeech.detection.started");
    }

    /**
     * Gets the text
     *
     * @return String
     */
    public String getPreproccesesViewAccordionProcesschainLogPartofspeechDetectionFinished() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.preprocesses.view.accordion.processchain.log.partofspeech.detection.finished");
    }

    /**
     * Gets the text
     *
     * @return String
     */
    public String getPreproccesesViewAccordionProcesschainLogNamedEntityRecognitionStarted() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.preprocesses.view.accordion.processchain.log.namedentity.recognition.started");
    }

    /**
     * Gets the text
     *
     * @return String
     */
    public String getPreproccesesViewAccordionProcesschainLogNamedEntityRecognitionFinished() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.preprocesses.view.accordion.processchain.log.namedentity.recognition.finished");
    }

    /**
     * Gets the text
     *
     * @return String
     */
    public String getPreproccesesViewAccordionProcesschainLogChainFinished() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.preprocesses.view.accordion.processchain.log.chain.finished");
    }

    /**
     * Gets the text
     *
     * @return String
     */
    public String getPreproccesesViewAccordionProcesschainEditWindowTitle() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.preprocesses.view.accordion.processchain.edit.window.title");
    }

    /**
     * Gets the text
     *
     * @return String
     */
    public String getPreproccesesViewAccordionProcesschainEditWindowTextareaTitle() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.preprocesses.view.accordion.processchain.edit.window.textarea.title");
    }

    /**
     * Gets the text
     *
     * @return String
     */
    public String getPreproccesesViewAccordionProcesschainEditWindowEditButtonTooltip() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.preprocesses.view.accordion.processchain.edit.window.button.edit.tooltip");
    }

    /**
     * Gets the text
     *
     * @return String
     */
    public String getPreproccesesViewAccordionProcesschainEditWindowDeleteButtonTooltip() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.preprocesses.view.accordion.processchain.edit.window.button.delete.tooltip");
    }

    /**
     * Gets the text
     *
     * @return String
     */
    public String getPreproccesesViewAccordionProcesschainEditWindowLanguageTitle() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.preprocesses.view.accordion.processchain.edit.window.language.title");
    }

    /**
     * Gets the text
     *
     * @return String
     */
    public String getPreproccesesViewAccordionProcesschainEditWindowLanguageInfoBox() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.preprocesses.view.accordion.processchain.edit.window.language.infobox");
    }

    /**
     * Gets the text
     *
     * @return String
     */
    public String getButtonBackTitle() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.footer.button.back.label");
    }

    /**
     * Gets the text
     *
     * @return String
     */
    public String getButtonNextTitle() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.footer.button.next.label");
    }

    /**
     * Gets the text
     *
     * @return String
     */
    public String getPreprocessesViewAccordionActivationOptiongroupYesLabel() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.preprocesses.view.accordion.activation.optiongroup.yes.label");
    }

    /**
     * Gets the text
     *
     * @return String
     */
    public String getPreprocessesViewAccordionActivationOptiongroupNoLabel() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.preprocesses.view.accordion.activation.optiongroup.no.label");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getPreprocessesViewAccordionProcesschainLogNlpFailed() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.preprocesses.view.accordion.processchain.log.nlp.failed");
    }

}

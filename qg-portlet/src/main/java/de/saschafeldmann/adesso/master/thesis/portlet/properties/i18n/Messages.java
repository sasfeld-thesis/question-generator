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
        return QuestionGeneratorPortletVaadinUi.getCurrentPortletVaadinUi().getMessages();
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
     * Gets the text.
     * @return String
     */
    public String getFooterInfoText() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.footer.info.text");
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
     * @return
     */
    public String getCourseInformationViewBtnPreviousLabel() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.course.information.view.course.button.previous.label");
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
    public String getPreproccesesViewAccordionProcesschainEditWindowTextareaPosTitle() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.preprocesses.view.accordion.processchain.edit.window.textarea.pos.title");
    }
    /**
     * Gets the text
     *
     * @return String
     */
    public String getPreproccesesViewAccordionProcesschainEditWindowTextareaNerTitle() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.preprocesses.view.accordion.processchain.edit.window.textarea.ner.title");
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

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewInfoText() {
       return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.info.text");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewIntroductionText() {
       return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.introduction.text");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionDetectionChainEditErrorTitle() {
       return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.detection.chain.edit.error.title");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionDetectionChainEditErrorMessage() {
       return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.detection.chain.edit.error.message");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionActivationLabel() {
       return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.activation.label");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionDetectionChainLabel() {
       return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.detection.chain.label");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionActivationOptiongroupFillsentencesLabel() {
       return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.activation.optiongroup.fillsentences.label");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionActivationOptiongroupFillsentencesTooltip() {
       return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.activation.optiongroup.fillsentences.tooltip");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionActivationOptiongroupCardinalitySentencesLabel() {
       return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.activation.optiongroup.cardinalitysentences.label");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionActivationOptiongroupCardinalitySentencesTooltip() {
       return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.activation.optiongroup.cardinalitysentences.tooltip");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionActivationSetSuccessInfo() {
       return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.activation.set.success.info");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionDetectionChainButtonStartLabel() {
       return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.detection.chain.button.start.label");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionDetectionChainFinishedLabel() {
       return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.detection.chain.finished.label");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionDetectionChainLogChainStartedStarted() {
       return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.detection.chain.log.chain.started");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionDetectionChainLogChainFillsentencesStarted() {
       return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.detection.chain.log.chain.fillsentences.started");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionDetectionChainLogChainFillsentencesFinished() {
       return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.detection.chain.log.chain.fillsentences.finished");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionDetectionChainLogChainCardinalitySentencesStarted() {
       return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.detection.chain.log.chain.cardinalitysentences.started");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionDetectionChainLogChainCardinalitySentencesFinished() {
       return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.detection.chain.log.chain.cardinalitysentences.finished");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionDetectionChainLogChainFinished() {
       return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.detection.chain.log.chain.finished");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionDetectionChainEditWindowTitle() {
       return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.detection.chain.edit.window.title");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionDetectionChainEditWindowTableColumnConceptHeader() {
       return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.detection.chain.edit.window.table.column.concept.header");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionDetectionChainEditWindowTableColumnOriginalSentenceHeader() {
       return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.detection.chain.edit.window.table.column.originalsentence.header");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionDetectionChainEditWindowTableColumnAttributesHeader() {
       return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.detection.chain.edit.window.table.column.attributes.header");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionDetectionChainEditWindowTableColumnEditHeader() {
       return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.detection.chain.edit.window.table.column.edit.header");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionDetectionChainEditWindowTableColumnConceptTypeFillsentences() {
       return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.detection.chain.edit.window.table.column.concept.type.fillsentences");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionDetectionChainEditWindowTableColumnConceptTypeCardinalitysentences() {
       return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.detection.chain.edit.window.table.column.concept.type.cardinalitysentences");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionDetectionChainEditWindowTableColumnAttributesFillsentencesFillsentence() {
       return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.detection.chain.edit.window.table.column.attributes.fillsentences.fillsentence");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionDetectionChainEditWindowTableColumnAttributesFillsentencesCorrectAnswer() {
       return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.detection.chain.edit.window.table.column.attributes.fillsentences.correctanswer");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionDetectionChainEditWindowTableColumnAttributesCardinalitySentencesComposite() {
       return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.detection.chain.edit.window.table.column.attributes.cardinalitysentences.composite");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionDetectionChainEditWindowTableColumnAttributesCardinaltySentencesComposition() {
       return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.detection.chain.edit.window.table.column.attributes.cardinalitysentences.composition");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionDetectionChainEditWindowTableColumnAttributesCardinalitySentencesCardinality() {
       return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.detection.chain.edit.window.table.column.attributes.cardinalitysentences.cardinality");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionDetectionChainEditWindowTableColumnEditEditButtonLabel() {
       return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.detection.chain.edit.window.table.column.edit.button.label");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionDetectionChainEditWindowTableColumnEditRowFillSentencesWindowOriginalSentenceLabel() {
       return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.detection.chain.edit.window.table.edit.row.fillsentences.window.originalsentence.label");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionDetectionChainEditWindowTableColumnEditRowFillSentencesWindowFillSentenceLabel() {
       return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.detection.chain.edit.window.table.edit.row.fillsentences.window.fillsentence.label");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionDetectionChainEditWindowTableColumnEditRowFillSentencesWindowCorrectAnswerLabel() {
       return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.detection.chain.edit.window.table.edit.row.fillsentences.window.correctanswer.label");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionDetectionChainEditWindowTableColumnEditRowFillSentencesWindowEditButtonLabel() {
       return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.detection.chain.edit.window.table.edit.row.fillsentences.window.edit.button.label");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionDetectionChainEditWindowTableColumnEditRowFillSentencesWindowEditButtonTooltip() {
       return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.detection.chain.edit.window.table.edit.row.fillsentences.window.edit.button.tooltip");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionDetectionChainEditWindowTableColumnEditRowFillSentencesWindowDeleteButtonLabel() {
       return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.detection.chain.edit.window.table.edit.row.fillsentences.window.delete.button.label");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionDetectionChainEditWindowTableColumnEditRowFillSentencesWindowDeleteButtonTooltip() {
       return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.detection.chain.edit.window.table.edit.row.fillsentences.window.delete.button.tooltip");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionDetectionChainEditWindowTableColumnEditRowCardinalitySentencesWindowTitle() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.detection.chain.edit.window.table.edit.row.cardinalitysentences.window.title");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionDetectionChainEditWindowTableColumnEditRowCardinalitySentencesWindowOriginalSentenceLabel() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.detection.chain.edit.window.table.edit.row.cardinalitysentences.window.originalsentence.label");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionDetectionChainEditWindowTableColumnEditRowCardinalitySentencesWindowsCompositeLabel() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.detection.chain.edit.window.table.edit.row.cardinalitysentences.window.composite.label");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionDetectionChainEditWindowTableColumnEditRowCardinalitySentencesWindowCompositionLabel() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.detection.chain.edit.window.table.edit.row.cardinalitysentences.window.composition.label");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionDetectionChainEditWindowTableColumnEditRowCardinalitySentencesWindowOriginalCompositeCardinalityLabel() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.detection.chain.edit.window.table.edit.row.cardinalitysentences.window.composite.cardinality.label");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionDetectionChainEditWindowTableColumnEditRowCardinalitySentencesWindowOriginalCompositionCardinalityLabel() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.detection.chain.edit.window.table.edit.row.cardinalitysentences.window.composition.cardinality.label");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionDetectionChainEditWindowTableColumnEditRowCardinalitySentencesWindowOriginalEditButtonLabel() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.detection.chain.edit.window.table.edit.row.cardinalitysentences.window.edit.button.label");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionDetectionChainEditWindowTableColumnEditRowCardinalitySentencesWindowOriginalEditButtonTooltip() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.detection.chain.edit.window.table.edit.row.cardinalitysentences.window.edit.button.tooltip");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionDetectionChainEditWindowTableColumnEditRowCardinalitySentencesWindowOriginalDeleteButtonLabel() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.detection.chain.edit.window.table.edit.row.cardinalitysentences.window.delete.button.label");
    }

    /**
     * Gets the text
     * @return String
     */
    public String getDetectionViewAccordionDetectionChainEditWindowTableColumnEditRowCardinalitySentencesWindowOriginalDeleteButtonTooltip() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.detection.view.accordion.detection.chain.edit.window.table.edit.row.cardinalitysentences.window.delete.button.tooltip");
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getQuestionGenerationViewInfoText() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.question.generation.view.info.text");
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getQuestionGenerationViewIntroductionText() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.question.generation.view.introduction.text");
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getQuestionGenerationViewButtonStartLabel() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.question.generation.view.button.start.label");
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getQuestionGenerationViewFinishedContentsLabels() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.question.generation.view.finished.contents.label");
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getQuestionGenerationViewFinishedQuestionsLabel() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.question.generation.view.finished.questions.label");
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getQuestionGenerationViewFinishedQuestionsFillsentencesPrefix() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.question.generation.view.finished.questions.fillsentences.prefix");
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getQuestionGenerationViewFinishedQuestionsCardinalSentencesPrefix() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.question.generation.view.finished.questions.cardinalsentences.prefix");
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getQuestionGenerationViewFinishedQuestionsEditWindowTitle() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.question.generation.view.finished.questions.edit.window.title");
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getQuestionGenerationViewFinishedQuestionsEditWindowInputTestquestion() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.question.generation.view.finished.questions.edit.window.input.testquestion");
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getQuestionGenerationViewFinishedQuestionsEditWindowInputCorrectAnswer() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.question.generation.view.finished.questions.edit.window.input.correctanswer");
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getQuestionGenerationViewFinishedQuestionsEditWindowInputQuestionTypeLabel() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.question.generation.view.finished.questions.edit.window.input.question.type.label");
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getQuestionGenerationViewFinishedQuestionsEditWindowInputQuestionTypeOptionSingle() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.question.generation.view.finished.questions.edit.window.input.question.type.option.single");
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getQuestionGenerationViewFinishedQuestionsEditWindowInputQuestionTypeOptionMultiple() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.question.generation.view.finished.questions.edit.window.input.question.type.option.multiple");
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getQuestionGenerationViewFinishedQuestionsEditWindowInputQuestionWrongAnswersLabel() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.question.generation.view.finished.questions.edit.window.input.question.wrong.answers.label");
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getQuestionGenerationViewFinishedQuestionsEditWindowInputQuestionWrongAnswersTooltip() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.question.generation.view.finished.questions.edit.window.input.question.wrong.answers.tooltip");
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getQuestionGenerationViewFinishedQuestionsEditWindowInputQuestionWrongAnswersInput() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.question.generation.view.finished.questions.edit.window.input.question.wrong.answers.input");
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getQuestionGenerationViewFinishedQuestionsEditWindowButtonEdit() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.question.generation.view.finished.questions.edit.window.button.edit");
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getQuestionGenerationViewFinishedQuestionsEditWindowButtonDelete() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.question.generation.view.finished.questions.edit.window.button.delete");
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getQuestionGenerationViewButtonExport() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.question.generation.view.button.export");
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getQuestionGenerationViewListselectExportCsv() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.question.generation.view.listselect.export.csv");
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getQuestionGenerationViewListselectExportValamis() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.question.generation.view.listselect.export.valamis");
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getQuestionGenerationViewFinishedQuestionsEditWindowInputQuestionCorrectAnswersLabel() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.question.generation.view.finished.questions.edit.window.input.question.correct.answers.label");
    }

    /**
     * Gets the text.
     * @return String
     */
    public String getQuestionGenerationViewFinishedQuestionsEditWindowInputQuestionCorrectAnswersTooltip() {
        return getMessageProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.portlet.question.generation.view.finished.questions.edit.window.input.question.correct.answers.tooltip");
    }
}

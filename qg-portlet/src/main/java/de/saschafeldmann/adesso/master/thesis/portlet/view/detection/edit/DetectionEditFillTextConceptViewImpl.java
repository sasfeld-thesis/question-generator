package de.saschafeldmann.adesso.master.thesis.portlet.view.detection.edit;

import com.vaadin.ui.Window;
import de.saschafeldmann.adesso.master.thesis.detection.algorithm.model.FillTextConcept;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.i18n.Messages;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.Button;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.FormLayout;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.HorizontalLayout;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  28.06.2016
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
 * [short description]
 */
@Component
@Scope("prototype")
public class DetectionEditFillTextConceptViewImpl extends Window implements DetectionEditFillTextConceptView {
    private final FormLayout formLayout;
    private final TextField originalSentenceInput;
    private final TextField filltextSentenceInput;
    private final TextField correctAnswerInput;
    private final HorizontalLayout buttonGroupLayout;
    private final Button btnEdit;
    private final Button btnDelete;
    private final Messages messages;

    /**
     * Constructs a new detection edit view.
     * @param formLayout FormLayout
     * @param originalSentenceInput TextField
     * @param filltextSentenceInput TextField
     * @param correctAnswerInput TextField
     */
    @Autowired
    public DetectionEditFillTextConceptViewImpl(
            final Messages messages,
            final FormLayout formLayout,
            final TextField originalSentenceInput,
            final TextField filltextSentenceInput,
            final TextField correctAnswerInput,
            final HorizontalLayout buttonGroupLayout,
            final Button btnEdit,
            final Button btnDelete
    ) {
        this.formLayout = formLayout;
        this.originalSentenceInput = originalSentenceInput;
        this.filltextSentenceInput = filltextSentenceInput;
        this.correctAnswerInput = correctAnswerInput;
        this.buttonGroupLayout = buttonGroupLayout;
        this.btnEdit = btnEdit;
        this.btnDelete = btnDelete;
        this.messages = messages;
    }

    @PostConstruct
    private void initializeView() {
        setLabels();
        addComponents();
        disableCertainInputs();

        setContent(formLayout);
    }

    private void setLabels() {
        originalSentenceInput.setCaption(messages.getDetectionViewAccordionDetectionChainEditWindowTableColumnEditRowFillSentencesWindowOriginalSentenceLabel());
        filltextSentenceInput.setCaption(messages.getDetectionViewAccordionDetectionChainEditWindowTableColumnEditRowFillSentencesWindowFillSentenceLabel());
        correctAnswerInput.setCaption(messages.getDetectionViewAccordionDetectionChainEditWindowTableColumnEditRowFillSentencesWindowCorrectAnswerLabel());
        btnEdit.setCaption(messages.getDetectionViewAccordionDetectionChainEditWindowTableColumnEditRowFillSentencesWindowEditButtonLabel());
        btnDelete.setCaption(messages.getDetectionViewAccordionDetectionChainEditWindowTableColumnEditRowFillSentencesWindowDeleteButtonLabel());
    }

    private void addComponents() {
        formLayout.addComponent(originalSentenceInput);
        formLayout.addComponent(filltextSentenceInput);
        formLayout.addComponent(correctAnswerInput);

        buttonGroupLayout.addComponent(btnEdit);
        buttonGroupLayout.addComponent(btnDelete);

        formLayout.addComponent(buttonGroupLayout);
    }

    private void disableCertainInputs() {
        originalSentenceInput.setEnabled(false);
    }

    @Override
    public String getFillTextSentenceInput() {
        return filltextSentenceInput.getValue();
    }

    @Override
    public String getCorrectAnswerInput() {
        return correctAnswerInput.getValue();
    }

    @Override
    public void displayForConcept(final FillTextConcept concept) {
        originalSentenceInput.setValue(concept.getOriginalSentence());
        filltextSentenceInput.setValue(concept.getFillSentence());
        correctAnswerInput.setValue(concept.getCorrectAnswer());
    }

    @Override
    public void close() {
        super.close();
    }
}

package de.saschafeldmann.adesso.master.thesis.portlet.view.detection.edit;

import com.vaadin.ui.Window;
import de.saschafeldmann.adesso.master.thesis.detection.model.FillInTheBlankTextConcept;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.QuestionGeneratorProperties;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.i18n.Messages;
import de.saschafeldmann.adesso.master.thesis.portlet.util.VaadinUtil;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.AutowirableButton;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.AutowirableFormLayout;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.AutowirableHorizontalLayout;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.AutowirableTextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  28.06.2016
 * Author:         Sascha Feldmann (sascha.feldmann@gmx.de)
 * <br><br>
 * University:
 * Hochschule für Technik und Wirtschaft, Berlin
 * Fachbereich 4
 * Studiengang Internationale Medieninformatik (Master)
 * <br><br>
 * Company:
 * adesso AG
 * <br><br>
 * [short description]
 */
@Component
@Scope("prototype")
public class DetectionEditFillInTheBlankConceptViewImpl extends AbstractDetectionEditConceptView implements DetectionEditFillInTheBlankConceptView, Window.CloseListener {
    private final AutowirableFormLayout formLayout;
    private final AutowirableTextField originalSentenceInput;
    private final AutowirableTextField filltextSentenceInput;
    private final AutowirableTextField correctAnswerInput;
    private final AutowirableHorizontalLayout buttonGroupLayout;
    private final AutowirableButton btnEdit;
    private final AutowirableButton btnDelete;
    private final Messages messages;
    private DetectionEditConceptViewListener<FillInTheBlankTextConcept> viewListener;
    private FillInTheBlankTextConcept fillInTheBlankTextConcept;

    /**
     * Constructs a new detection edit view.
     * @param formLayout FormLayout
     * @param originalSentenceInput TextField
     * @param filltextSentenceInput TextField
     * @param correctAnswerInput TextField
     */
    @Autowired
    public DetectionEditFillInTheBlankConceptViewImpl(
            final QuestionGeneratorProperties questionGeneratorProperties,
            final Messages messages,
            final AutowirableFormLayout formLayout,
            final AutowirableTextField originalSentenceInput,
            final AutowirableTextField filltextSentenceInput,
            final AutowirableTextField correctAnswerInput,
            final AutowirableHorizontalLayout buttonGroupLayout,
            final AutowirableButton btnEdit,
            final AutowirableButton btnDelete
    ) {
        super(questionGeneratorProperties);

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
    protected void initializeView() {
        super.initializeView();
        
        setLabels();
        addComponents();
        disableCertainInputs();
        addListeners();

        setContent(formLayout);
    }

    private void addListeners() {
        btnEdit.addClickListener(new com.vaadin.ui.Button.ClickListener() {
            @Override
            public void buttonClick(com.vaadin.ui.Button.ClickEvent clickEvent) {
                viewListener.onEditButtonClicked(fillInTheBlankTextConcept);
            }
        });

        btnDelete.addClickListener(new com.vaadin.ui.Button.ClickListener() {
            @Override
            public void buttonClick(com.vaadin.ui.Button.ClickEvent clickEvent) {
                viewListener.onDeleteButtonClicked(fillInTheBlankTextConcept);
            }
        });

        addCloseListener(this);
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
    public void displayForConcept(final FillInTheBlankTextConcept concept) {
        originalSentenceInput.setValue(concept.getOriginalSentence());
        filltextSentenceInput.setValue(concept.getFillSentence());
        correctAnswerInput.setValue(concept.getCorrectAnswer());

        this.fillInTheBlankTextConcept = concept;

        // displays the view in the current window
        VaadinUtil.addWindow(this);
    }

    @Override
    public void setViewListener(final DetectionEditConceptViewListener<FillInTheBlankTextConcept> viewListener) {
        this.viewListener = viewListener;
    }

    @Override
    public void close() {
        super.close();
    }

    @Override
    public void windowClose(CloseEvent closeEvent) {
        viewListener.onWindowClosed(fillInTheBlankTextConcept);
    }
}

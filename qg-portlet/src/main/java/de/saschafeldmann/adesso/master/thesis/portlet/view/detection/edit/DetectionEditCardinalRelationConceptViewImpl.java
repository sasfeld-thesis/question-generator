package de.saschafeldmann.adesso.master.thesis.portlet.view.detection.edit;

import com.vaadin.data.util.ObjectProperty;
import com.vaadin.ui.Window;
import de.saschafeldmann.adesso.master.thesis.detection.model.CardinalRelationConcept;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.QuestionGeneratorProperties;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.i18n.Messages;
import de.saschafeldmann.adesso.master.thesis.portlet.util.VaadinUtil;
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
public class DetectionEditCardinalRelationConceptViewImpl extends AbstractDetectionEditConceptView implements DetectionEditCardinalRelationConceptView {
    private static final java.lang.Integer INITIAL_NUMBER = 1;
    private final FormLayout formLayout;
    private final TextField originalSentenceInput;
    private final TextField compositeInput;
    private final TextField compositeCardinalityInput;
    private final TextField compositionInput;
    private final TextField compositionCardinalityInput;
    private final HorizontalLayout buttonGroupLayout;
    private final Button btnEdit;
    private final Button btnDelete;
    private final Messages messages;
    private DetectionEditConceptViewListener<CardinalRelationConcept> viewListener;
    private CardinalRelationConcept cardinalRelationConcept;

    /**
     * Constructs a new detection edit view.
     * @param formLayout FormLayout
     * @param originalSentenceInput TextField
     * @param compositeInput TextField
     * @param compositeCardinalityInput TextField
     */
    @Autowired
    public DetectionEditCardinalRelationConceptViewImpl(
            final QuestionGeneratorProperties questionGeneratorProperties,
            final Messages messages,
            final FormLayout formLayout,
            final TextField originalSentenceInput,
            final TextField compositeInput,
            final TextField compositeCardinalityInput,
            final TextField compositionInput,
            final TextField compositionCardinalityInput,
            final HorizontalLayout buttonGroupLayout,
            final Button btnEdit,
            final Button btnDelete
    ) {
        super(questionGeneratorProperties);

        this.formLayout = formLayout;
        this.originalSentenceInput = originalSentenceInput;
        this.compositeInput = compositeInput;
        this.compositeCardinalityInput = compositeCardinalityInput;
        this.compositionInput = compositionInput;
        this.compositionCardinalityInput = compositionCardinalityInput;
        this.buttonGroupLayout = buttonGroupLayout;
        this.btnEdit = btnEdit;
        this.btnDelete = btnDelete;
        this.messages = messages;
    }

    @PostConstruct
    protected void initializeView() {
        super.initializeView();

        setLabels();
        setInputTypes();
        addComponents();
        disableCertainInputs();
        addListeners();

        setContent(formLayout);
    }

    private void addListeners() {
        btnEdit.addClickListener(new com.vaadin.ui.Button.ClickListener() {
            @Override
            public void buttonClick(com.vaadin.ui.Button.ClickEvent clickEvent) {
                viewListener.onEditButtonClicked(cardinalRelationConcept);
            }
        });

        btnDelete.addClickListener(new com.vaadin.ui.Button.ClickListener() {
            @Override
            public void buttonClick(com.vaadin.ui.Button.ClickEvent clickEvent) {
                viewListener.onDeleteButtonClicked(cardinalRelationConcept);
            }
        });

        addCloseListener(new CloseListener() {
            @Override
            public void windowClose(CloseEvent closeEvent) {
                viewListener.onWindowClosed(cardinalRelationConcept);
            }
        });
    }

    private void setInputTypes() {
        compositeCardinalityInput.setPropertyDataSource(
                 new ObjectProperty<Integer>(INITIAL_NUMBER)
        );
        compositionCardinalityInput.setPropertyDataSource(
                new ObjectProperty<Integer>(INITIAL_NUMBER)
        );
    }

    private void setLabels() {
        originalSentenceInput.setCaption(messages.getDetectionViewAccordionDetectionChainEditWindowTableColumnEditRowCardinalitySentencesWindowOriginalSentenceLabel());
        compositeInput.setCaption(messages.getDetectionViewAccordionDetectionChainEditWindowTableColumnEditRowCardinalitySentencesWindowsCompositeLabel());
        compositeCardinalityInput.setCaption(messages.getDetectionViewAccordionDetectionChainEditWindowTableColumnEditRowCardinalitySentencesWindowOriginalCompositeCardinalityLabel());
        compositionInput.setCaption(messages.getDetectionViewAccordionDetectionChainEditWindowTableColumnEditRowCardinalitySentencesWindowCompositionLabel());
        compositionCardinalityInput.setCaption(messages.getDetectionViewAccordionDetectionChainEditWindowTableColumnEditRowCardinalitySentencesWindowOriginalCompositionCardinalityLabel());

        btnEdit.setCaption(messages.getDetectionViewAccordionDetectionChainEditWindowTableColumnEditRowFillSentencesWindowEditButtonLabel());
        btnDelete.setCaption(messages.getDetectionViewAccordionDetectionChainEditWindowTableColumnEditRowFillSentencesWindowDeleteButtonLabel());
    }

    private void addComponents() {
        formLayout.addComponent(originalSentenceInput);
        formLayout.addComponent(compositeInput);
        formLayout.addComponent(compositeCardinalityInput);
        formLayout.addComponent(compositionInput);
        formLayout.addComponent(compositionCardinalityInput);

        buttonGroupLayout.addComponent(btnEdit);
        buttonGroupLayout.addComponent(btnDelete);

        formLayout.addComponent(buttonGroupLayout);
    }

    private void disableCertainInputs() {
        originalSentenceInput.setEnabled(false);
    }


    @Override
    public void displayForConcept(CardinalRelationConcept concept) {
        originalSentenceInput.setValue(concept.getOriginalSentence());
        compositeInput.setValue(concept.getComposite());
        compositeCardinalityInput.setValue(String.valueOf(concept.getCompositeCardinality()));
        compositionInput.setValue(concept.getComposition());
        compositionCardinalityInput.setValue(String.valueOf(concept.getCompositionCardinality()));

        this.cardinalRelationConcept = concept;

        // displays the view in the current window
        VaadinUtil.addWindow(this);
    }

    @Override
    public void setViewListener(final DetectionEditConceptViewListener<CardinalRelationConcept> viewListener) {
        this.viewListener = viewListener;
    }

    @Override
    public void close() {
        super.close();
    }

    @Override
    public String getCompositeUserInput() {
        return compositeInput.getValue();
    }

    @Override
    public int getCompositeCardinalityUserInput() {
        return Integer.parseInt(compositeCardinalityInput.getValue());
    }

    @Override
    public String getCompositionUserInput() {
        return compositionInput.getValue();
    }

    @Override
    public int getCompositionCardinalityUserInput() {
        return Integer.parseInt(compositionCardinalityInput.getValue());
    }
}

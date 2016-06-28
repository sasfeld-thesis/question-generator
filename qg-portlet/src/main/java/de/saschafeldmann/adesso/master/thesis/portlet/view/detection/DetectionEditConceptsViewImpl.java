package de.saschafeldmann.adesso.master.thesis.portlet.view.detection;

import com.vaadin.ui.Window;
import de.saschafeldmann.adesso.master.thesis.detection.algorithm.model.CardinalRelationConcept;
import de.saschafeldmann.adesso.master.thesis.detection.algorithm.model.FillTextConcept;
import de.saschafeldmann.adesso.master.thesis.detection.algorithm.model.api.Concept;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.i18n.Messages;
import de.saschafeldmann.adesso.master.thesis.portlet.util.VaadinUtil;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.Button;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.Grid;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.VerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

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
 * Implementation of an {@link DetectionEditConceptsView}.
 */
@Component
@Scope("prototype")
public class DetectionEditConceptsViewImpl extends Window implements DetectionEditConceptsView {
    private final Messages messages;
    private final VerticalLayout mainLayout;
    private final Grid conceptsGrid;
    private DetectionEditConceptsViewListener viewListener;

    /**
     * Constructs a new view impl.
     * @param messages the messages
     * @param grid the grid
     */
    @Autowired
    public DetectionEditConceptsViewImpl(final VerticalLayout mainLayout, final Messages messages, final Grid grid) {
        this.mainLayout = mainLayout;
        this.messages = messages;
        this.conceptsGrid = grid;
    }

    @PostConstruct
    private void initialize() {
        setCaption(messages.getDetectionViewAccordionDetectionChainEditWindowTitle());

        mainLayout.addComponent(conceptsGrid);
        setContent(mainLayout);
    }

    @Override
    public void displayDetectedConcepts(final List<Concept> detectedConcepts) {
        conceptsGrid.removeAllColumns();

        initializeGridColumns();
        addRowsToGrid(detectedConcepts);

        // displays the window
        VaadinUtil.addWindow(getWindow());
    }

    private void initializeGridColumns() {
        conceptsGrid.addColumn(messages.getDetectionViewAccordionDetectionChainEditWindowTableColumnConceptHeader(), String.class);
        conceptsGrid.addColumn(messages.getDetectionViewAccordionDetectionChainEditWindowTableColumnOriginalSentenceHeader(), String.class);
        conceptsGrid.addColumn(messages.getDetectionViewAccordionDetectionChainEditWindowTableColumnAttributesHeader(), String.class);
        conceptsGrid.addColumn(messages.getDetectionViewAccordionDetectionChainEditWindowTableColumnEditHeader(), Button.class);
    }


    private void addRowsToGrid(List<Concept> detectedConcepts) {
        for (Concept detectedConcept: detectedConcepts) {
            addRowToGrid(detectedConcept);
        }
    }

    private void addRowToGrid(final Concept detectedConcept) {
        final Button editButton = new Button(messages.getDetectionViewAccordionDetectionChainEditWindowTableColumnEditEditButtonLabel());
        editButton.addClickListener(new com.vaadin.ui.Button.ClickListener() {
            @Override
            public void buttonClick(com.vaadin.ui.Button.ClickEvent clickEvent) {
                viewListener.onEditButtonClicked(detectedConcept);
            }
        });

        conceptsGrid.addRow(getConceptTitle(detectedConcept), detectedConcept.getOriginalSentence(), getConceptAttributes(detectedConcept),
                editButton);
    }

    private String getConceptTitle(Concept detectedConcept) {
        if (detectedConcept instanceof FillTextConcept) {
            return messages.getDetectionViewAccordionDetectionChainEditWindowTableColumnConceptTypeFillsentences();
        } else {
            return messages.getDetectionViewAccordionDetectionChainEditWindowTableColumnConceptTypeCardinalitysentences();
        }
    }

    private String getConceptAttributes(Concept detectedConcept) {
        if (detectedConcept instanceof FillTextConcept) {
            return buildAttributesStringForFillSentence((FillTextConcept) detectedConcept);
        } else {
            return buildAttributesStringForCardinalitySentence((CardinalRelationConcept) detectedConcept);
        }
    }

    private String buildAttributesStringForFillSentence(FillTextConcept detectedConcept) {
        StringBuilder stringBuilder = new StringBuilder();

        appendAttributesKeyValue(stringBuilder, messages.getDetectionViewAccordionDetectionChainEditWindowTableColumnAttributesFillsentencesFillsentence(), detectedConcept.getFillSentence());
        appendAttributesKeyValue(stringBuilder, messages.getDetectionViewAccordionDetectionChainEditWindowTableColumnAttributesFillsentencesCorrectAnswer(), detectedConcept.getCorrectAnswer());

        return stringBuilder.toString();
    }

    private String buildAttributesStringForCardinalitySentence(CardinalRelationConcept detectedConcept) {
        StringBuilder stringBuilder = new StringBuilder();

        appendAttributesKeyValue(stringBuilder, messages.getDetectionViewAccordionDetectionChainEditWindowTableColumnAttributesCardinalitySentencesComposite(), detectedConcept.getComposite());
        appendAttributesKeyValue(stringBuilder, messages.getDetectionViewAccordionDetectionChainEditWindowTableColumnAttributesCardinaltySentencesComposition(), detectedConcept.getComposition());
        appendAttributesKeyValue(stringBuilder, messages.getDetectionViewAccordionDetectionChainEditWindowTableColumnAttributesCardinalitySentencesCardinality(), detectedConcept.getCompositeCardinality() + ":" + detectedConcept.getCompositionCardinality());

        return stringBuilder.toString();
    }

    private void appendAttributesKeyValue(StringBuilder stringBuilder, String key, String value) {
        stringBuilder.append(key)
                    .append(": ")
                    .append(value)
                    .append("\n");
    }

    @Override
    public Window getWindow() {
        return this;
    }

    @Override
    public void setViewListener(DetectionEditConceptsViewListener viewListener) {
        this.viewListener = viewListener;
    }
}

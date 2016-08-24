package de.saschafeldmann.adesso.master.thesis.portlet.view.detection.edit;

import com.vaadin.ui.Window;
import com.vaadin.ui.renderers.ButtonRenderer;
import com.vaadin.ui.renderers.ClickableRenderer;
import com.vaadin.ui.renderers.HtmlRenderer;
import de.saschafeldmann.adesso.master.thesis.detection.model.CardinalRelationConcept;
import de.saschafeldmann.adesso.master.thesis.detection.model.FillInTheBlankTextConcept;
import de.saschafeldmann.adesso.master.thesis.detection.model.api.Concept;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.QuestionGeneratorProperties;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.i18n.Messages;
import de.saschafeldmann.adesso.master.thesis.portlet.util.VaadinUtil;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.AutowirableGrid;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.AutowirableVerticalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
 * Implementation of an {@link DetectionEditConceptsView}.
 */
@Component
@Scope("prototype")
public class DetectionEditConceptsViewImpl extends Window implements DetectionEditConceptsView {
    private static final String CSS_STYLE_CONCEPTS_VIEW_NAME = "edit-concepts-window";
    public static final String CONCEPT_ATTRIBUTES_DELIMITER = ";";

    private final Messages messages;
    private final AutowirableVerticalLayout mainLayout;
    private final QuestionGeneratorProperties properties;
    private AutowirableGrid conceptsGrid;
    private DetectionEditConceptsViewListener viewListener;
    private Map<Object, Concept> rowConceptMap;

    /**
     * Constructs a new view impl.
     * @param messages the messages
     */
    @Autowired
    public DetectionEditConceptsViewImpl(final AutowirableVerticalLayout mainLayout, final Messages messages, final QuestionGeneratorProperties properties) {
        this.messages = messages;
        this.mainLayout = mainLayout;
        this.properties = properties;
    }

    @PostConstruct
    private void initialize() {
        setCaption();

        setContent(mainLayout);

        setStyles();
    }

    private void setCaption() {
        setCaption(messages.getDetectionViewAccordionDetectionChainEditWindowTitle());
    }

    @Override
    public void displayDetectedConcepts(final List<Concept> detectedConcepts) {
        mainLayout.removeAllComponents();
        rowConceptMap = new HashMap<>();

        setCaption();
        setCaption(getCaption() + " (" + detectedConcepts.size() +")");

        initializeGrid();

        initializeGridColumns();
        addRowsToGrid(detectedConcepts);

        this.setPosition(properties.getConceptDetectionEditWindowPositionX(), properties.getConceptDetectionEditWindowPositionY());

        // displays the window
        VaadinUtil.addWindow(getWindow());
    }

    private void initializeGrid() {
        conceptsGrid = new AutowirableGrid();
        mainLayout.addComponent(conceptsGrid);

        conceptsGrid.setWidth(properties.getConceptDetectionEditWindowGridWidthEm(), Unit.EM);
        conceptsGrid.setHeight(properties.getConceptDetectionEditWindowGridHeightEm(), Unit.EM);
    }

    private void initializeGridColumns() {
        conceptsGrid.setSelectionMode(com.vaadin.ui.Grid.SelectionMode.NONE);

        conceptsGrid.addColumn(messages.getDetectionViewAccordionDetectionChainEditWindowTableColumnConceptHeader(), String.class);
        conceptsGrid.addColumn(messages.getDetectionViewAccordionDetectionChainEditWindowTableColumnOriginalSentenceHeader(), String.class);
        com.vaadin.ui.Grid.Column columnAttributes = conceptsGrid.addColumn(messages.getDetectionViewAccordionDetectionChainEditWindowTableColumnAttributesHeader(), String.class);
        columnAttributes.setRenderer(new HtmlRenderer()); // allows HTML rendering in grid cells

        com.vaadin.ui.Grid.Column columnEdit = conceptsGrid.addColumn(messages.getDetectionViewAccordionDetectionChainEditWindowTableColumnEditHeader(), String.class);
        columnEdit.setRenderer(new ButtonRenderer(new ClickableRenderer.RendererClickListener() { // renders a button in grid cell
            @Override
            public void click(ClickableRenderer.RendererClickEvent rendererClickEvent) {
                // determine the concept that this row represents and fire the event
                Object rowId = rendererClickEvent.getItemId();
                Concept detectedConcept = rowConceptMap.get(rowId);

                viewListener.onEditButtonClicked(detectedConcept);
            }
        }));
    }

    private void addRowsToGrid(List<Concept> detectedConcepts) {
        for (Concept detectedConcept: detectedConcepts) {
            addRowToGrid(detectedConcept);
        }
    }

    private void addRowToGrid(final Concept detectedConcept) {
        Object rowId = conceptsGrid.addRow(getConceptTitle(detectedConcept), detectedConcept.getOriginalSentence(), getConceptAttributes(detectedConcept),
                messages.getDetectionViewAccordionDetectionChainEditWindowTableColumnEditEditButtonLabel());
        rowConceptMap.put(rowId, detectedConcept);
    }

    private String getConceptTitle(Concept detectedConcept) {
        if (detectedConcept instanceof FillInTheBlankTextConcept) {
            return messages.getDetectionViewAccordionDetectionChainEditWindowTableColumnConceptTypeFillsentences();
        } else {
            return messages.getDetectionViewAccordionDetectionChainEditWindowTableColumnConceptTypeCardinalitysentences();
        }
    }

    private String getConceptAttributes(Concept detectedConcept) {
        if (detectedConcept instanceof FillInTheBlankTextConcept) {
            return buildAttributesStringForFillSentence((FillInTheBlankTextConcept) detectedConcept);
        } else {
            return buildAttributesStringForCardinalitySentence((CardinalRelationConcept) detectedConcept);
        }
    }

    private String buildAttributesStringForFillSentence(FillInTheBlankTextConcept detectedConcept) {
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
                    .append(CONCEPT_ATTRIBUTES_DELIMITER);
    }

    private void setStyles() {
        addStyleName(CSS_STYLE_CONCEPTS_VIEW_NAME);
    }

    @Override
    public Window getWindow() {
        return this;
    }

    @Override
    public void setViewListener(DetectionEditConceptsViewListener viewListener) {
        this.viewListener = viewListener;
    }

    @Override
    public AutowirableGrid getDetectedConceptsGrid() {
        return conceptsGrid;
    }
}

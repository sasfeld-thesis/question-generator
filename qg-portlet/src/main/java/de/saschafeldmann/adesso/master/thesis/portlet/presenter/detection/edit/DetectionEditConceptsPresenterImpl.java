package de.saschafeldmann.adesso.master.thesis.portlet.presenter.detection.edit;

import com.vaadin.shared.data.sort.SortDirection;
import de.saschafeldmann.adesso.master.thesis.detection.model.CardinalRelationConcept;
import de.saschafeldmann.adesso.master.thesis.detection.model.FillInTheBlankTextConcept;
import de.saschafeldmann.adesso.master.thesis.detection.model.api.Concept;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.portlet.QuestionGeneratorPortletVaadinUi;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.i18n.Messages;
import de.saschafeldmann.adesso.master.thesis.portlet.view.detection.edit.DetectionEditConceptsViewListener;
import de.saschafeldmann.adesso.master.thesis.portlet.view.detection.edit.DetectionEditConceptsView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * <br /><br />
 * University:
 * Hochschule für Technik und Wirtschaft, Berlin
 * Fachbereich 4
 * Studiengang Internationale Medieninformatik (Master)
 * <br /><br />
 * Company:
 * adesso AG
 * <br /><br />
 * The implementation of {@link DetectionEditConceptsPresenter}.
 */
@Component
@Scope("prototype")
public class DetectionEditConceptsPresenterImpl implements DetectionEditConceptsPresenter, DetectionEditConceptsViewListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(DetectionEditConceptsPresenterImpl.class);
    private final DetectionEditConceptsView view;
    private final Messages messages;

    private Map<Class<? extends Concept>, DetectionEditConceptPresenter> conceptEditViewMap;
    private List<Concept> detectedConcepts;

    /**
     * Creates a new presenter.
     * @param detectionEditConceptsView the view which this presenter handles
     */
    @Autowired
    public DetectionEditConceptsPresenterImpl(final DetectionEditConceptsView detectionEditConceptsView, final Messages messages) {
        this.view = detectionEditConceptsView;
        this.messages = messages;
    }

    @PostConstruct
    private void initialize() {
        this.view.setViewListener(this);
    }

    @Override
    public void displayDetectedConcepts(final LearningContent learningContent, final List<Concept> detectedConcepts) {
        LOGGER.info("displayDetectedConcepts(): displaying detected concepts.");

        this.detectedConcepts = detectedConcepts;
        refresh();
    }

    private void applyInitialSorting() {
        view.getDetectedConceptsGrid().sort(messages.getDetectionViewAccordionDetectionChainEditWindowTableColumnConceptHeader(), SortDirection.ASCENDING);
    }

    @Override
    public DetectionEditConceptsView getView() {
        return view;
    }

    @Override
    public void onEditButtonClicked(final Concept conceptToBeEdited) {
        LOGGER.info("displayDetectedConcepts(): onEditButtonClicked");

        getEditPresenter(conceptToBeEdited).displayEditViewForConcept(conceptToBeEdited);
        refresh();
    }

    private DetectionEditConceptPresenter getEditPresenter(final Concept conceptToBeEdited) {
        if (null == conceptEditViewMap) {
            conceptEditViewMap = new HashMap<>();
            conceptEditViewMap.put(FillInTheBlankTextConcept.class, QuestionGeneratorPortletVaadinUi.getCurrentPortletVaadinUi().getDetectionEditFillTextConceptPresenter());
            conceptEditViewMap.put(CardinalRelationConcept.class, QuestionGeneratorPortletVaadinUi.getCurrentPortletVaadinUi().getDetectionEditCardinalRelationConceptPresenter());
        }

        return conceptEditViewMap.get(conceptToBeEdited.getClass());
    }

    private void refresh() {
        view.displayDetectedConcepts(detectedConcepts);
        applyInitialSorting();
    }
}

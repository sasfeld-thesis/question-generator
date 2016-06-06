package de.saschafeldmann.adesso.master.thesis.portlet.presenter.preprocesses;

import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.portlet.model.preprocesses.ProcessActivationElement;
import de.saschafeldmann.adesso.master.thesis.portlet.presenter.AbstractStepPresenter;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.i18n.Messages;
import de.saschafeldmann.adesso.master.thesis.portlet.view.course.information.CourseInformationViewImpl;
import de.saschafeldmann.adesso.master.thesis.portlet.view.preprocesses.PreprocessesView;
import de.saschafeldmann.adesso.master.thesis.portlet.view.preprocesses.PreprocessesViewImpl;
import de.saschafeldmann.adesso.master.thesis.portlet.view.preprocesses.PreprocessesViewListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  04.06.2016
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
 * Implementation of the {@link PreprocessesPresenter}
 */
@Component
@Scope("prototype")
public class PreprocessesPresenterImpl extends AbstractStepPresenter implements PreprocessesPresenter, PreprocessesViewListener {
    private PreprocessesView preprocessesView;
    @Autowired
    private Messages messages;

    /**
     * Creates a new preprocesses presenter.
     * @param preprocessesView
     */
    @Autowired
    public PreprocessesPresenterImpl(
            PreprocessesView preprocessesView
    ) {
        this.preprocessesView = preprocessesView;
    }

    @Override
    public PreprocessesView initializeView() {
        this.preprocessesView.setMenuListener(this);
        this.preprocessesView.setCurrentSessionStatus(questionGenerationSession.getStatus());
        this.preprocessesView.setViewListener(this);

        loadProcessActivationElements();

        this.preprocessesView.reset();
        return this.preprocessesView;
    }

    private void loadProcessActivationElements() {
        List<ProcessActivationElement> processActivationElements = new ArrayList<ProcessActivationElement>();

        addLanguageDetectionActivationElement(processActivationElements);
        addPartOfSpeechActivationElement(processActivationElements);
        addNamedEntityRecognitionActivationElement(processActivationElements);

        this.preprocessesView.setProcessActivationElements(processActivationElements);
    }

    private void addLanguageDetectionActivationElement(List<ProcessActivationElement> processActivationElements) {
        ProcessActivationElement processActivationElement = new ProcessActivationElement.ProcessActivationElementBuilder()
                .withActivationLabel(messages.getPreproccesesViewAccordionActivationOptiongroupLanguageDetectionLabel())
                .withIsActivatedPerDefault(false)
                .withTooltip(messages.getPreproccesesViewAccordionActivationOptiongroupLanguageDetectionTooltip())
                .build();

        processActivationElements.add(processActivationElement);
    }

    private void addPartOfSpeechActivationElement(List<ProcessActivationElement> processActivationElements) {
        ProcessActivationElement processActivationElement = new ProcessActivationElement.ProcessActivationElementBuilder()
                .withActivationLabel(messages.getPreproccesesViewAccordionActivationOptiongroupPartOfSpeechDetectionLabel())
                .withIsActivatedPerDefault(false)
                .withTooltip(messages.getPreproccesesViewAccordionActivationOptiongroupPartOfSpeechDetectionTooltip())
                .build();

        processActivationElements.add(processActivationElement);
    }

    private void addNamedEntityRecognitionActivationElement(List<ProcessActivationElement> processActivationElements) {
        ProcessActivationElement processActivationElement = new ProcessActivationElement.ProcessActivationElementBuilder()
                .withActivationLabel(messages.getPreproccesesViewAccordionActivationOptiongroupNamedEntitiesDetectionLabel())
                .withIsActivatedPerDefault(false)
                .withTooltip(messages.getPreproccesesViewAccordionActivationOptiongroupNamedEntitiesDetectionTooltip())
                .build();

        processActivationElements.add(processActivationElement);
    }

    @Override
    public void onProcessChainStartButtonClick() {

    }

    @Override
    public void onEditLearningContentClick(LearningContent learningContentToBeEdited) {

    }

    @Override
    public void onActivationElementChange(ProcessActivationElement processActivationElement) {

    }

    @Override
    public void onViewFocus() {
        preprocessesView.setCurrentSessionStatus(questionGenerationSession.getStatus());
        preprocessesView.reset();
    }
}

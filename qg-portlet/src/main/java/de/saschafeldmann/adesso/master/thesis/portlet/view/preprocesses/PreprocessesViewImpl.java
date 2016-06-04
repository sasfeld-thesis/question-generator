package de.saschafeldmann.adesso.master.thesis.portlet.view.preprocesses;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.label.ContentMode;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.portlet.model.preprocesses.ProcessActivationElement;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.i18n.Messages;
import de.saschafeldmann.adesso.master.thesis.portlet.view.AbstractStepView;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

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
 * Vaadin view implementation of the {@link PreprocessesView}.
 */
@org.springframework.stereotype.Component
@Scope("prototype")
public class PreprocessesViewImpl extends AbstractStepView implements PreprocessesView{
    public static final String VIEW_NAME = "PreprocessesView";
    private final InfoBox infoBox;
    private final Label introductionLabel;
    private final Accordion accordion;

    private final VerticalLayout accordionActivationLayout;
    private final VerticalLayout accordionProcessChainLayout;

    private final HorizontalLayout bottomButtonGroupLayout;
    private final Button btnNext;
    private final Button btnPrevious;
    private PreprocessesViewListener viewListener;

    @Autowired
    public PreprocessesViewImpl(
            final Messages messages,
            final VersionLabel versionLabel,
            final InfoBox infoBox,
            final Label introductionLabel,
            final Accordion accordion,
            final VerticalLayout accordionActivationLayout,
            final VerticalLayout accordionProcessChainLayout,
            final HorizontalLayout bottomButtonGroupLayout,
            final Button btnNext,
            final Button btnPrevious
    ) {
        super(messages, versionLabel);

        this.infoBox = infoBox;
        this.introductionLabel = introductionLabel;
        this.accordion = accordion;
        this.accordionActivationLayout = accordionActivationLayout;
        this.accordionProcessChainLayout = accordionProcessChainLayout;
        this.bottomButtonGroupLayout = bottomButtonGroupLayout;
        this.btnNext = btnNext;
        this.btnPrevious = btnPrevious;
    }

    @PostConstruct
    private void initialize() {
        initializeIntroductionLabel();
        initializeAccordion();

        initializeBottomButtonGroup();
    }

    private void initializeIntroductionLabel() {
        this.introductionLabel.setContentMode(ContentMode.HTML);
        this.introductionLabel.setCaption(messages.getPreproccesesViewIntroductionText());

    }

    private void initializeAccordion() {
        initializeActivationPart();

        initializeProcessChainPart();
    }

    private void initializeBottomButtonGroup() {
        this.btnPrevious.setCaption(messages.getButtonBackTitle());
        this.btnNext.setCaption(messages.getButtonNextTitle());
    }

    private void initializeActivationPart() {
        accordion.addTab(accordionActivationLayout, messages.getPreproccesesViewAccordionActivationLabel());
    }

    private void initializeProcessChainPart() {
        accordion.addTab(accordionProcessChainLayout, messages.getPreproccesesViewAccordionProcesschainLabel());
    }


    @Override
    public void setProcessActivationElements(Iterable<ProcessActivationElement> elements) {

    }

    @Override
    public void showProcessActivationSuccessMessage() {

    }

    @Override
    public void addProcessChainLogEntry(String logEntry) {

    }

    @Override
    public void showProcessedLearningContents(Iterable<LearningContent> learningContents) {

    }

    @Override
    public void reset() {
        // add menu and set the preprocessing item to be active
        super.reset(messages.getMenuItemPreprocessesLabel());

        setInfoBox();
        addComponent(infoBox);
        addComponent(introductionLabel);
        addComponent(accordion);

        addFooterWithButtonGroup();
    }

    private void addFooterWithButtonGroup() {
        addButtonsAtBottom(bottomButtonGroupLayout, btnPrevious, btnNext);
        addComponent(bottomButtonGroupLayout);

        addFooter();
    }

    private void setInfoBox() {
        this.infoBox.setInfo();
        this.infoBox.setCaption(messages.getPreproccesesViewInfoText());
    }

    @Override
    public void setViewListener(PreprocessesViewListener preprocessesViewListener) {
        this.viewListener = preprocessesViewListener;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {
        viewListener.onViewFocus();
    }
}

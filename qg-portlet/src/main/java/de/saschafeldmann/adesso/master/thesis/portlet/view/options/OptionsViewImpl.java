package de.saschafeldmann.adesso.master.thesis.portlet.view.options;

import com.vaadin.ui.Window;
import de.saschafeldmann.adesso.master.thesis.detection.algorithm.DetectionOptions;
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
 * Creation date:  01.08.2016
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
 * The implementation of {@link OptionsView}
 */
@Component
@Scope("prototype")
public class OptionsViewImpl extends Window implements OptionsView {
    private static final String CSS_STYLE_NAME_WINDOW = "options-window";
    public static final String CSS_STYLE_NAME_EDIT_BUTTON = "edit-button";

    private OptionsViewListener viewListener;

    private final Messages messages;
    private final AutowirableFormLayout formLayout;
    private final AutowirableTextField inputNumberFillTextQuestions;
    private final AutowirableTextField inputNumberCardinalityQuestions;
    private final AutowirableTextField inputMaxNumberTokensFillTextQuestions;
    private final AutowirableHorizontalLayout buttonGroupLayout;
    private final AutowirableButton editButton;
    private final AutowirableButton resetButton;
    private final QuestionGeneratorProperties questionGeneratorProperties;

    /**
     * Constructs a new view.
     * @param messages the messagse
     * @param formLayout the form layout
     * @param inputNumberFillTextQuestions the input
     * @param inputNumberCardinalityQuestions the input
     * @param inputMaxNumberTokensFillTextQuestions the input
     * @param editButton the edit button
     * @param resetButton the reset button
     */
    @Autowired
    public OptionsViewImpl(
            final Messages messages,
            final AutowirableFormLayout formLayout,
            final AutowirableTextField inputNumberFillTextQuestions,
            final AutowirableTextField inputNumberCardinalityQuestions,
            final AutowirableTextField inputMaxNumberTokensFillTextQuestions,
            final AutowirableHorizontalLayout buttonGroupLayout,
            final AutowirableButton editButton,
            final AutowirableButton resetButton,
            final QuestionGeneratorProperties questionGeneratorProperties
    ) {
        this.messages = messages;
        this.formLayout = formLayout;
        this.inputNumberFillTextQuestions = inputNumberFillTextQuestions;
        this.inputNumberCardinalityQuestions = inputNumberCardinalityQuestions;
        this.inputMaxNumberTokensFillTextQuestions = inputMaxNumberTokensFillTextQuestions;
        this.buttonGroupLayout = buttonGroupLayout;
        this.editButton = editButton;
        this.resetButton = resetButton;
        this.questionGeneratorProperties = questionGeneratorProperties;
    }

    @PostConstruct
    private void initialize() {
        setStyles();

        arrangeComponents();

        setLabels();
        setActionListeners();

        setPosition(questionGeneratorProperties.getOptionsEditWindowPositionX(), questionGeneratorProperties.getOptionsEditWindowPositionY());
        setContent(formLayout);
    }

    private void arrangeComponents() {
        formLayout.addComponent(inputNumberFillTextQuestions);
        formLayout.addComponent(inputNumberCardinalityQuestions);
        formLayout.addComponent(inputMaxNumberTokensFillTextQuestions);

        buttonGroupLayout.addComponent(editButton);
        buttonGroupLayout.addComponent(resetButton);
        formLayout.addComponent(buttonGroupLayout);
    }

    private void setStyles() {
        addStyleName(CSS_STYLE_NAME_WINDOW);
        editButton.addStyleName(CSS_STYLE_NAME_EDIT_BUTTON);
    }

    private void setActionListeners() {
        editButton.addClickListener(new com.vaadin.ui.Button.ClickListener() {
            @Override
            public void buttonClick(com.vaadin.ui.Button.ClickEvent clickEvent) {
                viewListener.onEditButtonClicked();
            }
        });

        resetButton.addClickListener(new com.vaadin.ui.Button.ClickListener() {
            @Override
            public void buttonClick(com.vaadin.ui.Button.ClickEvent clickEvent) {
                viewListener.onResetButtonClicked();
            }
        });
    }

    private void setLabels() {
        setCaption(messages.getOptionsViewCaption());

        inputNumberFillTextQuestions.setCaption(messages.getOptionsViewNumberFillTextQuestionsLabel());
        inputNumberFillTextQuestions.setDescription(messages.getOptionsViewNumberFillTextQuestionsTooltip());
        inputNumberCardinalityQuestions.setCaption(messages.getOptionsViewNumberCardinalityQuestionsLabel());
        inputNumberCardinalityQuestions.setDescription(messages.getOptionsViewNumberCardinalityQuestionsTooltip());
        inputMaxNumberTokensFillTextQuestions.setCaption(messages.getOptionsViewMaxNumberTokensFillTextQuestionsLabel());
        inputMaxNumberTokensFillTextQuestions.setDescription(messages.getOptionsViewMaxNumberTokensFillTextQuestionsTooltip());

        editButton.setCaption(messages.getOptionsViewEditLabel());
        resetButton.setCaption(messages.getOptionsViewResetLabel());
    }

    @Override
    public void show() {
        // displays the window
        VaadinUtil.addWindow(this);
    }

    @Override
    public void close() {
        super.close();
    }

    @Override
    public void setViewListener(OptionsViewListener optionsViewListener) {
        this.viewListener = optionsViewListener;
    }

    @Override
    public int getFillTextQuestionsNumberInputValue() {
        return Integer.parseInt(inputNumberFillTextQuestions.getValue());
    }

    @Override
    public void setFillTextQuestionsNumberInputValue(int numberOfFilltextQuestions) {
        inputNumberFillTextQuestions.setValue(String.valueOf(numberOfFilltextQuestions));
    }

    @Override
    public int getCardinalityQuestionsNumberInputValue() {
        return Integer.parseInt(inputNumberCardinalityQuestions.getValue());
    }

    @Override
    public void setCardinalityQuestionsNumberInputValue(int numberOfCardinalityQuestions) {
        inputNumberCardinalityQuestions.setValue(String.valueOf(numberOfCardinalityQuestions));
    }

    @Override
    public int getMaxNumberOfTokensInFilltextQuestionsInputValue() {
        return Integer.parseInt(inputMaxNumberTokensFillTextQuestions.getValue());
    }

    @Override
    public void setMaxNumberOfTokensInFilltextQuestionsInputValue(int maxNumberOfTokensInFilltextQuestionsInputValue) {
        inputMaxNumberTokensFillTextQuestions.setValue(String.valueOf(maxNumberOfTokensInFilltextQuestionsInputValue));
    }

    @Override
    public void reset() {
        inputNumberFillTextQuestions.setValue(String.valueOf(DetectionOptions.UNLIMITED));
        inputNumberCardinalityQuestions.setValue(String.valueOf(DetectionOptions.UNLIMITED));

        setLabels();
    }
}

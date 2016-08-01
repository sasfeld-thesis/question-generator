package de.saschafeldmann.adesso.master.thesis.portlet.view.options;

import com.vaadin.ui.Window;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.QuestionGeneratorProperties;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.i18n.Messages;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.Button;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.FormLayout;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.TextField;
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
    private static final String CSS_STYLE_NAME_WINDOW = "question-generation-edit-question-window";
    public static final String CSS_STYLE_NAME_EDIT_BUTTON = "edit-button";

    private OptionsViewListener viewListener;

    private final Messages messages;
    private final FormLayout formLayout;
    private final TextField inputNumberFillTextQuestions;
    private final TextField inputNumberCardinalityQuestions;
    private final Button editButton;
    private final Button resetButton;
    private final QuestionGeneratorProperties questionGeneratorProperties;

    /**
     * Constructs a new view.
     * @param messages the messagse
     * @param formLayout the form layout
     * @param inputNumberFillTextQuestions the input
     * @param inputNumberCardinalityQuestions the input
     * @param editButton the edit button
     * @param resetButton the reset button
     */
    @Autowired
    public OptionsViewImpl(
            final Messages messages,
            final FormLayout formLayout,
            final TextField inputNumberFillTextQuestions,
            final TextField inputNumberCardinalityQuestions,
            final Button editButton,
            final Button resetButton,
            final QuestionGeneratorProperties questionGeneratorProperties
    ) {
        this.messages = messages;
        this.formLayout = formLayout;
        this.inputNumberFillTextQuestions = inputNumberFillTextQuestions;
        this.inputNumberCardinalityQuestions = inputNumberCardinalityQuestions;
        this.editButton = editButton;
        this.resetButton = resetButton;
        this.questionGeneratorProperties = questionGeneratorProperties;
    }

    @PostConstruct
    private void initialize() {
        setLabels();
        setActionListeners();

        setPosition(questionGeneratorProperties.getOptionsEditWindowPositionX(), questionGeneratorProperties.getOptionsEditWindowPositionY());
        setContent(formLayout);
    }

    private void setActionListeners() {

    }

    private void setLabels() {
        setCaption(messages.getOptionsViewCaption());

        inputNumberFillTextQuestions.setCaption(messages.getOptionsViewNumberFillTextQuestionsLabel());
        inputNumberFillTextQuestions.setDescription(messages.getOptionsViewNumberFillTextQuestionsTooltip());
        inputNumberCardinalityQuestions.setCaption(messages.getOptionsViewNumberCardinalityQuestionsLabel());
        inputNumberCardinalityQuestions.setDescription(messages.getOptionsViewNumberCardinalityQuestionsTooltip());

        editButton.setCaption(messages.getOptionsViewEditLabel());
        resetButton.setCaption(messages.getOptionsViewResetLabel());
    }

    @Override
    public void show() {

    }

    @Override
    public void close() {

    }

    @Override
    public void setViewListener(OptionsViewListener optionsViewListener) {

    }

    @Override
    public int getFillTextQuestionsNumberInputValue() {
        return 0;
    }

    @Override
    public void setFillTextQuestionsNumberInputValue(int numberOfFilltextQuestions) {

    }

    @Override
    public int getCardinalityQuestionsNumberInputValue() {
        return 0;
    }

    @Override
    public void setCardinalityQuestionsNumberInputValue(int numberOfCardinalityQuestions) {

    }

    @Override
    public void reset() {

    }
}

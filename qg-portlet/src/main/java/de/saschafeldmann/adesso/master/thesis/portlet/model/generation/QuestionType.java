package de.saschafeldmann.adesso.master.thesis.portlet.model.generation;

import de.saschafeldmann.adesso.master.thesis.portlet.QuestionGeneratorPortletVaadinUi;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  05.07.2016
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
 * Enumeration of all available question types.
 */
public enum QuestionType {
    /**
     * Short answer questions: questions that have a short correct solution.
     */
    SHORT_ANSWER_QUESTION("de.saschafeldmann.adesso.master.thesis.portlet.question.generation.view.finished.questions.edit.window.input.question.type.option.single"),
    /**
     * Multiple chice questions: questions that have one ore more correct solutions and different incorrect ones.
     */
    MULTIPLE_CHOICE_QUESTION("de.saschafeldmann.adesso.master.thesis.portlet.question.generation.view.finished.questions.edit.window.input.question.type.option.multiple");

    private String displayLabelMessageKey;

    /**
     * Creates the question type with the given message key representing the enum's display value.
     * @param displayLabelMessageKey the message key to the display value
     */
    QuestionType(final String displayLabelMessageKey) {
        this.displayLabelMessageKey = displayLabelMessageKey;
    }

    @Override
    public String toString() {
        return QuestionGeneratorPortletVaadinUi.getCurrentPortletVaadinUi().getMessages().get(displayLabelMessageKey);
    }
}

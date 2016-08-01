package de.saschafeldmann.adesso.master.thesis.portlet.view.options;

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
 * View where the user can do settings.
 */
public interface OptionsView {
    /**
     * Shows the dialog.
     */
    void show();

    /**
     * Closed the dialog.
     */
    void close();

    /**
     * Sets the view listener.
     * @param optionsViewListener the view listener.
     */
    void setViewListener(OptionsViewListener optionsViewListener);

    /**
     * Gets the user input of the input field number of filltext questions.
     * @return the number of filltext questions
     */
    int getFillTextQuestionsNumberInputValue();

    /**
     * Sets the value.
     * @param numberOfFilltextQuestions the number of filltext questions
     */
    void setFillTextQuestionsNumberInputValue(int numberOfFilltextQuestions);

    /**
     * Gets the user input of the input field number of cardinality questions.
     * @return the number of cardinality questions
     */
    int getCardinalityQuestionsNumberInputValue();

    /**
     * Sets the value.
     * @param numberOfCardinalityQuestions the number of cardinality questions
     */
    void setCardinalityQuestionsNumberInputValue(int numberOfCardinalityQuestions);

    /**
     * Resets the view.
     */
    void reset();
}

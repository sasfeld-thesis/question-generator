package de.saschafeldmann.adesso.master.thesis.portlet.view.generation.edit;

import de.saschafeldmann.adesso.master.thesis.generation.model.TestQuestion;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  05.07.2016
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
 * Interface for listeners of the {@link de.saschafeldmann.adesso.master.thesis.portlet.view.generation.QuestionGenerationView}
 */
public interface QuestionGenerationEditQuestionViewListener {

    /**
     * Triggered after the user clicked the edit button.
     * @param testQuestionToBeEdited the test question to be edited
     */
    void onEditButtonClicked(final TestQuestion testQuestionToBeEdited);

    /**
     * Triggered after the user clicked the delete button.
     * @param testQuestionToBeDeleted the test question to be deleted.
     */
    void onDeleteButtonClicked(final TestQuestion testQuestionToBeDeleted);

    /**
     * Triggered if the view was closed.
     * @param testQuestion the question
     */
    void onClosed(TestQuestion testQuestion);
}

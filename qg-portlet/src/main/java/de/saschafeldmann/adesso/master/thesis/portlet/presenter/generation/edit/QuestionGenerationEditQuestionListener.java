package de.saschafeldmann.adesso.master.thesis.portlet.presenter.generation.edit;

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
 * Interface for listeners of {@link QuestionGenerationEditQuestionPresenter}
 */
public interface QuestionGenerationEditQuestionListener
{
    /**
     * Triggered if the dialog was closed.
     * @param testQuestion the test question for which the edit dialog was closed
     */
    void onEditQuestionDialogClosed(TestQuestion testQuestion);
}

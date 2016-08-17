package de.saschafeldmann.adesso.master.thesis.portlet.view.generation;

import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.generation.model.TestQuestion;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  04.07.2016
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
 * Interface for listeners of the {@link QuestionGenerationView}
 */
public interface QuestionGenerationViewListener {

    /**
     * Triggered if the start question generation button was clicked.
     */
    void onStartQuestionGenerationButtonClicked();

    /**
     * Triggered if the given learning content was selected.
     * @param learningContent the selected learning content
     */
    void onCompletedLearningContentSelected(final LearningContent learningContent);

    /**
     * Triggered if the given test question was selected.
     * @param testQuestion the selected test question.
     */
    void onGeneratedQuestionSelected(final TestQuestion testQuestion);

    /**
     * Triggered if the user clicked the back butotn.
     */
    void onBackButtonClicked();

    /**
     * Triggered if the view was focused by the user.
     */
    void onViewFocus();
}

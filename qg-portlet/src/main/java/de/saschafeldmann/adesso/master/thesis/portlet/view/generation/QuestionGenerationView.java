package de.saschafeldmann.adesso.master.thesis.portlet.view.generation;

import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.generation.model.TestQuestion;
import de.saschafeldmann.adesso.master.thesis.portlet.view.ViewWithMenu;

import java.util.List;

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
 * Question generation view.
 */
public interface QuestionGenerationView extends ViewWithMenu {

    /**
     * Displays the list of learning contents for which the question generation was completed.
     * @param learningContents the learning contents
     */
    public void displayCompletedLearningContents(final List<LearningContent> learningContents);

    /**
     * Displays the given generated {@link TestQuestion}
     * @param testQuestions list of the generated {@link TestQuestion}
     */
    public void displayGeneratedQuestions(final List<TestQuestion> testQuestions);

    /**
     * Resets the view.
     */
    public void reset();

    /**
     * Sets the listener of this view.
     * @param viewListener the listener
     */
    public void setViewListener(QuestionGenerationViewListener viewListener);
}

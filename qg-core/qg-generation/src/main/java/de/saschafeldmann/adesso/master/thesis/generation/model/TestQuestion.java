package de.saschafeldmann.adesso.master.thesis.generation.model;

import de.saschafeldmann.adesso.master.thesis.detection.model.api.Concept;

import java.util.ArrayList;
import java.util.List;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  04.07.2016
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
 * Model of the finally created test question.
 */
public class TestQuestion {
    private final String sourceSentence;
    private final Concept sourceConcept;

    private String question;
    private String correctAnswer;;
    private List<String> alternativeWrongAnswers = new ArrayList<>();
    private List<String> alternativeCorrectAnswers = new ArrayList<>();
    private String label = "";

    /**
     * Creates the question.
     * @param sourceSentence tbe source sentence to which this question was generated
     */
    public TestQuestion(final String sourceSentence, final Concept sourceConcept) {
        this.sourceSentence = sourceSentence;
        this.sourceConcept = sourceConcept;
    }

    /**
     * Gets the generated question.
     * @return generated question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * Gets the generated question.
     * @param question generated question
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * Gets the correct answer.
     * @return correct answer.
     */
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Sets the correct answer.
     * @param correctAnswer the correct answer to be set.
     */
    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    /**
     * Sets the label of this test question.
     * @param label
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * Gets the modifiable list of alternative wrong answers.
     * @return the list of alternative wrong answers for muliple-choice
     */
    public List<String> getAlternativeWrongAnswers() {
        return alternativeWrongAnswers;
    }

    /**
     * Gets the modifiable list of alternative correct answers.
     * @return the list of alternative correct answers for muliple-choice
     */
    public List<String> getAlternativeCorrectAnswers() {
        return alternativeCorrectAnswers;
    }

    /**
     * Whether this is a multiple or single answer question.
     * @return true if this is a multiple choice question
     */
    public boolean isMultipleChoice() {
        return getAlternativeCorrectAnswers().size() > 0 || getAlternativeWrongAnswers().size() > 0;
    }

    /**
     * Gets the source concept that this question was generated for.
     * @return the concept
     */
    public Concept getSourceConcept() {
        return sourceConcept;
    }

    @Override
    public String toString() {
        return label;
    }
}

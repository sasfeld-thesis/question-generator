package de.saschafeldmann.adesso.master.thesis.generation.model;

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
 * Model of the finally created test question.
 */
public class TestQuestion {
    private final String sourceSentence;

    private String question;
    private String correctAnswer;;
    private List<String> alternativeWrongAnswers;

    /**
     * Creates the question.
     * @param sourceSentence tbe source sentence to which this question was generated
     */
    public TestQuestion(final String sourceSentence) {
        this.sourceSentence = sourceSentence;
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
     * Gets the list of alternative wrong answers.
     * @return the list of alternative wrong answers for muliple-choice
     */
    public List<String> getAlternativeWrongAnswers() {
        return alternativeWrongAnswers;
    }

    /**
     * Sets the alternative wrong answers.
     * @param alternativeWrongAnswers the list of alternative wrong answers for multiple-choice
     */
    public void setAlternativeWrongAnswers(List<String> alternativeWrongAnswers) {
        this.alternativeWrongAnswers = alternativeWrongAnswers;
    }
}

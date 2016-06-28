package de.saschafeldmann.adesso.master.thesis.detection.algorithm.model;

import static com.google.common.base.Preconditions.*;

import de.saschafeldmann.adesso.master.thesis.detection.algorithm.model.api.AbstractConcept;
import de.saschafeldmann.adesso.master.thesis.detection.algorithm.model.api.Concept;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  27.06.2016
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
 * Filltexts are simple concepts. <br />
 * Those are sentences that contain an object that is a named entity or part-of-speech-tag.<br />
 * For the question generation part, the learning student will be asked to fill the object.<br />
 * E.g. if the original sentence was "Berlin is the capital of Germany."
 * the fill text will be "Berlin is the capital of ___?"
 */
public class FillTextConcept extends AbstractConcept implements Concept {
    private final String fillSentence;
    private final String correctAnswer;

    private FillTextConcept(FillTextConceptBuilder fillTextConceptBuilder) {
        super(fillTextConceptBuilder.learningContent, fillTextConceptBuilder.originalSentence);

        fillSentence = fillTextConceptBuilder.fillSentence;
        correctAnswer = fillTextConceptBuilder.correctAnswer;
    }

    @Override
    public void getDocumentPlan() {
        // TODO
    }

    /**
     * Gets the sentence that needs to be filled in, e.g.
     * "Berlin is the capital of ___".
     * @return the fill sentence
     */
    public String getFillSentence() {
        return fillSentence;
    }

    /**
     * Gets the correct answer, e.g. "Germany" for the fill sentence "Berlin is the capital of ___".
     *
     * @return String
     */
    public String getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Builder for immutable {@link FillTextConcept}
     */
    public static class FillTextConceptBuilder {
        private String fillSentence;
        private String correctAnswer;
        private String originalSentence;
        private LearningContent learningContent;

        /**
         * Adds fill sentence - the sentence that needs to be filled in, e.g.
         * "Berlin is the capital of ___".
         * @param fillSentence the fill sentence
         * @return this
         */
        public FillTextConceptBuilder withFillSentence(final String fillSentence) {
            checkNotNull(fillSentence, "fillSentence must not be null");

            this.fillSentence = fillSentence;

            return this;
        }

        /**
         * The correct answer, e.g. "Germany" for the fill sentence "Berlin is the capital of ___".
         * @param correctAnswer the correct answer
         * @return this
         */
        public FillTextConceptBuilder withCorrectAnswer(final String correctAnswer) {
            checkNotNull(correctAnswer, "correctAnswer must not be null");

            this.correctAnswer = correctAnswer;

            return this;
        }

        /**
         * The original sentence, e.g. for fill sentence "Berlin is the capital of ___"
         * this would be "Berlin is the capital of Germany.".
         * @param originalSentence originalSentence
         * @return this
         */
        public FillTextConceptBuilder withOriginalSentence(final String originalSentence) {
            checkNotNull(originalSentence, "originalSentence must not be null");

            this.originalSentence = originalSentence;

            return this;
        }

        /**
         * The underlying learning cotnent.
         * @param learningContent learning content
         * @return this
         */
        public FillTextConceptBuilder withLearningContent(final LearningContent learningContent) {
            checkNotNull(learningContent, "learningContent must not be null");

            this.learningContent = learningContent;

            return this;
        }

        /**
         * Builds the final/immutable fill text concept.
         * @return the final fill text concept.
         */
        public FillTextConcept build() {
            checkNotNull(fillSentence, "fillSentence must not be null");
            checkNotNull(correctAnswer, "correctAnswer must not be null");
            checkNotNull(originalSentence, "originalSentence must not be null");
            checkNotNull(learningContent, "learningContent must not be null");

            return new FillTextConcept(this);
        }
    }
}
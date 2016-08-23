package de.saschafeldmann.adesso.master.thesis.elearningimport.model;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  15.08.2016
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
 * Simple object to store statistics information such as the runtime of algorithms.
 */
public class Statistics {
    private long numberOfWords;
    private long numberOfSentences;
    private long languageDetectionRuntime;
    private long naturalLanguageProcessingRuntime;
    private long fillInTheBlankTextConceptDetectionRuntime;
    private long cardinalitySentenceDetectionRuntime;
    private long questionGenerationRuntime;
    private long numberOfDetectedConcepts;

    /**
     * Gets the number of words from the e-learning input.
     * @return the number of words
     */
    public long getNumberOfWords() {
        return numberOfWords;
    }

    /**
     * Sets the number of words from the e-learning input.
     * @param numberOfWords the number of words
     */
    public void setNumberOfWords(long numberOfWords) {
        this.numberOfWords = numberOfWords;
    }

    /**
     * Gets the number of sentences from the e-learning input.
     * @return the number of sentences
     */
    public long getNumberOfSentences() {
        return numberOfSentences;
    }

    /**
     * Sets the number of sentences from the e-learning input.
     * @param numberOfSentences the number of sentences
     */
    public void setNumberOfSentences(long numberOfSentences) {
        this.numberOfSentences = numberOfSentences;
    }

    /**
     * Gets the language detection runtime.
     * @return the language detection runtime
     */
    public long getLanguageDetectionRuntime() {
        return languageDetectionRuntime;
    }

    /**
     * Sets the language detection runtime.
     * @param languageDetectionRuntime the language detection runtime
     */
    public void setLanguageDetectionRuntime(long languageDetectionRuntime) {
        this.languageDetectionRuntime = languageDetectionRuntime;
    }

    /**
     * Gets the NLP recognition runtime
     * @return the NLP runtime
     */
    public long getNaturalLanguageProcessingRuntime() {
        return naturalLanguageProcessingRuntime;
    }

    /**
     * Sets the NLP runtime
     * @param naturalLanguageProcessingRuntime  the NLP runtime
     */
    public void setNaturalLanguageProcessingRuntime(long naturalLanguageProcessingRuntime) {
        this.naturalLanguageProcessingRuntime = naturalLanguageProcessingRuntime;
    }

    /**
     * Gets the fill-in-the-blank-text concept detection runtime
     * @return the runtime
     */
    public long getFillInTheBlankTextConceptDetectionRuntime() {
        return fillInTheBlankTextConceptDetectionRuntime;
    }

    /**
     * Sets the fill-in-the-blank-text concept detection runtime
     * @param fillInTheBlankTextConceptDetectionRuntime  the runtime
     */
    public void setFillInTheBlankTextConceptDetectionRuntime(long fillInTheBlankTextConceptDetectionRuntime) {
        this.fillInTheBlankTextConceptDetectionRuntime = fillInTheBlankTextConceptDetectionRuntime;
    }

    /**
     * Gets the cardinality sentence detection runtime.
     * @return the runtime
     */
    public long getCardinalitySentenceDetectionRuntime() {
        return cardinalitySentenceDetectionRuntime;
    }

    /**
     * Sets the cardinality sentence detection runtime.
     * @param cardinalitySentenceDetectionRuntime  the runtime
     */
    public void setCardinalitySentenceDetectionRuntime(long cardinalitySentenceDetectionRuntime) {
        this.cardinalitySentenceDetectionRuntime = cardinalitySentenceDetectionRuntime;
    }

    /**
     * Gets the question generation runtime.
     * @return the runtime
     */
    public long getQuestionGenerationRuntime() {
        return questionGenerationRuntime;
    }

    /**
     * Sets the question generation runtime
     * @param questionGenerationRuntime the runtime
     */
    public void setQuestionGenerationRuntime(long questionGenerationRuntime) {
        this.questionGenerationRuntime = questionGenerationRuntime;
    }

    /**
     * Gets the number of detected concepts.
     * @return the number of detected concepts.
     */
    public long getNumberOfDetectedConcepts() {
        return numberOfDetectedConcepts;
    }

    /**
     * Sets the number of detected concepts.
     * @param numberOfDetectedConcepts number of detected concepts.
     */
    public void setNumberOfDetectedConcepts(long numberOfDetectedConcepts) {
        this.numberOfDetectedConcepts = numberOfDetectedConcepts;
    }

    /**
     * Resets the NLP statistics. Should be done if NLP is newly processed.
     */
    public void resetNlpnStatistics() {
        this.numberOfWords = 0;
        this.numberOfSentences = 0;
        this.naturalLanguageProcessingRuntime = 0;
        this.languageDetectionRuntime = 0;
    }

    /**
     * Resets the concept detection statistics. Should be done if concept detection is newly processed.
     */
    public void resetConceptDetectionStatistics() {
        this.numberOfDetectedConcepts = 0;
        this.cardinalitySentenceDetectionRuntime = 0;
        this.fillInTheBlankTextConceptDetectionRuntime = 0;
    }

    /**
     * Resets the question generation statistics. Should be done if the generation is newly processed.
     */
    public void resetQuestionGenerationtatistics() {
        this.questionGenerationRuntime = 0;
    }
}

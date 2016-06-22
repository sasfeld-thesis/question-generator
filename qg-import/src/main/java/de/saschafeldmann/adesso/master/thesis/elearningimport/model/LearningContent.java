package de.saschafeldmann.adesso.master.thesis.elearningimport.model;

import com.google.common.base.Strings;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  24.05.2016
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
 * Each course consists of n {@link LearningContent} instances which represent the courses learning material.
 * One course learning unit content instance should encapsulate a single learning unit (typically, one slide set of PDF pages is a learning unit), for example
 * a learning unit "Exceptions" in a programming course.
 *
 */
public class LearningContent {
    private final static String ANNOTATED_TEXT_DELETED = "!!!!deleted!!!!";

    public enum Type {
        /**
         * The learning content was parsed from a file, e.g. a PDF.
         */
        FILE,
        /**
         * The learning content was parsed from direct input raw text.
         */
        DIRECT_RAWTEXT
    }

    private final String title;
    private final String rawText;
    private String partOfSpeechAnnotatedText = "";
    private String namedEntityAnnotatedText = "";
    private Language determinedLanguage;
    private final Type type;
    private boolean languageCouldNotBeDetermined = false;

    private LearningContent(LearningContentBuilder learningContentBuilder) {
        this.title = learningContentBuilder.title;
        this.rawText = learningContentBuilder.rawText;
        this.type = learningContentBuilder.type;
    }

    /**
     * Gets the title / label of this learning content.
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the raw text of this learning content.
     * @return String
     */
    public String getRawText() {
        return rawText;
    }

    /**
     * Sets the annotated text (e.g. by part of speech characters).
     * @param partOfSpeechAnnotatedText String
     */
    public void setPartOfSpeechAnnotatedText(String partOfSpeechAnnotatedText) {
        this.partOfSpeechAnnotatedText = partOfSpeechAnnotatedText;
    }

    /**
     * Gets the annotated text (e.g. by part of speech characters).
     * @return String
     */
    public String getPartOfSpeechAnnotatedText() {
        return partOfSpeechAnnotatedText;
    }

    /**
     * Sets the Named entity recognition annotated text.
     * @param namedEntityAnnotatedText String
     */
    public void setNamedEntityAnnotatedText(String namedEntityAnnotatedText) {
        this.namedEntityAnnotatedText = namedEntityAnnotatedText;
    }

    /**
     * Gets the named entity recognition annotated text.
     * @return String
     */
    public String getNamedEntityAnnotatedText() {
        return namedEntityAnnotatedText;
    }

    /**
     * Sets the determined / infered language.
     * @param determinedLanguage the determined language
     */
    public void setDeterminedLanguage(Language determinedLanguage) {
        this.determinedLanguage = determinedLanguage;
        languageCouldNotBeDetermined = false;
    }

    /**
     * Gets the determined language.
     * Make sure that an determination algorithm was run before so that an appropriate value is set.
     * @return the determined language
     */
    public Language getDeterminedLanguage() {
        return determinedLanguage;
    }

    /**
     * Deletes and marks the annotated text as deleted.
     */
    public void deleteAnnotatedText() {
        this.setPartOfSpeechAnnotatedText(ANNOTATED_TEXT_DELETED);
        this.setNamedEntityAnnotatedText(ANNOTATED_TEXT_DELETED);
    }

    /**
     * Resets the annotated text.
     */
    public void resetAnnotatedText() {
        this.setPartOfSpeechAnnotatedText("");
        this.setNamedEntityAnnotatedText("");
    }

    /**
     * Whether this learning content has annotated text (was not deleted).
     * @return the annotated text
     */
    public boolean hasAnnotatedText() {
        return !getPartOfSpeechAnnotatedText().equals(ANNOTATED_TEXT_DELETED);
    }

    /**
     * Whether the language of the raw text could not be determined and it was fell back to the course's primary language.
     * @return boolean
     */
    public boolean isLanguageCouldNotBeDetermined() {
        return languageCouldNotBeDetermined;
    }

    /**
     * Gets the type of this learning content.
     * @return Type
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets the fallback language as given in the course.
     * @param course the course which's primary language will be used instead
     */
    public void setFallbackLanguage(final Course course) {
        determinedLanguage = course.getPrimaryLanguage();
        languageCouldNotBeDetermined = true;
    }

    /**
     * Only checks the title in the equals method.
     *
     * So two learning contents are equal if they both have the same title.
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LearningContent that = (LearningContent) o;

        return title.equals(that.title);
    }

    @Override
    public int hashCode() {
        return title.hashCode();
    }

    @Override
    public String toString() {
        return title;
    }

    /**
     * Creates a copy.
     * @param learningContent the learning content to copy from.
     * @return copy of LearningContent
     */
    public static LearningContent copyOf(LearningContent learningContent) {
        return new LearningContentBuilder()
                .withTitle(learningContent.getTitle())
                .withRawText(learningContent.getRawText())
                .withType(learningContent.getType())
                .build();
    }

    public static class LearningContentBuilder {
        private String title;
        private String rawText;
        private Type type;

        /**
         * Assigns a title (label) to this learning unit content.
         * @param title String
         * @return this
         */
        public LearningContentBuilder withTitle(final String title) {
            if (Strings.isNullOrEmpty(title) || 0 == title.trim().length()) {
                throw new NullPointerException("The value for the parameter title must not be null!");
            }

            this.title = title;

            return this;
        }
        /**
         * Assigns the raw text to this learning unit content.
         *
         * Make sure to have replaced control characters.
         * @param rawText String
         * @return this
         * @throws NullPointerException if the argument is null
         */
        public LearningContentBuilder withRawText(final String rawText) {
            if (Strings.isNullOrEmpty(rawText) || 0 == rawText.trim().length()) {
                throw new NullPointerException("The value for the parameter rawText must not be null!");
            }

            this.rawText = rawText;

            return this;
        }

        /**
         * Assigns a type to the learning unit content.
         *
         * If the user uploaded a file from which the raw text was extracted, the type will be FILE, otherwise DIRECT_RAWTEXT.
         * @param type the {@link Type}
         * @return this
         * @throws NullPointerException if the argument is null
         */
        public LearningContentBuilder withType(final Type type) {
            if (null == type) {
                throw new NullPointerException("The value for the parameter type must not be null!");
            }

            this.type = type;

            return this;
        }

        /**
         * Builds the final object.
         * @return CourseMaterial
         */
        public LearningContent build() {
            return new LearningContent(this);
        }

    }
}

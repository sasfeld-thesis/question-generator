package de.saschafeldmann.adesso.master.thesis.elearningimport.model;

import static com.google.common.base.Preconditions.*;
import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.List;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  24.05.2016
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
    private List<String> partOfSpeechAnnotatedText;
    private List<String> namedEntityAnnotatedText;
    private Language determinedLanguage;
    private final Type type;
    private boolean languageCouldNotBeDetermined = false;
    private final Course course;

    private LearningContent(LearningContentBuilder learningContentBuilder) {
        this.title = learningContentBuilder.title;
        this.rawText = learningContentBuilder.rawText;
        this.type = learningContentBuilder.type;
        this.course = learningContentBuilder.course;
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
     * @param partOfSpeechAnnotatedText String list of annotated sentences
     */
    public void setPartOfSpeechAnnotatedText(List<String> partOfSpeechAnnotatedText) {
        this.partOfSpeechAnnotatedText = partOfSpeechAnnotatedText;
    }

    /**
     * Gets the annotated text (e.g. by part of speech characters).
     * @return String list of annotated sentences
     */
    public List<String> getPartOfSpeechAnnotatedText() {
        return partOfSpeechAnnotatedText;
    }

    /**
     * Sets the Named entity recognition annotated text.
     * @param namedEntityAnnotatedText list of sentences
     */
    public void setNamedEntityAnnotatedText(List<String> namedEntityAnnotatedText) {
        this.namedEntityAnnotatedText = namedEntityAnnotatedText;
    }

    /**
     * Gets the named entity recognition annotated text.
     * @return String list of annotated sentences
     */
    public List<String> getNamedEntityAnnotatedText() {
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
     * Otherwise, the course's language will be returned.
     * @return the determined language
     */
    public Language getDeterminedLanguage() {
        if (null == determinedLanguage) {
            return course.getPrimaryLanguage();
        }

        return determinedLanguage;
    }

    /**
     * Deletes and marks the annotated text as deleted.
     */
    public void deleteAnnotatedText() {
        this.setPartOfSpeechAnnotatedText(new ArrayList<String>());
        this.setNamedEntityAnnotatedText(new ArrayList<String>());
    }

    /**
     * Resets the annotated text.
     */
    public void resetAnnotatedText() {
        this.setPartOfSpeechAnnotatedText(new ArrayList<String>());
        this.setNamedEntityAnnotatedText(new ArrayList<String>());
    }

    /**
     * Whether this learning content has annotated text (was not deleted).
     * @return the annotated text
     */
    public boolean hasAnnotatedText() {
        return null != getPartOfSpeechAnnotatedText() && getPartOfSpeechAnnotatedText().size() > 0
                && null != getNamedEntityAnnotatedText() && getNamedEntityAnnotatedText().size() > 0;
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
     * Gets the course that this learning content belongs to.
     * @return the course
     */
    public Course getCourse() {
        return course;
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
     * @param course the course
     * @return copy of LearningContent
     */
    public static LearningContent copyOf(LearningContent learningContent, Course course) {
        return new LearningContentBuilder()
                .withTitle(learningContent.getTitle())
                .withRawText(learningContent.getRawText())
                .withType(learningContent.getType())
                .withCourse(course)
                .build();
    }

    public static class LearningContentBuilder {
        private String title;
        private String rawText;
        private Type type;
        private Course course;

        /**
         * Assigns a title (label) to this learning unit content.
         * @param title String
         * @return this
         */
        public LearningContentBuilder withTitle(final String title) {
            if (Strings.isNullOrEmpty(title)) {
                throw new NullPointerException("The value for the parameter title must not be null!");
            }

            this.title = title;

            return this;
        }
        /**
         * Assigns the raw text to this learning unit content.
         *
         * Make sure to have replaced control characters.
         * @param rawText list of sentences String
         * @return this
         * @throws NullPointerException if the argument is null
         */
        public LearningContentBuilder withRawText(final String rawText) {
            checkNotNull(rawText, "The rawText must not be null");

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
            checkNotNull(type, "The type must not be null");

            this.type = type;

            return this;
        }

        /**
         * Sets the course that the learning content to be built belongs to.
         * @param course the course that this learning content belongs to
         * @return this
         * @throws NullPointerException if the argument is null
         */
        public LearningContentBuilder withCourse(final Course course) {
            checkNotNull(course, "The course must not be null");

            this.course = course;

            return this;
        }

        /**
         * Builds the final object.
         * @return CourseMaterial
         */
        public LearningContent build() {
            checkNotNull(title, "The title must not be null");
            checkNotNull(rawText, "The rawText must not be null");
            checkNotNull(type, "The type must not be null");
            checkNotNull(course, "The course must not be null");

            return new LearningContent(this);
        }

    }
}

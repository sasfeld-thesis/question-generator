package de.saschafeldmann.adesso.master.thesis.elearningexport.moodle.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  10.08.2016
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
 * A question XML element of the Moodle XML format.
 * https://docs.moodle.org/24/en/Moodle_XML_format
 */
public abstract class Question {
    /**
     * A question name XML element.
     */
    public static class QuestionName {
        @XmlElement
        private String text;

        /**
         * Sest the feedback
         * @param text feedback
         */
        public void setText(String text) {
            this.text = text;
        }
    }

    /**
     * A question feedback XML element.
     */
    public static class QuestionText {
        public static final String FORMAT_PLAIN = "plain_text";

        @XmlAttribute
        private String format = FORMAT_PLAIN;

        @XmlElement
        private String text;

        /**
         * Sets the format
         * @param format the format
         */
        public void setFormat(String format) {
            this.format = format;
        }

        /**
         * Sets the question feedback
         * @param text the question feedback
         */
        public void setText(String text) {
            this.text = text;
        }
    }

    public static class Answer {
        public static class Feedback {
            @XmlElement
            private String text;

            /**
             * Sets the feedback.
             * @param text the feedback feedback
             */
            public void setText(String text) {
                this.text = text;
            }
        }

        @XmlAttribute
        private int fraction;

        @XmlElement(name = "text")
        private String text;

        @XmlElement(name = "feedback")
        private Feedback feedback = new Feedback();


        /**
         * Sets the fraction (correctness percentage - 100 means correct answer).
         * @param fraction fraction.
         */
        public void setFraction(int fraction) {
            this.fraction = fraction;
        }

        /**
         * Sets the answer's text.
         * @param text the answer text / label.
         */
        public void setText(String text) {
            this.text = text;
        }

        /**
         * Gets the feedback feedback element.
         * @return XML element
         */
        public Feedback getFeedback() {
            return feedback;
        }
    }

    @XmlAttribute
    public String type;

    @XmlElement(name = "name")
    public QuestionName name = new QuestionName();

    @XmlElement(name = "questiontext")
    public QuestionText questionText = new QuestionText();

    public List<Answer> answer = new ArrayList<>();

    /**
     * Creates a question with given type.
     * @param type the question type.
     */
    public Question(String type) {
        setType(type);
    }

    /**
     * Sets the question type.
     * @param type the question type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Gets the question feedback.
     * @return the question feedback
     */
    public QuestionText getQuestionText() {
        return questionText;
    }

    /**
     * Gets the question name
     * @return the question name
     */
    public QuestionName getName() {
        return name;
    }

    /**
     * Gets the list of answer.
     * @return List of answer
     */
    public List<Answer> getAnswerList() {
        return answer;
    }
}

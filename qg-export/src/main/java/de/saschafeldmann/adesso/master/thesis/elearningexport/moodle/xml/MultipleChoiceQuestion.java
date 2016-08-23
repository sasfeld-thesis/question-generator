package de.saschafeldmann.adesso.master.thesis.elearningexport.moodle.xml;

import javax.xml.bind.annotation.XmlElement;

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
 * A multiple choice question element.
 * https://docs.moodle.org/24/en/Moodle_XML_format
 */
public class MultipleChoiceQuestion extends Question {
    private static final String TYPE = "multichoice";

    /**
     * Single question: true or false
     */
    @XmlElement(name = "single")
    private String singleQuestion;

    /**
     * Whether to shuffle the answer in random order
     * 1 (for true)
     * 0 (false)
     */
    @XmlElement(name = "shuffleanswers")
    private String shuffleAnswers;

    /**
     * Creates a question with given type.
     */
    public MultipleChoiceQuestion() {
        super();

        setType(TYPE);
        singleQuestion = "true";
        shuffleAnswers = "1";
    }

    /**
     * Sets the shuffleAnswers option.
     * @param shuffleAnswers String
     */
    public void setShuffleAnswers(String shuffleAnswers) {
        this.shuffleAnswers = shuffleAnswers;
    }

    /**
     * Gets the shuffleAnswers option.
     * @return String
     */
    public String getShuffleAnswers() {
        return shuffleAnswers;
    }

    /**
     * Sets the single question option.
     * @param singleQuestion String
     */
    public void setSingleQuestion(String singleQuestion) {
        this.singleQuestion = singleQuestion;
    }

    /**
     * Gets the single question option.
     * @return String
     */
    public String getSingleQuestion() {
        return singleQuestion;
    }
}

package de.saschafeldmann.adesso.master.thesis.elearningexport.moodle.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  10.08.2016
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
 * XML: top hierarchy element of the Moodle XML format.
 * https://docs.moodle.org/24/en/Moodle_XML_format
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "quiz")
public class Quiz {

    private List<Question> question = new ArrayList<>();

    /**
     * Gets the question list
     * @return the question list
     */
    public List<Question> getQuestionList() {
        return question;
    }
}

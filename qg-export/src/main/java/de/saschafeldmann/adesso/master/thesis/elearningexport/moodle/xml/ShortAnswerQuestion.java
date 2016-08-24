package de.saschafeldmann.adesso.master.thesis.elearningexport.moodle.xml;

import org.apache.poi.ss.formula.functions.T;

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
 * Short answer question XML element.
 */
public class ShortAnswerQuestion extends Question {
    private static final String TYPE = "shortanswer";

    /**
     * Creates a question with given type.
     */
    public ShortAnswerQuestion() {
        super();
        setType(TYPE);
    }
}

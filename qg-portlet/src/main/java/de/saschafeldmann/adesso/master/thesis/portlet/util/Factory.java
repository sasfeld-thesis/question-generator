package de.saschafeldmann.adesso.master.thesis.portlet.util;

import de.saschafeldmann.adesso.master.thesis.portlet.model.QuestionGenerationSession;

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
 * QG-Portlet factory
 */
public class Factory {

    /**
     * Creates a new QG-session.
     * @return the session
     */
    public static QuestionGenerationSession newQuestionGenerationSession() {
        return new QuestionGenerationSession();
    }
}

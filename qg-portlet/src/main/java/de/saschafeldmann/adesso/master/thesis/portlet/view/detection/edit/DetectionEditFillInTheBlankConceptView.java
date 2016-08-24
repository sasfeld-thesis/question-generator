package de.saschafeldmann.adesso.master.thesis.portlet.view.detection.edit;

import de.saschafeldmann.adesso.master.thesis.detection.model.FillInTheBlankTextConcept;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  28.06.2016
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
 * Special view that allows editing {@link FillInTheBlankTextConcept}.
 */
public interface DetectionEditFillInTheBlankConceptView extends DetectionEditConceptView<FillInTheBlankTextConcept> {
    /**
     * Gets the fill text sentence input done by the user.
     * @return String
     */
    public String getFillTextSentenceInput();

    /**
     * Gets the correct answer input done by the user.
     * @return String
     */
    public String getCorrectAnswerInput();
}

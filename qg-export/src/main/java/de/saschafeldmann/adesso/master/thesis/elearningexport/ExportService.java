package de.saschafeldmann.adesso.master.thesis.elearningexport;

import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.generation.model.TestQuestion;

import java.io.File;
import java.util.List;
import java.util.Map;

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
 * Interface for all export services in this application.
 */
public interface ExportService {

    /**
     * Exports the given question list to a file.
     * @param generatedQuestionsMap map of learning content and the generated questions
     * @return the file
     */
    File exportGeneratedQuestionsToFile(Map<LearningContent, List<TestQuestion>> generatedQuestionsMap);
}

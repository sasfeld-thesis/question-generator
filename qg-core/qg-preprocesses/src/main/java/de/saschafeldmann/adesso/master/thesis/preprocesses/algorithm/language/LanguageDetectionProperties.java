package de.saschafeldmann.adesso.master.thesis.preprocesses.algorithm.language;

import java.util.List;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  14.06.2016
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
 * Language detection properties service interface.
 */
public interface LanguageDetectionProperties {
    /**
     * Gets all common words for German from the underlying system.
     * @return List of String
     */
    List<String> getGermanCommonWords();

    /**
     * Gets all common words for English from the underlying system.
     * @return List of String
     */
    List<String> getEnglishCommonWords();

    /**
     * Gets the coverage delta in percent that must be exceeded to determine a {@link de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent}
     * to have a certain {@link de.saschafeldmann.adesso.master.thesis.elearningimport.model.Language}.
     * @return int the delta
     */
    int getCoverageDeltaInPercent();
}

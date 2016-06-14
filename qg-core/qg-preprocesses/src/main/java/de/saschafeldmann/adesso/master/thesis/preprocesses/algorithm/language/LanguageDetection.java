package de.saschafeldmann.adesso.master.thesis.preprocesses.algorithm.language;

import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.preprocesses.algorithm.PreprocessingAlgorithm;

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
 * This algorithm detects languages defined in the enumeration {@link de.saschafeldmann.adesso.master.thesis.elearningimport.model.Language}
 * by making use of statistical methods.<br />
 * Therefore, it calculates the coverage of a language's most common words within the given {@link de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent}
 * raw text. If the coverages is larger than a defined delta value for one of the languages, the language was determined successfully and is
 * set in the {@link de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent} model.<br />
 * If the language couldn't be determined, an {@link UndeterminableLanguageException} will be thrown.
 */
public class LanguageDetection implements PreprocessingAlgorithm {
    /**
     *
     * Calculates the coverage of a language's most common words within the given {@link de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent}
     * raw text. If the coverages is larger than a defined delta value for one of the languages, the language was determined successfully and is
     * set in the {@link de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent} model.<br />
     * @throws UndeterminableLanguageException if the language couldn't be determined.
     * @return the learning content model with a set language
     */
    @Override
    public LearningContent execute(final LearningContent learningContent) throws UndeterminableLanguageException {
        return learningContent;
    }
}

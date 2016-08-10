package de.saschafeldmann.adesso.master.thesis.preprocesses.algorithm.language;

import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Language;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.preprocesses.algorithm.PreprocessingAlgorithm;
import de.saschafeldmann.adesso.master.thesis.preprocesses.algorithm.model.PreprocessingOptions;
import de.saschafeldmann.adesso.master.thesis.util.linguistic.SentenceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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
 * This algorithm detects languages defined in the enumeration {@link de.saschafeldmann.adesso.master.thesis.elearningimport.model.Language}
 * by making use of statistical methods.<br />
 * Therefore, it calculates the coverage of a language's most common words within the given {@link de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent}
 * raw text. If the coverages is larger than a defined delta value for one of the languages, the language was determined successfully and is
 * set in the {@link de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent} model.<br />
 * If the language couldn't be determined, an {@link UndeterminableLanguageException} will be thrown.
 */
@Component
@Scope("singleton")
public class LanguageDetection implements PreprocessingAlgorithm {
    private final LanguageDetectionProperties languageDetectionProperties;

    @Autowired
    public LanguageDetection(final LanguageDetectionProperties languageDetectionProperties) {
        this.languageDetectionProperties = languageDetectionProperties;
    }

    /**
     *
     * Calculates the coverage of a language's most common words within the given {@link de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent}
     * raw text. If the coverages is larger than a defined delta value for one of the languages, the language was determined successfully and is
     * set in the {@link de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent} model.<br />
     * @throws UndeterminableLanguageException if the language couldn't be determined.
     * @return the learning content model with a set language
     */
    @Override
    public LearningContent execute(final LearningContent learningContent, final PreprocessingOptions preprocessingOptions) throws UndeterminableLanguageException {
        if (isGerman(learningContent)) {
            learningContent.setDeterminedLanguage(Language.GERMAN);
        } else if (isEnglish(learningContent)) {
            learningContent.setDeterminedLanguage(Language.ENGLISH);
        } else {
            throw new UndeterminableLanguageException("The language for the learning content " + learningContent.getTitle() + " could not be determined.");
        }

        return learningContent;
    }

    private boolean isGerman(LearningContent learningContent) {
        return checkIfDeltaIsReached(learningContent, languageDetectionProperties.getGermanCommonWords());
    }

    private boolean isEnglish(LearningContent learningContent) {
        return checkIfDeltaIsReached(learningContent, languageDetectionProperties.getEnglishCommonWords());
    }

    private boolean checkIfDeltaIsReached(LearningContent learningContent, List<String> wordCorpus) {
        String[] words = getWordsInRawText(learningContent);

        double numberOfWordsContainedInCorpus = 0.0;
        for (final String word: words) {
            if (wordCorpus.contains(word)) {
                numberOfWordsContainedInCorpus = numberOfWordsContainedInCorpus + 1.0;
            }
        }

        double numberOfWords = (double) words.length;
        double coverage = numberOfWordsContainedInCorpus / numberOfWords;
        long coverageInPercent = Math.round(coverage * 100.0);

        return coverageInPercent >= languageDetectionProperties.getCoverageDeltaInPercent();
    }

    private String[] getWordsInRawText(LearningContent learningContent) {
        return SentenceUtil.getWordsInRawText(learningContent.getRawText());
    }
}

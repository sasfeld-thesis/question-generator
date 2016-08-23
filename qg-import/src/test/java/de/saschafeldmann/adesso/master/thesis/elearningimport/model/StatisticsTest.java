package de.saschafeldmann.adesso.master.thesis.elearningimport.model;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  23.08.2016
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
 * UnitTest of {@link Statistics}
 */
public class StatisticsTest {

    @Test
    public void testResetNlpStatistics() {
        // given
        Statistics statistics = newStatisticsModel();

        // when NLP is reset
        statistics.resetNlpnStatistics();

        // then make sure fields are zero
        assertEquals(0, statistics.getLanguageDetectionRuntime());
        assertEquals(0, statistics.getNaturalLanguageProcessingRuntime());
        assertEquals(0, statistics.getNumberOfWords());
        assertEquals(0, statistics.getNumberOfSentences());
    }

    @Test
    public void testResetConceptDetectionStatistics() {
        // given
        Statistics statistics = newStatisticsModel();

        // when NLP is reset
        statistics.resetConceptDetectionStatistics();

        // then make sure fields are zero
        assertEquals(0, statistics.getNumberOfDetectedConcepts());
        assertEquals(0, statistics.getCardinalitySentenceDetectionRuntime());
        assertEquals(0, statistics.getFillInTheBlankTextConceptDetectionRuntime());
    }

    @Test
    public void testResetQuestionGenerationStatistics() {
        // given
        Statistics statistics = newStatisticsModel();

        // when NLP is reset
        statistics.resetQuestionGenerationtatistics();

        // then make sure fields are zero
        assertEquals(0, statistics.getQuestionGenerationRuntime());;
    }

    private Statistics newStatisticsModel() {
        Statistics statistics = new Statistics();

        statistics.setNumberOfSentences(10);
        statistics.setQuestionGenerationRuntime(2000);
        statistics.setNumberOfDetectedConcepts(5);
        statistics.setFillInTheBlankTextConceptDetectionRuntime(15000);
        statistics.setCardinalitySentenceDetectionRuntime(30000);
        statistics.setLanguageDetectionRuntime(1000);
        statistics.setNumberOfWords(100);
        statistics.setNaturalLanguageProcessingRuntime(30000);

        return statistics;
    }
}

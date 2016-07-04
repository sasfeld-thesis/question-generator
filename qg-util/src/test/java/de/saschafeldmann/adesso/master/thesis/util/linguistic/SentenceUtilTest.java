package de.saschafeldmann.adesso.master.thesis.util.linguistic;

import org.junit.Test;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  02.07.2016
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
 * Test of {@link SentenceUtil}.
 */
public class SentenceUtilTest {
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    @Test
    public void testBuildStringForListOfSentencesGeneratesStringWithNewLineForEachSentence() {
        // given a string list of sentences
        String[] sentences = {"This is the first sentence.", "This is the second sentence.", "This is the third sentence."};
        List<String> sentencesList = new ArrayList<>();
        Collections.addAll(sentencesList, sentences);

        // when utility method is called
        String resultingString = SentenceUtil.buildStringForListOfSentences(sentencesList);

        // then the expected String with sentences separated in lines should have been generated
        assertEquals("This is the first sentence." + LINE_SEPARATOR +
                "This is the second sentence." + LINE_SEPARATOR +
                "This is the third sentence." + LINE_SEPARATOR, resultingString);
    }

    @Test
    public void testBuildListOfSentencesForStringWithNewLineForEachSentence() {
        // given a string with new lines for each sentence
        String sentence = "This is the first sentence." + LINE_SEPARATOR +
                "This is the second sentence." + LINE_SEPARATOR +
                "This is the third sentence." + LINE_SEPARATOR;

        // when utility method is called
        List<String> resultingList = SentenceUtil.buildListOfSentencesForString(sentence);

        // then the expected list with elements for each sentenc should have been generated
        String[] sentences = {"This is the first sentence.", "This is the second sentence.", "This is the third sentence."};
        List<String> sentencesList = new ArrayList<>();
        Collections.addAll(sentencesList, sentences);
        assertEquals(sentencesList, resultingList);
    }
}

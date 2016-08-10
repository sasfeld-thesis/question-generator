package de.saschafeldmann.adesso.master.thesis.util.linguistic;

import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  29.06.2016
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
 * Utility to split sentences.
 */
public class SentenceUtil {
    private static final String REGEX_TO_SPLIT_WORDS = "[\\s,.\\-;:_!?]+";

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    /**
     * Builds a string for the given list of sentences.
     * @param sentences the list of sentences
     * @return the complete string
     */
    public static String buildStringForListOfSentences(final List<String> sentences) {
        StringBuilder stringBuilder = new StringBuilder();

        for(String sentence: sentences) {
            stringBuilder.append(sentence);
            stringBuilder.append(LINE_SEPARATOR);
        }

        return stringBuilder.toString();
    }

    /**
     * uilds a list of sentences for the given complete string where each sentence is given in its own line.
     * @param completeString the complete String
     * @return List of sentences
     */
    public static List<String> buildListOfSentencesForString(final String completeString) {
        List<String> sentences = new ArrayList<>();

        Collections.addAll(sentences, completeString.split(LINE_SEPARATOR));

        return sentences;
    }

    /**
     * Calculates the number of tokens in the given sentence.
     * @param sentence the sentence
     * @return int the number of tokens
     */
    public static int calculatesNumberOfTokens(final String sentence) {
        String[] wordsInRawText = getWordsInRawText(sentence);
        return wordsInRawText.length;
    }

    /**
     * Splits the given String and returns all words / tokens.
     * @param sentence the sentence to be split
     * @return the array of tokens (strings)
     */
    public static String[] getWordsInRawText(final String sentence) {
        return sentence.split(REGEX_TO_SPLIT_WORDS);
    }
}

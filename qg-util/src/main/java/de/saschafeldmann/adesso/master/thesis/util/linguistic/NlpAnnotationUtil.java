package de.saschafeldmann.adesso.master.thesis.util.linguistic;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  04.07.2016
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
 * Utility to annotate NLP - processed texts, e.g. by found part-of-speech tags or named entities. Example: &lt;LOCATION&gt;Berlin&lt;/LOCATION&gt;
 */
public class NlpAnnotationUtil {

    /**
     * Annotates the given token by the given annotation.
     *
     * @param tokenText     the orginal token, e.g. "Berlin".
     * @param nlpAnnotation the NLP part-of-speech-tag or named entity annotation for this token, e.g. "LOCATION"
     * @return the annotated token, e.g. &lt;LOCATION&gt;Berlin&lt;/LOCATION&gt;
     */
    public static String annotateToken(final String tokenText, final String nlpAnnotation) {
        return "<" +
                nlpAnnotation +
                ">" +
                tokenText +
                "</" +
                nlpAnnotation +
                ">";
    }

    /**
     * Removes all annotated tokens and therefore returns a new string.
     *
     * @param annotatedString the string with annotations which should be removed.
     * @return the new string with all removed annotations
     */
    public static String removeAllTokenAnnotations(final String annotatedString) {
        String whitespace = " ";
        // remove the annotation tag itself
        String replaced = annotatedString.replaceAll("<.*?>", whitespace);
        // replace double whitespaces by one whitespace
        replaced = replaced.replaceAll(whitespace + whitespace, whitespace);
        // remove whitespace before sentence delimiter
        replaced = replaced.replaceAll(whitespace + "([.?!;:]+)", "$1");

        return replaced.trim();
    }
}
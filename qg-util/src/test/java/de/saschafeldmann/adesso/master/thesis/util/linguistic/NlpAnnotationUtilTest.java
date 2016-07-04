package de.saschafeldmann.adesso.master.thesis.util.linguistic;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  04.07.2016
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
 * Unittest of the {@link NlpAnnotationUtil} class.
 */
public class NlpAnnotationUtilTest {

    @Test
    public void testAnnotateTokenAnnotatesCorrectly() {
        // given a token to be annotated
        String givenToken = "Berlin";
        // and an annotation
        String givenAnnotation = "LOCATION";

        // when the util method is called
        String resultingAnnotation = NlpAnnotationUtil.annotateToken(givenToken, givenAnnotation);

        // then the token should have been annotated as expected
        assertEquals("<LOCATION>Berlin</LOCATION>", resultingAnnotation);
    }

    @Test
    public void testRemoveAllTokenAnnotationsRemovesCorrectly() {
        // given a sentence to be "unannotated"
        String givenSentence = "<DT>The</DT><NN>capital</NN><IN>of</IN><NNP>Germany</NNP><VBZ>is</VBZ><NNP>Berlin</NNP><.>.</.>";

        // when the method is called
        String unnannotatedSentence = NlpAnnotationUtil.removeAllTokenAnnotations(givenSentence);

        // then the sentence should be the original one
        assertEquals("The capital of Germany is Berlin.", unnannotatedSentence);
    }
}

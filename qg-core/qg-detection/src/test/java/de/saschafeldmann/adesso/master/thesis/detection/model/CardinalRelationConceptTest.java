package de.saschafeldmann.adesso.master.thesis.detection.model;

import static org.junit.Assert.*;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Course;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Language;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

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
 * Test of {@link CardinalRelationConcept}
 */
public class CardinalRelationConceptTest {

    @Test
    public void testObjectIdentities() {
        // given two different instances with the same original sentence
        final CardinalRelationConcept cardinalRelationConcept1 = newCardinalRelationConceptWithOriginalSentence("This is a test sentence");
        final CardinalRelationConcept cardinalRelationConcept2 = newCardinalRelationConceptWithOriginalSentence("This is a test sentence");
        final Set<CardinalRelationConcept> list = new HashSet<>();

        // when they are added to a set
        list.add(cardinalRelationConcept1);
        list.add(cardinalRelationConcept2);

        // then the list should only contain one, since both elements were identical
        assertEquals(1, list.size());
    }

    private CardinalRelationConcept newCardinalRelationConceptWithOriginalSentence(String originalSentence) {
        CardinalRelationConcept build = new CardinalRelationConcept.CardinalRelationConceptBuilder()
                .withOriginalSentence(originalSentence)
                .withLearningContent(newLearningContent())
                .withComposition("Composition")
                .withCompositionCardinality(1)
                .withComposite("Composite")
                .withCompositionCardinality(2)
                .build();

        build.setComposition("Composition");
        build.setCompositionCardinality(1);
        build.setComposite("Composite");
        build.setCompositeCardinality(2);

        return build;
    }

    private LearningContent newLearningContent() {
        return  new LearningContent.LearningContentBuilder()
                .withCourse(newCourse())
                .withType(LearningContent.Type.DIRECT_RAWTEXT)
                .withRawText("This is a test sentence")
                .withTitle("UnitTest")
                .build();
    }

    private Course newCourse() {
        return new Course.CourseBuilder()
                .withLanguage(Language.ENGLISH)
                .withTitle("Test course")
                .withViewUrl("http://test.co")
                .build();
    }
}

package de.saschafeldmann.adesso.master.thesis.detection.model;

import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Course;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Language;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

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
public class FillInTheBlankConceptTest {

    @Test
    public void testObjectIdentities() {
        // given two different instances with the same original sentence
        final FillInTheBlankTextConcept concept1 = newFillInTheBlankConcept("This is a test sentence");
        final FillInTheBlankTextConcept concept2 = newFillInTheBlankConcept("This is a test sentence");
        final Set<FillInTheBlankTextConcept> list = new HashSet<>();

        // when they are added to a set
        list.add(concept1);
        list.add(concept2);

        // then the list should only contain one, since both elements were identical
        assertEquals(1, list.size());
    }

    private FillInTheBlankTextConcept newFillInTheBlankConcept(String originalSentenc) {
        FillInTheBlankTextConcept fillInTheBlankTextConcept = new FillInTheBlankTextConcept.FillTextConceptBuilder()
                .withLearningContent(newLearningContent())
                .withOriginalSentence(originalSentenc)
                .withCorrectAnswer("Something")
                .withFillSentence("Some fill sentence ___")
                .build();

        fillInTheBlankTextConcept.setCorrectAnswer("Something");
        fillInTheBlankTextConcept.setFillSentence("Some fill sentence ___");

        return fillInTheBlankTextConcept;
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

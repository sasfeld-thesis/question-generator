package de.saschafeldmann.adesso.master.thesis.generation;

import de.saschafeldmann.adesso.master.thesis.detection.model.CardinalRelationConcept;
import de.saschafeldmann.adesso.master.thesis.detection.model.FillInTheBlankTextConcept;
import de.saschafeldmann.adesso.master.thesis.detection.model.api.Concept;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Course;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Language;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.generation.model.TestQuestion;
import org.junit.Test;

import static org.junit.Assert.*;
/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  11.07.2016
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
 * Test of {@link de.saschafeldmann.adesso.master.thesis.generation.LinguisticRealiserImpl}
 */
public class LinguisticRealiserImplTest {

    @Test
    public void testGenerateQuestionThrowsExceptionForUnsupportedConcept() {
        // given an unsupported concept
        Concept unsupportedConcept = newUnsupportedConcept();
        LinguisticRealiser linguisticRealiser = newLinguisticRealiser();

        // when generate is called, then expect exception
        try {
            TestQuestion qeneratedQuestion = linguisticRealiser.generateQuestion(unsupportedConcept);
            fail("A QuestionGenerationException should have been thrown.");
        } catch (QuestionGenerationException e) {
            // this is the exception we expect
        }
    }

    @Test
    public void testGenerateQuestionForFillInTheBlankConceptTestQuestionSpec() {
        // given
        Concept fillInTheBlankTextConcept = newFillInTheBlankTextConcept();
        LinguisticRealiser linguisticRealiser = newLinguisticRealiser();

        // when generate is called
        TestQuestion generatedTestQuestion = linguisticRealiser.generateQuestion(fillInTheBlankTextConcept);

        // then expect correct fields
        assertEquals(fillInTheBlankTextConcept, generatedTestQuestion.getSourceConcept());
        assertEquals("Berlin", generatedTestQuestion.getCorrectAnswer());
        assertTrue("The generated question should not be empty", generatedTestQuestion.getQuestion().trim().length() > 0);
    }

    private Concept newFillInTheBlankTextConcept() {
        return new FillInTheBlankTextConcept.FillTextConceptBuilder()
                .withOriginalSentence("The capital of Germany is Berlin.")
                .withFillSentence("The capital of Germany is ___.")
                .withCorrectAnswer("Berlin")
                .withLearningContent(newLearningContentForFillInTheBlankConcept())
                .build();
    }

    private LearningContent newLearningContentForFillInTheBlankConcept() {
        LearningContent learningContent = new LearningContent.LearningContentBuilder()
                .withType(LearningContent.Type.DIRECT_RAWTEXT)
                .withRawText("Some geography learning content. The capital of Germany is Berlin. Germany is a country in Europe")
                .withTitle("Unittest learning content")
                .withCourse(newCourse())
                .build();
        learningContent.setDeterminedLanguage(Language.ENGLISH);
        return learningContent;
    }

    private Course newCourse() {
        return new Course.CourseBuilder()
                .withLanguage(Language.GERMAN)
                .withTitle("Unit test course")
                .withViewUrl("http://unittest.de")
                .build();
    }

    @Test
    public void testGenerateQuestionForCardinalRelationConceptTestQuestionSpec() {
        // given
        Concept cardinalRelationConcept = newCardinalRelationConcept();
        LinguisticRealiser linguisticRealiser = newLinguisticRealiser();

        // when generate is called
        TestQuestion generatedTestQuestion = linguisticRealiser.generateQuestion(cardinalRelationConcept);

        // then expect correct fields
        assertEquals(cardinalRelationConcept, generatedTestQuestion.getSourceConcept());
        assertEquals("16", generatedTestQuestion.getCorrectAnswer());
        assertTrue("The generated question should not be empty", generatedTestQuestion.getQuestion().trim().length() > 0);
    }

    private Concept newCardinalRelationConcept() {
        return new CardinalRelationConcept.CardinalRelationConceptBuilder()
                .withOriginalSentence("Germany has 16 federal states.")
                .withComposite("Germany")
                .withCompositeCardinality(1)
                .withComposition("federal states")
                .withCompositionCardinality(16)
                .withLearningContent(newLearningContentWithCardinalRelation())
                .build();
    }

    private LearningContent newLearningContentWithCardinalRelation() {
        LearningContent learningContent = new LearningContent.LearningContentBuilder()
                .withType(LearningContent.Type.DIRECT_RAWTEXT)
                .withRawText("Germany has 16 federal states.")
                .withTitle("Unittest learning content")
                .withCourse(newCourse())
                .build();
        learningContent.setDeterminedLanguage(Language.ENGLISH);
        return learningContent;
    }

    private LinguisticRealiser newLinguisticRealiser() {
        return new LinguisticRealiserImpl();
    }

    private Concept newUnsupportedConcept() {
        return new Concept() {
            @Override
            public LearningContent getLearningContent() {
                return null;
            }

            @Override
            public String getOriginalSentence() {
                return null;
            }
        };
    }
}

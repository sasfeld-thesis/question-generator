package de.saschafeldmann.adesso.master.thesis.generation.specifications;

import static org.junit.Assert.*;
import de.saschafeldmann.adesso.master.thesis.detection.model.FillInTheBlankTextConcept;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Language;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.generation.Factory;
import org.junit.Test;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  11.07.2016
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
 * Test of {@link FillInTheBlankConceptTestQuestionSpec}
 */
public class FillInTheBlankConceptTestQuestionSpecTest {

    private static final String QUESTION_MARK = "?";

    @Test
    public void testBuildSpecContainsAQuestionMark() {
        // given a spec on a filltext concept
        FillInTheBlankConceptTestQuestionSpec spec = newFillInTheBlankSpec();

        // when buildSpec() is called
        String builtSpec = spec.buildSpec();

        // then the generated question should not be empty and contain a question mark (?)
        assertTrue("the generated spec must contain a ? question mark", builtSpec.contains(QUESTION_MARK));
    }

    private FillInTheBlankConceptTestQuestionSpec newFillInTheBlankSpec() {
        FillInTheBlankTextConcept concept = new FillInTheBlankTextConcept.FillTextConceptBuilder()
                .withOriginalSentence("The capital of Germany is Berlin")
                .withFillSentence("The capital of Germany is ___.")
                .withCorrectAnswer("Berlin")
                .withLearningContent(newLearningContent())
                .build();

        return Factory.newFillTextConceptTestQuestionSpec(concept);
    }

    private LearningContent newLearningContent() {
        LearningContent learningContent = new LearningContent.LearningContentBuilder()
                .withType(LearningContent.Type.DIRECT_RAWTEXT)
                .withRawText("Some geography learning content. The capital of Germany is Berlin. Germany is a country in Europe")
                .withTitle("Unittest learning content")
                .build();
        learningContent.setDeterminedLanguage(Language.ENGLISH);
        return learningContent;
    }
}

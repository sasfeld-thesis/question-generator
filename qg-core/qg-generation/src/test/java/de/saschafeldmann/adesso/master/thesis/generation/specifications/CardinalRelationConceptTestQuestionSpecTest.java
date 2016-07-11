package de.saschafeldmann.adesso.master.thesis.generation.specifications;

import de.saschafeldmann.adesso.master.thesis.detection.model.CardinalRelationConcept;
import de.saschafeldmann.adesso.master.thesis.detection.model.FillInTheBlankTextConcept;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Language;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.generation.Factory;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

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
 * Test of {@link CardinalRelationConceptTestQuestionSpec}
 */
public class CardinalRelationConceptTestQuestionSpecTest {

    @Test
    public void testBuildSpecGeneratesQuestion() {
        // given a spec on a filltext concept
        CardinalRelationConceptTestQuestionSpec spec = newCardinalRelationTestQuestionSpec();

        // when buildSpec() is called
        String builtSpec = spec.buildSpec();

        // then the generated question should not be empty
        assertTrue("the generated spec must contain characters", builtSpec.trim().length() > 0);
    }

    private CardinalRelationConceptTestQuestionSpec newCardinalRelationTestQuestionSpec() {
        CardinalRelationConcept concept = new CardinalRelationConcept.CardinalRelationConceptBuilder()
                .withOriginalSentence("Germany has 16 federal states.")
                .withComposite("Germany")
                .withCompositeCardinality(1)
                .withComposition("federal states")
                .withCompositionCardinality(16)
                .withLearningContent(newLearningContent())
                .build();

        return Factory.newCardinalRelationConceptTestQuestionSpec(concept);
    }

    private LearningContent newLearningContent() {
        LearningContent learningContent = new LearningContent.LearningContentBuilder()
                .withType(LearningContent.Type.DIRECT_RAWTEXT)
                .withRawText("Germany has 16 federal states.")
                .withTitle("Unittest learning content")
                .build();
        learningContent.setDeterminedLanguage(Language.ENGLISH);
        return learningContent;
    }
}

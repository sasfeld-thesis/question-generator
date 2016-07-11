package de.saschafeldmann.adesso.master.thesis.generation;

import de.saschafeldmann.adesso.master.thesis.detection.model.FillInTheBlankTextConcept;
import de.saschafeldmann.adesso.master.thesis.detection.model.api.Concept;
import de.saschafeldmann.adesso.master.thesis.generation.model.TestQuestion;
import de.saschafeldmann.adesso.master.thesis.generation.specifications.FillInTheBlankConceptTestQuestionSpec;

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
 * Implementation of a {@link LinguisticRealiser}
 */
public class LinguisticRealiserImpl implements LinguisticRealiser {

    @Override
    public TestQuestion generateQuestion(Concept concept) {
        TestQuestion testQuestion = null;

        if (concept instanceof FillInTheBlankTextConcept) {
            testQuestion = buildTestQuestionForFillTextConcept((FillInTheBlankTextConcept) concept);
        } else {
            throw new QuestionGenerationException("Concept of type " + concept.getClass() + " is not supported.");
        }

        return testQuestion;
    }

    private TestQuestion buildTestQuestionForFillTextConcept(FillInTheBlankTextConcept concept) {
        TestQuestion testQuestion = newTestQuestion(concept);
        // delegates to the question generation specification for this concept which specifies the question
        testQuestion.setQuestion(new FillInTheBlankConceptTestQuestionSpec(concept).buildSpec());
        testQuestion.setCorrectAnswer(concept.getCorrectAnswer());
        return testQuestion;
    }

    private TestQuestion newTestQuestion(Concept concept) {
        return new TestQuestion(concept.getOriginalSentence(), concept);
    }
}

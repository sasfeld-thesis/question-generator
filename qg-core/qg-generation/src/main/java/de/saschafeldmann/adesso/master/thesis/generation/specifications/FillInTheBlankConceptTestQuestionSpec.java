package de.saschafeldmann.adesso.master.thesis.generation.specifications;

import de.saschafeldmann.adesso.master.thesis.detection.model.FillInTheBlankTextConcept;
import de.saschafeldmann.adesso.master.thesis.generation.Factory;
import de.saschafeldmann.adesso.master.thesis.generation.util.GenerationProperties;

import java.util.List;
import java.util.Random;

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
 * Implementation of a {@link TestQuestionSpecification} for {@link FillInTheBlankTextConcept} detected on
 * a {@link de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent}.
 */
public class FillInTheBlankConceptTestQuestionSpec implements TestQuestionSpecification<FillInTheBlankTextConcept> {
    private static final String PROPERTIES_KEY_PREFIX = "de.saschafeldmann.adesso.master.thesis.generation.fillintheblank.questions.";
    private final FillInTheBlankTextConcept concept;
    private final GenerationProperties generationProperties = Factory.newGenerationProperties();

    /**
     * Creates the specification based upon the given concept.
     * @param concept the concept detected within qg-detection module
     */
    public FillInTheBlankConceptTestQuestionSpec(final FillInTheBlankTextConcept concept) {
        this.concept = concept;
    }

    @Override
    public FillInTheBlankTextConcept getUnderlyingConcept() {
        return concept;
    }

    @Override
    public String buildSpec() {
        // build question by prefixing a random defined question from the generation properties followed by the fill text itself
        return chooseRandomFillTextQuestionIntroductionFromProperties() + " " + getUnderlyingConcept().getFillSentence();
    }

    private String chooseRandomFillTextQuestionIntroductionFromProperties() {
        List<String> possibleQuestions = generationProperties.fetchMultipleValues(PROPERTIES_KEY_PREFIX
                + concept.getLearningContent().getDeterminedLanguage().toString().toLowerCase());

        Random random = new Random();
        // generates random number between 0 (inclusive) and max size of the list (exclusive)
        return possibleQuestions.get(random.nextInt(possibleQuestions.size()));
    }
}

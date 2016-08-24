package de.saschafeldmann.adesso.master.thesis.generation.specifications;

import de.saschafeldmann.adesso.master.thesis.detection.model.CardinalRelationConcept;
import de.saschafeldmann.adesso.master.thesis.detection.model.api.Concept;
import de.saschafeldmann.adesso.master.thesis.generation.Factory;
import de.saschafeldmann.adesso.master.thesis.generation.util.GenerationProperties;

import java.util.List;
import java.util.Random;

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
 * Implementation of a {@link TestQuestionSpecification} based upon a {@link de.saschafeldmann.adesso.master.thesis.detection.model.CardinalRelationConcept}
 * detected upon a {@link de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent}.
 */
public class CardinalRelationConceptTestQuestionSpec implements TestQuestionSpecification<CardinalRelationConcept> {
    private final CardinalRelationConcept concept;
    private final GenerationProperties generationProperties = Factory.newGenerationProperties();

    /**
     * Creates the specification to create the test question based on the {@link de.saschafeldmann.adesso.master.thesis.detection.model.CardinalRelationConcept}.
     * @param concept the based concept detected within qg-detection module
     */
    public CardinalRelationConceptTestQuestionSpec(final CardinalRelationConcept concept) {
        this.concept = concept;
    }

    @Override
    public CardinalRelationConcept getUnderlyingConcept() {
        return concept;
    }

    @Override
    public String buildSpec() {
        return chooseRandomCardinalRelationQuestionAndFillParameters();
    }

    private String chooseRandomCardinalRelationQuestionAndFillParameters() {
        List<String> cardinalQuestionTemplates = generationProperties.fetchMultipleValues("de.saschafeldmann.adesso.master.thesis.generation.cardinalrelations.questions."
            + concept.getLearningContent().getDeterminedLanguage().toString().toLowerCase());

        Random random = new Random();
        String randomQuestionTemplate = cardinalQuestionTemplates.get(random.nextInt(cardinalQuestionTemplates.size()));

        return fillQuestionTemplate(randomQuestionTemplate);
    }

    /**
     * Fills the question template by the composition and composite within the concept.
     * @param randomQuestionTemplate the template
     * @return the question
     */
    private String fillQuestionTemplate(final String randomQuestionTemplate) {
        return String.format(randomQuestionTemplate, getUnderlyingConcept().getComposition(), getUnderlyingConcept().getComposite());
    }
}

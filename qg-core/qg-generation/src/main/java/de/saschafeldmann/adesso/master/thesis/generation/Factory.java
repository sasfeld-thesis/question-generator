package de.saschafeldmann.adesso.master.thesis.generation;

import de.saschafeldmann.adesso.master.thesis.detection.model.CardinalRelationConcept;
import de.saschafeldmann.adesso.master.thesis.detection.model.FillInTheBlankTextConcept;
import de.saschafeldmann.adesso.master.thesis.generation.specifications.CardinalRelationConceptTestQuestionSpec;
import de.saschafeldmann.adesso.master.thesis.generation.specifications.FillInTheBlankConceptTestQuestionSpec;
import de.saschafeldmann.adesso.master.thesis.generation.util.GenerationProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
 * Class with static factory methods.
 */
public class Factory {
    private static final Logger LOGGER = LoggerFactory.getLogger(Factory.class);

    /**
     * Creates a new {@link FillInTheBlankConceptTestQuestionSpec}
     * @param fillInTheBlankTextConcept the underlying concept
     * @return the {@link FillInTheBlankConceptTestQuestionSpec}
     */
    public static FillInTheBlankConceptTestQuestionSpec newFillTextConceptTestQuestionSpec(final FillInTheBlankTextConcept fillInTheBlankTextConcept) {
        return new FillInTheBlankConceptTestQuestionSpec(fillInTheBlankTextConcept);
    }

    /**
     * Creates a new {@link CardinalRelationConceptTestQuestionSpec} .
     * @param cardinalRelationConcept the underlinyg concept
     * @return the {@link CardinalRelationConceptTestQuestionSpec}
     */
    public static CardinalRelationConceptTestQuestionSpec newCardinalRelationConceptTestQuestionSpec(final CardinalRelationConcept cardinalRelationConcept) {
        return new CardinalRelationConceptTestQuestionSpec(cardinalRelationConcept);
    }

    /**
     * Creates a new properties instance.
     * @return the properties instance.
     */
    public static GenerationProperties newGenerationProperties() {
        try {
            return new GenerationProperties();
        } catch (Exception e) {
            LOGGER.error("newGenerationProperties(): could not initialize GenerationProperties due to exception {}", e);
            return null;
        }
    }
}

package de.saschafeldmann.adesso.master.thesis.detection.model;

import de.saschafeldmann.adesso.master.thesis.detection.model.api.AbstractConcept;
import de.saschafeldmann.adesso.master.thesis.detection.model.api.Concept;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  27.06.2016
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
 * A cardinal concept are numeric relations that were identified in sentences.<br>
 */
public class CardinalRelationConcept extends AbstractConcept implements Concept {
    private String composite;
    private String composition;
    private int compositionCardinality;
    private int compositeCardinality;

    private CardinalRelationConcept(CardinalRelationConceptBuilder conceptBuilder) {
        super(conceptBuilder.learningContent, conceptBuilder.originalSentence);

        this.composite = conceptBuilder.composite;
        this.composition = conceptBuilder.composition;
        this.compositionCardinality = conceptBuilder.compositionCardinality;
        this.compositeCardinality = conceptBuilder.compositeCardinality;
    }

    /**
     * Gets the composite of this relation concept, e.g.
     * "Germany consists of 16 federal states".
     * Composite: Germany
     * @return the composite
     */
    public String getComposite() {
        return composite;
    }

    /**
     * Gets the composition of this relation concept, e.g.
     * "Germany consists of 16 federal states".
     * Composition: federal states
     * @return the composition
     */
    public String getComposition() {
        return composition;
    }

    /**
     * Gets the cardinality of the composite, e.g.
     * "Germany consists of 16 federal states".
     * Composite cardinality: 1 (Germany represents a subject with cardinality 1)
     * @return the composite cardinality
     */
    public int getCompositeCardinality() {
        return compositeCardinality;
    }

    /**
     * Gets the cardinality of the composition, e.g.
     * "Germany consists of 16 federal states".
     * Composition cardinality: 16 (the objects federal states).
     * @return the composition cardinality
     */
    public int getCompositionCardinality() {
        return compositionCardinality;
    }

    /**
     * Sets the composition
     * @param composition String
     */
    public void setComposition(String composition) {
        this.composition = composition;
    }

    /**
     * Sets the composition cardinality.
     * @param compositionCardinality int
     */
    public void setCompositionCardinality(int compositionCardinality) {
        this.compositionCardinality = compositionCardinality;
    }

    /**
     * Sets the composite.
     * @param composite String
     */
    public void setComposite(String composite) {
        this.composite = composite;
    }

    /**
     * Sets the composite cardinality.
     * @param compositeCardinality int
     */
    public void setCompositeCardinality(int compositeCardinality) {
        this.compositeCardinality = compositeCardinality;
    }

    public static class CardinalRelationConceptBuilder {

        private String originalSentence;
        private LearningContent learningContent;
        private String composite;
        private String composition;
        private int compositionCardinality;
        private int compositeCardinality;

        /**
         * The original sentence, e.g. for fill sentence "Berlin is the capital of ___"
         * this would be "Berlin is the capital of Germany.".
         * @param originalSentence originalSentence
         * @return this
         */
        public CardinalRelationConceptBuilder withOriginalSentence(final String originalSentence) {
            checkNotNull(originalSentence, "originalSentence must not be null");

            this.originalSentence = originalSentence;

            return this;
        }

        /**
         * The underlying learning cotnent.
         * @param learningContent learning content
         * @return this
         */
        public CardinalRelationConceptBuilder withLearningContent(final LearningContent learningContent) {
            checkNotNull(learningContent, "learningContent must not be null");

            this.learningContent = learningContent;

            return this;
        }

        /**
         *
         * @param composite the composite
         * @return this
         */
        public CardinalRelationConceptBuilder withComposite(final String composite) {
            checkNotNull(composite, "composite must not be null");

            this.composite = composite;

            return this;
        }

        /**
         * @param composition the composition
         * @return this
         */
        public CardinalRelationConceptBuilder withComposition(final String composition) {
            checkNotNull(composition, "composition must not be null");

            this.composition = composition;

            return this;
        }

        /**
         * @param compositionCardinality the composition cardinality
         * @return this
         */
        public CardinalRelationConceptBuilder withCompositionCardinality(final int compositionCardinality) {
            checkNotNull(compositionCardinality, "compositionCardinality must not be null");

            this.compositionCardinality = compositionCardinality;

            return this;
        }
        /**
         * The underlying learning cotnent.
         * @param compositeCardinality the composite cardinality
         * @return this
         */
        public CardinalRelationConceptBuilder withCompositeCardinality(final int compositeCardinality) {
            checkNotNull(compositeCardinality, "compositeCardinality must not be null");

            this.compositeCardinality = compositeCardinality;

            return this;
        }

        /**
         * Builds the {@link CardinalRelationConcept}
         * @return the cardinal relation concept.
         */
        public CardinalRelationConcept build() {
            checkNotNull(originalSentence, "originalSentence must not be null");
            checkNotNull(learningContent, "learningContent must not be null");
            checkNotNull(composite, "composite must not be null");
            checkNotNull(composition, "composition must not be null");
            checkNotNull(compositionCardinality, "compositionCardinality must not be null");
            checkNotNull(compositeCardinality, "compositeCardinality must not be null");

            return new CardinalRelationConcept(this);
        }
    }
}

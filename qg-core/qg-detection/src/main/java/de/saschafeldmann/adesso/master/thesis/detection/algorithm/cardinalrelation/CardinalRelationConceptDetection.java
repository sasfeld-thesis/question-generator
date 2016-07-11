package de.saschafeldmann.adesso.master.thesis.detection.algorithm.cardinalrelation;

import com.google.common.base.Joiner;
import de.saschafeldmann.adesso.master.thesis.detection.algorithm.DetectionAlgorithm;
import de.saschafeldmann.adesso.master.thesis.detection.algorithm.DetectionOptions;
import de.saschafeldmann.adesso.master.thesis.detection.model.CardinalRelationConcept;
import de.saschafeldmann.adesso.master.thesis.detection.util.DetectionProperties;
import de.saschafeldmann.adesso.master.thesis.detection.util.ValidateUtil;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Language;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.util.linguistic.NlpAnnotationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
 * Implementation of a {@link DetectionAlgorithm}
 */
@Component
@Scope("prototype")
public class CardinalRelationConceptDetection implements DetectionAlgorithm<CardinalRelationConcept> {
    private final Joiner REGEX_OR_JOINER = Joiner.on("|").skipNulls();

    private final DetectionProperties detectionProperties;

    /**
     * Creates a new cardinal relation concept.
     * @param detectionProperties the modules properties
     */
    @Autowired
    public CardinalRelationConceptDetection(final DetectionProperties detectionProperties) {
        this.detectionProperties = detectionProperties;
    }

    @Override
    public List<CardinalRelationConcept> execute(final LearningContent learningContent, final DetectionOptions detectionOptions) {
        ValidateUtil.validate(learningContent, detectionOptions);

        return findSentencesWithCardinalRelations(learningContent);
    }

    private List<CardinalRelationConcept> findSentencesWithCardinalRelations(final LearningContent learningContent) {
        final List<CardinalRelationConcept> concepts = new ArrayList<>();

        for (final String posAnnotatedSentence : learningContent.getPartOfSpeechAnnotatedText()) {
            CardinalRelationConcept cardinalRelationConcept = sentenceMatchesConfiguredCardinalRelationPatterns(learningContent, posAnnotatedSentence);

            if (null != cardinalRelationConcept) {
                concepts.add(cardinalRelationConcept);
            }
        }

        return concepts;
    }

    private CardinalRelationConcept sentenceMatchesConfiguredCardinalRelationPatterns(LearningContent learningContent, final String posAnnotatedSentence) {
        final Language targetLanguage = learningContent.getDeterminedLanguage();

        // TODO add articles, "Die Bundesrepublik"...
        final List<String> cardinalRelationCompositePosTags = detectionProperties.getCardinalRelationCompositePosTags(targetLanguage);
        final List<String> cardinalRelationAdjectivePosTags = detectionProperties.getCardinalRelationAdjectivePosTags(targetLanguage);
        final List<String> cardinalRelationKeyWordsPosTags = detectionProperties.getCardinalRelationKeywordsPosTags(targetLanguage);
        final List<String> cardinalRelationCardinalityPosTags = detectionProperties.getCardinalRelationCardinalityPosTags(targetLanguage);
        final List<String> cardinalRelationCompositionPosTags = detectionProperties.getCardinalRelationCompositionPosTags(targetLanguage);

        // find patterns of (cardinalRelationAdjectivePosTags) <cardinalRelationCompositePosTags> <cardinalRelationKeyWordsPosTags> <cardinalRelationCardinalityPosTags> (cardinalRelationAdjectivePosTags) <cardinalRelationCompositionPosTags>
        final String cardinalSentencePatternString = buildCardinalSentencePattern(cardinalRelationCompositePosTags, cardinalRelationKeyWordsPosTags, cardinalRelationCardinalityPosTags, cardinalRelationCompositionPosTags, cardinalRelationAdjectivePosTags);
        final Pattern cardinalSentencePattern = Pattern.compile(cardinalSentencePatternString);
        final Matcher mCardinalSentenceMatcher = cardinalSentencePattern.matcher(posAnnotatedSentence);

        if (mCardinalSentenceMatcher.matches()) {
            // group numbers follow the sequence of part of speech tag regular expression groups in the method buildCardinalSentencePattern()
            final String compositeAdjective = mCardinalSentenceMatcher.group(1);
            final String composite = mCardinalSentenceMatcher.group(2);
            final String cardinality = mCardinalSentenceMatcher.group(4);
            final String compositionAdjective = mCardinalSentenceMatcher.group(5);
            final String composition = mCardinalSentenceMatcher.group(6);

            return newCardinalRelationConcept(learningContent, compositeAdjective, composite, cardinality, compositionAdjective, composition, posAnnotatedSentence);
        }

        return null;
    }

    private CardinalRelationConcept newCardinalRelationConcept(LearningContent learningContent, String compositeAdjective, String composite, String cardinality, String compositionAdjective, String composition, String posAnnotatedSentence) {
        String originalSentence = NlpAnnotationUtil.removeAllTokenAnnotations(posAnnotatedSentence);

        String compositeWithAdjective = compositeAdjective + " " + composite;
        String compositionWithAdjective = compositionAdjective + " " + composition;

        return new CardinalRelationConcept.CardinalRelationConceptBuilder()
                .withComposite(compositeWithAdjective.trim())
                .withCompositeCardinality(1)
                .withCompositionCardinality(Integer.parseInt(cardinality))
                .withComposition(compositionWithAdjective.trim())
                .withLearningContent(learningContent)
                .withOriginalSentence(originalSentence)
                .build();
    }

    /**
     * Builds the regular expression pattern to match cardinal relation sentences based on configured possible sequences of part of speech tags.
     *
     * @param cardinalRelationCompositePosTags the POS tags surrounding the composite
     * @param cardinalRelationKeyWordsPosTags the POS tags signaling the composition verb
     * @param cardinalRelationCardinalityPosTags the POS tags representing tha cardinality of the composition
     * @param cardinalRelationCompositionPosTags the POS tags surrounding the composition
     * @param cardinalRelationAdjectivePosTags additional POS tags representing adjectives that describe composition and / or composite better
     *
     * @return String the regular expression pattern
     */
    private String buildCardinalSentencePattern(List<String> cardinalRelationCompositePosTags, List<String> cardinalRelationKeyWordsPosTags, List<String> cardinalRelationCardinalityPosTags, List<String> cardinalRelationCompositionPosTags, List<String> cardinalRelationAdjectivePosTags) {
        StringBuilder patternBuilder = new StringBuilder();

        patternBuilder
                .append(buildOrRegex(cardinalRelationAdjectivePosTags, "*"))
                .append(buildOrRegex(cardinalRelationCompositePosTags, "+"))
                .append(buildOrRegex(cardinalRelationKeyWordsPosTags, "+"))
                .append(buildOrRegex(cardinalRelationCardinalityPosTags, "+"))
                .append(buildOrRegex(cardinalRelationAdjectivePosTags, "*"))
                .append(buildOrRegex(cardinalRelationCompositionPosTags, "+"))
                .append("(.*?)")
        ;


        return patternBuilder.toString();
    }

    private String buildOrRegex(List<String> cardinalRelationAdjectivePosTags, String modifier) {
        String alternativeOpeningPosTags = REGEX_OR_JOINER.join(cardinalRelationAdjectivePosTags);
        String alternativeClosingPosTags = REGEX_OR_JOINER.join(cardinalRelationAdjectivePosTags);
        alternativeClosingPosTags = alternativeClosingPosTags.replaceAll("<", "</");

        String regex = "[" + alternativeOpeningPosTags + "]" + modifier;
        regex += "(.*?)";
        regex += "[" + alternativeClosingPosTags + "]" + modifier;
        regex += "[ ]*";
        
        return regex;
    }
}

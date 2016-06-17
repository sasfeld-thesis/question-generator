package de.saschafeldmann.adesso.master.thesis.preprocesses.algorithm.nlp;

import static com.google.common.base.Preconditions.*;

import com.google.common.base.Joiner;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Language;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.preprocesses.algorithm.PreprocessingAlgorithm;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.util.*;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  17.06.2016
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
 * Natural language preprocessing algorithm implementation.
 * Uses the Stanford NLP library and trained models for both the German and English languages.
 *
 * http://stanfordnlp.github.io/CoreNLP/
 */
public class NlpPreprocessingAlgorithm implements PreprocessingAlgorithm {
    private static final Joiner STANFORD_ANNOTATORS_PROPERTY_JOINER = Joiner.on(", ");

    private Boolean activatePartOfSpeechTagging = false;
    private Boolean activateNamedEntityRecognition = false;

    /**
     * Whether named entity recognition should be done.
     * @param activateNamedEntityRecognition boolean
     */
    public void setActivateNamedEntityRecognition(boolean activateNamedEntityRecognition) {
        this.activateNamedEntityRecognition = activateNamedEntityRecognition;
    }

    /**
     * Whether part of speech tagging should be done.
     * @param activatePartOfSpeechTagging boolean
     */
    public void setActivatePartOfSpeechTagging(boolean activatePartOfSpeechTagging) {
        this.activatePartOfSpeechTagging = activatePartOfSpeechTagging;
    }

    @Override
    public LearningContent execute(LearningContent learningContent) {
        final StanfordCoreNLP stanfordCoreNLP = createStanfordCoreNlpInstanceForLanguage(learningContent.getDeterminedLanguage());

        final String annotatedText = annotateRawText(stanfordCoreNLP, learningContent);
        learningContent.setAnnotatedText(annotatedText);
        return learningContent;
    }

    private StanfordCoreNLP createStanfordCoreNlpInstanceForLanguage(Language determinedLanguage) {
        checkNotNull(determinedLanguage, "The language of the given learning content must not be null.");

        Properties stanfordCoreNlpPoperties = new Properties();
        stanfordCoreNlpPoperties.setProperty("-props", getPropertyForLanguage(determinedLanguage));
        stanfordCoreNlpPoperties.setProperty("annotators", getAnnotatorsProperty());

        return new StanfordCoreNLP(stanfordCoreNlpPoperties);
    }

    /**
     * Builds the "annotators" property to be put into the {@link StanfordCoreNLP} instance
     * by setting the user's desired annotations (such as Named Entity Recognition and/or Part Of Speech tagging).
     * @return String
     */
    private String getAnnotatorsProperty() {
        final List<String> annotatorProperties = new ArrayList<>();
        // required: tokenizes the raw text (isolates words)
        annotatorProperties.add("tokenize");
        // reqzired: splits sentences.
        annotatorProperties.add("ssplit");

        if (activatePartOfSpeechTagging) {
            annotatorProperties.add("pos");
        }
        if (activateNamedEntityRecognition) {
            annotatorProperties.add("ner");
        }

        return STANFORD_ANNOTATORS_PROPERTY_JOINER.join(annotatorProperties);
    }

    /**
     * Builds the "-props" property to be put into the {@link StanfordCoreNLP} instance
     * by setting the correct model for the given determinedLanguage.
     * @param determinedLanguage language of the learning content to be processed, English or German
     * @return String
     */
    private String getPropertyForLanguage(Language determinedLanguage) {
        switch (determinedLanguage) {
            case ENGLISH:
                System.out.println("english");
                return "StanfordCoreNLP-english.properties";
            case GERMAN:
                System.out.println("german");
                return "StanfordCoreNLP-german.properties";
            default:
                return "";
        }
    }

    /**
     * Annotates the given raw text by returning an annotated copy.
     * @param stanfordCoreNLP see above
     * @param learningContent (raw) learning content
     * @return annotated text
     */
    private String annotateRawText(StanfordCoreNLP stanfordCoreNLP, LearningContent learningContent) {
        final String rawText = learningContent.getRawText();
        final Annotation annotation = new Annotation(rawText);

        stanfordCoreNLP.annotate(annotation);

        final StringBuilder annotatedTextBuilder = new StringBuilder();
        final List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);

        for (CoreMap sentence: sentences) {
            // iterate over sentences
            for (CoreLabel token: sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                // iterate over tokens / words in the current sentence
                String tokenText = token.get(CoreAnnotations.TextAnnotation.class); // the raw word itself
                String partOfSpeechTag = token.get(CoreAnnotations.PartOfSpeechAnnotation.class); // the part of speech tag itself
                createXmlTagForAnnotation(annotatedTextBuilder, tokenText, partOfSpeechTag);
            }
            annotatedTextBuilder.append("\n");
        }

        return annotatedTextBuilder.toString();
    }

    private void createXmlTagForAnnotation(StringBuilder annotatedTextBuilder, String tokenText, String annotation) {
        annotatedTextBuilder.append("<")
                .append(annotation)
                .append(">")
                .append(tokenText)
                .append("</")
                .append(annotation)
                .append(">");
    }
}

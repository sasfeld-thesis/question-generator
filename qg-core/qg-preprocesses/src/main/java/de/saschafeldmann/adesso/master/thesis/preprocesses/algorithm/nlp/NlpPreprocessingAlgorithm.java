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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final Logger LOGGER = LoggerFactory.getLogger(NlpPreprocessingAlgorithm.class);

    private static final Joiner STANFORD_ANNOTATORS_PROPERTY_JOINER = Joiner.on(", ");
    // Huge German Corpus - for all possible taggers see JAR file stanford-german-2016-01-19-models.jar
    private static final String GERMAN_PART_OF_SPEECH_MODEL = "edu/stanford/nlp/models/pos-tagger/german/german-hgc.tagger";
    // Huge German Corpus - for all possible NER classifiers see JAR file stanford-german-2016-01-19-models.jar
    private static final String GERMAN_NAMED_ENTITY_RECOGNITION_MODEL = "edu/stanford/nlp/models/ner/german.hgc_175m_600.crf.ser.gz";
    // MUC-7 class model - for all possible taggers see JAR file stanford-english-corenlp-2016-01-10-models.jar
    private static final String ENGLISH_NAMED_ENTITY_RECOGNITION_MODEL = "edu/stanford/nlp/models/ner/english.muc.7class.distsim.crf.ser.gz";
    // English POS model - for all possible NER classifiers see JAR file stanford-english-corenlp-2016-01-10-models.jar
    private static final String ENGLISH_PART_OF_SPEECH_MODEL = "edu/stanford/nlp/models/pos-tagger/english-bidirectional/english-bidirectional-distsim.tagger";

    private Boolean activatePartOfSpeechTagging = false;
    private Boolean activateNamedEntityRecognition = false;

    private final Map<Language, StanfordCoreNLP> stanfordPipelineMap = new HashMap<>();

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
        final StanfordCoreNLP stanfordCoreNLP = getStanfordCoreNlpInstanceForLanguage(learningContent.getDeterminedLanguage());

        annotateRawText(stanfordCoreNLP, learningContent);
        return learningContent;
    }

    /**
     * Gets the stanford core nlp instance for the given language.
     * Uses a map to store the pipeline since its creation is very expensive.
     *
     * @param determinedLanguage the language for which the pipeline should be created
     * @return the pipeline
     */
    private StanfordCoreNLP getStanfordCoreNlpInstanceForLanguage(Language determinedLanguage) {
        checkNotNull(determinedLanguage, "The language of the given learning content must not be null.");

        if (!stanfordPipelineMap.containsKey(determinedLanguage)) {
            LOGGER.info("getStanfordCoreNlpInstanceForLanguage(): EXPENSIVE CREATION - creating new stanford natural language processing pipeline for language {}", determinedLanguage);
            stanfordPipelineMap.put(determinedLanguage, createNewStanfordNlpPipeline(determinedLanguage));
            LOGGER.info("getStanfordCoreNlpInstanceForLanguage(): finished natural language processing pipeline creation");
        }

        return stanfordPipelineMap.get(determinedLanguage);
    }

    private StanfordCoreNLP createNewStanfordNlpPipeline(Language determinedLanguage) {
        Properties stanfordCoreNlpPoperties = new Properties();
        stanfordCoreNlpPoperties.setProperty("annotators", getAnnotatorsProperty());
        addPropertiesForLanguage(stanfordCoreNlpPoperties, determinedLanguage);

        return new StanfordCoreNLP(stanfordCoreNlpPoperties);
    }

    /**
     * Add language-specific properties.
     * @param stanfordCoreNlpPoperties the properties to be put into the Stanford NLP pipeline
     * @param determinedLanguage the language of the content to be processed
     */
    private void addPropertiesForLanguage(Properties stanfordCoreNlpPoperties, Language determinedLanguage) {
        switch (determinedLanguage) {
            case ENGLISH:
                // see https://github.com/stanfordnlp/CoreNLP/blob/master/src/edu/stanford/nlp/pipeline/StanfordCoreNLP.properties
                stanfordCoreNlpPoperties.put("pos.model", ENGLISH_PART_OF_SPEECH_MODEL);
                stanfordCoreNlpPoperties.put("ner.model", ENGLISH_NAMED_ENTITY_RECOGNITION_MODEL);
                break;
            case GERMAN:
              // see https://github.com/stanfordnlp/CoreNLP/blob/master/src/edu/stanford/nlp/pipeline/StanfordCoreNLP-german.properties
              stanfordCoreNlpPoperties.put("tokenize.language",  "de");
              stanfordCoreNlpPoperties.put("pos.model", GERMAN_PART_OF_SPEECH_MODEL);
              stanfordCoreNlpPoperties.put("ner.model", GERMAN_NAMED_ENTITY_RECOGNITION_MODEL); // Huge German Corpus
              break;
        }
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
        // required: splits sentences.
        annotatorProperties.add("ssplit");
        // activate: Part-of-Speech tagging
        annotatorProperties.add("pos");
        // required for NER: word Lemma
        annotatorProperties.add("lemma");
        // activate: Named Entity Recognition
        annotatorProperties.add("ner");

        return STANFORD_ANNOTATORS_PROPERTY_JOINER.join(annotatorProperties);
    }

    /**
     * Annotates the given raw text by returning an annotated copy.
     * @param stanfordCoreNLP see above
     * @param learningContent (raw) learning content
     * @return annotated text
     */
    private void annotateRawText(StanfordCoreNLP stanfordCoreNLP, LearningContent learningContent) {
        final String rawText = learningContent.getRawText();
        final Annotation annotation = new Annotation(rawText);

        stanfordCoreNLP.annotate(annotation);

        // store named entity and part of speech annotated texts in different strings
        final StringBuilder posAnnotatedTextBuilder = new StringBuilder();
        final StringBuilder nerAnnotatedTextBuilder = new StringBuilder();
        final List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);

        for (CoreMap sentence: sentences) {
            // iterate over sentences
            for (CoreLabel token: sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                // iterate over tokens / words in the current sentence
                String tokenText = token.get(CoreAnnotations.TextAnnotation.class); // the raw word itself

                if (activatePartOfSpeechTagging) {
                    String partOfSpeechTag = token.get(CoreAnnotations.PartOfSpeechAnnotation.class); // the part of speech tag itself
                    createXmlTagForAnnotation(posAnnotatedTextBuilder, tokenText, partOfSpeechTag);
                }
                if (activateNamedEntityRecognition) {
                    String namedEntityTag = token.get(CoreAnnotations.NamedEntityTagAnnotation.class); // the named entity tag itself
                    createXmlTagForAnnotation(nerAnnotatedTextBuilder, tokenText, namedEntityTag);
                }
            }

            nerAnnotatedTextBuilder.append("\n");
            posAnnotatedTextBuilder.append("\n");
        }

        if (activatePartOfSpeechTagging) {
            learningContent.setPartOfSpeechAnnotatedText(posAnnotatedTextBuilder.toString());
        }

        if (activateNamedEntityRecognition) {
            learningContent.setNamedEntityAnnotatedText(nerAnnotatedTextBuilder.toString());
        }
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

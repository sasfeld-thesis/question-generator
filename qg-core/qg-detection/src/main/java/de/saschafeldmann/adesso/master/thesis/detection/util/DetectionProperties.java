package de.saschafeldmann.adesso.master.thesis.detection.util;

import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Language;
import de.saschafeldmann.adesso.master.thesis.util.properties.PropertiesReader;
import de.saschafeldmann.adesso.master.thesis.util.properties.PropertiesReaderApi;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

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
 * Implementation of a properties reader for the detection module.
 */
@Component
@Scope("singleton")
public class DetectionProperties extends PropertiesReader implements PropertiesReaderApi {
    private static final String PROPERTIES_FILE_NAME = "/detection.properties";

    /**
     * Constructs a new properties instance.
     * @throws Exception if the properties file could not be found
     */
    public DetectionProperties() throws Exception {
        super(DetectionProperties.class.getResourceAsStream(PROPERTIES_FILE_NAME));
    }

    /**
     * Gets the named entity types that are asked for within fill text concept questions.<br />
     * Example original sentence: The capital of Germany is <LOCATION>Berlin</LOCATION>. <br />
     * Berlin as location would be offered for the filltext if location is contained within the properties values.<br />
     * The resulting fill text question would be: "What is the complete sentence? The capital of Germany is ___?".
     *
     * @return a list of named entities
     */
    public List<String> getFillTextFillNamedEntities() {
        return fetchMultipleValues("de.saschafeldmann.adesso.master.thesis.detection.filltext.fill.named.entities");
    }

    /**
     * Gets the part of speech tag typesthat are asked for within fill text concept questions.<br />
     * Example original sentence: The capital of Germany is <NNP>Berlin</NNP>. <br />
     * Berlin as a proper noun singular would be offered for the filltext if location is contained within the properties values.<br />
     * The resulting fill text question would be: "What is the complete sentence? The capital of Germany is ___?".
     *
     * @return a list of named entities
     */
    public List<String> getFillTextFillPartOfSpeechTags() {
        return fetchMultipleValues("de.saschafeldmann.adesso.master.thesis.detection.filltext.fill.part.of.speech.tags");
    }

    /**
     * Gets the filltext replacement character, e.g. "___".
     * @return the filltext replacement character
     */
    public String getFillTextReplacementCharacter() {
        return fetchValue("de.saschafeldmann.adesso.master.thesis.detection.filltext.replacement.character");
    }

    /**
     * Gets the cardinal relation cardinality part - of - speech tags,
     * e.g. "<CARD>16</CARD>" represents a cardinality of 16.
     * @param language the given language
     * @return the list
     */
    public List<String> getCardinalRelationCardinalityPosTags(final Language language) {
        return fetchMultipleValues("de.saschafeldmann.adesso.master.thesis.detection.cardinalrelation.cardinality.postag." + language.toString().toLowerCase());
    }

    /**
     * Gets the cardinal relation keywords POS tags for the given language.<br />
     * These are verbs most of times.
     * @param language the given language
     * @return the list
     */
    public List<String> getCardinalRelationKeywordsPosTags(final Language language) {
        return fetchMultipleValues("de.saschafeldmann.adesso.master.thesis.detection.cardinalrelation.keywords.postag." + language.toString().toLowerCase());
    }

    /**
     * Gets the configured part of speech tags that can be represented by a cardinal relation. <br />
     * E.g. the sentence <NN>Germany</NN> has 16 <NN>states</NN> -> here, the composite part of speech tag is NN (for noun).
     * @param language the language
     * @return the list
     */
    public List<String> getCardinalRelationCompositePosTags(final Language language) {
        return fetchMultipleValues("de.saschafeldmann.adesso.master.thesis.detection.cardinalrelation.composite.postag." + language.toString().toLowerCase());
    }

    /**
     * Gets the configured part of speech tags that can be represented by a cardinal relation. <br />
     * E.g. the sentence <NN>Germany</NN> has 16 <NN>states</NN> -> here, the composition part of speech tag is NN (for noun).
     * @param language the language
     * @return the list
     */
    public List<String> getCardinalRelationCompositionPosTags(final Language language) {
        return fetchMultipleValues("de.saschafeldmann.adesso.master.thesis.detection.cardinalrelation.composition.postag." + language.toString().toLowerCase());
    }

    /**
     * Gets the configured part of speech tags for optional adjectives on the composite or composition.
     * E.g. the sentence <ADJ>Beautiful</ADJ> </ADJ><NN>Germany</NN> has 16 <NN>states</NN> -> Germany has the adjective beautiful
     * @param language the language
     * @return the list
     */
    public List<String> getCardinalRelationAdjectivePosTags(final Language language) {
        return fetchMultipleValues("de.saschafeldmann.adesso.master.thesis.detection.cardinalrelation.adjective.postag." + language.toString().toLowerCase());
    }

}

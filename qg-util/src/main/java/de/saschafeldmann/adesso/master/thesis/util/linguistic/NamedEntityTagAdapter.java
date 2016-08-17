package de.saschafeldmann.adesso.master.thesis.util.linguistic;

import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Language;
import de.saschafeldmann.adesso.master.thesis.util.properties.PropertiesReaderApi;
import de.saschafeldmann.adesso.master.thesis.util.properties.linguistic.NaturalLanguageProcessingPropertiesReader;

import java.util.List;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  29.06.2016
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
 * Adapter to support different tagsets of named entity annotatioms, e.g.
 * "I-LOC" (German MUC-7 corpus) vs. "LOCATION" (English corpus).
 */
public enum NamedEntityTagAdapter {
    LOCATION,
    PERSON,
    ORGANIZATION,
    DATE,
    NUMERIC,
    MISCELLANEOUS;

    /**
     * Gets the specific named entity tag for the given language, e.g. "I-LOC" for a German location.
     * @param language the language
     * @return String
     */
    public List<String> getTagsForLanguage(final Language language) {
        switch (language) {
            case GERMAN:
                return getTagsForGerman();
            case ENGLISH:
                return getTagsForEnglish();
            default:
                throw new UnsupportedOperationException("Language " + language + " is not supported.");
        }
    }

    private List<String> getTagsForEnglish() {
        switch (this) {
            case LOCATION:
                return getEnglishLocationTags();
            case PERSON:
                return getEnglishPersonTags();
            case ORGANIZATION:
                return getEnglishOrganizationTags();
            case DATE:
                return getEnglishDateTags();
            case NUMERIC:
                return getEnglishNumericTags();
            case MISCELLANEOUS:
                return getEnglishMiscTags();
            default:
                throw new UnsupportedOperationException("Tag " + this + " is not supported.");
        }
    }

    private List<String> getEnglishNumericTags() {
        return getProperties().fetchMultipleValues("de.saschafeldmann.adesso.master.thesis.util.named.entity.adapter.numeric.english");
    }

    private List<String> getEnglishDateTags() {
        return getProperties().fetchMultipleValues("de.saschafeldmann.adesso.master.thesis.util.named.entity.adapter.date.english");
    }

    private List<String> getEnglishOrganizationTags() {
        return getProperties().fetchMultipleValues("de.saschafeldmann.adesso.master.thesis.util.named.entity.adapter.organization.english");
    }

    private List<String> getEnglishPersonTags() {
        return getProperties().fetchMultipleValues("de.saschafeldmann.adesso.master.thesis.util.named.entity.adapter.person.english");
    }

    private List<String> getEnglishLocationTags() {
        return getProperties().fetchMultipleValues("de.saschafeldmann.adesso.master.thesis.util.named.entity.adapter.location.english");
    }

    private List<String> getEnglishMiscTags() {
        return getProperties().fetchMultipleValues("de.saschafeldmann.adesso.master.thesis.util.named.entity.adapter.misc.english");
    }

    private PropertiesReaderApi getProperties() {
        return NaturalLanguageProcessingPropertiesReader.getInstance();
    }

    private List<String> getTagsForGerman() {
        switch (this) {
            case LOCATION:
                return getGermanLocationTags();
            case PERSON:
                return getGermanPersonTags();
            case ORGANIZATION:
                return getGermanOrganizationTags();
            case DATE:
                return getGermanDateTags();
            case NUMERIC:
                return getGermanNumericTags();
            case MISCELLANEOUS:
                return getGermanMiscTags();
            default:
                throw new UnsupportedOperationException("Tag " + this + " is not supported.");
        }
    }

    private List<String> getGermanNumericTags() {
        return getProperties().fetchMultipleValues("de.saschafeldmann.adesso.master.thesis.util.named.entity.adapter.numeric.german");
    }

    private List<String> getGermanDateTags() {
        return getProperties().fetchMultipleValues("de.saschafeldmann.adesso.master.thesis.util.named.entity.adapter.date.german");
    }

    private List<String> getGermanOrganizationTags() {
        return getProperties().fetchMultipleValues("de.saschafeldmann.adesso.master.thesis.util.named.entity.adapter.organization.german");
    }

    private List<String> getGermanPersonTags() {
        return getProperties().fetchMultipleValues("de.saschafeldmann.adesso.master.thesis.util.named.entity.adapter.person.german");
    }

    private List<String> getGermanLocationTags() {
        return getProperties().fetchMultipleValues("de.saschafeldmann.adesso.master.thesis.util.named.entity.adapter.location.german");
    }

    private List<String> getGermanMiscTags() {
        return getProperties().fetchMultipleValues("de.saschafeldmann.adesso.master.thesis.util.named.entity.adapter.misc.german");
    }
}

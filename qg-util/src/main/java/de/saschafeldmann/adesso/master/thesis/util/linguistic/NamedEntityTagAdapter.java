package de.saschafeldmann.adesso.master.thesis.util.linguistic;

import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Language;
import de.saschafeldmann.adesso.master.thesis.util.properties.PropertiesReaderApi;
import de.saschafeldmann.adesso.master.thesis.util.properties.linguistic.NaturalLanguageProcessingPropertiesReader;

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
    NUMERIC;

    /**
     * Gets the specific named entity tag for the given language, e.g. "I-LOC" for a German location.
     * @param language the language
     * @return String
     */
    public String getTagForLanguage(final Language language) {
        switch (language) {
            case GERMAN:
                return getTagForGerman();
            case ENGLISH:
                return getTagForEnglish();
            default:
                throw new UnsupportedOperationException("Language " + language + " is not supported.");
        }
    }

    private String getTagForEnglish() {
        switch (this) {
            case LOCATION:
                return getEnglishLocationTag();
            case PERSON:
                return getEnglishPersonTag();
            case ORGANIZATION:
                return getEnglishOrganizationTag();
            case DATE:
                return getEnglishDateTag();
            case NUMERIC:
                return getEnglishNumericTag();
            default:
                throw new UnsupportedOperationException("Tag " + this + " is not supported.");
        }
    }

    private String getEnglishNumericTag() {
        return getProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.util.named.entity.adapter.numeric.english");
    }

    private String getEnglishDateTag() {
        return getProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.util.named.entity.adapter.date.english");
    }

    private String getEnglishOrganizationTag() {
        return getProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.util.named.entity.adapter.organization.english");
    }

    private String getEnglishPersonTag() {
        return getProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.util.named.entity.adapter.person.english");
    }

    private String getEnglishLocationTag() {
        return getProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.util.named.entity.adapter.location.english");
    }

    private PropertiesReaderApi getProperties() {
        return NaturalLanguageProcessingPropertiesReader.getInstance();
    }

    private String getTagForGerman() {
        switch (this) {
            case LOCATION:
                return getGermanLocationTag();
            case PERSON:
                return getGermanPersonTag();
            case ORGANIZATION:
                return getGermanOrganizationTag();
            case DATE:
                return getGermanDateTag();
            case NUMERIC:
                return getGermanNumericTag();
            default:
                throw new UnsupportedOperationException("Tag " + this + " is not supported.");
        }
    }

    private String getGermanNumericTag() {
        return getProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.util.named.entity.adapter.numeric.german");
    }

    private String getGermanDateTag() {
        return getProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.util.named.entity.adapter.date.german");
    }

    private String getGermanOrganizationTag() {
        return getProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.util.named.entity.adapter.organization.german");
    }

    private String getGermanPersonTag() {
        return getProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.util.named.entity.adapter.person.german");
    }

    private String getGermanLocationTag() {
        return getProperties().fetchValue("de.saschafeldmann.adesso.master.thesis.util.named.entity.adapter.location.german");
    }
}

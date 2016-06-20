package de.saschafeldmann.adesso.master.thesis.portlet.model;

import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Language;
import de.saschafeldmann.adesso.master.thesis.portlet.QuestionGeneratorPortletVaadinUi;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.i18n.Messages;

import java.util.ArrayList;
import java.util.List;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  14.06.2016
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
 * Wrapper between {@link de.saschafeldmann.adesso.master.thesis.portlet.view.components.ListSelect} and {@link de.saschafeldmann.adesso.master.thesis.elearningimport.model.Language}
 */
public class LanguageWrapper {
    public static final String DEFAULT_SELECTION = "-";
    private final Language language;

    private LanguageWrapper(final Language language) {
        this.language = language;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LanguageWrapper that = (LanguageWrapper) o;

        return language == that.language;

    }

    @Override
    public int hashCode() {
        return language != null ? language.hashCode() : 0;
    }

    /**
     * Gets the wrapped language.
     * @return the lang
     */
    public Language getLanguage() {
        return language;
    }

    @Override
    public String toString() {
        Messages messages = QuestionGeneratorPortletVaadinUi.getCurrentPortletVaadinUi().getMessages();

        switch (language) {
            case ENGLISH:
                return messages.getCourseInformationViewEnglishLanguageLabel();
            case GERMAN:
                return messages.getCourseInformationViewGermanLanguageLabel();
            default:
                return DEFAULT_SELECTION;
        }
    }

    /**
     * Returns the language wrapper for the given language.
     * @param language
     * @return
     */
    public static LanguageWrapper forLanguage(Language language) {
        switch (language) {
            case ENGLISH:
                return getEnglishWrapper();
            case GERMAN:
                return getGermanWrapper();
            default:
                throw new IllegalArgumentException("language is not supported.");
        }
    }

    /**
     * Gets all languages for the selection.
     * @return the list of all languages for {@link de.saschafeldmann.adesso.master.thesis.portlet.view.components.ListSelect}
     */
    public static List<LanguageWrapper> getAllLanguageItems() {
        List<LanguageWrapper> wrapperList = new ArrayList<>();

        wrapperList.add(getGermanWrapper());
        wrapperList.add(getEnglishWrapper());

        return wrapperList;
    }

    public static LanguageWrapper getGermanWrapper() {
        return new LanguageWrapper(Language.GERMAN);
    }

    public static LanguageWrapper getEnglishWrapper() {
        return new LanguageWrapper(Language.ENGLISH);
    }

}

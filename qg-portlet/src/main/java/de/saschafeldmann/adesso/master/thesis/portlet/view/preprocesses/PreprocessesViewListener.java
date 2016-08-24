package de.saschafeldmann.adesso.master.thesis.portlet.view.preprocesses;

import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Language;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.portlet.model.preprocesses.ProcessActivationElement;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  04.06.2016
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
 * Interface for listeners of {@link PreprocessesView}
 */
public interface PreprocessesViewListener {

    /**
     * Triggered if the user clicked the process chain start button.
     */
    void onProcessChainStartButtonClick();

    /***
     * Triggered if the user edited a processed learning content.
     * @param learningContentToBeEdited {@link LearningContent}
     * @param namedEntitiesInput String
     * @param partOfSpeechInput String
     */
    void onEditLearningContentClick(final LearningContent learningContentToBeEdited, final String partOfSpeechInput, final String namedEntitiesInput);

    /**
     * Triggered if the user wants to delete a process learning content.
     * @param selectedContent the content to be deleted
     */
    void onDeleteLearningContentClick(LearningContent selectedContent);

    /**
     * Triggered if the user activated or deactivated the given {@link ProcessActivationElement}
     * @param processActivationElement the processActivationElement
     */
    void onActivationElementChange(final ProcessActivationElement.ProcessActivationElementState processActivationElement);

    /**
     * Called if the view gets the user's focus.
     */
    void onViewFocus();

    /**
     * Triggered if the user changed the given content's language.
     * @param selectedContent given content
     * @param newLanguage the new language
     */
    void onEditLearningContentLanguageClick(LearningContent selectedContent, Language newLanguage);

    /**
     * On back button clicked action.
     */
    void onBackButtonClicked();

    /**
     * On next button clicked action.
     */
    void onNextButtonClicked();
}

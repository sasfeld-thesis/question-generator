package de.saschafeldmann.adesso.master.thesis.portlet.view.preprocesses;

import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;
import de.saschafeldmann.adesso.master.thesis.portlet.model.preprocesses.ProcessActivationElement;
import de.saschafeldmann.adesso.master.thesis.portlet.view.ViewWithMenu;

import java.util.Collection;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  04.06.2016
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
 * Interface for the preprocesses view where the user configures the preprocesses and can edit their outputs.
 */
public interface PreprocessesView extends ViewWithMenu {

    /**
     * Sets the process activation elements to be rendered so that the user
     * can (de-) activate them.
     * @param elements iterable
     */
    void addProcessActivationElements(Iterable<ProcessActivationElement> elements);

    /**
     * Displays the activation success message.
     */
    void showProcessActivationSuccessMessage();

    /**
     * Adds and displays a log entry informing about the process chain progress.
     * @param logEntry String the entry to be shown.
     */
    void addProcessChainLogEntry(final String logEntry);

    /**
     * Shows the given processed learning contents.
     * @param learningContents processed learning contents
     */
    void showProcessedLearningContents(Collection<LearningContent> learningContents);

    /**
     * Resets / initializes the view layout.
     */
    void reset();

    /**
     * Sets the view listener.
     * @param preprocessesViewListener the view listener
     */
    void setViewListener(PreprocessesViewListener preprocessesViewListener);
}

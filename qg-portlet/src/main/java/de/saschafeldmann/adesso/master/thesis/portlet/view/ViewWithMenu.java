package de.saschafeldmann.adesso.master.thesis.portlet.view;

import com.vaadin.navigator.View;
import de.saschafeldmann.adesso.master.thesis.portlet.model.QuestionGenerationSession;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  23.05.2016
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
 * All views with menus should implement this interface so that using presenters can register themselves as menu listeners.
 */
public interface ViewWithMenu extends View {

    /**
     * Sets the menu listener which is informed if menu items are clicked.
     * @param menuListener the menu listener (a presenter).
     */
    void setMenuListener(MenuListener menuListener);

    /**
     * Sets the current user session status so that the menu can be adjusted.
     * @param currentSessionStatus the current session status
     */
    void setCurrentSessionStatus(QuestionGenerationSession.Status currentSessionStatus);

    /**
     * Refreshes the view by reinitializing the labels and
     * resetting all inputs in the current view.
     */
    void refreshView();
}

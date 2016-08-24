package de.saschafeldmann.adesso.master.thesis.portlet.util;

import com.vaadin.server.Extension;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.AutowirableComboBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  20.06.2016
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
 * Static util class for Vaadin.
 */
public class VaadinUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(VaadinUtil.class);

    /**
     * Removes a window if it was already attached to the current (request-scoped) view.
     *
     * @param windowToBeRemoved the window
     */
    private static void removeWindowIfAlreadyAttached(final Window windowToBeRemoved) {
        for (Window window : UI.getCurrent().getWindows()) {
            if (window.equals(windowToBeRemoved)) {
                LOGGER.info("removeWindowIfAlreadyAttached(): removing window {} since it was already attached to the current UI.", windowToBeRemoved);
                UI.getCurrent().removeWindow(windowToBeRemoved);
            }
        }
    }

    /**
     * Adds a window to the current UI (request-scoped).
     * Ensures to cleanly remove the given window if it was already attached and readds it.
     *
     * @param window the window to be added.
     */
    public static void addWindow(final Window window) {
        removeWindowIfAlreadyAttached(window);
        UI.getCurrent().addWindow(window);
    }

    /**
     * Gets all the items of the given combo box.
     *
     * @param comboBox the combobox
     * @return a list of all items
     */
    public static <T> List<T> getAllItems(AutowirableComboBox comboBox) {
        List<T> items = new ArrayList<>();

        for (Object itemId : comboBox.getItemIds()) {
            items.add((T) itemId);
        }

        return items;
    }

    /**
     * Removes all extensions on the given component.
     *
     * @param component the given component
     */
    public static void removeAllExtensions(Component component) {
        for (Extension extension : component.getExtensions()) {
            component.removeExtension(extension);
        }
    }
}

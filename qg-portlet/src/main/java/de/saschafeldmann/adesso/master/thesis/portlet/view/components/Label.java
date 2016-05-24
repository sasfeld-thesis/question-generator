package de.saschafeldmann.adesso.master.thesis.portlet.view.components;

import com.vaadin.shared.ui.label.ContentMode;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  23.05.2016
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
 * A Vaadin label component.
 */
@Component
@Scope("prototype")
public class Label extends com.vaadin.ui.Label {

    /**
     * Creates a label.
     */
    public Label() {
        super();
    }

    /**
     * Creates a new label.
     * @param infoText the info text
     * @param mode the content mode
     */
    public Label(String infoText, ContentMode mode) {
        super(infoText, mode);
    }
}

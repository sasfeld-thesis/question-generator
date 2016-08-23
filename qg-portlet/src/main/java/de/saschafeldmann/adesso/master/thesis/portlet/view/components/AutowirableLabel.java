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
public class AutowirableLabel extends com.vaadin.ui.Label {

    /**
     * Creates a label.
     */
    public AutowirableLabel() {
        super();
    }

    /**
     * Creates a new label.
     * @param infoText the info text
     * @param mode the content mode
     */
    public AutowirableLabel(String infoText, ContentMode mode) {
        super(infoText, mode);
    }

    /**
     * Creates a new label with the given string to be displayed.
     * @param activationLabel
     */
    public AutowirableLabel(String activationLabel) {
        super(activationLabel);
    }
}

package de.saschafeldmann.adesso.master.thesis.portlet.view.components;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

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
 * A Vaadin button component.
 */
@Component
@Scope("prototype")
public class AutowirableButton extends com.vaadin.ui.Button {

    /**
     * Creates a simple button.
     */
    public AutowirableButton() {
        super();
    }

    /**
     * Creates a new button with the given label / caption.
     * @param label caption
     */
    public AutowirableButton(String label) {
        super(label);
    }
}

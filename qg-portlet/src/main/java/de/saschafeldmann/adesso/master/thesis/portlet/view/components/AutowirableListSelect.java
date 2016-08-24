package de.saschafeldmann.adesso.master.thesis.portlet.view.components;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

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
 * A Vaadin list select component.
 */
@Component
@Scope("prototype")
public class AutowirableListSelect extends com.vaadin.ui.ListSelect {

    @PostConstruct
    public void initialize() {
        // do not allow null selections per default
        this.setNullSelectionAllowed(false);
    }
}

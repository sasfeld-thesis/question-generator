package de.saschafeldmann.adesso.master.thesis.portlet.view.components;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;

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
 * An info box consists of a icon indicating the type of information (info, warning, error) and the info text.
 */
@org.springframework.stereotype.Component
@Scope("prototype")
public class InfoBox extends HorizontalLayout {
    private final Label infoLabel;

    @Autowired
    public InfoBox(final Label infoLabel) {
        this.infoLabel = infoLabel;
    }

    @PostConstruct
    public void reset() {
        this.removeAllComponents();

        this.addComponent(infoLabel);
    }

    /**
     * Sets the information to be displayed.
     * @param infoText
     */
    public void setInfoText(final String infoText) {
        this.infoLabel.setContentMode(ContentMode.HTML);
        this.infoLabel.setCaption(infoText);
    }


}

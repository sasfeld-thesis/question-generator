package de.saschafeldmann.adesso.master.thesis.portlet.view.components;

import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.vaadin.teemu.VaadinIcons;

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
     * @see Component#setCaption(String)
     */
    @Override
    public void setCaption(final String infoText) {
        this.infoLabel.setContentMode(ContentMode.HTML);
        this.infoLabel.setCaption(infoText);
    }

    /**
     * @see Component#setIcon(Resource)
     */
    @Override
    public void setIcon(final Resource icon) {
        this.infoLabel.setIcon(icon);
    }

    /**
     * Sets the info box as information.
     * An info icon will be drawn.
     */
    public void setInfo() {
        this.setIcon(VaadinIcons.INFO_CIRCLE);
    }

}

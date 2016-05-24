package de.saschafeldmann.adesso.master.thesis.portlet.view.components;

import com.vaadin.server.Resource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
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
    private static final String CSS_STYLE_NAME = "infobox";
    private Label iconLabel;
    private Label infoLabel;

    @PostConstruct
    public void reset() {
        this.removeAllComponents();

        this.addStyleName(CSS_STYLE_NAME);
    }

    /**
     * @see Component#setCaption(String)
     */
    @Override
    public void setCaption(final String infoText) {
        this.infoLabel = new Label(infoText, ContentMode.HTML);

        removeAndAdd();
    }

    private void removeAndAdd() {
        this.removeAllComponents();

        if (null != this.iconLabel) {
            addComponent(iconLabel);
        }

        if (null != this.infoLabel) {
            addComponent(infoLabel);
        }
    }

    /**
     * @see Component#setIcon(Resource)
     */
    @Override
    public void setIcon(final Resource icon) {
        this.iconLabel = new Label();
        this.iconLabel.setIcon(icon);

        removeAndAdd();
    }

    /**
     * Sets the info box as information.
     * An info icon will be drawn.
     */
    public void setInfo() {
        this.setIcon(VaadinIcons.INFO_CIRCLE);
    }

}

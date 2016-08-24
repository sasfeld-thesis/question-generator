package de.saschafeldmann.adesso.master.thesis.portlet.view.components;

import com.vaadin.ui.Label;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.VersionProperties;
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
 * A version label Vaadin component which displays the current build number and datetime.
 */
@Component
@Scope("prototype")
public class VersionLabel extends Label {

    /**
     * Initializes or resets the version label.
     */
    @PostConstruct
    public void reset() {
        // version label
        this.setCaption(VersionProperties.getBuildLabel());
    }
}

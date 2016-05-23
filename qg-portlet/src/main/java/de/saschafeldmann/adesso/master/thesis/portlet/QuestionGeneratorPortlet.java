package de.saschafeldmann.adesso.master.thesis.portlet;

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.VaadinProperties;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.VersionProperties;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  17.05.2016
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
 * This class defines the (vaadin) portlet. It is the single entry point for the portlet handling logic.
 */
@Theme(VaadinProperties.THEME)
public class QuestionGeneratorPortlet extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);
        Button button = new Button("Do not Click Me");
        button.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                layout.addComponent(
                        new Label("Thank you for clicking"));
            }
        });
        layout.addComponent(button);

        // version label
        final Label versionLabel = new Label();
        versionLabel.setCaption(VersionProperties.getBuildLabel());
        layout.addComponent(versionLabel);
    }
}

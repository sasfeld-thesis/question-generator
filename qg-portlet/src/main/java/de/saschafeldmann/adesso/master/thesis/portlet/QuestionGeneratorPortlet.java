package de.saschafeldmann.adesso.master.thesis.portlet;

import com.vaadin.server.VaadinPortlet;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.QuestionGeneratorProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.portlet.*;
import java.io.IOException;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  20.06.2016
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
 * This is the Portlet class as defined in the portlet.xml.
 * To add portlet initialization logic, extend the init() method here.
 */
public class QuestionGeneratorPortlet extends VaadinPortlet {
    public static final String PORTLET_SESSION_ATTRIBUTE_LANGUAGE = "org.apache.struts.action.LOCALE";

    private static final Logger LOGGER = LoggerFactory.getLogger(QuestionGeneratorPortlet.class);

    @Override
    public void init(PortletConfig config) throws PortletException {
        super.init(config);
    }
}

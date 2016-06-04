package de.saschafeldmann.adesso.master.thesis.portlet.properties.i18n;

import de.saschafeldmann.adesso.master.thesis.portlet.properties.QuestionGeneratorProperties;
import de.saschafeldmann.adesso.master.thesis.portlet.util.PropertiesReader;
import de.saschafeldmann.adesso.master.thesis.portlet.util.PropertiesReaderApi;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  04.06.2016
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
 * German message properties accessor.
 */
@Component
@Scope("singleton")
public class MessagesDeProperties extends PropertiesReader implements PropertiesReaderApi {
    private static final String PROPERTIES_FILE_NAME = "/i18n/messages_de.properties";

    /**
     * Create a new config reader which reads from a '.property' - file. <br />
     * A critical log entry will be executed if the given file doesn't exist or
     * isn't accessible.
     */
    public MessagesDeProperties() throws Exception {
        super(MessagesDeProperties.class.getResourceAsStream(PROPERTIES_FILE_NAME));
    }
}

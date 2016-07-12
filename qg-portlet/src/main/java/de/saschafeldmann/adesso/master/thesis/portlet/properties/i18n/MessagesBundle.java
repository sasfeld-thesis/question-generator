package de.saschafeldmann.adesso.master.thesis.portlet.properties.i18n;

import de.saschafeldmann.adesso.master.thesis.portlet.QuestionGeneratorPortletVaadinUi;
import de.saschafeldmann.adesso.master.thesis.util.properties.PropertiesReader;
import de.saschafeldmann.adesso.master.thesis.util.properties.PropertiesReaderApi;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

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
@Scope("prototype")
public class MessagesBundle implements PropertiesReaderApi {
    private static final String MESSAGE_PROPERTIES_BASE_NAME = "/i18n/messages";
    private final ResourceBundle messageProperties;

    /**
     * Create a new config reader which reads from a '.property' - file. <br />
     * A critical log entry will be executed if the given file doesn't exist or
     * isn't accessible.
     */
    public MessagesBundle() throws Exception {
        messageProperties = ResourceBundle.getBundle(MESSAGE_PROPERTIES_BASE_NAME, QuestionGeneratorPortletVaadinUi.getCurrentPortletVaadinUi().getCurrentLocale());
    }

    @Override
    public String fetchValue(String propertyKey) {
        return messageProperties.getString(propertyKey);
    }

    @Override
    public Map<String, String> fetchValues() {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<String> fetchMultipleValues(String propertyKey) {
        throw new UnsupportedOperationException();
    }

    @Override
    public <T> T getConfiguredClass(String classPropertyKey, Object... params) {
        throw new UnsupportedOperationException();
    }
}

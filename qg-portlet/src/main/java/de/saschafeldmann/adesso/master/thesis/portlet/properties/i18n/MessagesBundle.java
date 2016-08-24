package de.saschafeldmann.adesso.master.thesis.portlet.properties.i18n;

import de.saschafeldmann.adesso.master.thesis.util.properties.PropertiesReaderApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  04.06.2016
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
 * German message properties accessor.
 */
public class MessagesBundle implements PropertiesReaderApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessagesBundle.class);
    private static final String MESSAGE_PROPERTIES_BASE_NAME = "/i18n/messages";
    private ResourceBundle messageProperties;

    /**
     * Create a new config reader which reads from a '.property' - file. <br>
     * A critical log entry will be executed if the given file doesn't exist or
     * isn't accessible.
     * @param locale the user's locale to make use of the correct messages file
     */
    public MessagesBundle(final Locale locale) {
        if (null != locale) {
            messageProperties = ResourceBundle.getBundle(MESSAGE_PROPERTIES_BASE_NAME, locale);
        } else {
            messageProperties = ResourceBundle.getBundle(MESSAGE_PROPERTIES_BASE_NAME);
        }
    }

    @Override
    public String fetchValue(String propertyKey) {
        return toUtf8(messageProperties.getString(propertyKey));
    }

    private String toUtf8(String value) {
        try {
            // convert from ISO-8859-1 (ResourcesBundle implementation) to UTF-8
            return new String(value.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("toUtf8(): can't convert string '{}' to UTF-8 because of exception: \n{}", value, e);
            return value;
        }
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

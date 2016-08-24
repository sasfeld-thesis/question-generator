package de.saschafeldmann.adesso.master.thesis.portlet.util;

import de.saschafeldmann.adesso.master.thesis.portlet.properties.i18n.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  27.06.2016
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
 * [short description]
 */
@Component
@Scope("singleton")
public class TimeUtil {
    private Messages messages;

    /**
     * Creates a new time utility class.
     * @param messages
     */
    @Autowired
    public TimeUtil(Messages messages) {
        this.messages = messages;
    }

    /**
     * Builds a time entry to be shown to the user, e.g. "15:15 Uhr"
     *
     * @return String the current time entry
     */
    public String buildTimeEntryForUserLogs() {
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(messages.getLocaleTimeFormat());
        final String time = simpleDateFormat.format(new Date(System.currentTimeMillis()));

        final String localeTimePrefix = messages.getLocaleTimePrefix().trim();
        final String whitespace = " ";
        final String emptyString = "";
        final String localeTimeSuffix = messages.getLocaleTimeSuffix().trim();
        return localeTimePrefix + (localeTimePrefix.length() > 0 ? whitespace : emptyString) + time
                + (localeTimeSuffix.length() > 0 ? whitespace : emptyString) + localeTimeSuffix;

    }
}

package de.saschafeldmann.adesso.master.thesis.portlet.util;

import com.google.common.base.Joiner;
import com.google.common.base.Strings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  05.07.2016
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
 * Util for multiple comma-separated values that can be entered by a user.
 */
public class CommaseparatedValueUtil {
    private static final String SEPARATOR = ",";
    private static final Joiner COMMASEPARATED_VALUES_JOINER = Joiner.on(SEPARATOR).skipNulls();

    /**
     * Returns a string of comma-separated values for the given list of multiple values.
     * @param multipleValues the list
     * @return the string
     */
    public static String toCommaSeparatedString(final List<String> multipleValues) {
        // remove whitespace after ,
        return COMMASEPARATED_VALUES_JOINER.join(multipleValues).replaceAll(", ", ",");
    }

    /**
     * Returns a list of multi values.
     * @param listWithCommaseparatedValues the original comma-separated list of values
     * @return list of multi values
     */
    public static List<String> toMultipleValuesList(final String listWithCommaseparatedValues) {
        if (Strings.isNullOrEmpty(listWithCommaseparatedValues.trim())) {
            // returns the empty list
            return new ArrayList<>();
        }

        // remove whitespace after ,
        return Arrays.asList(listWithCommaseparatedValues.replaceAll(", ", ",").split(SEPARATOR));
    }
}

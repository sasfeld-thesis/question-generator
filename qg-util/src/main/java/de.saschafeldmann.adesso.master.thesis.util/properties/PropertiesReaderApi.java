package de.saschafeldmann.adesso.master.thesis.util.properties;

import java.util.List;
import java.util.Map;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  25.05.2016
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
 * Adapter to Java properties.
 * To use it for a special property file, implement a subclass and define the properties file.
 *
 * This class was taken from my own proect JumpUpMe, but completely written by myself.
 * https://github.com/JumpUpMe/jumpup_webapp/blob/master/src/main/java/de/htw/fb4/imi/jumpup/config/APropertiesConfigReader.java

 */
public interface PropertiesReaderApi {

    /**
     * Fetch the single value for a given property key.
     * @param propertyKey String
     * @return the value as String.
     * @throws IllegalArgumentException if the property doesn't exist in the configuration target.
     */
    String fetchValue(String propertyKey);

    /**
     * Fetch all config values from the target system.
     * @return a map with key - value - pairs. The key is the original config value from the target system.
     */
    Map<String, String> fetchValues();

    /**
     * <p>Fetch multiple values for a given property key.<br />
     * Multiple values are given when the config value points to a list of values.<br />
     * Example: test.1 = somevalue, test.2 = someothervalue <br />
     * fetchMultipleValues("test") will result in a list of String with entries "somevalue" and "someothervalue".</p>
     * @param propertyKey String
     * @return a list of values. The value list will contain at least 1 element.
     * @throws IllegalArgumentException
     */
    List< String > fetchMultipleValues(String propertyKey);

    /**
     * Get a new instance for the configured class using parameters.
     * The classPropertyKey must point to a property which contains a fully qualified class name.
     * @param classPropertyKey the property key
     * @param params several parameters to be given to the new instance
     * @return a new instance for the configured class.
     * @throws IllegalArgumentException if the property is invalid
     */
    public < T > T getConfiguredClass( String classPropertyKey, Object... params );
}


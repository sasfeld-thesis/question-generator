package de.saschafeldmann.adesso.master.thesis.portlet.util;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

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
 * Abstract adapter to a properties file.
 * To use it for a special property file, implement a subclass and define the properties file.
 * Make sure that all property files are UTF-8 encoded.
 *
 * This class was taken from my own proect JumpUpMe, but completely written by myself.
 * https://github.com/JumpUpMe/jumpup_webapp/blob/master/src/main/java/de/htw/fb4/imi/jumpup/config/APropertiesConfigReader.java
 */
public abstract class PropertiesReader implements PropertiesReaderApi {
    private static final Logger LOGGER = LoggerFactory.getLogger(Properties.class);
    /**
     * Defines the property files encodings. Make sure to use them correctly!
     */
    public static final String PROPERTY_FILES_ENCODING = "UTF-8";

    protected Properties properties;
    protected Map<String, String> propertiesMap;

    /**
     * Create a new config reader which reads from a '.property' - file. <br />
     * A critical log entry will be executed if the given file doesn't exist or
     * isn't accessible.
     *
     * @param inputFile the properties file
     */
    public PropertiesReader(File inputFile) {
        super();

        properties = new Properties();
        try {
            properties.load(new FileInputStream(inputFile));
        } catch (IOException e) { // log critical message
            LOGGER.error("PropertiesReader(): could not load properties - exception:\n{}",
                    ExceptionUtils.getStackTrace(e));
        }
    }
    /**
     * Create a new config reader which reads from a '.property' - input stream. <br />
     * A critical log entry will be executed if the given file doesn't exist or
     * isn't accessible.
     *
     * @param inputStream the input stream
     */
    public PropertiesReader(InputStream inputStream) throws Exception {
        super();

        properties = new Properties();
        try {
            // use UTF-8 encoding since the property files should be UTF-8 encoded
            properties.load(new InputStreamReader(inputStream, PROPERTY_FILES_ENCODING));
        } catch (Exception e) { // log critical message
            LOGGER.error("PropertiesReader(): could not load properties - exception:\n{}",
                    ExceptionUtils.getStackTrace(e));

            throw e; // make sure to throw exception: the properties are required to run the portlet
        }
    }


    public String fetchValue(String property) {
        String value = this.properties.getProperty(property.trim()
                .toLowerCase());

        if (null == value) {
            throw new IllegalArgumentException(
                    "PropertiesReader::fetchValue(): No value found for the given property '"
                            + property + "'.");
        }
        return value;
    }


    public Map<String, String> fetchValues() {
        if (null == this.propertiesMap) { // be lazy
            this.propertiesMap = new HashMap<String, String>();

            // fill properties map
            for (Object key : properties.keySet()) {
                Object property = properties.get(key);
                if (key instanceof String && property instanceof String) {
                    propertiesMap.put((String) key, (String) property);
                } else {
                    LOGGER.error("\"ConfigReader::fetchValues(): the properties keys or values are not strings.}");
                }
            }
        }

        return this.propertiesMap;
    }


    public List<String> fetchMultipleValues(String propertyKey) {
        List<String> returnValues = new ArrayList<String>();
        Map<String, String> propertiesMap = this.fetchValues();

        /*
         * Check for properties with the format: a.b.c.d = property1 a.b.c.d.1 =
         * property2 a.b.c.d.2 = property3
         *
         * An example input to get a list of the properties 1 - 3 would be:
         * a.b.c.d
         */
        for (String key : propertiesMap.keySet()) {
            // check if the propertyKey is the beginning of the one in the map
            // and the one in the map is followed by other characters (such as
            // ".anotherconfigkey")
            if (key.startsWith(propertyKey)
                    && key.substring(propertyKey.length(), key.length())
                    .length() > 0) {
                returnValues.add(propertiesMap.get(key));
            }
        }

        /*
         * Otherwise, check for properties with the format: a.b.c.d =
         * property1,property2,property3
         *
         * The result would also be a list of properties 1 - 3.
         */
        // if the list is empty the config key doesn't point to a list of values
        if (0 == returnValues.size()) {
            // maybe the config key is single and the values are comma-separated
            if (propertiesMap.containsKey(propertyKey)) {
                String property = propertiesMap.get(propertyKey);
                String[] properties = property.split(",");
                returnValues.addAll(Arrays.asList(properties));
            } else {
                throw new IllegalArgumentException(
                        getClass()
                                + ":fetchMultipleValues: the given config key ("
                                + propertyKey
                                + ") doesn't point to a list of values.");
            }
        }

        return returnValues;
    }

    public <T> T getConfiguredClass(String classPropertyKey, Object... params) {
        String classProperty = this.fetchValue(classPropertyKey);
        try { // try to instanciate an instance from the property value
            Class<?> className = Class.forName(classProperty);

            // fetch parameters' classes and hand it to the reflection method
            // getConstructor()
            Class<?>[] parameterTypes = new Class<?>[params.length];
            for (int i = 0; i < params.length; i++) {
                parameterTypes[i] = params[i].getClass();
            }
            Constructor<?> constructor = className
                    .getConstructor(parameterTypes);

            // try to create the given instance: a class cast exception will be
            // thrown in the case of no success
            @SuppressWarnings("unchecked")
            T strategyObject = (T) constructor.newInstance(params);
            return strategyObject;
        } catch (ClassNotFoundException e) {
            String errorMessage = "getConfiguredClass(): the instanciation of the given configured class (given value: "
                    + classPropertyKey
                    + ") was not successfull. Either it doesn't exist or doesn't extend the given class. Or the constructor raises an exception. Full exception: \n"
                    + e;
            throw new IllegalArgumentException(errorMessage);
        } catch (NoSuchMethodException e) {
            String errorMessage = "getConfiguredClass(): the instanciation of the given configured class (given value: "
                    + classPropertyKey
                    + ") was not successfull. Either it doesn't exist or doesn't extend the given class. Or the constructor raises an exception. Full exception: \n"
                    + e;
            throw new IllegalArgumentException(errorMessage);
        } catch (SecurityException e) {
            String errorMessage = "getConfiguredClass(): the instanciation of the given configured class (given value: "
                    + classPropertyKey
                    + ") was not successfull. Either it doesn't exist or doesn't extend the given class. Or the constructor raises an exception. Full exception: \n"
                    + e;
            throw new IllegalArgumentException(errorMessage);
        } catch (InstantiationException e) {
            String errorMessage = "getConfiguredClass(): the instanciation of the given configured class (given value: "
                    + classPropertyKey
                    + ") was not successfull. Either it doesn't exist or doesn't extend the given class. Or the constructor raises an exception. Full exception: \n"
                    + e;
            throw new IllegalArgumentException(errorMessage);
        } catch (IllegalAccessException e) {
            String errorMessage = "getConfiguredClass(): the instanciation of the given configured class (given value: "
                    + classPropertyKey
                    + ") was not successfull. Either it doesn't exist or doesn't extend the given class. Or the constructor raises an exception. Full exception: \n"
                    + e;
            throw new IllegalArgumentException(errorMessage);
        } catch (IllegalArgumentException e) {
            String errorMessage = "getConfiguredClass(): the instanciation of the given configured class (given value: "
                    + classPropertyKey
                    + ") was not successfull. Either it doesn't exist or doesn't extend the given class. Or the constructor raises an exception. Full exception: \n"
                    + e;
            throw new IllegalArgumentException(errorMessage);
        } catch (InvocationTargetException e) {
            String errorMessage = "getConfiguredClass(): the instanciation of the given configured class (given value: "
                    + classPropertyKey
                    + ") was not successfull. Either it doesn't exist or doesn't extend the given class. Or the constructor raises an exception. Full exception: \n"
                    + e;
            throw new IllegalArgumentException(errorMessage);
        } catch (ClassCastException e) {
            String errorMessage = "getConfiguredClass(): the instanciation of the given configured class (given value: "
                    + classPropertyKey
                    + ") was not successfull. Either it doesn't exist or doesn't extend the given class. Or the constructor raises an exception. Full exception: \n"
                    + e;
            throw new IllegalArgumentException(errorMessage);
        }
    }

    /**
     * Check if the value for the given config key is 'enabled'.
     *
     * @param configKey String
     * @return boolean
     */
    public boolean isValueEnabled(final String configKey) {
        if ("enabled".equals(this.fetchValue(configKey).trim().toLowerCase())) {
            return true;
        }

        return false;
    }
}

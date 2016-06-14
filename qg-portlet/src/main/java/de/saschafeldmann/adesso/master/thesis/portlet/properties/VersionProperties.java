
package de.saschafeldmann.adesso.master.thesis.portlet.properties;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  23.05.2016
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
 * Connector to the version.porperties file filled during build.
 */
public class VersionProperties {
    private static final String PROPERTY_QUESTION_GENERATOR_VERSION = "question_generator.version";
    private static final java.lang.String PROPERTY_QUESTION_GENERATOR_BUILD_TIME = "question_generator.build_date";
    private static final String PATH_TO_VERSION_PROPERTIES = "/build/version.properties";
    private static final Logger LOGGER = LoggerFactory.getLogger(VersionProperties.class);

    private static final Properties versionProperties;

    static {
        versionProperties = new Properties();
        InputStream in = VersionProperties.class.getResourceAsStream(PATH_TO_VERSION_PROPERTIES);

        LOGGER.info("Initializing version properties...");
        try {
            if (null != in) {
                versionProperties.load(in);
            } else {
                LOGGER.error("Could not load version properties since the file doesn't exist: {}", PATH_TO_VERSION_PROPERTIES);
            }
        } catch (Exception e) {
            LOGGER.error("Could not load version properties: {}\n{}",
                    e.getMessage(),
                    ExceptionUtils.getStackTrace(e));
        } finally {
            try {
                in.close();
            } catch (Exception e) { // ignore
            }
        }
    }

    /**
     * Gets the build version string.
     * @return the version or null if not available
     */
    public static String getVersion() {
        return versionProperties.getProperty(PROPERTY_QUESTION_GENERATOR_VERSION);
    }

    /**
     * Get the build timestamp.
     * @return the build timestamp
     */
    public static String getBuildTime() {
        return versionProperties.getProperty(PROPERTY_QUESTION_GENERATOR_BUILD_TIME) + " UTC";
    }

    /**
     * Get the completed build label.
     * @return String the build label.
     */
    public static String getBuildLabel() {
        return "Build: " + (null == getVersion() ? "" : getVersion()) + " @ "
                + (null == getBuildTime() ? "" : getBuildTime());
    }

}
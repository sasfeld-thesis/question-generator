package de.saschafeldmann.adesso.master.thesis.elearningimport.parser;

import static com.google.common.base.Preconditions.*;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.sax.BodyContentHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import java.io.*;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  28.05.2016
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
 * A {@link RawtextParserAdapter} to the Apache Tika library.
 */
@Component
@Scope("singleton")
public class TikaRawtextParserAdapter implements RawtextParserAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(TikaRawtextParserAdapter.class);

    @Override
    public String extractRawtext(final File file) throws ParserException {
        checkNotNull(file, "File must not be null.");

        AutoDetectParser autoDetectParser = new AutoDetectParser();
        BodyContentHandler bodyContentHandler = new BodyContentHandler();
        Metadata metadata = new Metadata();

        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            autoDetectParser.parse(inputStream, bodyContentHandler, metadata);

            return bodyContentHandler.toString();
        } catch (SAXException | IOException | TikaException e ) {
            LOGGER.error("extractRawtext(): could not extract raw text from file {} due to exception:\n{}",
                    file.getAbsolutePath(), e);
            throw new ParserException(e);
        } finally {
            // cleanly close the resources
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (Exception e) {
                    // ignore
                }
            }
        }
    }
}

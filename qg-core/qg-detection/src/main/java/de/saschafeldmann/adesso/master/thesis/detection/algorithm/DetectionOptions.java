package de.saschafeldmann.adesso.master.thesis.detection.algorithm;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  29.06.2016
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
 * Options to be handed to {@link DetectionAlgorithm} implementations in their execute() method to ensure thread-safetyness.
 */
@Component
@Scope("prototype")
public class DetectionOptions {
}

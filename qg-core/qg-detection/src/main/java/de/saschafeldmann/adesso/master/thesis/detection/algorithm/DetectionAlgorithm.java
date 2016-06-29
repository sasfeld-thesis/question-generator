package de.saschafeldmann.adesso.master.thesis.detection.algorithm;

import de.saschafeldmann.adesso.master.thesis.detection.model.api.Concept;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;

import java.util.List;

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
 * Implementations of the detection algorithm are able to identify their special concepts and build {@link de.saschafeldmann.adesso.master.thesis.detection.model.api.Concept}
 * instances as output.
 */
public interface DetectionAlgorithm<ConceptType extends Concept> {

    /**
     * Executes the algorithm to detect the special ConceptType (a {@link Concept}) within the given learningContent.
     * Therefore, use the part-of-speech and named-entity-annotated texts within the {@link LearningContent}.
     * @param learningContent the learning content for which the ConceptType shall be detected
     * @param detectionOptions options to be thread-safe
     * @return a list of detected concepts in the given {@link LearningContent}.
     */
    public List<ConceptType> execute(final LearningContent learningContent, final DetectionOptions detectionOptions);
}

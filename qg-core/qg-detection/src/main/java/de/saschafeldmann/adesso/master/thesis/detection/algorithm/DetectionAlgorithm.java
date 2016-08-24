package de.saschafeldmann.adesso.master.thesis.detection.algorithm;

import de.saschafeldmann.adesso.master.thesis.detection.model.api.Concept;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;

import java.util.List;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  29.06.2016
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
 * Implementations of the detection algorithm are able to identify their special concepts and build {@link de.saschafeldmann.adesso.master.thesis.detection.model.api.Concept}
 * instances as output.<br>
 * Conceps are detected upon previously filled {@link LearningContent}. <br>
 * A concept also fulfills the definition of a text plan as introduced by [Reiter] in their book on Natural Language Generation.<br>
 * A concept is the first step to create questions since they structure information detected within the unstructured natural language processing annotated {@link LearningContent}.<br>
 * Following [Reiter] in his proposal of a natural language generation system, a concept is the result of the document planner which
 * did the tasks of the content determination ("what information should be communicated?").<br>
 * See: Reiter, E., Dale, R., &amp; Feng, Z. (2000). Building natural language generation systems (Vol. 33). Cambridge: Cambridge university press.
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

package de.saschafeldmann.adesso.master.thesis.detection.algorithm;

import de.saschafeldmann.adesso.master.thesis.detection.model.FillTextConcept;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;

import java.util.ArrayList;
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
 * Filltext concept detection.<br />
 * Filltexts are simple concepts. <br />
 * Those are sentences that contain an object that is a named entity and at least one more noun.<br />
 * For the question generation part, the learning student will be asked to fill the object.<br />
 * E.g. if the original sentence was "Berlin is the capital of Germany."
 * the fill text will be "Berlin is the capital of ___?"
 */
public class FillTextConceptDetection implements DetectionAlgorithm<FillTextConcept> {


    @Override
    public List<FillTextConcept> execute(final LearningContent learningContent, final DetectionOptions detectionOptions) {
        List<String> sentencesWithNamedEntityAndAdditionalNoun = findSentencesWithNamedEntitiesAndAnAdditionalNoun(learningContent);
        return null;
    }

    private List<String> findSentencesWithNamedEntitiesAndAnAdditionalNoun(LearningContent learningContent) {
        final List<String> foundSentencesList = new ArrayList<>();



        return foundSentencesList;
    }
}

package de.saschafeldmann.adesso.master.thesis.detection.util;

import static org.junit.Assert.*;

import de.saschafeldmann.adesso.master.thesis.detection.model.FillInTheBlankTextConcept;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;

import de.saschafeldmann.adesso.master.thesis.util.ListUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  01.08.2016
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
 * Test of {@link ListUtil}
 */
public class ListUtilTest {

    @Test
    public void testReduceListReturnsOriginalListIfSizeIsGreaterThanGivenOne() {
        // given
        final List<FillInTheBlankTextConcept> concepts = newListOfConceptOfSize(5);
        final int maximumNumberOfElements = 10;

        // when utility is called
        final List<FillInTheBlankTextConcept> resultingList = ListUtil.<FillInTheBlankTextConcept> reduceListToMaximumOfElements(concepts, maximumNumberOfElements);

        // then make sure it is equal to original one
        assertEquals(resultingList, concepts);
    }

    @Test
    public void testReduceListReturnsSmallerList() {
        // given
        final List<FillInTheBlankTextConcept> concepts = newListOfConceptOfSize(10);
        final int maximumNumberOfElements = 5;

        // when utility is called
        final List<FillInTheBlankTextConcept> resultingList = ListUtil.<FillInTheBlankTextConcept> reduceListToMaximumOfElements(concepts, maximumNumberOfElements);

        // then make sure the list consists of five elements only
        assertEquals(5, resultingList.size());
        assertEquals(resultingList.get(0), concepts.get(0));
        assertEquals(resultingList.get(1), concepts.get(2));
        assertEquals(resultingList.get(2), concepts.get(4));
        assertEquals(resultingList.get(3), concepts.get(6));
        assertEquals(resultingList.get(4), concepts.get(8));
    }

    private List<FillInTheBlankTextConcept> newListOfConceptOfSize(int size) {
        final List<FillInTheBlankTextConcept> list = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            list.add(newConcept(i));
        }

        return list;
    }

    private FillInTheBlankTextConcept newConcept(int i) {
        return new FillInTheBlankTextConcept.FillTextConceptBuilder()
                .withCorrectAnswer("test" + i)
                .withFillSentence("test" + i)
                .withLearningContent(newLearningContent())
                .withOriginalSentence("test" + i)
                .build();
    }

    private LearningContent newLearningContent() {
        return new LearningContent.LearningContentBuilder()
                .withTitle("test")
                .withType(LearningContent.Type.DIRECT_RAWTEXT)
                .withRawText("test")
                .build();
    }
}

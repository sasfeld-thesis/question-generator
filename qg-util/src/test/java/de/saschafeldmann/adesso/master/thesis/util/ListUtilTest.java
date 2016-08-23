package de.saschafeldmann.adesso.master.thesis.util;

import org.junit.Test;

import static org.junit.Assert.*;
import java.util.Arrays;
import java.util.List;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  23.08.2016
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
 * Test of {@link ListUtil} class.
 */
public class ListUtilTest {
    @Test
    public void testReduceListToMaximumElementsWithUniformDistribution() {
        // given a list with 10 elements
        Integer[] test = {1,2,3,4,5,6,7,8,9,10};
        List<Integer> testList = Arrays.asList(test);

        // when list size should be reduce to 2
        List<Integer> newList = ListUtil.reduceListToMaximumOfElements(testList, 2);

        // then only the following elements shouldbe contained
        assertEquals(2, newList.size());
        assertTrue("First element should be the value 1", newList.get(0) == 1);
        assertTrue("Second element should be the value 6", newList.get(1) == 6);
    }

    @Test
    public void testReduceListToMaximumElementsReturnsCompleteListIfSizeIsLessThan() {
        // given a list with 10 elements
        Integer[] test = {1,2,3,4,5,6,7,8,9,10};
        List<Integer> testList = Arrays.asList(test);

        // when list size should be reduce to 15
        List<Integer> newList = ListUtil.reduceListToMaximumOfElements(testList, 15);

        // then the list size should have remained
        assertEquals(10, newList.size());
    }
}

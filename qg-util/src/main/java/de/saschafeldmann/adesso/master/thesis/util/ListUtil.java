package de.saschafeldmann.adesso.master.thesis.util;

import java.util.ArrayList;
import java.util.List;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  01.08.2016
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
 * Util to work with lists
 */
public class ListUtil {

    /**
     * Reduces the given list to have a maximum of maximumNumberOfElements elements.<br>
     * Does a uniform distribution instead of just removing elements in sequence.
     * @param list the list
     * @param maximumNumberOfElements the maximum number of elements
     * @return the list with uniform distribution of elements so that maximumNumberOfElements is not passed
     */
    public static <T> List<T>reduceListToMaximumOfElements(final List<T> list, final int maximumNumberOfElements) {
        if (maximumNumberOfElements >= list.size()) {
            // the maximum number of elements is greater than given one, so return original complete list
            return list;
        } else {
            // the maximum number of elements is less than the given one, so only return each nth element
            List<T> newList = new ArrayList<>();

            int addEachNthPosition = (int) Math.floor((double) list.size() / (double) maximumNumberOfElements);

            int numberAdded = 0;
            for (int i=0; i + addEachNthPosition <= list.size() && numberAdded < maximumNumberOfElements; i += addEachNthPosition) {
                newList.add(list.get(i));
                numberAdded++;
            }

            return newList;
        }
    }
}

package de.saschafeldmann.adesso.master.thesis.detection.util;

import de.saschafeldmann.adesso.master.thesis.detection.model.api.Concept;

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
 * Util to work with lists of {@link de.saschafeldmann.adesso.master.thesis.detection.model.api.Concept}
 */
public class ListUtil {

    /**
     * Reduces the given list to have a maximum of maximumNumberOfElements elements.<br />
     * Does a uniform distribution instead of just removing elements in sequence.
     * @param concepts the concepts
     * @param maximumNumberOfElements the maximum number of elements
     * @return the list with uniform distribution of elements so that maximumNumberOfElements is not passed
     */
    public static <T extends Concept> List<T>reduceListToMaximumOfElements(final List<T> concepts, final int maximumNumberOfElements) {
        if (maximumNumberOfElements >= concepts.size()) {
            // the maximum number of elements is greater than given one, so return original complete list
            return concepts;
        } else {
            // the maximum number of elements is less than the given one, so only return each nth element
            List<T> newList = new ArrayList<>();

            int addEachNthPosition = (int) Math.floor(concepts.size() / maximumNumberOfElements);

            for (int i=0; i + addEachNthPosition <= concepts.size(); i += addEachNthPosition) {
                newList.add(concepts.get(i));
            }

            return newList;
        }
    }
}

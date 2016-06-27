package de.saschafeldmann.adesso.master.thesis.portlet.util;

import com.google.common.base.Predicate;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  27.06.2016
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
 * [short description]
 */
public class FilterUtil {
    /**
     * Filters deleted annotated learning contents.
     */
    public static final Predicate<LearningContent> FILTER_DELETED_ANNOTATED_TEXTS_PREDICATE =
            new Predicate<LearningContent>() {
                @Override
                public boolean apply(LearningContent learningContent) {
                    return learningContent.hasAnnotatedText();
                }
            };
    /**
     * Filters not deleted annotated learning contents.
     */
    public static final Predicate<LearningContent> FILTER_NOT_DELETED_ANNOTATED_TEXTS_PREDICATE =
            new Predicate<LearningContent>() {
                @Override
                public boolean apply(LearningContent learningContent) {
                    return !learningContent.hasAnnotatedText();
                }
            };
}

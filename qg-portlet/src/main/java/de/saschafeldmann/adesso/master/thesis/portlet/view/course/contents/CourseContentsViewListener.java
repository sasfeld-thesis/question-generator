package de.saschafeldmann.adesso.master.thesis.portlet.view.course.contents;

import de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent;

import java.io.File;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  24.05.2016
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
 * Course contents view listener implement methods to react on user-triggered actions within the {@link CourseContentsView}.
 */
public interface CourseContentsViewListener {
    /**
     * Triggered after the user uploaded a content file.
     * @param contentFile the file
     */
    void onContentFileUploaded(final File contentFile);

    /**
     * Triggered if a content file was selected and its content was changed.
     * @param learningContent the associated {@link LearningContent} of {@link de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent.Type} FILE
     */
    void onContentFileChangeClick(final LearningContent learningContent);

    /**
     * Triggered if a content file was selected an delete was pressed.
     * @param learningContent the associated {@link LearningContent} of {@link de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent.Type} FILE
     */
    void onContentFileDeleteClick(final LearningContent learningContent);

    /**
     * Triggered if a raw text was selected and changed.
     * @param learningContent the associated {@link LearningContent} of {@link de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent.Type} DIRECT_RAWTEXT
     */
    void onContentRawTextChangeClick(final LearningContent learningContent);

    /**
     * Triggered if a raw text was selected and deleted.
     * @param learningContent the associated {@link LearningContent} of {@link de.saschafeldmann.adesso.master.thesis.elearningimport.model.LearningContent.Type} DIRECT_RAWTEXT
     */
    void onContentRawTextDeleteClick(final LearningContent learningContent);

    /**
     * On next button clicked action.
     */
    void onNextButtonClicked();

    /**
     * Triggered if the button "Add" at the raw text section was clicked.
     */
    void onContentRawTextAddClick();
}

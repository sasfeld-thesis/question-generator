package de.saschafeldmann.adesso.master.thesis.portlet.view.detection.edit;

import com.vaadin.ui.Window;
import de.saschafeldmann.adesso.master.thesis.detection.util.DetectionProperties;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.QuestionGeneratorProperties;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  12.07.2016
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
 * Abstract for all specific {@link de.saschafeldmann.adesso.master.thesis.detection.model.api.Concept} views.
 */
public class AbstractDetectionEditConceptView extends Window {
    private static final String CSS_STYLE_NAME_EDIT_CONCEPT_VIEW = "edit-concept-window";
    private final QuestionGeneratorProperties questionGeneratorProperties;

    /**
     * Create a new abstract view with the given properties.
     * @param questionGeneratorProperties the properties
     */
    public AbstractDetectionEditConceptView(final QuestionGeneratorProperties questionGeneratorProperties) {
        this.questionGeneratorProperties = questionGeneratorProperties;
    }

    protected void initializeView() {
        this.setPosition(questionGeneratorProperties.getConceptDetectionEditWindowPositionX(), questionGeneratorProperties.getConceptDetectionEditWindowPositionY());

        addStyleName(CSS_STYLE_NAME_EDIT_CONCEPT_VIEW);
    }
}

package de.saschafeldmann.adesso.master.thesis.portlet.view.components.upload;

import com.vaadin.ui.Upload;

import java.io.File;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  25.05.2016
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
 * Interface for listeners on {@link FileUpload} components.
 */
public interface FileUploadListener {
    /**
     * The upload failed.
     */
    void uploadFailed();

    /**
     * The upload succeeded and is represented by the given file.
     * @param file the uploaded file.
     */
    void uploadSucceeded(File file);
}

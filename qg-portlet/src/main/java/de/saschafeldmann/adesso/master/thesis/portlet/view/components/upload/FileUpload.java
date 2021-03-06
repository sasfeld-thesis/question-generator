package de.saschafeldmann.adesso.master.thesis.portlet.view.components.upload;

import de.saschafeldmann.adesso.master.thesis.portlet.properties.QuestionGeneratorProperties;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.AutowirableProgressBar;
import com.vaadin.ui.Upload;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.net.MalformedURLException;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  24.05.2016
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
 * A Vaadin file upload component.
 * <p>
 * This component contains the file receiver as well and hides the receiving and progress logic.
 *
 * Since it consists of a {@link AutowirableProgressBar}, you need to call getProgressBar() to include it in the view which contains the file upload
 * component.
 *
 * Attention: the file upload uses the method createTempFile() to create the upload file.
 * This means, the usage of it is restricted to be directly processed.
 * If you want to store the file permanently, you need to copy it somewhere else.
 */
@Component
@Scope("prototype")
public class FileUpload extends com.vaadin.ui.Upload implements Upload.Receiver, Upload.SucceededListener, Upload.ProgressListener, Upload.FailedListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(FileUpload.class);

    private File uploadFile;
    private FileUploadListener fileUploadListener;

    private final AutowirableProgressBar progressBar;
    private final QuestionGeneratorProperties questionGeneratorProperties;

    /**
     * Creates a file upload component.
     */
    @Autowired
    public FileUpload(final AutowirableProgressBar progressBar, final QuestionGeneratorProperties questionGeneratorProperties) {
        super();

        this.progressBar = progressBar;
        this.questionGeneratorProperties = questionGeneratorProperties;
    }

    @PostConstruct
    private void initialize() {
        // the component also handles the receiving of the file
        super.setReceiver(this);
        // and the progress handling
        super.addProgressListener(this);
        // and the succeeded event of Vaadin
        super.addSucceededListener(this);
        // and the upload failed listener
        super.addFailedListener(this);
    }

    /**
     * Sets the file upload listenr.
     * @param fileUploadListener the file upload listener which is notified
     */
    public void setFileUploadListener(FileUploadListener fileUploadListener) {
        this.fileUploadListener = fileUploadListener;
    }

    /**
     * Gets the progress bar component.
     * The file upload component will change the progress value during upload.
     * For sure, you can adjust the components look as you want.
     * @return the progress bar
     */
    public AutowirableProgressBar getProgressBar() {
        return progressBar;
    }

    /**
     * Handles the file receiving.
     *
     * @see com.vaadin.ui.Upload.Receiver#receiveUpload(String, String)
     */
    public OutputStream receiveUpload(String fileName, String mimeType) {
        // Create the upload stream
        FileOutputStream fileOutputStream = null;

        try {
            // create a temporarily file
            uploadFile = new File(getTempFolder(), fileName);
            fileOutputStream = new FileOutputStream(uploadFile);
        } catch (IOException e) {
            LOGGER.error("receiveUpload(): could not upload file {} due to an exception {}:\n{}",
                    fileName, e.getMessage(), ExceptionUtils.getStackTrace(e));
            fileUploadListener.uploadFailed();
            return null;
        }

        return fileOutputStream;
    }


    /**
     * @see com.vaadin.ui.Upload.SucceededListener#uploadSucceeded(SucceededEvent)
     */
    public void uploadSucceeded(SucceededEvent succeededEvent) {
        fileUploadListener.uploadSucceeded(uploadFile); // notifies the listener
    }

    /**
     * @see com.vaadin.ui.Upload.ProgressListener#updateProgress(long, long)
     * @param readBytes bytes transfereed
     * @param contentLength  total size of file currently being uploaded, -1 if unknown
     */
    public void updateProgress(long readBytes, long contentLength) {
        if (-1 == contentLength) {
            // since the overall content length is not known, set the progress bar to indeterminate
            this.progressBar.setIndeterminate(true);
        } else {
            // the progress is the ratio of transfered bytes and the overall content length if known
            this.progressBar.setIndeterminate(false);
            this.progressBar.setValue((float) readBytes / (float) contentLength);
        }
    }

    private String getTempFileFolderFromProperty() {
        return questionGeneratorProperties.getFileTempFolder();
    }

    /**
     * @see com.vaadin.ui.Upload.FailedListener#uploadFailed(FailedEvent)
     */
    public void uploadFailed(FailedEvent failedEvent) {
        LOGGER.error("uploadFailed(): could not upload file {} due to an exception :\n{}",
                failedEvent.getFilename(), failedEvent);

        fileUploadListener.uploadFailed();
    }

    /**
     * Gets the temp folder as file.
     * @return File the temp folder
     */
    private File getTempFolder() throws MalformedURLException {
        LOGGER.info("getTempFolder(): current CWD {}", System.getProperty("user.dir"));
        return new File(getTempFileFolderFromProperty());
    }
}

package de.saschafeldmann.adesso.master.thesis.portlet.model;

import com.google.common.base.Strings;
import de.saschafeldmann.adesso.master.thesis.elearningimport.model.Course;
import de.saschafeldmann.adesso.master.thesis.portlet.QuestionGeneratorPortletVaadinUi;

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
 * Session-scoped bean that is valid for the whole user session.
 * It is a wrapper around a {@link Course} model and has a clear {@link Status}.
 */
public class QuestionGenerationSession {
    /**
     * Session status enumeration.
     */
    public enum Status {
        /**
         * The session was just started, no course information was entered yet.
         */
        STARTED(1),
        /**
         * The course information was just added, but no contents were added.
         */
        BASIC_INFORMATION_ADDED(2),
        /**
         * The course contents were added, but no preprocesses done yet.
         */
        CONTENTS_ADDED(3),
        /**
         * The linguistic preprocesses were done, but no detection was started yet.
         */
        PREPROCESSES_DONE(4),
        /**
         * The detection was done, but no questions were generated yet.
         */
        DETECTION_DONE(5),
        /**
         * The final questions were generated.
         */
        QUESTIONS_GENERATED(6);

        private final Integer statusSequenceNumber;

        /**
         * Create a status.
         * The status has a sequence number to define the sequence of status.
         * @param statusSequenceNumber the sequence number of this status
         */
        Status(final Integer statusSequenceNumber) {
            this.statusSequenceNumber = statusSequenceNumber;
        }

        /**
         * Gets the sequence number of this status in the processing.
         * @return the sequence number
         */
        public Integer getSequenceNumber() {
            return statusSequenceNumber;
        }
    }

    private Course course;
    private Status status = Status.STARTED;

    /**
     * Sets the course that the user works on in this session.
     * @param course the {@link Course}
     */
    public void setCourse(Course course) {
        this.course = course;
    }

    /**
     * Gets the course that the user works on in this session.
     * @return the {@link Course}
     */
    public Course getCourse() {
        return course;
    }

    /**
     * Sets the session status.
     * @param status the {@link Status}
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * Gets the session status.
     * @return status the {@link Status}
     */
    public Status getStatus() {
        return status;
    }
}

package de.saschafeldmann.adesso.master.thesis.portlet.model.detection;

import de.saschafeldmann.adesso.master.thesis.detection.algorithm.DetectionAlgorithm;
import de.saschafeldmann.adesso.master.thesis.portlet.model.preprocesses.ProcessActivationElement;
import de.saschafeldmann.adesso.master.thesis.portlet.model.preprocesses.ProcessActivationStateChangeListener;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.i18n.Messages;
import de.saschafeldmann.adesso.master.thesis.preprocesses.algorithm.PreprocessingAlgorithm;

import static com.google.common.base.Preconditions.checkNotNull;

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
 * Model to connect a concept detection strategy from the qg-detection module with a view element to en- or disable it.
 */
public class DetectionActivationElement {
    public enum ActivationOptionGroupItem {
        /**
         * Activates the process.
         */
        YES("de.saschafeldmann.adesso.master.thesis.portlet.preprocesses.view.accordion.activation.optiongroup.yes.label"),
        /**
         * Deactivates the process.
         */
        NO("de.saschafeldmann.adesso.master.thesis.portlet.preprocesses.view.accordion.activation.optiongroup.no.label");


        private final String displayLabelMessageKey;

        /**
         * Creates a new group item enum.
         *
         * @param displayLabelMessageKey the message key to the label that is shown to the user.
         */
        ActivationOptionGroupItem(String displayLabelMessageKey) {
            this.displayLabelMessageKey = displayLabelMessageKey;
        }

        /**
         * Gets the the message key to the label that is shown to the user.
         * @return String
         */
        public String getDisplayLabelMessageKey() {
            return displayLabelMessageKey;
        }

        @Override
        public String toString() {
            return Messages.getInstance().get(displayLabelMessageKey);
        }
    };

    /**
     * Class for the connection between {@link ProcessActivationElement} and {@link ProcessActivationElement.ActivationOptionGroupItem}.
     */
    public class DetectionActivationElementState {
        private DetectionActivationElement parentProcessActivationElement;
        private DetectionActivationElement.ActivationOptionGroupItem activationOptionGroupItem;

        private DetectionActivationElementState(DetectionActivationElement parentProcessActivationElement, DetectionActivationElement.ActivationOptionGroupItem activationOptionGroupItem) {
            this.parentProcessActivationElement = parentProcessActivationElement;
            this.activationOptionGroupItem = activationOptionGroupItem;
        }

        /**
         * Gets the process activation element.
         * @return
         */
        public DetectionActivationElement getParentProcessActivationElement() {
            return parentProcessActivationElement;
        }

        /**
         * Gets the option group item selected by the user.
         * @return the state
         */
        public DetectionActivationElement.ActivationOptionGroupItem getActivationOptionGroupItem() {
            return activationOptionGroupItem;
        }

        @Override
        public String toString() {
            return getActivationOptionGroupItem().toString();
        }
    }

    private final String activationLabel;
    private final Boolean isActivatedPerDefault;
    private final DetectionAlgorithm processAlgorithm;
    private final String tooltip;
    private final String startedLogEntry;
    private final String finishedLogEntry;
    private final DetectionActivationElement.DetectionActivationElementState detectionActivationElementStateActivated;
    private final DetectionActivationElement.DetectionActivationElementState detectionActivationElementStateDeactivated;
    private final DetectionActivationStateChangeListener stateChangeListener;
    private DetectionActivationElementState detectionActivationElementState;

    private DetectionActivationElement(DetectionActivationElement.DetectionActivationElementBuilder processActivationElementBuilder) {
        this.activationLabel = processActivationElementBuilder.activationLabel;
        this.isActivatedPerDefault = processActivationElementBuilder.isActivatedPerDefault;
        this.processAlgorithm = processActivationElementBuilder.processAlgorithm;
        this.tooltip = processActivationElementBuilder.tooltip;
        this.startedLogEntry = processActivationElementBuilder.startedLogEntry;
        this.finishedLogEntry = processActivationElementBuilder.finishedLogEntry;
        this.detectionActivationElementStateActivated = new DetectionActivationElementState(this, DetectionActivationElement.ActivationOptionGroupItem.YES);
        this.detectionActivationElementStateDeactivated = new DetectionActivationElementState(this, DetectionActivationElement.ActivationOptionGroupItem.NO);
        this.stateChangeListener = processActivationElementBuilder.stateChangeListener;
    }

    /**
     * Gets the activation label to be shown to the user.
     * @return String
     */
    public String getActivationLabel() {
        return activationLabel;
    }

    /**
     * Gets the activated per default setting.
     * @return Boolean
     */
    public Boolean isActivatedPerDefault() {
        return isActivatedPerDefault;
    }

    /**
     * Gets the underlying algorithm to be run.
     * @return the underyling algorithm
     */
    public DetectionAlgorithm getProcessAlgorithm() {
        return processAlgorithm;
    }

    /**
     * Gets the internationalized explaining tooltip texet shown to the user.
     * @return String
     */
    public String getTooltip() {
        return tooltip;
    }

    /**
     * Gets the internationalized log entry shown to the user if the process was started.
     * @return String
     */
    public String getStartedLogEntry() {
        return startedLogEntry;
    }

    /**
     * Gets the internationalized log entry shown to the user if the process was finished successfully.
     * @return String
     */
    public String getFinishedLogEntry() {
        return finishedLogEntry;
    }

    /**
     * Gets the detection activation element state for the 'yes' option.
     * @return the state for the 'yes' option
     */
    public DetectionActivationElementState getDetectionActivationElementStateActivated() {
        return detectionActivationElementStateActivated;
    }

    /**
     * Gets the detection activation element state for the 'no' option.
     * @return the state for the 'no' option
     */
    public DetectionActivationElementState getDetectionActivationElementStateDeactivated() {
        return detectionActivationElementStateDeactivated;
    }

    /**
     * Sets the user's or default (currently active) selected state.
     * @param detectionActivationElementState state
     */
    public void setDetectionActivationElementState(DetectionActivationElementState detectionActivationElementState) {
        this.detectionActivationElementState = detectionActivationElementState;

        if (null != stateChangeListener) {
            stateChangeListener.onStateChanged(this);
        }
    }

    /**
     * Gets the user's selected state.
     * @return state
     */
    public DetectionActivationElementState getDetectionActivationElementState() {
        return detectionActivationElementState;
    }

    /**
     * Builder of {@link ProcessActivationElement} .
     */
    public static class DetectionActivationElementBuilder {
        private String activationLabel;
        private Boolean isActivatedPerDefault;
        private String tooltip;
        private String startedLogEntry;
        private String finishedLogEntry;
        private DetectionAlgorithm processAlgorithm;
        private DetectionActivationStateChangeListener stateChangeListener;

        /**
         * Sets the label to be displayed to the end user to activate / deactive the detection.
         * @param activationLabel String
         * @return this
         */
        public DetectionActivationElementBuilder withActivationLabel(final String activationLabel) {
            checkNotNull(activationLabel, "activationLabel must not be null.");

            this.activationLabel = activationLabel;

            return this;
        }

        /**
         * Sets whether this detection should be activated per default.
         * If so, the view should also activate the option.
         * @param isActivatedPerDefault Boolean
         * @return this
         */
        public DetectionActivationElementBuilder withIsActivatedPerDefault(final Boolean isActivatedPerDefault) {
            checkNotNull(isActivatedPerDefault, "isActivatedPerDefault must not be null.");

            this.isActivatedPerDefault = isActivatedPerDefault;

            return this;
        }

        /**
         * Sets the algorithm implementation that should be run.
         * @see PreprocessingAlgorithm for more information
         * @param processAlgorithm the underlying algorithm implementation within the qg-detection module
         * @return this
         */
        public DetectionActivationElementBuilder withAlgorithm(final DetectionAlgorithm processAlgorithm) {
            checkNotNull(processAlgorithm, "processAlgorithm must not be null.");

            this.processAlgorithm = processAlgorithm;

            return this;
        }

        /**
         * Sets the tooltipl with an explanation of the detection.
         * @param tooltip String
         * @return this
         */
        public DetectionActivationElementBuilder withTooltip(final String tooltip) {
            checkNotNull(tooltip, "tooltip must not be null.");

            this.tooltip = tooltip;

            return this;
        }

        /**
         * Sets the internationalized log entry shown to the user if the detection was started.
         * @param startedLogEntry String
         * @return this
         */
        public DetectionActivationElementBuilder withStartedLogEntry(final String startedLogEntry) {
            checkNotNull(startedLogEntry, "startedLogEntry must not be null.");

            this.startedLogEntry = startedLogEntry;

            return this;
        }

        /**
         * Sets the internationalized log entry shown to the user if the detection was finished.
         * @param finishedLogEntry String
         * @return this
         */
        public DetectionActivationElementBuilder withFinishedLogEntry(final String finishedLogEntry) {
            checkNotNull(finishedLogEntry, "finishedLogEntry must not be null.");

            this.finishedLogEntry = finishedLogEntry;

            return this;
        }

        /**
         * Sets the listener informed on state changes.
         * @param stateChangeListener {@link ProcessActivationStateChangeListener}
         * @return this
         */
        public DetectionActivationElementBuilder withStateChangeListener(final DetectionActivationStateChangeListener stateChangeListener) {
            checkNotNull(stateChangeListener, "stateChangeListener must not be null.");

            this.stateChangeListener = stateChangeListener;

            return this;
        }

        /**
         * Builds a new {@link ProcessActivationElement} instance.
         * @return the new {@link ProcessActivationElement}
         */
        public DetectionActivationElement build() {
            checkNotNull(activationLabel, "activationLabel must not be null.");
            checkNotNull(isActivatedPerDefault, "isActivatedPerDefault must not be null.");
            checkNotNull(tooltip, "tooltip must not be null.");
            checkNotNull(startedLogEntry, "startedLogEntry must not be null.");
            checkNotNull(finishedLogEntry, "finishedLogEntry must not be null.");
            checkNotNull(processAlgorithm, "processAlgorithm must not be null.");

            return new DetectionActivationElement(this);
        }
    }
}

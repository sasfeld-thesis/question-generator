package de.saschafeldmann.adesso.master.thesis.portlet.model.preprocesses;

import de.saschafeldmann.adesso.master.thesis.portlet.properties.i18n.Messages;
import de.saschafeldmann.adesso.master.thesis.preprocesses.algorithm.PreprocessingAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;

import static com.google.common.base.Preconditions.*;
/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  04.06.2016
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
 * Model to connect a process from the qg-preprocesses module with a view element to de- or activate it.
 */
public class ProcessActivationElement {
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
     * Class for the connection between {@link ProcessActivationElement} and {@link ActivationOptionGroupItem}.
     */
    public class ProcessActivationElementState {
        private ProcessActivationElement parentProcessActivationElement;
        private ActivationOptionGroupItem activationOptionGroupItem;

        private ProcessActivationElementState(ProcessActivationElement parentProcessActivationElement, ActivationOptionGroupItem activationOptionGroupItem) {
            this.parentProcessActivationElement = parentProcessActivationElement;
            this.activationOptionGroupItem = activationOptionGroupItem;
        }

        /**
         * Gets the process activation element.
         * @return
         */
        public ProcessActivationElement getParentProcessActivationElement() {
            return parentProcessActivationElement;
        }

        /**
         * Gets the option group item selected by the user.
         * @return the state
         */
        public ActivationOptionGroupItem getActivationOptionGroupItem() {
            return activationOptionGroupItem;
        }

        @Override
        public String toString() {
            return getActivationOptionGroupItem().toString();
        }
    }


    private final String activationLabel;
    private final Boolean isActivatedPerDefault;
    private final PreprocessingAlgorithm processAlgorithm;
    private final String tooltip;
    private final String startedLogEntry;
    private final String finishedLogEntry;
    private final ProcessActivationElementState processActivationElementStateActivated;
    private final ProcessActivationElementState processActivationElementStateDeactivated;
    private final ProcessActivationStateChangeListener stateChangeListener;
    private ProcessActivationElementState processActivationElementState;

    private ProcessActivationElement(ProcessActivationElementBuilder processActivationElementBuilder) {
        this.activationLabel = processActivationElementBuilder.activationLabel;
        this.isActivatedPerDefault = processActivationElementBuilder.isActivatedPerDefault;
        this.processAlgorithm = processActivationElementBuilder.processAlgorithm;
        this.tooltip = processActivationElementBuilder.tooltip;
        this.startedLogEntry = processActivationElementBuilder.startedLogEntry;
        this.finishedLogEntry = processActivationElementBuilder.finishedLogEntry;
        this.processActivationElementStateActivated = new ProcessActivationElementState(this, ActivationOptionGroupItem.YES);
        this.processActivationElementStateDeactivated = new ProcessActivationElementState(this, ActivationOptionGroupItem.NO);
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
    public PreprocessingAlgorithm getProcessAlgorithm() {
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
     * Gets the process activation element state for the 'yes' option.
     * @return the state for the 'yes' option
     */
    public ProcessActivationElementState getProcessActivationElementStateActivated() {
        return processActivationElementStateActivated;
    }

    /**
     * Gets the process activation element state for the 'no' option.
     * @return the state for the 'no' option
     */
    public ProcessActivationElementState getProcessActivationElementStateDeactivated() {
        return processActivationElementStateDeactivated;
    }

    /**
     * Sets the user's or default (currently active) selected state.
     * @param processActivationElementState state
     */
    public void setProcessActivationElementState(ProcessActivationElementState processActivationElementState) {
        this.processActivationElementState = processActivationElementState;

        if (null != stateChangeListener) {
            stateChangeListener.onStateChanged(this);
        }
    }

    /**
     * Gets the user's selected state.
     * @return state
     */
    public ProcessActivationElementState getProcessActivationElementState() {
        return processActivationElementState;
    }

    /**
     * Builder of {@link ProcessActivationElement} .
     */
    public static class ProcessActivationElementBuilder {
        private String activationLabel;
        private Boolean isActivatedPerDefault;
        private String tooltip;
        private String startedLogEntry;
        private String finishedLogEntry;
        private PreprocessingAlgorithm processAlgorithm;
        private ProcessActivationStateChangeListener stateChangeListener;

        /**
         * Sets the label to be displayed to the end user to activate / deactive the process.
         * @param activationLabel String
         * @return this
         */
        public ProcessActivationElementBuilder withActivationLabel(final String activationLabel) {
            checkNotNull(activationLabel, "activationLabel must not be null.");

            this.activationLabel = activationLabel;

            return this;
        }

        /**
         * Sets whether this process should be activated per default.
         * If so, the view should also activate the option.
         * @param isActivatedPerDefault Boolean
         * @return this
         */
        public ProcessActivationElementBuilder withIsActivatedPerDefault(final Boolean isActivatedPerDefault) {
            checkNotNull(isActivatedPerDefault, "isActivatedPerDefault must not be null.");

            this.isActivatedPerDefault = isActivatedPerDefault;

            return this;
        }

        /**
         * Sets the algorithm implementation that should be run.
         * @see PreprocessingAlgorithm for more information
         * @param processAlgorithm the underlying algorithm implementation within the qg-preprocesses module
         * @return this
         */
        public ProcessActivationElementBuilder withAlgorithm(final PreprocessingAlgorithm processAlgorithm) {
            checkNotNull(processAlgorithm, "processAlgorithm must not be null.");

            this.processAlgorithm = processAlgorithm;

            return this;
        }

        /**
         * Sets the tooltipl with an explanation of the process.
         * @param tooltip String
         * @return this
         */
        public ProcessActivationElementBuilder withTooltip(final String tooltip) {
            checkNotNull(tooltip, "tooltip must not be null.");

            this.tooltip = tooltip;

            return this;
        }

        /**
         * Sets the internationalized log entry shown to the user if the process was started.
         * @param startedLogEntry String
         * @return this
         */
        public ProcessActivationElementBuilder withStartedLogEntry(final String startedLogEntry) {
            checkNotNull(startedLogEntry, "startedLogEntry must not be null.");

            this.startedLogEntry = startedLogEntry;

            return this;
        }

        /**
         * Sets the internationalized log entry shown to the user if the process was finished.
         * @param finishedLogEntry String
         * @return this
         */
        public ProcessActivationElementBuilder withFinishedLogEntry(final String finishedLogEntry) {
            checkNotNull(finishedLogEntry, "finishedLogEntry must not be null.");

            this.finishedLogEntry = finishedLogEntry;

            return this;
        }

        /**
         * Sets the listener informed on state changes.
         * @param stateChangeListener {@link ProcessActivationStateChangeListener}
         * @return this
         */
        public ProcessActivationElementBuilder withStateChangeListener(final ProcessActivationStateChangeListener stateChangeListener) {
            checkNotNull(stateChangeListener, "stateChangeListener must not be null.");

            this.stateChangeListener = stateChangeListener;

            return this;
        }

        /**
         * Builds a new {@link ProcessActivationElement} instance.
         * @return the new {@link ProcessActivationElement}
         */
        public ProcessActivationElement build() {
            checkNotNull(activationLabel, "activationLabel must not be null.");
            checkNotNull(isActivatedPerDefault, "isActivatedPerDefault must not be null.");
            checkNotNull(processAlgorithm, "processAlgorithm must not be null.");
            checkNotNull(tooltip, "tooltip must not be null.");
            checkNotNull(startedLogEntry, "startedLogEntry must not be null.");
            checkNotNull(finishedLogEntry, "finishedLogEntry must not be null.");

            return new ProcessActivationElement(this);
        }
    }
}

package de.saschafeldmann.adesso.master.thesis.portlet.model.preprocesses;

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
    private final String activationLabel;
    private final Boolean isActivatedPerDefault;
    private final String tooltip;

    private ProcessActivationElement(ProcessActivationElementBuilder processActivationElementBuilder) {
        this.activationLabel = processActivationElementBuilder.activationLabel;
        this.isActivatedPerDefault = processActivationElementBuilder.isActivatedPerDefault;
        this.tooltip = processActivationElementBuilder.tooltip;
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
    public Boolean getActivatedPerDefault() {
        return isActivatedPerDefault;
    }

    /**
     * Gets the explaining tooltip texet shown to the user.
     * @return String
     */
    public String getTooltip() {
        return tooltip;
    }

    /**
     * Builder of {@link ProcessActivationElement} .
     */
    public static class ProcessActivationElementBuilder {
        private String activationLabel;
        private Boolean isActivatedPerDefault;
        private String tooltip;

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
         * Builds a new {@link ProcessActivationElement} instance.
         * @return the new {@link ProcessActivationElement}
         */
        public ProcessActivationElement build() {
            return new ProcessActivationElement(this);
        }
    }
}

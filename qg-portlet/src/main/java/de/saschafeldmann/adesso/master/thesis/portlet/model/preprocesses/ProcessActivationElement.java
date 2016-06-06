package de.saschafeldmann.adesso.master.thesis.portlet.model.preprocesses;

import de.saschafeldmann.adesso.master.thesis.portlet.properties.i18n.Messages;
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

        public String toString() {
            return Messages.getInstance().get(displayLabelMessageKey);
        }
    };

    private ActivationOptionGroupItem activationOptionGroupItem;
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
     * Sets the option group item that the user selected, e.g. YES means: the process was activated by the user.
     * @param activationOptionGroupItem the user's selection
     */
    public void setActivationOptionGroupItem(ActivationOptionGroupItem activationOptionGroupItem) {
        this.activationOptionGroupItem = activationOptionGroupItem;
    }

    /**
     * Gets the option group item selected by the user.
     * @return
     */
    public ActivationOptionGroupItem getActivationOptionGroupItem() {
        return activationOptionGroupItem;
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

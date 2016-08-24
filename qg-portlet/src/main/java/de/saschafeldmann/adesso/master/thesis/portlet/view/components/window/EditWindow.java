package de.saschafeldmann.adesso.master.thesis.portlet.view.components.window;

import de.saschafeldmann.adesso.master.thesis.portlet.properties.i18n.Messages;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

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
 * An edit modal view / window / dialog.
 * Contains a simple textarea, edit and delete buttons.
 */
@Component
@Scope("prototype")
public class EditWindow extends AutowirableWindow {
    private static final String CSS_EDIT_WINDOW_STYLENAME = "edit-window";
    private final Messages messages;
    private String title;
    private String textareaLabel;
    protected EditWindowListener editWindowListener;

    protected final AutowirableTextArea textArea;
    protected final AutowirableButton btnEdit;
    protected final AutowirableButton btnDelete;
    protected final AutowirableFormLayout formLayout;
    protected final AutowirableHorizontalLayout buttonGroupLayout;
    private String textareaInput;

    /**
     * Creates a simple textarea dialog with edit and delete buttons.
     * @param textArea the textarea
     * @param btnEdit the edit button
     * @param btnDelete the delete button
     * @param formLayout the layout
     */
    @Autowired
    public EditWindow(final Messages messages, final AutowirableTextArea textArea, final AutowirableButton btnEdit, final AutowirableButton btnDelete, final AutowirableFormLayout formLayout, final AutowirableHorizontalLayout buttonGroupLayout) {
        this.messages = messages;
        this.textArea = textArea;
        this.btnEdit = btnEdit;
        this.btnDelete = btnDelete;
        this.formLayout = formLayout;
        this.buttonGroupLayout = buttonGroupLayout;
    }

    @PostConstruct
    private void initialize() {
        setListeners();
        addStyleNames();
    }

    protected void setListeners() {
        btnEdit.addClickListener(new com.vaadin.ui.Button.ClickListener() {
            public void buttonClick(com.vaadin.ui.Button.ClickEvent clickEvent) {
                // notify listener and close the window
                editWindowListener.onEditButtonClicked(textArea.getValue());
                close();
            }
        });

        btnDelete.addClickListener(new com.vaadin.ui.Button.ClickListener() {
            public void buttonClick(com.vaadin.ui.Button.ClickEvent clickEvent) {
                // notify listener and close the window
                editWindowListener.onDeleteButtonClicked();
                close();
            }
        });
    }

    protected void addStyleNames() {
        setStyleName(CSS_EDIT_WINDOW_STYLENAME);
        btnDelete.addStyleName(CommonStyleNames.BUTTON_GROUP_RIGHT_BUTTON_STYLENAME);
    }

    /**
     * Sets the window's title.
     * @param title String
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the textarea's label.
     * @param textareaLabel String
     */
    public void setTextareaLabel(String textareaLabel) {
        this.textareaLabel = textareaLabel;
    }

    /**
     * Sets the single window listener.
     * @param editWindowListener the window listener.
     */
    public void setEditWindowListener(EditWindowListener editWindowListener) {
        this.editWindowListener = editWindowListener;
    }

    /**
     * Sets the textarea input.
     * @param textareInput the input to be shown
     */
    public void setTextareaInput(String textareInput) {
        this.textareaInput = textareInput;
    }

    /**
     * Resets the window.
     */
    public void reset() {
        formLayout.removeAllComponents();

        setCaptionsAndValues();
        arrangeLayout();

        this.setContent(formLayout);
    }

    protected void setCaptionsAndValues() {
        setCaption(title);
        textArea.setCaption(textareaLabel);
        textArea.setValue(textareaInput);

        btnEdit.setCaption(messages.getEditWindowChangeButtonLabel());
        btnDelete.setCaption(messages.getEditWindowDeleteButtonLabel());
    }

    protected void arrangeLayout() {
        formLayout.addComponent(textArea);

        buttonGroupLayout.addComponent(btnEdit);
        buttonGroupLayout.addComponent(btnDelete);

        formLayout.addComponent(buttonGroupLayout);
    }

    /**
     * Sets a tooltip / description text on the window's edit button.
     * @param tooltip String
     */
    public void setEditButtonTooltip(final String tooltip) {
        this.btnEdit.setDescription(tooltip);
    }

    /**
     * Sets a tooltip / description text on the window's delete button.
     * @param tooltip String
     */
    public void setDeleteButtonTooltip(final String tooltip) {
        this.btnDelete.setDescription(tooltip);
    }
}

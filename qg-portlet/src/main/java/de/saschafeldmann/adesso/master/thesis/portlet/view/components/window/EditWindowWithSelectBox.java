package de.saschafeldmann.adesso.master.thesis.portlet.view.components.window;

import com.vaadin.data.Property;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.i18n.Messages;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.*;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.AutowirableButton;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.AutowirableFormLayout;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.AutowirableHorizontalLayout;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.AutowirableListSelect;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.AutowirableTextArea;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  14.06.2016
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
 * [short description]
 */
@Component
@Scope("prototype")
public class EditWindowWithSelectBox extends EditWindow {
    private static final java.lang.String CSS_STYLE_NAME = "edit-window-with-two-areas-and-selectlist";
    @Autowired
    private AutowirableListSelect listSelect;
    @Autowired
    private InfoBox infoBox;
    @Autowired
    protected AutowirableTextArea secondTextArea;

    private String secondTextareaLabel;
    private String secondTextareaInput;
    private String listSelectLabel;
    private String infoBoxText;

    /**
     * Creates a simple textarea dialog with edit and delete buttons.
     *
     * @param messages
     * @param textArea          the textarea
     * @param btnEdit           the edit button
     * @param btnDelete         the delete button
     * @param formLayout        the layout
     * @param buttonGroupLayout
     */
    @Autowired
    public EditWindowWithSelectBox(Messages messages, AutowirableTextArea textArea, AutowirableButton btnEdit, AutowirableButton btnDelete, AutowirableFormLayout formLayout, AutowirableHorizontalLayout buttonGroupLayout) {
        super(messages, textArea, btnEdit, btnDelete, formLayout, buttonGroupLayout);
    }

    protected void setListeners() {
        super.setListeners();

        listSelect.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent valueChangeEvent) {
                ((EditWindowWithSelectBoxListener) editWindowListener).onSelectBoxItemChanged(valueChangeEvent.getProperty().getValue());
            }
        });

        btnEdit.addClickListener(new com.vaadin.ui.Button.ClickListener() {
            public void buttonClick(com.vaadin.ui.Button.ClickEvent clickEvent) {
                // notify listener and close the window
                ((EditWindowWithSelectBoxListener) editWindowListener).onEditButtonClicked(textArea.getValue(), secondTextArea.getValue());
                close();
            }
        });
    }

    protected void addStyleNames() {
        super.addStyleNames();
        setStyleName(CSS_STYLE_NAME);
    }

    /**
     * Sets the single window listener.
     * @param editWindowListener the window listener.
     */
    public void setEditWindowListener(EditWindowWithSelectBoxListener editWindowListener) {
        this.editWindowListener = editWindowListener;
    }

    /**
     * Sets the label for the second text area.
     * @param secondTextareaLabel String
     */
    public void setSecondTextareaLabel(String secondTextareaLabel) {
        this.secondTextareaLabel = secondTextareaLabel;
    }

    /**
     * Sets the input of the second text area.
     * @param secondTextareaInput String
     */
    public void setSecondTextareaInput(String secondTextareaInput) {
        this.secondTextareaInput = secondTextareaInput;
    }

    /**
     * Sets the list select title / label.
     * @param listSelectTitle String
     */
    public void setListSelectLabel(String listSelectTitle) {
        this.listSelectLabel = listSelectTitle;
    }

    /**
     * Adds a select item.
     * @param item Object
     */
    public void addListSelectItem(Object item) {
        this.listSelect.addItem(item);
    }

    /**
     * Removes all list select items.
     */
    public void resetListSelectItems() {
        this.listSelect.removeAllItems();
    }

    /**
     * Sets the list select rows.
     * @param rows int
     */
    public void setListSelectRows(int rows) {
        this.listSelect.setRows(rows);
    }

    /**
     * Selects an item in the given select list.
     * @param selection item
     */
    public void setListSelectSelection(Object selection) {
        this.listSelect.select(selection);
    }

    /**
     * Gets the select value of the current list selection.
     * @return the value
     */
    public Object getListSelectValue() {
        return listSelect.getValue();
    }
    /**
     * Sets the info box text.
     * Set to null to disable the info box.
     * @param infoBoxText String or null to disable (default)
     */
    public void setInfoBoxText(String infoBoxText) {
        this.infoBoxText = infoBoxText;
    }

    protected void setCaptionsAndValues() {
        super.setCaptionsAndValues();

        secondTextArea.setCaption(secondTextareaLabel);
        secondTextArea.setValue(secondTextareaInput);

        listSelect.setCaption(listSelectLabel);

        if (null != infoBoxText) {
            infoBox.setInfo();
            infoBox.setCaption(infoBoxText);
        }
    }

    protected void arrangeLayout() {
        formLayout.addComponent(textArea);
        formLayout.addComponent(secondTextArea);
        formLayout.addComponent(listSelect);

        if (null != infoBoxText) {
            formLayout.addComponent(infoBox);
        }

        buttonGroupLayout.addComponent(btnEdit);
        buttonGroupLayout.addComponent(btnDelete);

        formLayout.addComponent(buttonGroupLayout);
    }
}

package de.saschafeldmann.adesso.master.thesis.portlet.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.Messages;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.FormLayout;
import de.saschafeldmann.adesso.master.thesis.portlet.view.components.VersionLabel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  23.05.2016
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
 * This abstract view defines the basic components that each view in the application has, e.g. the current step indicator etc.
 * It notifies the set {@link MenuListener} instance on menu item click.
 */
public abstract class AbstractStepView extends VerticalLayout implements ViewWithMenu {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractStepView.class);
    private static final String CSS_ACTIVE_ITEM_STYLENAME = "active_menuitem";
    private static final String CSS_MAIN_CONTAINER_STYLENAME = "maincontainer";
    private static final String CSS_RIGHT_BUTTON_STYLENAME = "right-button";

    /**
     * The menu listener implementation.
     */
    private class MenuClickListener implements MenuBar.Command {

        /**
         * @param menuItem the menu item which was selected.
         * @see com.vaadin.ui.MenuBar.Command#menuSelected(MenuBar.MenuItem)
         */
        public void menuSelected(MenuBar.MenuItem menuItem) {
            LOGGER.info("Menu item {} clicked.", menuItem);

            if (null == outsideMenuListener) {
                LOGGER.error("No menu listener was registered, but a menu item was selected. Please make sure to call AbstractStepView#setMenuListener().");
                return;
            }

            if (menuItem.getText().equals(messages.getMenuItemCourseInformationLabel())) {
                outsideMenuListener.onCourseInformationClicked();
            } else if (menuItem.getText().equals(messages.getMenuItemContentsLabel())) {
                outsideMenuListener.onContentsClicked();
            } else if (menuItem.getText().equals(messages.getMenuItemPreprocessesLabel())) {
                outsideMenuListener.onPreprocessesClicked();
            } else if (menuItem.getText().equals(messages.getMenuItemDetectionLabel())) {
                outsideMenuListener.onDetectionClicked();
            } else if (menuItem.getText().equals(messages.getMenuItemQuestionGenerationLabel())) {
                outsideMenuListener.onQuestionGenerationClicked();
            }
        }
    }

    protected final Messages messages;
    private MenuBar menuBar;
    private MenuClickListener menuClickListener;
    private MenuListener outsideMenuListener;
    private VersionLabel versionLabel;


    public AbstractStepView(Messages messages, VersionLabel versionLabel) {
        this.messages = messages;
        this.versionLabel = versionLabel;
    }

    /**
     * Sets the menu listener that is informed after click on menu items.
     * @param outsideMenuListener the outsideMenuListener
     */
    private void setOutsideMenuListener(MenuListener outsideMenuListener) {
        this.outsideMenuListener = outsideMenuListener;
    }

    /**
     * @see ViewWithMenu#setMenuListener(MenuListener)
     */
    public void setMenuListener(MenuListener menuListener) {
        setOutsideMenuListener(menuListener);
    }

    /**
     * Initializes or resets the view.
     *
     * @param activeItem the menu item to be marked active. Set to null if no item is active.
     */
    protected void reset(final String activeItem) {
        resetLayout();
        resetMenu(activeItem);
    }

    private void resetMenu(final String activeItem) {
        this.menuClickListener = new MenuClickListener();

        this.menuBar = new MenuBar();
        this.addComponent(menuBar);

        addMenuItems(activeItem);
    }

    protected void addVersionLabel() {
        this.addComponent(versionLabel);
    }

    private void addMenuItems(final String activeItem) {
        addMenuItem(messages.getMenuItemCourseInformationLabel(), activeItem);
        addMenuItem(messages.getMenuItemContentsLabel(), activeItem);
        addMenuItem(messages.getMenuItemPreprocessesLabel(), activeItem);
        addMenuItem(messages.getMenuItemDetectionLabel(), activeItem);
        addMenuItem(messages.getMenuItemQuestionGenerationLabel(), activeItem);
    }

    private MenuBar.MenuItem addMenuItem(final String label, final String activeItemLabel) {
        MenuBar.MenuItem item = this.menuBar.addItem(label, null, this.menuClickListener);

        if (label.equals(activeItemLabel)) {
            item.setStyleName(CSS_ACTIVE_ITEM_STYLENAME);
        }

        return item;
    }

    private void resetLayout() {
        this.removeAllComponents();
        this.addStyleName(CSS_MAIN_CONTAINER_STYLENAME);
    }

    /**
     * Adds the buttons in a shared layout so they look nice.
     * @param layout the wrapping layout used in the view (e.g. a form layout)
     * @param leftButton button on the left side
     * @param rightButton button on the right side
     */
    protected void addButtonsAtBottom(Layout layout, Button leftButton, Button rightButton) {
        layout.addComponent(leftButton);
        layout.addComponent(rightButton);
        rightButton.addStyleName(CSS_RIGHT_BUTTON_STYLENAME);
    }
}

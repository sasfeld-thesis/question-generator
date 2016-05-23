package de.saschafeldmann.adesso.master.thesis.portlet.view;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.VerticalLayout;
import de.saschafeldmann.adesso.master.thesis.portlet.properties.Messages;
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
public class AbstractStepView extends VerticalLayout implements ViewWithMenu {
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractStepView.class);

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

    private final Messages messages;
    private MenuBar menuBar;
    private MenuClickListener menuClickListener;
    private MenuListener outsideMenuListener;
    private VersionLabel versionLabel;


    public AbstractStepView(Messages messages, VersionLabel versionLabel) {
        this.messages = messages;
        this.versionLabel = versionLabel;
    }

    /**
     * @see View#enter(ViewChangeListener.ViewChangeEvent)
     */
    public void enter(ViewChangeListener.ViewChangeEvent viewChangeEvent) {

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
     */
    public void reset() {
        resetLayout();
        resetMenu();
    }

    private void resetMenu() {
        this.menuClickListener = new MenuClickListener();

        this.menuBar = new MenuBar();
        this.addComponent(menuBar);

        addMenuItems();
        addVersionLabel();
    }

    private void addVersionLabel() {
        this.addComponent(versionLabel);
    }

    private void addMenuItems() {
        this.menuBar.addItem(messages.getMenuItemCourseInformationLabel(), null, this.menuClickListener);
        this.menuBar.addItem(messages.getMenuItemContentsLabel(), null, this.menuClickListener);
        this.menuBar.addItem(messages.getMenuItemPreprocessesLabel(), null, this.menuClickListener);
        this.menuBar.addItem(messages.getMenuItemDetectionLabel(), null, this.menuClickListener);
        this.menuBar.addItem(messages.getMenuItemQuestionGenerationLabel(), null, this.menuClickListener);
    }

    private void resetLayout() {
        this.removeAllComponents();
    }
}

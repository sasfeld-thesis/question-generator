package de.saschafeldmann.adesso.master.thesis.portlet.view.components;

import com.vaadin.data.Container;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  28.06.2016
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
 * A Vaadin {@link com.vaadin.ui.Grid}
 */
@Component
@Scope("prototype")
public class Grid extends com.vaadin.ui.Grid {

    /**
     * Creates a grid.
     */
    public Grid() {
        super();
    }

    /**
     * Creates a grid with the given date source.
     * @param dataSource the data source
     */
    public Grid(Container.Indexed dataSource) {
        super(dataSource);
    }

    /**
     * Creates a grid with the given caption.
     * @param caption String
     */
    public Grid(String caption) {
        super(caption);
    }

    /**
     * Creates a grid with the given caption and data source.
     * @param caption String
     * @param dataSource the data source
     */
    public Grid(String caption, Container.Indexed dataSource) {
        super(caption, dataSource);
    }

}

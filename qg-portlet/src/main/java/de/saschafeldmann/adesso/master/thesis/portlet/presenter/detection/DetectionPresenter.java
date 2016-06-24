package de.saschafeldmann.adesso.master.thesis.portlet.presenter.detection;

import de.saschafeldmann.adesso.master.thesis.portlet.presenter.VaadinViewPresenter;
import de.saschafeldmann.adesso.master.thesis.portlet.view.detection.DetectionView;

/**
 * Project:        Masterthesis of Sascha Feldmann
 * Creation date:  24.06.2016
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
 * Presenter for the concept detection step.
 */
public interface DetectionPresenter extends VaadinViewPresenter {
    /**
     * Initializes the {@link DetectionView} belonging to this presenter.
     * @return the {@link DetectionView}
     */
    DetectionView initializeView();
}

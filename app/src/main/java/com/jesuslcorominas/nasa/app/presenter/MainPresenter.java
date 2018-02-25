package com.jesuslcorominas.nasa.app.presenter;

import com.jesuslcorominas.nasa.app.view.callbackview.MainView;

/**
 * {@link Presenter} de la {@link com.jesuslcorominas.nasa.app.view.activity.MainActivity}
 *
 * @author Jesús López Corominas
 */
public interface MainPresenter extends Presenter<MainView> {

    /**
     * Obtiene el listado de {@link com.jesuslcorominas.nasa.app.model.Photo}
     */
    void getPhotos();
}

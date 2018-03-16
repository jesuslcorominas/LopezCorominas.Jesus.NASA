package com.jesuslcorominas.nasa.app.presenter;

import com.jesuslcorominas.nasa.app.view.activity.MainActivity;
import com.jesuslcorominas.nasa.app.view.callbackview.MainView;

import org.joda.time.DateTime;

/**
 * {@link Presenter} de la {@link MainActivity}
 *
 * @author Jesús López Corominas
 */
public interface MainPresenter extends Presenter<MainView> {

    /**
     * Establece el apiKey de las peticiones remotas en preferencias
     */
    void setApiKey();

    /**
     * Obtiene el listado de {@link com.jesuslcorominas.nasa.common.model.Photo}
     */
    void getPhotosBySol(int sol);

    void getPhotosByDate(DateTime earthDate);
}

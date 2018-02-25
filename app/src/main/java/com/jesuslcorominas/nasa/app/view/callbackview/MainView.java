package com.jesuslcorominas.nasa.app.view.callbackview;

import com.jesuslcorominas.nasa.app.model.Photo;

import java.util.Collection;

/**
 * CallbackView de la vista principal.
 *
 * @author Jesús López Corominas
 */
public interface MainView extends CallbackView {

    /**
     * Muestra un listado de {@link Photo} al usuario
     *
     * @param photos La lista de {@link Photo} a mostrar
     */
    void showPhotos(Collection<Photo> photos);
}

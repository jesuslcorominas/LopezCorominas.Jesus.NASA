package com.jesuslcorominas.nasa.app.event.impl;

import com.jesuslcorominas.nasa.app.event.Event;
import com.jesuslcorominas.nasa.common.model.Photo;

import java.util.Collection;

/**
 * Evento lanzado al obtener las fotos del servicio Rest.
 * <p>
 * Contiene las fotos obtenidas del servicio.
 *
 * @author Jesús López Corominas
 */
public class GetPhotosEvent implements Event {

    /**
     * Las fotos obtenidas del servicio
     */
    private final Collection<Photo> photos;

    /**
     * Constructor unico parametrizado con las fotos obtenidas
     *
     * @param photos Las fotos obtenidas
     */
    public GetPhotosEvent(Collection<Photo> photos) {
        this.photos = photos;
    }

    /**
     * Obtiene la coleccion de fotos
     *
     * @return La coleccion de fotos
     */
    public Collection<Photo> getPhotos() {
        return photos;
    }
}

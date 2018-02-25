package com.jesuslcorominas.nasa.data.net.dto;

import java.io.Serializable;
import java.util.Collection;

/**
 * Representacion de la respuesta del servidor a la peticion {@link com.jesuslcorominas.nasa.data.net.util.NetKeys#METHOD_GET_PHOTOS}
 *
 * @author Jesús López Corominas
 */
public class GetPhotosResponseDto implements Serializable {

    private Collection<PhotoDto> photos;

    public Collection<PhotoDto> getPhotos() {
        return photos;
    }

    public void setPhotos(Collection<PhotoDto> photos) {
        this.photos = photos;
    }
}

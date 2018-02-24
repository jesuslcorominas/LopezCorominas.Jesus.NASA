package com.jesuslcorominas.nasa.data.net.dto;

import java.io.Serializable;
import java.util.Collection;

/**
 * @author Jesús López Corominas
 */
public class ServerResponseDto implements Serializable {

    private Collection<PhotoDto> photos;

    public Collection<PhotoDto> getPhotos() {
        return photos;
    }

    public void setPhotos(Collection<PhotoDto> photos) {
        this.photos = photos;
    }
}

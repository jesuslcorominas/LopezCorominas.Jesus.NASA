package com.jesuslcorominas.nasa.app.event.impl;

import com.jesuslcorominas.nasa.app.event.Event;
import com.jesuslcorominas.nasa.app.model.Photo;

import java.util.Collection;

/**
 * @author Jesús López Corominas
 */
public class GetPhotosEvent implements Event {

    private final Collection<Photo> photos;

    public GetPhotosEvent(Collection<Photo> photos) {
        this.photos = photos;
    }

    public Collection<Photo> getPhotos() {
        return photos;
    }
}

package com.jesuslcorominas.nasa.app.view.callbackview;

import com.jesuslcorominas.nasa.app.model.Photo;

import java.util.Collection;

/**
 * @author Jesús López Corominas
 */
public interface MainView extends CallbackView {

    void showPhotos(Collection<Photo> photos);
}

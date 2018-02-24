package com.jesuslcorominas.nasa.app.presenter;

import com.jesuslcorominas.nasa.app.view.callbackview.MainView;

/**
 * @author Jesús López Corominas
 */
public interface MainPresenter extends Presenter<MainView> {

    void getPhotos();
}

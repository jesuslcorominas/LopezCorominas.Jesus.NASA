package com.jesuslcorominas.nasa.app.presenter.impl;

import com.jesuslcorominas.nasa.app.presenter.Presenter;
import com.jesuslcorominas.nasa.app.view.callbackview.CallbackView;

/**
 * @author Jesús López Corominas
 */
public abstract class AbstractPresenter<V extends CallbackView> implements Presenter<V> {

    V callbackView;
    boolean resumed;

    @Override
    public void setCallbackView(V view) {
        this.callbackView = view;
    }

    @Override
    public void onResume() {
        resumed = true;
    }

    @Override
    public void onPause() {
        resumed = false;
    }

}
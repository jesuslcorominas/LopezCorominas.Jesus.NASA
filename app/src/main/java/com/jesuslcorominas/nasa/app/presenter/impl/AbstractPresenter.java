package com.jesuslcorominas.nasa.app.presenter.impl;

import com.jesuslcorominas.nasa.app.presenter.Presenter;
import com.jesuslcorominas.nasa.app.view.callbackview.CallbackView;

/**
 * Clase abstracta con la funcionalidad comun de todos los {@link Presenter}. Establece la
 * {@link CallbackView}, y los metodos onResume y onPause
 *
 * @author Jesús López Corominas
 */
public abstract class AbstractPresenter<V extends CallbackView> implements Presenter<V> {

    /**
     * El {@link CallbackView} del {@link Presenter}
     */
    V callbackView;

    /**
     * Flag que indica si la vista asociada esta en primer plano o no, para saber si hay que
     * notificarle los cambios
     */
    boolean resumed;

    /**
     * {@inheritDoc}
     *
     * @param view La vista asociada al presenter
     */
    @Override
    public void setCallbackView(V view) {
        this.callbackView = view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onResume() {
        resumed = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onPause() {
        resumed = false;
    }

}
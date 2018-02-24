package com.jesuslcorominas.nasa.app.view.callbackview;

/**
 * @author Jesús López Corominas
 */
public interface CallbackView {

    /**
     * Show progress view
     */
    void showProgress();

    /**
     * Hide progress view
     */
    void hideProgress();

    /**
     * Show an error
     */
    void showError(int code, String message);
}

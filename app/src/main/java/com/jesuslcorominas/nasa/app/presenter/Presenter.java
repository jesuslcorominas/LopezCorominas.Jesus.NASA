package com.jesuslcorominas.nasa.app.presenter;

import com.jesuslcorominas.nasa.app.view.callbackview.CallbackView;

/**
 * @author Jesús López Corominas
 */
public interface Presenter<V extends CallbackView> {

    void setCallbackView(V view);

    void onResume();

    void onPause();

}

package com.jesuslcorominas.nasa.common;

/**
 * @author Jesús López Corominas
 */
public interface GetCallback<T> extends GenericCallback {

    void onSuccess(T result);
}

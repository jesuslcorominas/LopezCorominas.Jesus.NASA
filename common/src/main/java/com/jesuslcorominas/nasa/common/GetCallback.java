package com.jesuslcorominas.nasa.common;

/**
 * {@link GenericCallback} para obtener un objeto de tipo T
 *
 * @param <T> El tipo devuelto en el metodo de exito
 * @author Jesús López Corominas
 */
public interface GetCallback<T> extends GenericCallback {

    /**
     * Metodo para tratar el exito en una peticion
     *
     * @param result El resultado de la peticion
     */
    void onSuccess(T result);
}

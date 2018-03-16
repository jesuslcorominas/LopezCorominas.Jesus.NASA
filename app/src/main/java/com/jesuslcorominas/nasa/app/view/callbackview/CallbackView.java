package com.jesuslcorominas.nasa.app.view.callbackview;

/**
 * Interface base para todas las vistas con un {@link com.jesuslcorominas.nasa.app.presenter.Presenter}
 * asociado.
 * <p>
 * Describe los metodos que tiene que implementar una vista para actualizar la UI despues de una
 * accion completada en el {@link com.jesuslcorominas.nasa.app.presenter.Presenter}
 *
 * @author Jesús López Corominas
 */
public interface CallbackView {


    /**
     * Muestra un progress view indeterminado
     */
    void showProgress();

    /**
     * Oculta la vista de progreso
     */
    void hideProgress();

    /**
     * Muestra un error al usuario
     */
    void showError(int code, String message);
}

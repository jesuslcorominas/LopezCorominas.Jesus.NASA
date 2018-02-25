package com.jesuslcorominas.nasa.app.presenter;

import com.jesuslcorominas.nasa.app.view.callbackview.CallbackView;

/**
 * Interface base para todos los presenter de la aplicacion. Todas las
 * {@link com.jesuslcorominas.nasa.app.view.activity.AbstractBaseActivity } y las
 * {@link com.jesuslcorominas.nasa.app.view.activity.AbstractBaseAppCompatActivity} tendran un
 * Presenter para abstraer a la vista de toda logica ajena a como se muestra la informacion.
 * A partir de este punto no debe haber ninguna referencia a Android, siendo simplemente codigo y
 * librerias java lo que se use.
 * <p>
 * Esta interface esta parametrizada por un {@link CallbackView} que sera implementada por la
 * Activity asociada con lo que le podremos decir a la como tiene que comportarse en respuesta a
 * lo ocurrido en el Presenter.
 *
 * @param <V> Vista asociada al presenter que debera extender la interface {@link CallbackView}.
 * @author Jesús López Corominas
 */
public interface Presenter<V extends CallbackView> {

    /**
     * Establece la vista asociada al presenter
     *
     * @param view La vista asociada al presenter
     */
    void setCallbackView(V view);

    /**
     * Metodo a ejecutar cuando la vista asociada obtiene el foco
     */
    void onResume();

    /**
     * Metodo a ejecutar cuando la vista asociada pierde el foco
     */
    void onPause();

}

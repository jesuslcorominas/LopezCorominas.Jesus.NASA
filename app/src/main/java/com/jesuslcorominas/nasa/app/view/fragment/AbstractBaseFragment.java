package com.jesuslcorominas.nasa.app.view.fragment;

import android.app.Activity;
import android.app.Fragment;

import com.jesuslcorominas.nasa.app.event.AbstractEvent;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

/**
 * Clase base para los distintos fragments de la aplicacion. Registra el fragment para el bus de eventos
 * al agregarlo a la activity y se desregistra al destruirla quitarlo.
 * <p>
 * Anotada como @{@link EFragment} para poder hacer uso de Android Annotations.
 * Las clases hijas han de implementar el metodo abstracto @link {@link AbstractBaseFragment#init()} que al
 * estar anotado como @{@link AfterViews} sera invocado una vez se hayan inyectado las vistas
 *
 * @see <a href="https://github.com/excilys/androidannotations/wiki/Enhance-Fragments">Enhance-Fragments</a>
 */
@EFragment
abstract class AbstractBaseFragment extends Fragment {

    @SuppressWarnings("deprecation")
    @Override
    public void onAttach(Activity context) {
        super.onAttach(context);

        EventBus.getDefault().register(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();

        EventBus.getDefault().unregister(this);
    }

    @AfterViews
    abstract void init();

    /**
     * Metodo sin funcionalidad unicamente para evitar errores de EventBus.
     *
     * @param event Un evento de mentira que nunca llegara a ejecutarse
     * @see AbstractEvent
     */
    @SuppressWarnings("unused")
    @Subscribe
    public void onEvent(AbstractEvent event) {
    }
}

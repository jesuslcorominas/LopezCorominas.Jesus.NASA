package com.jesuslcorominas.nasa.app.event;

/**
 * Por el funcionamiento de EventBus, si alguna activity o fragment se registra para un bus
 * debe tener por lo menos un metodo publico anotado Subscribe. Hacemos que la
 * {@link com.jesuslcorominas.nasa.app.view.activity.AbstractBaseActivity},
 * {@link com.jesuslcorominas.nasa.app.view.activity.AbstractBaseAppCompatActivity} y el
 * {@link com.jesuslcorominas.nasa.app.view.fragment.AbstractBaseFragment} se suscriban a un evento
 * de este tipo y asi no tenemos que suscribir a todos las clases hijas
 *
 * @author Jesús López Corominas
 */
public class AbstractEvent implements Event {

    /**
     * Constructor privado para evitar la instanciacion
     */
    private AbstractEvent() {

    }
}

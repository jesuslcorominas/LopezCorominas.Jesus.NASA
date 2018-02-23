package com.jesuslcorominas.nasa.app.event;

/**
 * Por el funcionamiento de EventBus, si alguna activity o fragment se registra para un bus
 * debe tener por lo menos un metodo publico anotado Subscribe. Hacemos que la ActivityBase y el
 * FragmentBase se suscriban a un evento de este tipo y asi no tenemos que suscribir a todos las
 * clases hijas
 *
 * @author Jesús López Corominas
 */
public class AbstractEvent implements Event {

    private AbstractEvent() {

    }
}

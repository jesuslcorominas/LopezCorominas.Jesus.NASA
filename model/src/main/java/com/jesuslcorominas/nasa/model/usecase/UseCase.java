package com.jesuslcorominas.nasa.model.usecase;

/**
 * Interface base para todos los casos de uso de la aplicacion. En esta capa es donde debe realizarse
 * toda la logica. Sera la encargada de recibir los datos en bruto desde la interfaz grafica a traves
 * de los Presenter, procesarla, llamar a los {@link com.jesuslcorominas.nasa.model.repository.Repository}
 * si es necesario y devolver una respuesta formateada al presenter.
 *
 * @author Jesús López Corominas
 */
public interface UseCase {
}

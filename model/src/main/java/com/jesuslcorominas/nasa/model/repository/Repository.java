package com.jesuslcorominas.nasa.model.repository;

/**
 * Interface base para todos los repositorios de la aplicacion. La capa Repository se encarga de
 * abstraer a los casos de uso del donde vienen los datos. Los casos de uso unicamente contaran
 * con elementos Repository y ellos seran los encargados de tener las fuentes de datos necesarias,
 * como pueden ser bases de datos, servicios rest, ficheros, preferencias del sistema, etc,
 * para proveer de informacion a los casos de uso o para que estos la modifiquen.
 *
 * @author Jesús López Corominas
 */
public interface Repository {
}

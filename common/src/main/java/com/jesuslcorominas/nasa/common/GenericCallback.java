package com.jesuslcorominas.nasa.common;

/**
 * Callback generico. En la aplicacion las distintas capas se comunican entre ellas con llamadas
 * directas desde la capa superior a la inferior y a traves de callback desde la inferior a la
 * superior. Todas las llamadas, desde el Presenter hacia abajo, reciben como ultimo parametro un
 * objeto Callback para poder tratar el resultado. Con esto conseguimos en primer lugar un gran
 * desacoplamiento entre capas, ya que las capas inferiores no necesitan tener ningun tipo de
 * conocimiento de quien las esta invocando. En segundo lugar, conseguimos no tener que trabajar
 * con excepciones, ya que los Callback tienen informacion sobre el resultado de la peticion, lo
 * que nos permite tratar el resultado fallido. Por ultimo, tambien conseguimos poder trabajar
 * de manera asincrona. Nosotros lanzamos la peticion sin bloquear la ejecucion y cuando termine
 * se llamara al callback para que trate el resultado
 *
 * @author Jesús López Corominas
 */
public interface GenericCallback<T> {

    void onResult(Result<T> result);

}

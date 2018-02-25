package com.jesuslcorominas.nasa.data.datasource.remote;

import com.jesuslcorominas.nasa.common.GetCallback;
import com.jesuslcorominas.nasa.data.net.dto.PhotoDto;

import java.util.Collection;

/**
 * Fuente de datos remota de {@link PhotoDto}. Contiene un metodo para obtener un listado de
 * {@link PhotoDto}
 *
 * @author Jesús López Corominas
 */
public interface PhotoRemoteDatasource extends RemoteDatasource {

    /**
     * Obtiene un listado de {@link PhotoDto}
     *
     * @param sol      El sol del que se quiere obtener el listado de {@link PhotoDto}
     * @param apiKey   El api key para la llamada remota
     * @param callback El objeto donde tratar el resultado de la peticion
     */
    void getPhotos(Integer sol, String apiKey, GetPhotosCallback callback);

    /**
     * Callback para tratar el resultado de la peticion para obtener las {@link PhotoDto}
     */
    interface GetPhotosCallback extends GetCallback<Collection<PhotoDto>> {

    }
}

package com.jesuslcorominas.nasa.model.repository;

import com.jesuslcorominas.nasa.common.GetCallback;
import com.jesuslcorominas.nasa.model.entity.PhotoModelEntity;

import java.util.Collection;

/**
 * {@link Repository} de {@link PhotoModelEntity}
 *
 * @author Jesús López Corominas
 */
public interface PhotoRepository extends Repository {

    /**
     * Obtiene un listado de {@link PhotoModelEntity}
     *
     * @param sol      El sol del que se quieren obtener las {@link PhotoModelEntity}
     * @param apiKey   El apiKey para la peticion
     * @param callback Objeto donde tratar la respuesta
     */
    void getPhotos(Integer sol, String apiKey, GetPhotosCallback callback);

    /**
     * Interface para tratar la respuesta en la capa que invoque al repositorio
     */
    interface GetPhotosCallback extends GetCallback<Collection<PhotoModelEntity>> {

    }
}

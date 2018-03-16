package com.jesuslcorominas.nasa.model.repository;

import com.jesuslcorominas.nasa.common.GenericCallback;
import com.jesuslcorominas.nasa.common.model.Photo;

import org.joda.time.DateTime;

import java.util.List;

/**
 * {@link Repository} de {@link Photo}
 *
 * @author Jesús López Corominas
 */
public interface PhotoRepository extends Repository {

    /**
     * Obtiene un listado de {@link Photo}
     *
     * @param sol      El sol del que se quieren obtener las {@link Photo}
     * @param callback Objeto donde tratar la respuesta
     */
    void getPhotosBySol(Integer sol, GetPhotosCallback callback);

    void getPhotosByDate(DateTime earthDate, GetPhotosCallback callback);

    /**
     * Interface para tratar la respuesta en la capa que invoque al repositorio
     */
    interface GetPhotosCallback extends GenericCallback<List<Photo>> {

    }
}

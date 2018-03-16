package com.jesuslcorominas.nasa.model.usecase;

import com.jesuslcorominas.nasa.common.GenericCallback;
import com.jesuslcorominas.nasa.common.model.Photo;

import org.joda.time.DateTime;

import java.util.List;

/**
 * Caso de uso para obtener las fotos de una fecha concreta
 *
 * @author Jesús López Corominas
 */
public interface GetPhotosByDateUseCase extends UseCase {

    /**
     * Obtiene un listado de {@link Photo}
     *
     * @param callback Interface de respuesta para tratar los datos obtenidos
     */
    void getPhotosByDate(DateTime earthDate, GetPhotosCallback callback);

    /**
     * Interface de respuesta para tratar los datos obtenidos en la capa que invoque al caso de uso
     */
    interface GetPhotosCallback extends GenericCallback<List<Photo>> {
    }
}

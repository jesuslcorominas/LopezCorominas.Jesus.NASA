package com.jesuslcorominas.nasa.model.usecase;

import com.jesuslcorominas.nasa.common.GenericCallback;
import com.jesuslcorominas.nasa.common.model.Photo;

import java.util.List;

/**
 * Caso de uso para obtener las fotos a mostrar
 *
 * @author Jesús López Corominas
 */
public interface GetPhotosBySolUseCase extends UseCase {

    /**
     * Obtiene un listado de {@link Photo}
     *
     * @param sol      El sol del que se quieren obtener las {@link Photo}
     * @param callback Interface de respuesta para tratar los datos obtenidos
     */
    void getPhotosBySol(int sol, GetPhotosCallback callback);

    /**
     * Interface de respuesta para tratar los datos obtenidos en la capa que invoque al caso de uso
     */
    interface GetPhotosCallback extends GenericCallback<List<Photo>> {
    }
}

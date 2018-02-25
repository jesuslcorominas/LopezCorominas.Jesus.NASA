package com.jesuslcorominas.nasa.model.usecase;

import com.jesuslcorominas.nasa.common.GetCallback;
import com.jesuslcorominas.nasa.model.entity.PhotoModelEntity;

import java.util.Collection;

/**
 * Caso de uso para obtener las fotos a mostrar
 *
 * @author Jesús López Corominas
 */
public interface GetPhotosUseCase extends UseCase {

    /**
     * Obtiene un listado de {@link PhotoModelEntity}
     *
     * @param callback Interface de respuesta para tratar los datos obtenidos
     */
    void getPhotos(GetPhotosCallback callback);

    /**
     * Interface de respuesta para tratar los datos obtenidos en la capa que invoque al caso de uso
     */
    interface GetPhotosCallback extends GetCallback<Collection<PhotoModelEntity>> {
    }
}

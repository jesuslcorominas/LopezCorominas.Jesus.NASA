package com.jesuslcorominas.nasa.model.usecase.impl;

import com.jesuslcorominas.nasa.common.Error;
import com.jesuslcorominas.nasa.model.entity.PhotoModelEntity;
import com.jesuslcorominas.nasa.model.repository.PhotoRepository;
import com.jesuslcorominas.nasa.model.usecase.GetPhotosUseCase;

import java.util.Collection;

/**
 * {@inheritDoc}
 * <p>
 * Invoca al {@link PhotoRepository} para obtener el listado de {@link PhotoModelEntity}
 *
 * @author Jesús López Corominas
 */
public class GetPhotosUseCaseImpl implements GetPhotosUseCase {

    /**
     * El {@link com.jesuslcorominas.nasa.model.repository.Repository} de {@link PhotoModelEntity}
     */
    private final PhotoRepository photoRepository;

    /**
     * Constructor unico parametrizado con el {@link PhotoRepository}. Sera utilizado por Dagger2 para
     * inyectar las dependecias de la clase.
     *
     * @param photoRepository El repositorio de {@link PhotoModelEntity}
     */
    public GetPhotosUseCaseImpl(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    /**
     * {@inheritDoc}
     *
     * @param callback Interface de respuesta para tratar los datos obtenidos
     */
    @Override
    public void getPhotos(final GetPhotosCallback callback) {
        // TODO Sacarlo a preferencias. Mejor ahi que hardcodeado
        Integer sol = 1000;
        String apiKey = "DEMO_KEY";

        photoRepository.getPhotos(sol, apiKey, new PhotoRepository.GetPhotosCallback() {
            @Override
            public void onSuccess(Collection<PhotoModelEntity> result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(Error error) {
                callback.onError(error);
            }
        });
    }
}

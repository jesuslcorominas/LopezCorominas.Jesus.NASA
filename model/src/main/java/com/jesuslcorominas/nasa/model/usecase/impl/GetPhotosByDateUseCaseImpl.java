package com.jesuslcorominas.nasa.model.usecase.impl;

import com.jesuslcorominas.nasa.common.Result;
import com.jesuslcorominas.nasa.common.model.Photo;
import com.jesuslcorominas.nasa.model.repository.PhotoRepository;
import com.jesuslcorominas.nasa.model.usecase.GetPhotosByDateUseCase;

import org.joda.time.DateTime;

import java.util.List;

/**
 * {@inheritDoc}
 * <p>
 * Invoca al {@link PhotoRepository} para obtener el listado de {@link Photo}
 *
 * @author Jesús López Corominas
 */
public class GetPhotosByDateUseCaseImpl implements GetPhotosByDateUseCase {

    /**
     * El {@link com.jesuslcorominas.nasa.model.repository.Repository} de {@link Photo}
     */
    private final PhotoRepository photoRepository;

    /**
     * Constructor unico parametrizado con el {@link PhotoRepository}. Sera utilizado por Dagger2 para
     * inyectar las dependecias de la clase.
     *
     * @param photoRepository El repositorio de {@link Photo}
     */
    public GetPhotosByDateUseCaseImpl(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    /**
     * {@inheritDoc}
     *
     * @param callback Interface de respuesta para tratar los datos obtenidos
     */
    @Override
    public void getPhotosByDate(DateTime earthDate, final GetPhotosCallback callback) {
        photoRepository.getPhotosByDate(earthDate, new PhotoRepository.GetPhotosCallback() {

            @Override
            public void onResult(Result<List<Photo>> result) {
                callback.onResult(result);
            }

        });
    }
}

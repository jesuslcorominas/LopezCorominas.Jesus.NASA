package com.jesuslcorominas.nasa.model.usecase.impl;

import com.jesuslcorominas.nasa.common.Error;
import com.jesuslcorominas.nasa.model.entity.PhotoModelEntity;
import com.jesuslcorominas.nasa.model.repository.PhotoRepository;
import com.jesuslcorominas.nasa.model.usecase.GetPhotosUseCase;

import java.util.Collection;

/**
 * @author Jesús López Corominas
 */
public class GetPhotosUseCaseImpl implements GetPhotosUseCase {

    private final PhotoRepository photoRepository;

    public GetPhotosUseCaseImpl(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public void getPhotos(final GetPhotosCallback callback) {
        // TODO si hay tiempo sacarlo a preferencias
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

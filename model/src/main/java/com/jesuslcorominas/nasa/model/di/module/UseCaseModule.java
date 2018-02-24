package com.jesuslcorominas.nasa.model.di.module;

import com.jesuslcorominas.nasa.model.repository.PhotoRepository;
import com.jesuslcorominas.nasa.model.usecase.GetPhotosUseCase;
import com.jesuslcorominas.nasa.model.usecase.impl.GetPhotosUseCaseImpl;

import dagger.Module;
import dagger.Provides;

/**
 * @author Jesús López Corominas
 */
@Module
public class UseCaseModule {

    @Provides
    GetPhotosUseCase provideGetPhotosUseCase(PhotoRepository photoRepository) {
        return new GetPhotosUseCaseImpl(photoRepository);
    }
}



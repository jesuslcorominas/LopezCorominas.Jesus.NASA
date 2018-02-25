package com.jesuslcorominas.nasa.model.di.module;

import com.jesuslcorominas.nasa.model.repository.PhotoRepository;
import com.jesuslcorominas.nasa.model.usecase.GetPhotosUseCase;
import com.jesuslcorominas.nasa.model.usecase.impl.GetPhotosUseCaseImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Modulo de Dagger2 que provee de los casos de uso de la aplicacion
 *
 * @author Jesús López Corominas
 */
@Module
public class UseCaseModule {

    /**
     * Provee de un {@link GetPhotosUseCase}
     *
     * @param photoRepository El {@link com.jesuslcorominas.nasa.model.repository.Repository} de {@link com.jesuslcorominas.nasa.model.entity.PhotoModelEntity}
     * @return El {@link GetPhotosUseCase}
     */
    @SuppressWarnings("unused")
    @Provides
    GetPhotosUseCase provideGetPhotosUseCase(PhotoRepository photoRepository) {
        return new GetPhotosUseCaseImpl(photoRepository);
    }
}



package com.jesuslcorominas.nasa.model.di.module;

import com.jesuslcorominas.nasa.model.repository.PhotoRepository;
import com.jesuslcorominas.nasa.model.repository.PreferencesRepository;
import com.jesuslcorominas.nasa.model.usecase.GetPhotosByDateUseCase;
import com.jesuslcorominas.nasa.model.usecase.GetPhotosBySolUseCase;
import com.jesuslcorominas.nasa.model.usecase.SetApiKeyUseCase;
import com.jesuslcorominas.nasa.model.usecase.impl.GetPhotosByDateUseCaseImpl;
import com.jesuslcorominas.nasa.model.usecase.impl.GetPhotosBySolUseCaseImpl;
import com.jesuslcorominas.nasa.model.usecase.impl.SetApiKeyUseCaseImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Modulo de Dagger2 que provee de los casos de uso de la aplicacion
 *
 * @author Jesús López Corominas
 */
@Module
public class UseCaseModule {

    @Provides
    SetApiKeyUseCase provideSetApiKeyUseCase(PreferencesRepository preferencesRepository) {
        return new SetApiKeyUseCaseImpl(preferencesRepository);
    }

    /**
     * Provee de un {@link GetPhotosBySolUseCase}
     *
     * @param photoRepository El {@link com.jesuslcorominas.nasa.model.repository.Repository} de {@link com.jesuslcorominas.nasa.common.model.Photo}
     * @return El {@link GetPhotosBySolUseCase}
     */
    @Provides
    GetPhotosBySolUseCase provideGetPhotosBySolUseCase(PhotoRepository photoRepository) {
        return new GetPhotosBySolUseCaseImpl(photoRepository);
    }

    @Provides
    GetPhotosByDateUseCase provideGetPhotosByDateUseCase(PhotoRepository photoRepository) {
        return new GetPhotosByDateUseCaseImpl(photoRepository);
    }
}



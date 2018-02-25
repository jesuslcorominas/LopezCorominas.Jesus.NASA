package com.jesuslcorominas.nasa.data.di.module;

import com.jesuslcorominas.nasa.data.datasource.remote.PhotoRemoteDatasource;
import com.jesuslcorominas.nasa.data.repository.impl.PhotoRepositoryImpl;
import com.jesuslcorominas.nasa.model.repository.PhotoRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Modulo de Dagger 2 que provee de los repositorios de la aplicacion
 *
 * @author Jesús López Corominas
 */
@Module(includes = {DatasourceModule.class})
public class RepositoryModule {

    /**
     * Provee del repositorio de {@link com.jesuslcorominas.nasa.data.net.dto.PhotoDto}
     *
     * @param photoRemoteDatasource La fuente de datos remota de
     *                              {@link com.jesuslcorominas.nasa.data.net.dto.PhotoDto}
     * @return El repositorio de {@link com.jesuslcorominas.nasa.data.net.dto.PhotoDto}
     */
    @Provides
    public PhotoRepository providePhotoRepository(PhotoRemoteDatasource photoRemoteDatasource) {
        return new PhotoRepositoryImpl(photoRemoteDatasource);
    }

}

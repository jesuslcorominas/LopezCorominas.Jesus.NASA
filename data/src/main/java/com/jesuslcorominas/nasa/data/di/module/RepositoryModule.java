package com.jesuslcorominas.nasa.data.di.module;

import com.jesuslcorominas.nasa.data.datasource.PhotoLocalDatasource;
import com.jesuslcorominas.nasa.data.datasource.PhotoRemoteDatasource;
import com.jesuslcorominas.nasa.data.datasource.PreferencesDatasource;
import com.jesuslcorominas.nasa.data.repository.impl.PhotoRepositoryImpl;
import com.jesuslcorominas.nasa.data.repository.impl.PreferencesRepositoryImpl;
import com.jesuslcorominas.nasa.model.repository.PhotoRepository;
import com.jesuslcorominas.nasa.model.repository.PreferencesRepository;

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
    PhotoRepository providePhotoRepository(PhotoRemoteDatasource photoRemoteDatasource, PhotoLocalDatasource photoLocalDatasource) {
        return new PhotoRepositoryImpl(photoRemoteDatasource, photoLocalDatasource);
    }

    @Provides
    PreferencesRepository providePreferencesRepository(PreferencesDatasource preferencesDatasource) {
        return new PreferencesRepositoryImpl(preferencesDatasource);
    }

}

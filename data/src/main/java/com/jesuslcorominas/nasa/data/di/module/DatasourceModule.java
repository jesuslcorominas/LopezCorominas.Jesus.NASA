package com.jesuslcorominas.nasa.data.di.module;

import com.jesuslcorominas.nasa.data.datasource.remote.PhotoRemoteDatasource;
import com.jesuslcorominas.nasa.data.datasource.remote.impl.PhotoRemoteDatasourceImpl;
import com.jesuslcorominas.nasa.data.net.client.PhotoClient;

import dagger.Module;
import dagger.Provides;

/**
 * Modulo de Dagger 2 que provee de las fuentes de datos de la aplicacion
 *
 * @author Jesús López Corominas
 */
@Module(includes = {NetModule.class})
public class DatasourceModule {

    /**
     * Provee de la fuente de datos remota de {@link com.jesuslcorominas.nasa.data.net.dto.PhotoDto}
     *
     * @param photoClient El cliente rest para
     *                    {@link com.jesuslcorominas.nasa.data.net.dto.PhotoDto}
     * @return La fuente de datos remota de {@link com.jesuslcorominas.nasa.data.net.dto.PhotoDto}
     */
    @Provides
    public PhotoRemoteDatasource providePhotoRemoteDatasource(PhotoClient photoClient) {
        return new PhotoRemoteDatasourceImpl(photoClient);
    }
}

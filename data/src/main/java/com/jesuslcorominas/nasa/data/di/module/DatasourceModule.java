package com.jesuslcorominas.nasa.data.di.module;

import com.jesuslcorominas.nasa.data.datasource.remote.PhotoRemoteDatasource;
import com.jesuslcorominas.nasa.data.datasource.remote.impl.PhotoRemoteDatasourceImpl;
import com.jesuslcorominas.nasa.data.net.client.PhotoClient;

import dagger.Module;
import dagger.Provides;

/**
 * @author Jesús López Corominas
 */
@Module(includes = {NetModule.class})
public class DatasourceModule {

    @Provides
    public PhotoRemoteDatasource providePhotoRemoteDatasource(PhotoClient photoClient) {
        return new PhotoRemoteDatasourceImpl(photoClient);
    }
}

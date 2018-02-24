package com.jesuslcorominas.nasa.data.di.module;

import com.jesuslcorominas.nasa.data.datasource.remote.PhotoRemoteDatasource;
import com.jesuslcorominas.nasa.data.repository.impl.PhotoRepositoryImpl;
import com.jesuslcorominas.nasa.model.repository.PhotoRepository;

import dagger.Module;
import dagger.Provides;

/**
 * @author Jesús López Corominas
 */
@Module(includes = {DatasourceModule.class})
public class RepositoryModule {

    @Provides
    public PhotoRepository providePhotoRepository(PhotoRemoteDatasource photoRemoteDatasource) {
        return new PhotoRepositoryImpl(photoRemoteDatasource);
    }

}

package com.jesuslcorominas.nasa.data.di.module;

import com.jesuslcorominas.nasa.data.database.TransactionManager;
import com.jesuslcorominas.nasa.data.database.objectbox.dao.PhotoDao;
import com.jesuslcorominas.nasa.data.datasource.PhotoLocalDatasource;
import com.jesuslcorominas.nasa.data.datasource.PhotoRemoteDatasource;
import com.jesuslcorominas.nasa.data.datasource.PreferencesDatasource;
import com.jesuslcorominas.nasa.data.datasource.impl.PhotoLocalDatasourceImpl;
import com.jesuslcorominas.nasa.data.datasource.impl.PhotoRemoteDatasourceImpl;
import com.jesuslcorominas.nasa.data.datasource.impl.PreferencesDatasourceImpl;
import com.jesuslcorominas.nasa.data.net.client.PhotoClient;
import com.jesuslcorominas.nasa.model.preferences.PreferencesHelper;

import dagger.Module;
import dagger.Provides;

/**
 * Modulo de Dagger 2 que provee de las fuentes de datos de la aplicacion
 *
 * @author Jesús López Corominas
 */
@Module(includes = {NetModule.class, DatabaseModule.class})
public class DatasourceModule {

    /**
     * Provee de la fuente de datos remota de {@link com.jesuslcorominas.nasa.data.net.dto.PhotoDto}
     *
     * @param photoClient El cliente rest para
     *                    {@link com.jesuslcorominas.nasa.data.net.dto.PhotoDto}
     * @return La fuente de datos remota de {@link com.jesuslcorominas.nasa.data.net.dto.PhotoDto}
     */
    @Provides
    PhotoRemoteDatasource providePhotoRemoteDatasource(PhotoClient photoClient) {
        return new PhotoRemoteDatasourceImpl(photoClient);
    }

    @Provides
    PhotoLocalDatasource providePhotoLocalDatasource(PhotoDao photoDao, TransactionManager manager) {
        return new PhotoLocalDatasourceImpl(photoDao, manager);
    }

    @Provides
    PreferencesDatasource providePreferencesDatasource(PreferencesHelper preferencesHelper) {
        return new PreferencesDatasourceImpl(preferencesHelper);
    }
}

package com.jesuslcorominas.nasa.data.datasource.remote.impl;

import com.jesuslcorominas.nasa.common.Error;
import com.jesuslcorominas.nasa.data.datasource.remote.PhotoRemoteDatasource;
import com.jesuslcorominas.nasa.data.net.client.PhotoClient;
import com.jesuslcorominas.nasa.data.net.dto.PhotoDto;

import java.util.Collection;

/**
 * {@inheritDoc}
 * <p>
 * Implementacion de la fuente de datos remota de {@link PhotoDto}. Contiene un objeto
 * {@link PhotoClient} para realizar la peticion al servicio Rest
 *
 * @author Jesús López Corominas
 */
public class PhotoRemoteDatasourceImpl implements PhotoRemoteDatasource {

    /**
     * Cliente Rest para las peticiones remotas
     */
    private final PhotoClient photoClient;

    /**
     * Constructor unico parametrizado con las dependencias de la clase. Sera utilizado por
     * Dagger 2 para la inyeccion de dependencias
     *
     * @param photoClient El cliente rest de {@link PhotoDto}
     */
    public PhotoRemoteDatasourceImpl(PhotoClient photoClient) {
        this.photoClient = photoClient;
    }

    @Override
    public void getPhotos(Integer sol, String apiKey, final GetPhotosCallback callback) {
        photoClient.getPhotos(sol, apiKey, new PhotoClient.GetPhotosCallback() {
            @Override
            public void onSuccess(Collection<PhotoDto> result) {
                callback.onSuccess(result);
            }

            @Override
            public void onError(Error error) {
                callback.onError(error);
            }
        });
    }
}

package com.jesuslcorominas.nasa.data.datasource.impl;

import com.jesuslcorominas.nasa.common.Result;
import com.jesuslcorominas.nasa.common.model.Photo;
import com.jesuslcorominas.nasa.data.datasource.PhotoRemoteDatasource;
import com.jesuslcorominas.nasa.data.net.client.PhotoClient;
import com.jesuslcorominas.nasa.data.net.dto.PhotoDto;

import org.joda.time.DateTime;

import java.util.List;

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
    public void getPhotosBySol(Integer sol, final GetPhotosCallback callback) {
        photoClient.getPhotosBySol(sol, new PhotoClient.GetPhotosCallback() {

            @Override
            public void onResult(Result<List<Photo>> result) {
                callback.onResult(result);
            }
        });
    }

    @Override
    public void getPhotosByDate(DateTime earthDate, final GetPhotosCallback callback) {
        photoClient.getPhotosByDate(earthDate, new PhotoClient.GetPhotosCallback() {
            @Override
            public void onResult(Result<List<Photo>> result) {
                callback.onResult(result);
            }
        });
    }
}

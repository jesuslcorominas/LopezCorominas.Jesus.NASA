package com.jesuslcorominas.nasa.data.datasource.remote.impl;

import com.jesuslcorominas.nasa.common.Error;
import com.jesuslcorominas.nasa.data.datasource.remote.PhotoRemoteDatasource;
import com.jesuslcorominas.nasa.data.net.client.PhotoClient;
import com.jesuslcorominas.nasa.data.net.dto.PhotoDto;

import java.util.Collection;

/**
 * @author Jesús López Corominas
 */
public class PhotoRemoteDatasourceImpl implements PhotoRemoteDatasource {

    private final PhotoClient photoClient;

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

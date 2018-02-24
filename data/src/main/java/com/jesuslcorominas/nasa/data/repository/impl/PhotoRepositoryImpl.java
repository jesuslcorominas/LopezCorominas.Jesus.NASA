package com.jesuslcorominas.nasa.data.repository.impl;

import com.jesuslcorominas.nasa.common.Error;
import com.jesuslcorominas.nasa.data.datasource.remote.PhotoRemoteDatasource;
import com.jesuslcorominas.nasa.data.net.dto.PhotoDto;
import com.jesuslcorominas.nasa.model.entity.PhotoModelEntity;
import com.jesuslcorominas.nasa.model.repository.PhotoRepository;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.Collection;

/**
 * @author Jesús López Corominas
 */
public class PhotoRepositoryImpl implements PhotoRepository {

    private final PhotoRemoteDatasource photoRemoteDatasource;

    public PhotoRepositoryImpl(PhotoRemoteDatasource photoRemoteDatasource) {
        this.photoRemoteDatasource = photoRemoteDatasource;
    }

    @Override
    public void getPhotos(Integer sol, String apiKey, final GetPhotosCallback callback) {
        photoRemoteDatasource.getPhotos(sol, apiKey, new PhotoRemoteDatasource.GetPhotosCallback() {
            @Override
            public void onSuccess(Collection<PhotoDto> result) {
                Collection<PhotoModelEntity> photoEntities = new ModelMapper().map(result, new TypeToken<Collection<PhotoModelEntity>>() {}.getType());
                callback.onSuccess(photoEntities);
            }

            @Override
            public void onError(Error error) {
                callback.onError(error);
            }
        });
    }
}

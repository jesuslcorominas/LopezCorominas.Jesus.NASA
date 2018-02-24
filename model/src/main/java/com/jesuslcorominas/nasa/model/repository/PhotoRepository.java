package com.jesuslcorominas.nasa.model.repository;

import com.jesuslcorominas.nasa.common.GetCallback;
import com.jesuslcorominas.nasa.model.entity.PhotoModelEntity;

import java.util.Collection;

/**
 * @author Jesús López Corominas
 */
public interface PhotoRepository extends Repository {

    void getPhotos(Integer sol, String apiKey, GetPhotosCallback callback);

    interface GetPhotosCallback extends GetCallback<Collection<PhotoModelEntity>> {

    }
}

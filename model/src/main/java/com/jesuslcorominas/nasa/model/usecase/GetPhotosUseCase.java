package com.jesuslcorominas.nasa.model.usecase;

import com.jesuslcorominas.nasa.common.GetCallback;
import com.jesuslcorominas.nasa.model.entity.PhotoModelEntity;

import java.util.Collection;

/**
 * @author Jesús López Corominas
 */
public interface GetPhotosUseCase extends UseCase {

    void getPhotos(GetPhotosCallback callback);

    interface GetPhotosCallback extends GetCallback<Collection<PhotoModelEntity>> {
    }
}

package com.jesuslcorominas.nasa.data.datasource.remote;

import com.jesuslcorominas.nasa.common.GetCallback;
import com.jesuslcorominas.nasa.data.net.dto.PhotoDto;

import java.util.Collection;

/**
 * @author Jesús López Corominas
 */
public interface PhotoRemoteDatasource extends RemoteDatasource {

    void getPhotos(Integer sol, String apiKey, GetPhotosCallback callback);

    interface GetPhotosCallback extends GetCallback<Collection<PhotoDto>> {

    }
}

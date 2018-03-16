package com.jesuslcorominas.nasa.data.datasource;

import com.jesuslcorominas.nasa.common.GenericCallback;
import com.jesuslcorominas.nasa.common.VoidCallback;
import com.jesuslcorominas.nasa.common.model.Photo;

import org.joda.time.DateTime;

import java.util.List;

/**
 * @author Jesús López Corominas
 */
public interface PhotoLocalDatasource extends Datasource {

    void getPhotosBySol(int sol, GetPhotosCallback callback);

    void getPhotosByDate(DateTime earthDate, GetPhotosCallback callback);

    void savePhotos(List<Photo> photos, SavePhotosCallback callback);

    interface SavePhotosCallback extends VoidCallback {

    }


    interface GetPhotosCallback extends GenericCallback<List<Photo>> {
    }
}

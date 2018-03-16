package com.jesuslcorominas.nasa.data.datasource.impl;

import com.jesuslcorominas.nasa.common.Error;
import com.jesuslcorominas.nasa.common.Result;
import com.jesuslcorominas.nasa.common.model.Photo;
import com.jesuslcorominas.nasa.common.util.DateUtil;
import com.jesuslcorominas.nasa.data.database.TransactionManager;
import com.jesuslcorominas.nasa.data.database.objectbox.dao.PhotoDao;
import com.jesuslcorominas.nasa.data.datasource.PhotoLocalDatasource;

import org.joda.time.DateTime;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author Jesús López Corominas
 */
public class PhotoLocalDatasourceImpl implements PhotoLocalDatasource {

    private final PhotoDao photoDao;

    private final TransactionManager transactionManager;

    public PhotoLocalDatasourceImpl(PhotoDao photoDao, TransactionManager transactionManager) {
        this.photoDao = photoDao;

        this.transactionManager = transactionManager;
    }

    @Override
    public void getPhotosBySol(int sol, GetPhotosCallback callback) {
        try {
            callback.onResult(new Result<>(photoDao.getPhotosBySol(sol)));
        } catch (Exception e) {
            callback.onResult(new Result<List<Photo>>(Error.DATABASE_GENERIC_ERROR, "Se ha producido un error al obtener las Photo para el sol " + sol + ": " + e.getMessage()));
        }

    }

    @Override
    public void getPhotosByDate(DateTime earthDate, GetPhotosCallback callback) {
        try {
            callback.onResult(new Result<>(photoDao.getPhotosByDate(earthDate)));
        } catch (Exception e) {
            callback.onResult(new Result<List<Photo>>(Error.DATABASE_GENERIC_ERROR, "Se ha producido un error al obtener las Photo de " + earthDate.toString(DateUtil.PRINT_DATE_FORMAT) + ": " + e.getMessage()));
        }
    }

    @Override
    public void savePhotos(final List<Photo> photos, SavePhotosCallback callback) {
        try {
            boolean result = transactionManager.callInTx(new Callable<Boolean>() {
                @Override
                public Boolean call() throws Exception {
                    try {
                        photoDao.save(photos);
                        return true;
                    } catch (Exception e) {
                        return false;
                    }
                }
            });

            if (result) {
                callback.onResult(new Result<Void>());
            } else {
                callback.onResult(new Result<Void>(Error.DATABASE_GENERIC_ERROR, "Se ha producido un error desconocido al insertar las Photo"));
            }
        } catch (Exception e) {
            callback.onResult(new Result<Void>(Error.DATABASE_GENERIC_ERROR, "Se ha producido un error al insertar las Photo: " + e.getMessage()));
        }
    }
}

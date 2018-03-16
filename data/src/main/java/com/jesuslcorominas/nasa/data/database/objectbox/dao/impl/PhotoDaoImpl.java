package com.jesuslcorominas.nasa.data.database.objectbox.dao.impl;

import com.jesuslcorominas.nasa.common.model.Photo;
import com.jesuslcorominas.nasa.data.database.objectbox.dao.PhotoDao;
import com.jesuslcorominas.nasa.data.database.objectbox.entity.PhotoEntity;
import com.jesuslcorominas.nasa.data.database.objectbox.entity.PhotoEntity_;

import org.joda.time.DateTime;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;

import io.objectbox.Box;

/**
 * @author Jesús López Corominas
 */
public class PhotoDaoImpl extends AbstractObjectBoxDao<Photo, PhotoEntity> implements PhotoDao {

    public PhotoDaoImpl(Box<PhotoEntity> box, ModelMapper mapper) {
        super(box, Photo.class, PhotoEntity.class,
                new TypeToken<List<Photo>>() {
                }.getType(),
                new TypeToken<List<PhotoEntity>>() {
                }.getType(), mapper);
    }

    @Override
    public List<Photo> getPhotosBySol(Integer sol) {
        return find(box.
                query().
                equal(PhotoEntity_.sol, sol).
                build());
    }

    @Override
    public List<Photo> getPhotosByDate(DateTime earthDate) {
        return find(box.
                query().
                equal(PhotoEntity_.earthDate, earthDate.getMillis()).
                build());
    }
}

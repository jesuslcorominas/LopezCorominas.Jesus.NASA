package com.jesuslcorominas.nasa.data.database.objectbox.dao;

import com.jesuslcorominas.nasa.common.model.Photo;
import com.jesuslcorominas.nasa.data.database.objectbox.entity.PhotoEntity;

import org.joda.time.DateTime;

import java.util.List;

/**
 * @author Jesús López Corominas
 */
public interface PhotoDao extends ObjectBoxDao<Photo, PhotoEntity> {

    List<Photo> getPhotosBySol(Integer sol);

    List<Photo> getPhotosByDate(DateTime dateTime);
}

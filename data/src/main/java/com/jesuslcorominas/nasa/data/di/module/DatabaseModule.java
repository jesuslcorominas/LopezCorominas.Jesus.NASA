package com.jesuslcorominas.nasa.data.di.module;

import com.jesuslcorominas.nasa.data.database.TransactionManager;
import com.jesuslcorominas.nasa.data.database.objectbox.ObjectBoxTransactionManager;
import com.jesuslcorominas.nasa.data.database.objectbox.dao.PhotoDao;
import com.jesuslcorominas.nasa.data.database.objectbox.dao.impl.PhotoDaoImpl;
import com.jesuslcorominas.nasa.data.database.objectbox.entity.MyObjectBox;
import com.jesuslcorominas.nasa.data.database.objectbox.entity.PhotoEntity;

import org.modelmapper.ModelMapper;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.objectbox.Box;
import io.objectbox.BoxStore;

/**
 * @author Jesús López Corominas
 */
@Module(includes = {MapperModule.class})
public class DatabaseModule {

    private static DatabaseModule instance;

    private BoxStore boxStore;

    private DatabaseModule(File baseDirectory) {
        boxStore = MyObjectBox.builder().baseDirectory(baseDirectory).build();
    }

    public static DatabaseModule getInstance(File baseDirectory) {
        if (instance == null) {
            instance = new DatabaseModule(baseDirectory);
        }

        return instance;
    }

    @Provides
    @Singleton
    BoxStore provideBoxStore() {
        return boxStore;
    }

    @Provides
    TransactionManager provideTransactionManager() {
        return new ObjectBoxTransactionManager(boxStore);
    }

    @Provides
    Box<PhotoEntity> providePhotoBox(BoxStore boxStore) {
        return boxStore.boxFor(PhotoEntity.class);
    }

    @Provides
    PhotoDao providePhotoDao(Box<PhotoEntity> box, ModelMapper mapper) {
        return new PhotoDaoImpl(box, mapper);
    }
}

package com.jesuslcorominas.nasa.data.di.module;

import com.jesuslcorominas.nasa.common.model.Camera;
import com.jesuslcorominas.nasa.common.model.Photo;
import com.jesuslcorominas.nasa.common.model.Rover;
import com.jesuslcorominas.nasa.data.database.objectbox.entity.CameraEntity;
import com.jesuslcorominas.nasa.data.database.objectbox.entity.PhotoEntity;
import com.jesuslcorominas.nasa.data.database.objectbox.entity.RoverEntity;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MappingContext;

import dagger.Module;
import dagger.Provides;
import io.objectbox.BoxStore;

/**
 * @author Jesús López Corominas
 */
@Module
public class MapperModule {


    @Provides
    Converter<Photo, PhotoEntity> provideConverterPhotoToPhotoEntity(final BoxStore boxStore) {
        return new Converter<Photo, PhotoEntity>() {
            @Override
            public PhotoEntity convert(MappingContext<Photo, PhotoEntity> context) {
                return toPhotoEntity(context.getSource(), boxStore);
            }
        };
    }

    @Provides
    Converter<PhotoEntity, Photo> provideConverterPhotoEntityToPhoto() {
        return new Converter<PhotoEntity, Photo>() {
            @Override
            public Photo convert(MappingContext<PhotoEntity, Photo> context) {
                return toPhoto(context.getSource());
            }
        };
    }

    @Provides
    Converter<Rover, RoverEntity> provideConverterRoverToRoverEntity(final BoxStore boxStore) {
        return new Converter<Rover, RoverEntity>() {
            @Override
            public RoverEntity convert(MappingContext<Rover, RoverEntity> context) {
                return toRoverEntity(context.getSource(), boxStore);
            }
        };
    }

    @Provides
    Converter<RoverEntity, Rover> provideConverterRoverEntityToRover() {
        return new Converter<RoverEntity, Rover>() {
            @Override
            public Rover convert(MappingContext<RoverEntity, Rover> context) {
                return toRover(context.getSource());
            }
        };
    }

    @Provides
    Converter<Camera, CameraEntity> provideConverterCameraToCameraEntity(final BoxStore boxStore) {
        return new Converter<Camera, CameraEntity>() {
            @Override
            public CameraEntity convert(MappingContext<Camera, CameraEntity> context) {
                return toCameraEntity(context.getSource(), boxStore);
            }
        };

    }

    @Provides
    Converter<CameraEntity, Camera> provideConverterCameraEntityToCamera() {
        return new Converter<CameraEntity, Camera>() {
            @Override
            public Camera convert(MappingContext<CameraEntity, Camera> context) {
                return toCamera(context.getSource());
            }
        };
    }


    @Provides
    ModelMapper provideModelMapper(Converter<Photo, PhotoEntity> converterPhotoToPhotoEntity,
                                   Converter<PhotoEntity, Photo> converterPhotoEntityToPhoto,
                                   Converter<Rover, RoverEntity> converterRoverToRoverEntity,
                                   Converter<RoverEntity, Rover> converterRoverEntityToRover,
                                   Converter<Camera, CameraEntity> converterCameraToCameraEntity,
                                   Converter<CameraEntity, Camera> converterCameraEntityToCamera) {
        ModelMapper mapper = new ModelMapper();

        mapper.addConverter(converterPhotoToPhotoEntity);
        mapper.addConverter(converterPhotoEntityToPhoto);
        mapper.addConverter(converterRoverToRoverEntity);
        mapper.addConverter(converterRoverEntityToRover);
        mapper.addConverter(converterCameraToCameraEntity);
        mapper.addConverter(converterCameraEntityToCamera);

        return mapper;
    }

    private Rover toRover(RoverEntity roverEntity) {
        if (roverEntity == null) {
            return null;
        }

        Rover rover = new Rover();
        rover.setId(roverEntity.getId());
        rover.setTotalPhotos(roverEntity.getTotalPhotos());
        rover.setName(roverEntity.getName());
        rover.setMaxSol(roverEntity.getMaxSol());
        rover.setStatus(roverEntity.getStatus());
        rover.setMaxDate(roverEntity.getMaxDate());
        rover.setLaunchDate(roverEntity.getLaunchDate());
        rover.setLandingDate(roverEntity.getLandingDate());

        for (CameraEntity cameraEntity : roverEntity.getCamerasRelation()) {
            Camera camera = new Camera();
            camera.setId(cameraEntity.getId());
            camera.setName(cameraEntity.getName());
            camera.setFullName(cameraEntity.getFullName());

            for (PhotoEntity photoEntity : cameraEntity.getPhotosRelation()) {
                Photo photo = new Photo();
                photo.setId(photoEntity.getId());
                photo.setEarthDate(photoEntity.getEarthDate());
                photo.setImgSrc(photoEntity.getImgSrc());
                photo.setSol(photoEntity.getSol());

                photo.setCamera(camera);
                camera.getPhotos().add(photo);
            }

            camera.setRover(rover);
            rover.getCameras().add(camera);
        }

        return rover;
    }

    private RoverEntity toRoverEntity(Rover rover, BoxStore boxStore) {
        if (rover == null || rover.getId() == null || rover.getId() == 0) {
            return null;
        }

        RoverEntity roverEntity = new RoverEntity();
        roverEntity.setId(rover.getId());
        boxStore.boxFor(RoverEntity.class).attach(roverEntity);

        roverEntity.setTotalPhotos(rover.getTotalPhotos());
        roverEntity.setName(rover.getName());
        roverEntity.setMaxSol(rover.getMaxSol());
        roverEntity.setStatus(rover.getStatus());
        roverEntity.setMaxDate(rover.getMaxDate());
        roverEntity.setLaunchDate(rover.getLaunchDate());
        roverEntity.setLandingDate(rover.getLandingDate());

        for (Camera camera : rover.getCameras()) {
            if (camera.getId() != null && camera.getId() != 0) {
                CameraEntity cameraEntity = new CameraEntity();
                cameraEntity.setId(camera.getId());
                boxStore.boxFor(CameraEntity.class).attach(cameraEntity);

                cameraEntity.setName(camera.getName());
                cameraEntity.setFullName(camera.getFullName());

                for (Photo photo : camera.getPhotos()) {
                    if (photo.getId() != null && photo.getId() != 0) {
                        PhotoEntity photoEntity = new PhotoEntity();
                        photoEntity.setId(photo.getId());
                        boxStore.boxFor(PhotoEntity.class).attach(photoEntity);

                        photoEntity.setEarthDate(photo.getEarthDate());
                        photoEntity.setImgSrc(photo.getImgSrc());
                        photoEntity.setSol(photo.getSol());

                        photoEntity.setCamera(cameraEntity);
                        cameraEntity.getPhotosRelation().add(photoEntity);
                    }
                }

                cameraEntity.setRover(roverEntity);
                roverEntity.getCamerasRelation().add(cameraEntity);
            }
        }

        return roverEntity;
    }

    private Camera toCamera(CameraEntity cameraEntity) {
        if (cameraEntity == null) {
            return null;
        }

        Camera camera = new Camera();
        camera.setId(cameraEntity.getId());
        camera.setName(cameraEntity.getName());
        camera.setFullName(cameraEntity.getFullName());

        for (PhotoEntity photoEntity : cameraEntity.getPhotosRelation()) {
            Photo photo = new Photo();
            photo.setId(photoEntity.getId());
            photo.setEarthDate(photoEntity.getEarthDate());
            photo.setImgSrc(photoEntity.getImgSrc());
            photo.setSol(photoEntity.getSol());

            photo.setCamera(camera);
            camera.getPhotos().add(photo);
        }

        camera.setRover(toRover(cameraEntity.getRover()));

        return camera;
    }

    private CameraEntity toCameraEntity(Camera camera, BoxStore boxStore) {
        if (camera == null || camera.getId() == null || camera.getId() == 0) {
            return null;
        }

        CameraEntity cameraEntity = new CameraEntity();
        cameraEntity.setId(camera.getId());
        boxStore.boxFor(CameraEntity.class).attach(cameraEntity);

        cameraEntity.setName(camera.getName());
        cameraEntity.setFullName(camera.getFullName());

        for (Photo photo : camera.getPhotos()) {
            if (photo.getId() != null && photo.getId() != 0) {
                PhotoEntity photoEntity = new PhotoEntity();
                photoEntity.setId(photo.getId());
                boxStore.boxFor(PhotoEntity.class).attach(photoEntity);

                photoEntity.setEarthDate(photo.getEarthDate());
                photoEntity.setImgSrc(photo.getImgSrc());
                photoEntity.setSol(photo.getSol());

                photoEntity.setCamera(cameraEntity);
                cameraEntity.getPhotosRelation().add(photoEntity);
            }
        }

        cameraEntity.setRover(toRoverEntity(camera.getRover(), boxStore));

        return cameraEntity;
    }

    private Photo toPhoto(PhotoEntity photoEntity) {
        if (photoEntity == null) {
            return null;
        }

        Photo photo = new Photo();
        photo.setId(photoEntity.getId());
        photo.setEarthDate(photoEntity.getEarthDate());
        photo.setImgSrc(photoEntity.getImgSrc());
        photo.setSol(photoEntity.getSol());

        photo.setCamera(toCamera(photoEntity.getCamera()));
        photo.setRover(toRover(photoEntity.getRover()));

        return photo;
    }

    private PhotoEntity toPhotoEntity(Photo photo, BoxStore boxStore) {
        if (photo == null || photo.getId() == null || photo.getId() == 0) {
            return null;
        }

        PhotoEntity photoEntity = new PhotoEntity();
        photoEntity.setId(photo.getId());
        boxStore.boxFor(PhotoEntity.class).attach(photoEntity);

        photoEntity.setEarthDate(photo.getEarthDate());
        photoEntity.setImgSrc(photo.getImgSrc());
        photoEntity.setSol(photo.getSol());

        photoEntity.setCamera(toCameraEntity(photo.getCamera(), boxStore));
        photoEntity.setRover(toRoverEntity(photo.getRover(), boxStore));

        return photoEntity;
    }
}

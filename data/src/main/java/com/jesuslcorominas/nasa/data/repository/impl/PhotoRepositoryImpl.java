package com.jesuslcorominas.nasa.data.repository.impl;

import com.jesuslcorominas.nasa.common.Result;
import com.jesuslcorominas.nasa.common.model.Photo;
import com.jesuslcorominas.nasa.data.datasource.PhotoLocalDatasource;
import com.jesuslcorominas.nasa.data.datasource.PhotoRemoteDatasource;
import com.jesuslcorominas.nasa.data.net.dto.PhotoDto;
import com.jesuslcorominas.nasa.model.repository.PhotoRepository;

import org.joda.time.DateTime;

import java.util.List;

/**
 * {@inheritDoc}
 * <p>
 * Implementacion del {@link PhotoRepository}. Tiene una unica fuente de datos remota y cada vez
 * que se quiere obtener el listado de {@link Photo} se hace una llamada que nos devolvera
 * un listado de {@link PhotoDto}.
 * <p>
 * TODO agregar una fuente de datos local para que si no hay datos se pidan a remoto y se guarden
 *
 * @author Jesús López Corominas
 */
public class PhotoRepositoryImpl implements PhotoRepository {

    /**
     * Fuente de datos remota
     */
    private final PhotoRemoteDatasource photoRemoteDatasource;

    private final PhotoLocalDatasource photoLocalDatasource;

    /**
     * Constructor unico parametrizado con la fuente de datos remota. Sera usado por Dagger2 para
     * inyectar las dependencias de la clase
     *
     * @param photoRemoteDatasource La fuente de datos remota
     */
    public PhotoRepositoryImpl(PhotoRemoteDatasource photoRemoteDatasource, PhotoLocalDatasource photoLocalDatasource) {
        this.photoRemoteDatasource = photoRemoteDatasource;
        this.photoLocalDatasource = photoLocalDatasource;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Hace una llamada a la fuente de datos remota para obtener un listado de {@link PhotoDto}.
     * Mapeamos esos objetos con ModelMapper a {@link Photo} para devolverlos a la capa
     * superior
     *
     * @param sol      El sol del que se quieren obtener las {@link Photo}
     * @param callback Objeto donde tratar la respuesta
     */
    @Override
    public void getPhotosBySol(final Integer sol, final GetPhotosCallback callback) {
//        photoRemoteDatasource.getPhotosBySol(sol, new PhotoRemoteDatasource.GetPhotosCallback() {
//            @Override
//            public void onResult(Result<List<Photo>> result) {
//                callback.onResult(result);
//            }
//        });


        photoLocalDatasource.getPhotosBySol(sol, new PhotoLocalDatasource.GetPhotosCallback() {
            @Override
            public void onResult(final Result<List<Photo>> result) {
                if (result.getResultCode() != Result.OK) {
                    callback.onResult(result);
                    return;
                }

                if (result.getData() != null && result.getData().size() > 0) {
                    callback.onResult(result);
                    return;
                }

                photoRemoteDatasource.getPhotosBySol(sol, new PhotoRemoteDatasource.GetPhotosCallback() {

                    @Override
                    public void onResult(final Result<List<Photo>> remotePhotos) {
                        if (remotePhotos.getResultCode() != Result.OK) {
                            callback.onResult(result);
                            return;
                        }

                        photoLocalDatasource.savePhotos(remotePhotos.getData(), new PhotoLocalDatasource.SavePhotosCallback() {
                            @Override
                            public void onResult(Result<Void> saveResult) {
                                if (result.getResultCode() != Result.OK) {
                                    callback.onResult(new Result<List<Photo>>(saveResult));
                                    return;
                                }

                                callback.onResult(remotePhotos);
                            }
                        });
                    }
                });

            }
        });
    }

    @Override
    public void getPhotosByDate(final DateTime earthDate, final GetPhotosCallback callback) {
        photoLocalDatasource.getPhotosByDate(earthDate, new PhotoLocalDatasource.GetPhotosCallback() {
            @Override
            public void onResult(Result<List<Photo>> persistedPhotos) {
                if (persistedPhotos.getResultCode() != 0) {
                    // Si ocurre un error obteniendo de base de datos lo devolvemos
                    callback.onResult(persistedPhotos);
                    return;
                }

                if (persistedPhotos.getData() != null && persistedPhotos.getData().size() > 0) {
                    // Si hay fotos en base de datos, las devolvemos
                    callback.onResult(persistedPhotos);
                    return;
                }

                photoRemoteDatasource.getPhotosByDate(earthDate, new PhotoRemoteDatasource.GetPhotosCallback() {

                    @Override
                    public void onResult(final Result<List<Photo>> remotePhotos) {
                        if (remotePhotos.getResultCode() != Result.OK) {
                            // Si ocurre un error al obtener las Photo remotas, lo devolvemos
                            callback.onResult(remotePhotos);
                            return;
                        }

                        if (remotePhotos.getData() == null || remotePhotos.getData().size() == 0) {
                            // Si no hay fotos remotas lo devolvemos
                            callback.onResult(remotePhotos);
                            return;
                        }

                        photoLocalDatasource.savePhotos(remotePhotos.getData(), new PhotoLocalDatasource.SavePhotosCallback() {
                            @Override
                            public void onResult(Result<Void> saveResult) {
                                if (saveResult.getResultCode() != Result.OK) {
                                    // Si ocurre un error al guardar las Photo remotas lo devolvemos
                                    callback.onResult(new Result<List<Photo>>(saveResult));
                                    return;
                                }

                                // Devolvemos las Photo remotas ?? TODO devolver estas o las persistidas?
                                callback.onResult(remotePhotos);
                            }
                        });
                    }
                });
            }
        });
    }
}

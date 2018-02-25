package com.jesuslcorominas.nasa.data.repository.impl;

import com.jesuslcorominas.nasa.common.Error;
import com.jesuslcorominas.nasa.data.datasource.remote.PhotoRemoteDatasource;
import com.jesuslcorominas.nasa.data.net.dto.PhotoDto;
import com.jesuslcorominas.nasa.model.entity.PhotoModelEntity;
import com.jesuslcorominas.nasa.model.repository.PhotoRepository;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.Collection;

/**
 * {@inheritDoc}
 * <p>
 * Implementacion del {@link PhotoRepository}. Tiene una unica fuente de datos remota y cada vez
 * que se quiere obtener el listado de {@link PhotoModelEntity} se hace una llamada que nos devolvera
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

    /**
     * Constructor unico parametrizado con la fuente de datos remota. Sera usado por Dagger2 para
     * inyectar las dependencias de la clase
     *
     * @param photoRemoteDatasource La fuente de datos remota
     */
    public PhotoRepositoryImpl(PhotoRemoteDatasource photoRemoteDatasource) {
        this.photoRemoteDatasource = photoRemoteDatasource;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Hace una llamada a la fuente de datos remota para obtener un listado de {@link PhotoDto}.
     * Mapeamos esos objetos con ModelMapper a {@link PhotoModelEntity} para devolverlos a la capa
     * superior
     *
     * @param sol      El sol del que se quieren obtener las {@link PhotoModelEntity}
     * @param apiKey   El apiKey para la peticion
     * @param callback Objeto donde tratar la respuesta
     * @see <a href="http://modelmapper.org/">ModelMapper</a>
     */
    @Override
    public void getPhotos(Integer sol, String apiKey, final GetPhotosCallback callback) {
        photoRemoteDatasource.getPhotos(sol, apiKey, new PhotoRemoteDatasource.GetPhotosCallback() {
            @Override
            public void onSuccess(Collection<PhotoDto> result) {
                Collection<PhotoModelEntity> photoEntities = new ModelMapper().map(result, new TypeToken<Collection<PhotoModelEntity>>() {
                }.getType());
                callback.onSuccess(photoEntities);
            }

            @Override
            public void onError(Error error) {
                callback.onError(error);
            }
        });
    }
}

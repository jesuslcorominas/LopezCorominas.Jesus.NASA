package com.jesuslcorominas.nasa.data.net.client.impl;

import com.jesuslcorominas.nasa.common.Error;
import com.jesuslcorominas.nasa.common.Result;
import com.jesuslcorominas.nasa.common.model.Photo;
import com.jesuslcorominas.nasa.data.net.client.PhotoClient;
import com.jesuslcorominas.nasa.data.net.dto.GetPhotosResponseDto;

import org.joda.time.DateTime;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * {@inheritDoc}
 *
 * @author Jesús López Corominas
 */
@SuppressWarnings("NullableProblems") /* SuppressWarning from retrofit @ParameterAreNonnullByDefault */
public class PhotoClientImpl implements PhotoClient {

    /**
     * Api de retrofit para realizar las llamadas a los servicios Rest
     */
    private final PhotoClient.Api api;

    private final ModelMapper mapper;

    /**
     * Constructor unico con la {@link com.jesuslcorominas.nasa.data.net.client.PhotoClient.Api} de
     * retrofit. Sera utilizado por Dagger2 para inyectar las dependecias de la clase.
     *
     * @param api La api de retrofit para las llamadas remotas
     */
    public PhotoClientImpl(Api api, ModelMapper mapper) {
        this.api = api;
        this.mapper = mapper;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Invoca a la {@link com.jesuslcorominas.nasa.data.net.client.PhotoClient.Api} de Retrofit para
     * conseguir el listado de {@link com.jesuslcorominas.nasa.data.net.dto.PhotoDto}
     *
     * @param sol      El sol del que se quieren obtener las {@link com.jesuslcorominas.nasa.data.net.dto.PhotoDto}
     * @param callback Objeto donde tratar la respuesta
     */
    @Override
    public void getPhotosBySol(Integer sol, final GetPhotosCallback callback) {
        api.photosBySol(sol).enqueue(new Callback<GetPhotosResponseDto>() {
            @Override
            public void onResponse(Call<GetPhotosResponseDto> call, Response<GetPhotosResponseDto> response) {
                if (!response.isSuccessful()) {
                    callback.onResult(new Result<List<Photo>>(Error.HTTP_ERROR, "HTTP Error: " + response.code()));
                    return;
                }

                if (response.body() != null) {
                    GetPhotosResponseDto photosResponseDto = response.body();
                    if (photosResponseDto != null) {
                        List<Photo> photoEntities = mapper.map(photosResponseDto.getPhotos(), new TypeToken<List<Photo>>() {
                        }.getType());

                        callback.onResult(new Result<>(photoEntities));
                        return;
                    }
                }

                // No deberiamos llegar nunca hasta aqui
                callback.onResult(new Result<List<Photo>>(Error.NULL, "La peticion de getPhotos no ha devuelto resultado"));
            }

            @Override
            public void onFailure(Call<GetPhotosResponseDto> call, Throwable t) {
                callback.onResult(new Result<List<Photo>>(Error.GENERIC_ERROR, "Petition error: " + t.getMessage()));
            }
        });
    }

    @Override
    public void getPhotosByDate(DateTime earthDate, final GetPhotosCallback callback) {
        api.photosByDate(earthDate).enqueue(new Callback<GetPhotosResponseDto>() {
            @Override
            public void onResponse(Call<GetPhotosResponseDto> call, Response<GetPhotosResponseDto> response) {
                if (!response.isSuccessful()) {
                    callback.onResult(new Result<List<Photo>>(Error.HTTP_ERROR, "HTTP Error: " + response.code()));
                    return;
                }

                if (response.body() != null) {
                    GetPhotosResponseDto photosResponseDto = response.body();
                    if (photosResponseDto != null) {
                        List<Photo> photoEntities = mapper.map(photosResponseDto.getPhotos(), new TypeToken<List<Photo>>() {
                        }.getType());

                        callback.onResult(new Result<>(photoEntities));
                        return;
                    }
                }

                // No deberiamos llegar nunca hasta aqui
                callback.onResult(new Result<List<Photo>>(Error.NULL, "La peticion de getPhotos no ha devuelto resultado"));
            }

            @Override
            public void onFailure(Call<GetPhotosResponseDto> call, Throwable t) {
                callback.onResult(new Result<List<Photo>>(Error.GENERIC_ERROR, "Petition error: " + t.getMessage()));
            }
        });
    }
}

package com.jesuslcorominas.nasa.data.net.client.impl;

import com.jesuslcorominas.nasa.common.Error;
import com.jesuslcorominas.nasa.data.net.client.PhotoClient;
import com.jesuslcorominas.nasa.data.net.dto.GetPhotosResponseDto;

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

    /**
     * Constructor unico con la {@link com.jesuslcorominas.nasa.data.net.client.PhotoClient.Api} de
     * retrofit. Sera utilizado por Dagger2 para inyectar las dependecias de la clase.
     *
     * @param api La api de retrofit para las llamadas remotas
     */
    public PhotoClientImpl(Api api) {
        this.api = api;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Invoca a la {@link com.jesuslcorominas.nasa.data.net.client.PhotoClient.Api} de Retrofit para
     * conseguir el listado de {@link com.jesuslcorominas.nasa.data.net.dto.PhotoDto}
     *
     * @param sol      El sol del que se quieren obtener las {@link com.jesuslcorominas.nasa.data.net.dto.PhotoDto}
     * @param apiKey   El api key para la peticion
     * @param callback Objeto donde tratar la respuesta
     */
    @Override
    public void getPhotos(Integer sol, String apiKey, final GetPhotosCallback callback) {
        api.photos(sol, apiKey).enqueue(new Callback<GetPhotosResponseDto>() {
            @Override
            public void onResponse(Call<GetPhotosResponseDto> call, Response<GetPhotosResponseDto> response) {
                if (!response.isSuccessful()) {
                    callback.onError(new Error(Error.HTTP_ERROR, "HTTP Error: " + response.code()));
                    return;
                }

                callback.onSuccess(response.body().getPhotos());
            }

            @Override
            public void onFailure(Call<GetPhotosResponseDto> call, Throwable t) {
                callback.onError(new Error(Error.GENERIC_ERROR, "Petition error: " + t.getMessage()));
            }
        });
    }
}

package com.jesuslcorominas.nasa.data.net.client.impl;

import com.jesuslcorominas.nasa.common.Error;
import com.jesuslcorominas.nasa.data.net.client.PhotoClient;
import com.jesuslcorominas.nasa.data.net.dto.ServerResponseDto;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Jesús López Corominas
 */
@SuppressWarnings("NullableProblems") /* SuppressWarning from retrofit @ParameterAreNonnullByDefault */
public class PhotoClientImpl implements PhotoClient {

    private final PhotoClient.Api api;

    public PhotoClientImpl(Api api) {
        this.api = api;
    }

    @Override
    public void getPhotos(Integer sol, String apiKey, final GetPhotosCallback callback) {
        api.photos(sol, apiKey).enqueue(new Callback<ServerResponseDto>() {
            @Override
            public void onResponse(Call<ServerResponseDto> call, Response<ServerResponseDto> response) {
                if (!response.isSuccessful()) {
                    callback.onError(new Error(Error.HTTP_ERROR, "HTTP Error: " + response.code()));
                    return;
                }

                callback.onSuccess(response.body().getPhotos());
            }

            @Override
            public void onFailure(Call<ServerResponseDto> call, Throwable t) {
                callback.onError(new Error(Error.GENERIC_ERROR, "Petition error: " + t.getMessage()));
            }
        });
    }
}

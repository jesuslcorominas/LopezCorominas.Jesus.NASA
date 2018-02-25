package com.jesuslcorominas.nasa.data.net.client;

import com.jesuslcorominas.nasa.common.GetCallback;
import com.jesuslcorominas.nasa.data.net.dto.PhotoDto;
import com.jesuslcorominas.nasa.data.net.dto.GetPhotosResponseDto;
import com.jesuslcorominas.nasa.data.net.util.NetKeys;

import java.util.Collection;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author Jesús López Corominas
 */
public interface PhotoClient {

    void getPhotos(Integer sol, String apiKey, GetPhotosCallback callback);

    interface Api {

        @GET(NetKeys.METHOD_GET_PHOTOS)
        Call<GetPhotosResponseDto> photos(@Query(NetKeys.PARAM_SOL) Integer sol, @Query(NetKeys.PARAM_API_KEY) String apiKey);
    }

    interface GetPhotosCallback extends GetCallback<Collection<PhotoDto>> {

    }
}

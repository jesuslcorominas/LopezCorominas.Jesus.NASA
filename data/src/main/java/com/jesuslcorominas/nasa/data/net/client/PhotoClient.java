package com.jesuslcorominas.nasa.data.net.client;

import com.jesuslcorominas.nasa.common.GetCallback;
import com.jesuslcorominas.nasa.data.net.dto.GetPhotosResponseDto;
import com.jesuslcorominas.nasa.data.net.dto.PhotoDto;
import com.jesuslcorominas.nasa.data.net.util.NetKeys;

import java.util.Collection;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Cliente Rest para {@link PhotoDto}. Contiene un metodo para obtener un listado de {@link PhotoDto}
 * en funcion de un sol y de un api key
 *
 * @author Jesús López Corominas
 */
public interface PhotoClient {

    /**
     * Obtiene un listado de {@link PhotoDto}
     *
     * @param sol      El sol del que se quieren obtener las {@link PhotoDto}
     * @param apiKey   El api key para la peticion
     * @param callback Objeto donde tratar la respuesta
     */
    void getPhotos(Integer sol, String apiKey, GetPhotosCallback callback);

    /**
     * Interface de Retrofit para las llamadas a los servicios Rest relacionados con {@link PhotoDto}
     */
    interface Api {

        /**
         * Construye la peticion remota para obtener un listado de {@link PhotoDto} en funcion de
         * un sol y una api key
         *
         * @param sol    El sol del que se quieren obtener las {@link PhotoDto}
         * @param apiKey El api key para la peticion
         * @return Objeto Call para ejecutar la peticion remota
         * @see <a href="http://square.github.io/retrofit/">Retrofit</a>
         */
        @GET(NetKeys.METHOD_GET_PHOTOS)
        Call<GetPhotosResponseDto> photos(@Query(NetKeys.PARAM_SOL) Integer sol, @Query(NetKeys.PARAM_API_KEY) String apiKey);
    }

    /**
     * Callback para tratar el resultado de la peticion para obtener las {@link PhotoDto}
     */
    interface GetPhotosCallback extends GetCallback<Collection<PhotoDto>> {

    }
}

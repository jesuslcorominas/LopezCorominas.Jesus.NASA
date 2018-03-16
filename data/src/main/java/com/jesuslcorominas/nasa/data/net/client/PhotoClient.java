package com.jesuslcorominas.nasa.data.net.client;

import com.jesuslcorominas.nasa.common.GenericCallback;
import com.jesuslcorominas.nasa.common.model.Photo;
import com.jesuslcorominas.nasa.data.net.dto.GetPhotosResponseDto;
import com.jesuslcorominas.nasa.data.net.dto.PhotoDto;
import com.jesuslcorominas.nasa.data.net.util.NetKeys;

import org.joda.time.DateTime;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Cliente Rest para {@link Photo}. Contiene un metodo para obtener un listado de {@link Photo}
 * en funcion de un sol y de un api key
 *
 * @author Jesús López Corominas
 */
public interface PhotoClient {

    /**
     * Obtiene un listado de {@link Photo}
     *
     * @param sol      El sol del que se quieren obtener las {@link Photo}
     * @param callback Objeto donde tratar la respuesta
     */
    void getPhotosBySol(Integer sol, GetPhotosCallback callback);

    /**
     * Obtiene un listado de {@link Photo}
     *
     * @param earthDate La fecha en la tierra de la que se quieren obtener las {@link Photo}
     * @param callback  Objeto donde tratar la respuesta
     */
    void getPhotosByDate(DateTime earthDate, GetPhotosCallback callback);

    /**
     * Interface de Retrofit para las llamadas a los servicios Rest relacionados con {@link Photo}
     */
    interface Api {

        /**
         * Construye la peticion remota para obtener un listado de {@link Photo} en funcion de
         * un sol y una api key
         *
         * @param sol El sol del que se quieren obtener las {@link Photo}
         * @return Objeto Call para ejecutar la peticion remota
         * @see <a href="http://square.github.io/retrofit/">Retrofit</a>
         */
        @GET(NetKeys.METHOD_GET_PHOTOS)
        Call<GetPhotosResponseDto> photosBySol(@Query(NetKeys.PARAM_SOL) Integer sol);

        /**
         * Construye la peticion remota para obtener un listado de {@link Photo} en funcion de
         * un sol y una api key
         *
         * @param earthDate La fecha en la tierra de la que se quieren obtener las {@link Photo}
         * @return Objeto Call para ejecutar la peticion remota
         * @see <a href="http://square.github.io/retrofit/">Retrofit</a>
         */
        @GET(NetKeys.METHOD_GET_PHOTOS)
        Call<GetPhotosResponseDto> photosByDate(@Query(NetKeys.PARAM_HEARTH_DATE) DateTime earthDate);
    }

    /**
     * Callback para tratar el resultado de la peticion para obtener las {@link PhotoDto}
     */
    interface GetPhotosCallback extends GenericCallback<List<Photo>> {

    }
}

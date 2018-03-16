package com.jesuslcorominas.nasa.data.datasource;

import com.jesuslcorominas.nasa.common.GenericCallback;
import com.jesuslcorominas.nasa.common.model.Photo;
import com.jesuslcorominas.nasa.data.net.dto.PhotoDto;

import org.joda.time.DateTime;

import java.util.List;

/**
 * Fuente de datos remota de {@link PhotoDto}. Contiene un metodo para obtener un listado de
 * {@link PhotoDto}
 *
 * @author Jesús López Corominas
 */
public interface PhotoRemoteDatasource extends Datasource {

    /**
     * Obtiene un listado de {@link Photo}
     *
     * @param sol      El sol del que se quiere obtener el listado de {@link Photo}
     * @param callback El objeto donde tratar el resultado de la peticion
     */
    void getPhotosBySol(Integer sol, GetPhotosCallback callback);

    void getPhotosByDate(DateTime earthDate, GetPhotosCallback callback);

    /**
     * Callback para tratar el resultado de la peticion para obtener las {@link Photo}
     */
    interface GetPhotosCallback extends GenericCallback<List<Photo>> {

    }
}

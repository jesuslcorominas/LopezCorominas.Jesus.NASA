package com.jesuslcorominas.nasa.data.net.util;

/**
 * Clase de utilidades con las constantes utilizadas por los servicios Rest
 *
 * @author Jesús López Corominas
 */
public abstract class NetKeys {

    /**
     * Parametro sol a enviar en la peticion
     */
    public static final String PARAM_SOL = "sol";

    /**
     * Parametro api_key a enviar en la peticion
     */
    public static final String PARAM_API_KEY = "api_key";

    /**
     * Ruta base de los servicios Rest
     */
    private static final String API_PATH = "api/v1/rovers/curiosity/";

    /**
     * Metodo de la api Rest para obtener el listado de {@link com.jesuslcorominas.nasa.data.net.dto.PhotoDto}
     */
    public static final String METHOD_GET_PHOTOS = API_PATH + "photos";

    /**
     * Constructor unico privado para evitar la instaciacion
     */
    private NetKeys() {

    }


}

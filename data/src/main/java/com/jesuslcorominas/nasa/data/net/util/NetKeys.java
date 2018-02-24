package com.jesuslcorominas.nasa.data.net.util;

/**
 * @author Jesús López Corominas
 */
public abstract class NetKeys {

    public static final String PATH_PHOTOS = "photos";

    public static final String PARAM_SOL = "sol";
    public static final String PARAM_API_KEY = "api_key";

    private static final String API_PATH = "api/v1/rovers/curiosity/";

    public static final String METHOD_GET_PHOTOS = API_PATH + "photos";

    private NetKeys() {

    }


}

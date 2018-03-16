package com.jesuslcorominas.nasa.common;

/**
 * Clase que define un error producido en la aplicacion. Contiene un codigo de error y un mensaje
 * con informacion sobre el error producido
 * <p>
 * Codigo 0: RESERVADO OK
 * Codigo 1-999:        Errores varios
 * Codigo 1000-1999:    Errores en las peticiones remotas
 * Codigo 2000-2999:    Errores en base de datos
 *
 * @author Jesús López Corominas
 */
public class Error {

    /**
     * Codigo de error generico
     */
    public static final int GENERIC_ERROR = 1;

    /**
     * Error en preferencias
     */
    public static final int PREFERENCES_ERROR = 2;

    /**
     * Codigo de error en una peticion HTTP
     */
    public static final int HTTP_ERROR = 1000;

    /**
     * Codigo de error para cuando el resultado de la peticion es nulo
     */
    public static final int NULL = 1001;

    /**
     * Codigo de error generico de base de datos     *
     */
    public static final int DATABASE_GENERIC_ERROR = 2000;

    /**
     * El codigo del error
     */
    private final int code;

    /**
     * El mensaje de error. Este mensaje, en un principio, no es para mostrar al usuario al no
     * estar internacionalizado
     */
    private final String message;

    /**
     * Constructor unico parametrizado con el codigo del error y el mensaje de informacion.
     *
     * @param code    El codigo del error
     * @param message El mensaje de error
     */
    public Error(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Obtiene el codigo del error
     *
     * @return el codigo del error
     */
    public int getCode() {
        return code;
    }

    /**
     * Obtiene el mensaje del error
     *
     * @return el mensaje del error
     */
    public String getMessage() {
        return message;
    }
}

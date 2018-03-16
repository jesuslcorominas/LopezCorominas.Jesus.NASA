package com.jesuslcorominas.nasa.common.util;

import org.joda.time.DateTime;

/**
 * Clase de utilidades para el tratamiento de fechas
 *
 * @author Jesús López Corominas
 */
public abstract class DateUtil {


    /**
     * Formato de fecha interno a la aplicacion. Es, por ejemplo, el formato en el que se recibe
     * la fecha en el servicio Rest
     */
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    /**
     * Formato de fecha para mostrar al usuario
     */
    public static final String PRINT_DATE_FORMAT = "dd/MM/yyyy";

    /**
     * Constructor privado unico para evitar la instanciacion
     */
    private DateUtil() {

    }

    /**
     * La fecha minima para mostrar {@link com.jesuslcorominas.nasa.common.model.Photo}: 1 de enero de 2017, por poner una
     */
    public static DateTime minDate() {
        return new DateTime(2017, 1, 1, 0, 0);
    }

    public static DateTime maxDate() {
        return new DateTime().minusDays(1);
    }
}

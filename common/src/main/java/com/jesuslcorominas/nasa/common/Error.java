package com.jesuslcorominas.nasa.common;

/**
 * @author Jesús López Corominas
 */
public class Error {

    public static final int GENERIC_ERROR = 1000;

    public static final int HTTP_ERROR = 1001;

    private final int code;

    private final String message;

    public Error(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

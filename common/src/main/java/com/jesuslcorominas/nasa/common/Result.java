package com.jesuslcorominas.nasa.common;

/**
 * @author Jesús López Corominas
 */
public class Result<T> {

    public static final int OK = 0;

    private int resultCode;
    private String message;

    private T data;

    public Result() {
        this(OK, "OK");
    }

    public Result(T data) {
        this(OK, "OK", data);
    }

    public Result(Result origin) {
        this(origin.getResultCode(), origin.getMessage());
    }

    public Result(int resultCode, String message) {
        this(resultCode, message, null);
    }

    public Result(int resultCode, String message, T data) {
        this.resultCode = resultCode;
        this.message = message;
        this.data = data;
    }


    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

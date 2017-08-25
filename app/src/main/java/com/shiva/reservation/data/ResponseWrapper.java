package com.shiva.reservation.data;

/**
 * Created by shivananda.darura on 17/08/17.
 */
public class ResponseWrapper<T> {

    private int code;
    private T response;
    private Throwable throwable;

    public ResponseWrapper(int code, T response) {
        this.code = code;
        this.response = response;
    }

    public ResponseWrapper(int code, T response, Throwable throwable) {
        this(code, response);
        this.throwable = throwable;
    }

    public ResponseWrapper(T response) {
        this.response = response;
    }

    public int getCode() {
        return code;
    }

    public T getResponse() {
        return response;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}

package com.navent.api.orders.exception;

public abstract class AppException extends Exception {

    private String code;

    public AppException(long code, String message) {
        super(message);
        this.code = String.valueOf(code);
    }

    public String getCode() {
        return code;
    }
}

package com.navent.api.orders.error;

public class ErrorMessage {

    private final String code;

    private final String message;

    public ErrorMessage(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

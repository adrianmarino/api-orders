package com.navent.api.orders.error;

import com.navent.api.orders.exception.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.stream.Stream;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

public class ErrorResponseFactory {

    private ErrorResponseFactory() {
    }

    public static ResponseEntity<ErrorMessage> create(AppException exception) {
        return new ResponseEntity<>(
                new ErrorMessage(exception.getCode(), exception.getMessage()),
                getStatus(exception.getCode())
        );
    }

    private static HttpStatus getStatus(String code) {
        return Stream.of(HttpStatus.values())
                .filter(it -> code.startsWith(String.valueOf(it.value())))
                .findFirst()
                .orElse(INTERNAL_SERVER_ERROR);
    }
}

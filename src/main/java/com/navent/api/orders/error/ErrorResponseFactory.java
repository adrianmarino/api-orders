package com.navent.api.orders.error;

import com.navent.api.orders.exception.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Service
public class ErrorResponseFactory {

    public ResponseEntity<ErrorMessage> create(AppException exception) {
        return new ResponseEntity<>(
                new ErrorMessage(exception.getCode(), exception.getMessage()),
                getStatus(exception.getCode())
        );
    }

    private HttpStatus getStatus(String code) {
        return Stream.of(HttpStatus.values())
                .filter(it -> code.startsWith(String.valueOf(it.value())))
                .findFirst()
                .orElse(INTERNAL_SERVER_ERROR);
    }
}

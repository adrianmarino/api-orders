package com.navent.api.orders.persistence;

import com.navent.api.orders.exception.AppException;

import static java.lang.String.format;

public class NotFoundEntityException extends AppException {
    public NotFoundEntityException(String id) {
        super(404100, format("Not found entity with %s identifiers!", id));
    }
}

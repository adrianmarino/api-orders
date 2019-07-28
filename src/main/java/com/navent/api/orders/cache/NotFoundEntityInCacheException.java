package com.navent.api.orders.cache;

import com.navent.api.orders.exception.AppException;

import static java.lang.String.format;

public class NotFoundEntityInCacheException extends AppException {
    public NotFoundEntityInCacheException(String name) {
        super(500100, format("Not found %s cache", name));
    }
}

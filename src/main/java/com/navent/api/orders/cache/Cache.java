package com.navent.api.orders.cache;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Supplier;

import static java.util.Optional.ofNullable;

public class Cache<V> {

    private Map<String, V> map;

    public Cache() {
        clean();
    }

    public Optional<V> get(String key, Supplier<Optional<V>> supplier) {
        return ofNullable(map.computeIfAbsent(key, (k) -> {
            Optional<V> value = supplier.get();
            if (value.isPresent()) {
                return value.get();
            } else {
                return null;
            }
        }));
    }

    public void clean() {
        this.map = new ConcurrentHashMap<>();
    }

    public Integer entriesCount() {
        return map.size();
    }
}

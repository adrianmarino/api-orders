package com.navent.api.orders.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

@Service
public class BumexMemcachedPool {

    public static final String ORDERS_CACHE = "orders";

    private static final Logger LOGGER = LoggerFactory.getLogger(BumexMemcachedPool.class);

    private final Map<String, Cache<Object>> caches;

    @Autowired
    public BumexMemcachedPool() {
        caches = new HashMap<>();
        caches.put(ORDERS_CACHE, new Cache<>());
    }

    @SuppressWarnings("unchecked")
    public <ENTITY> Cache<ENTITY> get(String name) throws NotFoundEntityInCacheException {
        Cache<Object> cache = caches.get(name);
        if (isNull(cache))
            throw new NotFoundEntityInCacheException(name);
        return (Cache<ENTITY>) cache;
    }

    @Scheduled(fixedRate = 15000) // TODO: 15 seg => Only for test purpose.
    public void cleanJob() {
        caches.forEach((cacheName, cache) -> {
            if (cache.entriesCount() == 0) return;

            Integer beforeCleanCount = cache.entriesCount();
            cache.clean();
            LOGGER.info(
                    "{} entity/es removed from {} cache (Actual size: {}).",
                    beforeCleanCount, cacheName, cache.entriesCount()
            );
        });
    }
}

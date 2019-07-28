package com.navent.api.orders.service;

import com.navent.api.orders.cache.BumexMemcachedPool;
import com.navent.api.orders.cache.Cache;
import com.navent.api.orders.cache.NotFoundEntityInCacheException;
import com.navent.api.orders.persistence.NotFoundEntityException;
import com.navent.api.orders.persistence.repository.MongoRepository;
import ma.glasnost.orika.MapperFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public abstract class EntityService<DOMAIN_ENTITY, PER_ENTITY> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntityService.class);

    protected final MapperFacade mapper;

    private final MongoRepository<PER_ENTITY> repository;

    private final BumexMemcachedPool cachePool;

    private final String cacheName;

    public EntityService(
            MongoRepository<PER_ENTITY> repository,
            MapperFacade mapper,
            BumexMemcachedPool cachePool,
            String cacheName
    ) {
        this.repository = repository;
        this.mapper = mapper;
        this.cachePool = cachePool;
        this.cacheName = cacheName;
    }

    public DOMAIN_ENTITY save(DOMAIN_ENTITY entity) {
        return toDomain(repository.save(toPersistence(entity)));
    }

    public void remove(String id) throws NotFoundEntityException {
        repository.remove(id);
    }

    public Optional<DOMAIN_ENTITY> findById(String id) throws NotFoundEntityInCacheException {
        return getCache().get(id, () ->
                repository.findById(id).map(entity -> {
                    LOGGER.info("'{}' {} entity was found in database.", id, cacheName);
                    return this.toDomain(entity);
                })
        );
    }

    private Cache<DOMAIN_ENTITY> getCache() throws NotFoundEntityInCacheException {
        return cachePool.get(cacheName);
    }

    public Collection<DOMAIN_ENTITY> findAll() {
        return toDomain(repository.findAll());
    }

    private Collection<DOMAIN_ENTITY> toDomain(Collection<PER_ENTITY> persistenceEntities) {
        return persistenceEntities.stream().map(this::toDomain).collect(toList());
    }

    protected abstract DOMAIN_ENTITY toDomain(PER_ENTITY persistenceEntity);

    protected abstract PER_ENTITY toPersistence(DOMAIN_ENTITY entity);
}

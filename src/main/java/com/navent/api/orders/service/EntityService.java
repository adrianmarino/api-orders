package com.navent.api.orders.service;

import com.navent.api.orders.persistence.NotFoundEntityException;

import java.util.Collection;
import java.util.Optional;

public interface EntityService<DOMAIN_ENTITY> {
    DOMAIN_ENTITY save(DOMAIN_ENTITY entity);

    void remove(String id) throws NotFoundEntityException;

    Optional<DOMAIN_ENTITY> findById(String id);

    Collection<DOMAIN_ENTITY> findAll();
}

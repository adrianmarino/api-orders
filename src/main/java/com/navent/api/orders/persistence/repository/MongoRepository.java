package com.navent.api.orders.persistence.repository;


import com.navent.api.orders.persistence.NotFoundEntityException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Collection;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

public abstract class MongoRepository<DOCUMENT> {

    protected final Class<DOCUMENT> documentClass;

    protected final MongoTemplate template;

    public MongoRepository(MongoTemplate template, Class<DOCUMENT> documentClass) {
        this.template = template;
        this.documentClass = documentClass;
    }

    public Optional<DOCUMENT> findById(String id) {
        return findOneBy("_id", id);
    }

    public Optional<DOCUMENT> findOneBy(String property, String value) {
        return ofNullable(template.findOne(query(where(property).in(value)), documentClass));
    }

    public Collection<DOCUMENT> findAll() {
        return template.findAll(documentClass);
    }

    public Collection<DOCUMENT> findBy(Query query) {
        return template.find(query, documentClass);
    }

    public DOCUMENT save(DOCUMENT document) {
        template.save(document);
        return document;
    }

    public void remove(String id) throws NotFoundEntityException {
        findById(id).map(template::remove).orElseThrow(() -> new NotFoundEntityException(id));
    }
}

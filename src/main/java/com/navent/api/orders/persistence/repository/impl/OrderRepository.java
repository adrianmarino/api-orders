package com.navent.api.orders.persistence.repository.impl;

import com.navent.api.orders.persistence.entity.OrderPersistence;
import com.navent.api.orders.persistence.repository.MongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

// TODO: PedidosDAO
@Repository
public class OrderRepository extends MongoRepository<OrderPersistence> {
    @Autowired
    public OrderRepository(MongoTemplate mongoTemplate) {
        super(mongoTemplate, OrderPersistence.class);
    }
}

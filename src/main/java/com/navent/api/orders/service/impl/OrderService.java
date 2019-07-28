package com.navent.api.orders.service.impl;

import com.navent.api.orders.cache.BumexMemcachedPool;
import com.navent.api.orders.domain.Order;
import com.navent.api.orders.persistence.entity.OrderPersistence;
import com.navent.api.orders.persistence.repository.impl.OrderRepository;
import com.navent.api.orders.service.EntityService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.navent.api.orders.cache.BumexMemcachedPool.ORDERS_CACHE;

@Service
public class OrderService extends EntityService<Order, OrderPersistence> {
    
    @Autowired
    public OrderService(OrderRepository repository, MapperFacade mapper, BumexMemcachedPool cache) {
        super(repository, mapper, cache, ORDERS_CACHE);
    }

    protected Order toDomain(OrderPersistence persistenceEntity) {
        return mapper.map(persistenceEntity, Order.class);
    }

    protected OrderPersistence toPersistence(Order entity) {
        return mapper.map(entity, OrderPersistence.class);
    }
}

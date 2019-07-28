package com.navent.api.orders.service;

import com.navent.api.orders.domain.Order;
import com.navent.api.orders.persistence.entity.OrderPersistence;
import com.navent.api.orders.persistence.NotFoundEntityException;
import com.navent.api.orders.persistence.repository.impl.OrderRepository;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    private final OrderRepository repository;

    private final MapperFacade mapper;

    @Autowired
    public OrderService(OrderRepository repository, MapperFacade mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Order save(Order entity) {
        return toDomain(repository.save(toPersistence(entity)));
    }

    public void remove(String id) throws NotFoundEntityException {
        repository.remove(id);
    }

    public Optional<Order> findById(String id) {
        return repository.findById(id).map(this::toDomain);
    }

    private Order toDomain(OrderPersistence savedPersistenceEntity) {
        return mapper.map(savedPersistenceEntity, Order.class);
    }

    private OrderPersistence toPersistence(Order domainEntity) {
        return mapper.map(domainEntity, OrderPersistence.class);
    }
}

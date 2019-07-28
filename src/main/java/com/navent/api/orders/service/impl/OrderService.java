package com.navent.api.orders.service.impl;

import com.navent.api.orders.domain.Order;
import com.navent.api.orders.persistence.NotFoundEntityException;
import com.navent.api.orders.persistence.entity.OrderPersistence;
import com.navent.api.orders.persistence.repository.impl.OrderRepository;
import com.navent.api.orders.service.EntityService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
public class OrderService implements EntityService<Order> {

    private final OrderRepository repository;

    private final MapperFacade mapper;

    @Autowired
    public OrderService(OrderRepository repository, MapperFacade mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Order save(Order entity) {
        return toDomain(repository.save(toPersistence(entity)));
    }

    @Override
    public void remove(String id) throws NotFoundEntityException {
        repository.remove(id);
    }

    @Override
    public Optional<Order> findById(String id) {
        return repository.findById(id).map(this::toDomain);
    }

    @Override
    public Collection<Order> findAll() {
        return repository.findAll().stream().map(this::toDomain).collect(toList());
    }

    private Order toDomain(OrderPersistence savedPersistenceEntity) {
        return mapper.map(savedPersistenceEntity, Order.class);
    }

    private OrderPersistence toPersistence(Order domainEntity) {
        return mapper.map(domainEntity, OrderPersistence.class);
    }
}

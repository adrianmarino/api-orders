package com.navent.api.orders.controller.impl;

import com.navent.api.orders.contract.OrderDto;
import com.navent.api.orders.controller.EntityController;
import com.navent.api.orders.controller.documentation.OrderDocumentation;
import com.navent.api.orders.domain.Order;
import com.navent.api.orders.error.ErrorResponseFactory;
import com.navent.api.orders.service.impl.OrderService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController extends EntityController<OrderService, Order, OrderDto> implements OrderDocumentation {

    @Autowired
    public OrderController(
            OrderService service,
            MapperFacade mapper,
            ErrorResponseFactory errorResponseFactory
    ) {
        super(service, mapper, errorResponseFactory);
    }

    protected OrderDto toDto(Order order) {
        return mapper.map(order, OrderDto.class);
    }

    protected Order toDomain(OrderDto order) {
        return mapper.map(order, Order.class);
    }
}

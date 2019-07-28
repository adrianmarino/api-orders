package com.navent.api.orders.controller;

import com.navent.api.orders.contract.OrderDto;
import com.navent.api.orders.domain.Order;
import com.navent.api.orders.error.ErrorMessage;
import com.navent.api.orders.error.ErrorResponseFactory;
import com.navent.api.orders.exception.AppException;
import com.navent.api.orders.persistence.NotFoundEntityException;
import com.navent.api.orders.service.OrderService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/orders")
@ControllerAdvice
public class OrderController {

    private final OrderService service;

    private final MapperFacade mapper;

    @Autowired
    public OrderController(OrderService service, MapperFacade mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<OrderDto> create(@Valid @RequestBody OrderDto orderDto) {
        return new ResponseEntity<>(toDto(service.save(toDomain(orderDto))), CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> update(@PathVariable String id, @Valid @RequestBody OrderDto orderDto) {
        return new ResponseEntity<>(toDto(service.save(toDomain(orderDto))), OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) throws NotFoundEntityException {
        service.remove(id);
    }

    @RequestMapping("/{id}")
    public ResponseEntity<OrderDto> findById(@PathVariable String id) throws NotFoundEntityException {
        return service.findById(id)
                .map(it -> new ResponseEntity<>(toDto(it), OK))
                .orElseThrow(() -> new NotFoundEntityException(id));
    }

    private OrderDto toDto(Order order) {
        return mapper.map(order, OrderDto.class);
    }

    private Order toDomain(OrderDto order) {
        return mapper.map(order, Order.class);
    }

    @ExceptionHandler(AppException.class)
    public ResponseEntity<ErrorMessage> exceptionHandler(HttpServletRequest request, AppException exception) {
        return ErrorResponseFactory.create(exception);
    }
}

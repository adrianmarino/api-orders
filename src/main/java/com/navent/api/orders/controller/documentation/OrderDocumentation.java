package com.navent.api.orders.controller.documentation;

import com.navent.api.orders.contract.OrderDto;
import com.navent.api.orders.persistence.NotFoundEntityException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Used to api documentation purpose.
 */
@Api(
        tags = "Order resource",
        description = "This Resource allow to create orders."
)
public interface OrderDocumentation {

    @ApiOperation(value = "Create an order")
    ResponseEntity<OrderDto> create(
            @ApiParam(
                    name = "order",
                    type = "com.navent.api.orders.contract.Order",
                    value = "Entity DTO that represent an order",
                    required = true
            )
            @RequestBody OrderDto entityDto
    );

    @ApiOperation(value = "Update an order")
    ResponseEntity<OrderDto> update(
            @ApiParam(
                    name = "id",
                    value = "The order identifier.",
                    required = true
            )
            @PathVariable String id,
            @ApiParam(
                    name = "order",
                    type = "com.navent.api.orders.contract.Order",
                    value = "Entity DTO that represent an order",
                    required = true
            )
            @RequestBody OrderDto entityDto
    );

    @ApiOperation(value = "Delete an order")
    void delete(
            @ApiParam(
                    name = "id",
                    value = "The order identifier.",
                    required = true
            )
            @PathVariable String id
    ) throws NotFoundEntityException;

    @ApiOperation(value = "Find an order")
    ResponseEntity<OrderDto> findById(
            @ApiParam(
                    name = "id",
                    value = "The order identifier.",
                    required = true
            )
            @PathVariable String id
    ) throws NotFoundEntityException;

    @ApiOperation(value = "Get all orders")
    ResponseEntity<List<OrderDto>> findAll();
}

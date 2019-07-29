package com.navent.api.orders.controller;

import com.navent.api.orders.App;
import com.navent.api.orders.contract.OrderDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.math.BigDecimal;

import static com.navent.api.orders.controller.JSONUtils.fromJson;
import static com.navent.api.orders.controller.MockMvcUtils.*;
import static org.apache.commons.lang3.StringUtils.repeat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = {App.class})
@ActiveProfiles({"test"})
class OrderControllerTest {

    @Autowired
    private MockMvc client;

    @Test
    @DisplayName("when create an order it is stored")
    void test1() throws Exception {
        // Prepare
        OrderDto order = new OrderDto(null, "Order 1", BigDecimal.valueOf(1000), BigDecimal.valueOf(100));

        // Perform
        OrderDto createdOrder = toDto(postOrder(order));

        // Asserts
        assertThat(createdOrder.getId(), is(notNullValue()));
        // Get and compare
        OrderDto getOrder = toDto(getOrder(createdOrder.getId()));
        getOrder.setId(null);
        assertThat(getOrder, is(equalTo(order)));
    }

    @Test
    @DisplayName("when update an order it store order changes")
    void test2() throws Exception {
        // Prepare
        OrderDto order = new OrderDto(null, "Order 1", BigDecimal.valueOf(1000), BigDecimal.valueOf(100));
        OrderDto createdOrder = toDto(postOrder(order));
        order.setName("New order name");

        // Perform
        OrderDto updatedOrder = toDto(putOrder(createdOrder.getId(), order));

        // Asserts
        assertThat(updatedOrder.getName(), is(equalTo(order.getName())));

        // Get and compare
        OrderDto getOrder = toDto(getOrder(updatedOrder.getId()));
        getOrder.setId(null);
        assertThat(getOrder, is(equalTo(order)));
    }

    @Test
    @DisplayName("when request to remove a previously save order it is removed from database")
    void test3() throws Exception {
        // Prepare
        OrderDto order = new OrderDto(null, "Order 1", BigDecimal.valueOf(1000), BigDecimal.valueOf(100));
        OrderDto createdOrder = toDto(postOrder(order));

        // Perform
        deleteOrder(createdOrder.getId());

        // Asserts
        get(client, "/orders/" + createdOrder.getId(), status().isNotFound());
    }

    @Test
    @DisplayName("when request get an order that was previously stored it respond this order")
    void test4() throws Exception {
        // Prepare
        OrderDto order = new OrderDto(null, "Order 1", BigDecimal.valueOf(1000), BigDecimal.valueOf(100));
        OrderDto createdOrder = toDto(postOrder(order));

        // Perform
        OrderDto getOrder = toDto(getOrder(createdOrder.getId()));

        // Asserts
        assertThat(getOrder, is(equalTo(createdOrder)));
    }

    @Test
    @DisplayName("when create an order without name it response bad request")
    void test5() throws Exception {
        // Prepare
        OrderDto order = new OrderDto(null, null, BigDecimal.valueOf(1000), BigDecimal.valueOf(100));

        // Perform & assert
        postOrder(order, status().isBadRequest());
    }

    @Test
    @DisplayName("when create an order with name length greater that 100 characters it response bad request")
    void test6() throws Exception {
        // Prepare
        OrderDto order = new OrderDto(null, repeat("x", 500), BigDecimal.valueOf(1000), BigDecimal.valueOf(100));

        // Perform & assert
        postOrder(order, status().isBadRequest());
    }

    @Test
    @DisplayName("when create an order with amount less than zero it response bad request")
    void test7() throws Exception {
        // Prepare
        OrderDto order = new OrderDto(null, "Order", BigDecimal.valueOf(-100), BigDecimal.valueOf(100));

        // Perform & assert
        postOrder(order, status().isBadRequest());
    }
    
    @Test
    @DisplayName("when create an order with discount less than zero it response bad request")
    void test8() throws Exception {
        // Prepare
        OrderDto order = new OrderDto(null, "Order", BigDecimal.valueOf(-100), BigDecimal.valueOf(100));

        // Perform & assert
        postOrder(order, status().isBadRequest());
    }

    private void deleteOrder(String id) throws Exception {
        delete(client, "/orders/" + id, status().isOk());
    }

    private String putOrder(String id, OrderDto order) throws Exception {
        return put(client, "/orders/" + id, order, status().isOk());
    }

    private String getOrder(String id) throws Exception {
        return get(client, "/orders/" + id, status().isOk());
    }

    private String postOrder(OrderDto order) throws Exception {
        return postOrder(order, status().isCreated());
    }

    private String postOrder(OrderDto order, ResultMatcher resultMatcher) throws Exception {
        return post(client, "/orders", order, resultMatcher);
    }

    private OrderDto toDto(String response) throws Exception {
        return fromJson(response, OrderDto.class);
    }
}

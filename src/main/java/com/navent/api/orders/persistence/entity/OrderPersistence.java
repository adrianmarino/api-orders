package com.navent.api.orders.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document(collection = "orders")
public class OrderPersistence {

    @Id
    private String id;

    private String name;

    private BigDecimal amount;

    private BigDecimal discount;

    // Don't use: Required by persistence layer
    @Deprecated
    public OrderPersistence() {
    }

    public OrderPersistence(String name, BigDecimal amount, BigDecimal discount) {
        this.name = name;
        this.amount = amount;
        this.discount = discount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }
}

package com.navent.api.orders.domain;

import java.math.BigDecimal;

public class Order {

    private String id;

    private String name;

    private BigDecimal amount;

    private BigDecimal discount;

    // Don't use. Required by object mapper.
    @Deprecated
    public Order() {
    }

    public Order(String name, BigDecimal amount, BigDecimal discount) {
        this.name = name;
        this.amount = amount;
        this.discount = discount;
    }

    public String getId() {
        return id;
    }

    // Don't use. Required by object mapper.
    @Deprecated
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    // Don't use. Required by object mapper.
    @Deprecated
    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    // Don't use. Required by object mapper.
    @Deprecated
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    // Don't use. Required by object mapper.
    @Deprecated
    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
}

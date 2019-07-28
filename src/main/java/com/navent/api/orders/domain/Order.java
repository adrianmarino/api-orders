package com.navent.api.orders.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Order implements Entity {

    private String id;

    private String name;

    private BigDecimal amount;

    private BigDecimal discount;

    // Don't use. Required by object mapper.
    @Deprecated
    public Order() {
    }

    public Order(String id, String name, BigDecimal amount, BigDecimal discount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.discount = discount;
    }

    public Order(String name, BigDecimal amount, BigDecimal discount) {
        this(null, name, amount, discount);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Objects.equals(id, order.id) &&
                Objects.equals(name, order.name) &&
                Objects.equals(amount, order.amount) &&
                Objects.equals(discount, order.discount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, amount, discount);
    }

    @Override
    public String toString() {
        return "(id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", amount=" + amount +
                ", discount=" + discount +
                ')';
    }
}

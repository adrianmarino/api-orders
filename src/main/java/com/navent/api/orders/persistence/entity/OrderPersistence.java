package com.navent.api.orders.persistence.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Objects;

@Document(collection = "orders")
public class OrderPersistence {

    @Id
    private String id;

    private String name;

    private BigDecimal amount;

    private BigDecimal discount;

    public OrderPersistence() {
    }

    public OrderPersistence(String id, String name, BigDecimal amount, BigDecimal discount) {
        this.id = id;
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

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderPersistence that = (OrderPersistence) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(discount, that.discount);
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

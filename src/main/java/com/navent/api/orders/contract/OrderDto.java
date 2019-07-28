package com.navent.api.orders.contract;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class OrderDto {

    private String id;

    @NotNull
    private String name;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private BigDecimal discount;

    public OrderDto() {
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
}

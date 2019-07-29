package com.navent.api.orders.contract;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Objects;

public class OrderDto implements EntityDto {

    @Null
    private String id;

    @NotNull
    @NotBlank
    @Size(min = 0, max = 100)
    private String name;

    @NotNull
    @Min(0)
    private BigDecimal amount;

    @NotNull
    @Min(0)
    private BigDecimal discount;

    public OrderDto() {
    }

    public OrderDto(String id, String name, BigDecimal amount, BigDecimal discount) {
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
        OrderDto orderDto = (OrderDto) o;
        return Objects.equals(id, orderDto.id) &&
                Objects.equals(name, orderDto.name) &&
                Objects.equals(amount, orderDto.amount) &&
                Objects.equals(discount, orderDto.discount);
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

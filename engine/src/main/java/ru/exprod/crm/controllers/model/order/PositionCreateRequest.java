package ru.exprod.crm.controllers.model.order;

import java.math.BigDecimal;

public class PositionCreateRequest {
    private Integer variantId;
    private BigDecimal quantity;
    private BigDecimal price;
    private BigDecimal discount;

    public Integer getVariantId() {
        return variantId;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getDiscount() {
        return discount;
    }
}

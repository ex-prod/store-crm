package ru.exprod.crm.controllers.model.order;

import java.math.BigDecimal;

public class PositionCreateRequest {
    protected Integer variantId;
    protected BigDecimal quantity;
    protected BigDecimal price;
    protected BigDecimal discount;

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

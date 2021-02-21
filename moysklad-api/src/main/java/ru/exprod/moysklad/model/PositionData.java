package ru.exprod.moysklad.model;

import java.math.BigDecimal;

public interface PositionData {
    BigDecimal getQuantity();
    BigDecimal getPrice();
    BigDecimal getDiscount();
    String getVariantId();
}

package ru.exprod.moysklad.api.model;

import ru.exprod.moysklad.model.PositionData;

import java.math.BigDecimal;
import java.math.BigInteger;

import static ru.exprod.moysklad.api.model.MetaList.VARIANT;

public class Position {
    private MetaWrapper assortment;
    private BigDecimal quantity;
    private BigDecimal reserve;
    private BigInteger price;
    private BigDecimal discount;

    public static Position create(PositionData positionData) {
        Position position = new Position();
        position.assortment = VARIANT.getMetaWrapper(positionData.getVariantId());
        position.quantity = positionData.getQuantity();
        position.reserve = positionData.getQuantity();
        position.price = positionData.getPrice().multiply(BigDecimal.valueOf(100)).toBigInteger();
        position.discount = positionData.getDiscount();
        return position;
    }

    public MetaWrapper getAssortment() {
        return assortment;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public BigInteger getPrice() {
        return price;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public BigDecimal getReserve() {
        return reserve;
    }
}

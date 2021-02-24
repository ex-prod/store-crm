package ru.exprod.crm.service.moyskladapi.model;

import ru.exprod.crm.service.model.CustomerOrderPositionModel;
import ru.exprod.moysklad.model.PositionData;

import java.math.BigDecimal;

public class CreatePositionModel implements PositionData {
    private final String variantId;
    private final BigDecimal quantity;
    private final BigDecimal discount;
    private final BigDecimal price;

    public CreatePositionModel(CustomerOrderPositionModel order) {
        this.variantId = order.getVariant().getMoyskladId();
        this.discount = order.getDiscount();
        this.price = order.getPrice();
        this.quantity = order.getCount();
    }

    @Override
    public String getVariantId() {
        return variantId;
    }

    @Override
    public BigDecimal getQuantity() {
        return quantity;
    }

    @Override
    public BigDecimal getDiscount() {
        return discount;
    }

    @Override
    public BigDecimal getPrice() {
        return price;
    }
}

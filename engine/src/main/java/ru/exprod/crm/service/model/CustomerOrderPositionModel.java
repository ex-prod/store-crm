package ru.exprod.crm.service.model;

import ru.exprod.crm.dao.model.CustomerOrderPositionEntity;

import java.math.BigDecimal;

public class CustomerOrderPositionModel {
    private final Integer id;
    private final String moyskladId;
    private final VariantModel variant;
    private final BigDecimal count;
    private final BigDecimal price;
    private final BigDecimal discount;

    public CustomerOrderPositionModel(CustomerOrderPositionEntity entity) {
        this.id = entity.getCustomerOrderPositionId();
        this.moyskladId = entity.getMoyskladId();
        this.variant = new VariantModel(entity.getVariant());
        this.count = entity.getCount();
        this.price = entity.getPrice();
        this.discount = entity.getDiscount();
    }

    public Integer getId() {
        return id;
    }

    public String getMoyskladId() {
        return moyskladId;
    }

    public VariantModel getVariant() {
        return variant;
    }

    public BigDecimal getCount() {
        return count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getDiscount() {
        return discount;
    }
}

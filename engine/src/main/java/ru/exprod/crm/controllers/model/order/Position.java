package ru.exprod.crm.controllers.model.order;

import ru.exprod.crm.service.model.CustomerOrderPositionModel;

public class Position extends PositionCreateRequest {
    private final Integer id;

    public Position(CustomerOrderPositionModel position) {
        this.id = position.getId();
        this.variantId = position.getVariant().getId();
        this.quantity = position.getCount();
        this.price = position.getPrice();
        this.discount = position.getDiscount();
    }
}

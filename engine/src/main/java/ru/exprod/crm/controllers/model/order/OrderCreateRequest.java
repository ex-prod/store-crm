package ru.exprod.crm.controllers.model.order;

import ru.exprod.crm.service.model.DeliveryType;
import ru.exprod.crm.service.model.PrepaidType;

import java.math.BigDecimal;
import java.util.List;

public class OrderCreateRequest {
    protected String description;
    protected List<PositionCreateRequest> positions;

    protected CreateCustomerRequest customer;

    protected PrepaidType prepaidType;
    protected BigDecimal toPay;
    protected BigDecimal deliveryCost;
    protected DeliveryType deliveryType;

    public String getDescription() {
        return description;
    }

    public List<PositionCreateRequest> getPositions() {
        return positions;
    }

    public CreateCustomerRequest getCustomer() {
        return customer;
    }

    public PrepaidType getPrepaidType() {
        return prepaidType;
    }

    public BigDecimal getToPay() {
        return toPay;
    }

    public BigDecimal getDeliveryCost() {
        return deliveryCost;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }
}

package ru.exprod.crm.controllers.model.ordercreate;

import ru.exprod.crm.service.model.DeliveryType;
import ru.exprod.crm.service.model.PrepaidType;

import java.math.BigDecimal;
import java.util.List;

public class OrderCreateRequest {
    private String description;
    private List<PositionCreateRequest> positions;

    private Customer customer;

    private PrepaidType prepaidType;
    private BigDecimal toPay;
    private BigDecimal deliveryCost;
    private DeliveryType deliveryType;

    public String getDescription() {
        return description;
    }

    public List<PositionCreateRequest> getPositions() {
        return positions;
    }

    public Customer getCustomer() {
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

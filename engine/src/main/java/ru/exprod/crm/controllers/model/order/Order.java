package ru.exprod.crm.controllers.model.order;

import ru.exprod.crm.service.model.CustomerOrderModel;

import java.util.stream.Collectors;

public class Order extends OrderCreateRequest {
    protected final Integer id;
    protected final Integer positionsCount;
    protected final String state;
    protected final String manager;

    public Order(CustomerOrderModel order) {
        this.id = order.getId();
        this.state = order.getState();
        this.positionsCount = order.getPositions();

        this.customer = new Customer(order.getCustomer());
        this.positions = order.getPositionList().stream()
                .map(Position::new)
                .collect(Collectors.toList());

        this.prepaidType = order.getPrepaidType();
        this.toPay = order.getPaid();
        this.deliveryCost = order.getDeliveryCost();
        this.deliveryType = order.getDeliveryType();
        this.manager = order.getManager().getFullName();
        this.description = order.getComment();
    }

    public Integer getId() {
        return id;
    }

    public Integer getPositionsCount() {
        return positionsCount;
    }

    public String getState() {
        return state;
    }

    public String getManager() {
        return manager;
    }
}

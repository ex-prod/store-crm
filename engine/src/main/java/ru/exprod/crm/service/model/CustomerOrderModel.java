package ru.exprod.crm.service.model;

import ru.exprod.crm.dao.model.CustomerOrderEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class CustomerOrderModel {
    private final Integer id;
    private final UnitModel unit;
    private final String moyskladId;
    private final String name;
    private final Integer positions;
    private final BigDecimal amount;
    private final String state;
    private final CustomerModel customer;
    private final ManagerModel manager;
    private final BigDecimal deliveryCost;
    private final DeliveryType deliveryType;
    private final BigDecimal paid;
    private final String prepaidType;
    private final String comment;
    private final List<CustomerOrderPositionModel> positionList;

    public CustomerOrderModel(CustomerOrderEntity entity) {
        this.id = entity.getCustomerOrderId();
        this.unit = new UnitModel(entity.getUnit());
        this.moyskladId = entity.getMoyskladId();
        this.name = entity.getName();
        this.positions = entity.getPositions();
        this.amount = entity.getAmount();
        this.state = entity.getState();
        this.customer = new CustomerModel(entity.getCustomer());
        this.manager = new ManagerModel(entity.getManager());
        this.deliveryCost = entity.getDeliveryCost();
        this.deliveryType = DeliveryType.valueOf(entity.getDeliveryType());
        this.paid = entity.getPaid();
        this.prepaidType = entity.getPrepaidType();
        this.comment = entity.getComment();
        this.positionList = entity.getPositionList().stream()
                .map(CustomerOrderPositionModel::new)
                .collect(Collectors.toList());
    }

    public Integer getId() {
        return id;
    }

    public UnitModel getUnit() {
        return unit;
    }

    public String getMoyskladId() {
        return moyskladId;
    }

    public String getName() {
        return name;
    }

    public Integer getPositions() {
        return positions;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getState() {
        return state;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public ManagerModel getManager() {
        return manager;
    }

    public BigDecimal getDeliveryCost() {
        return deliveryCost;
    }

    public DeliveryType getDeliveryType() {
        return deliveryType;
    }

    public BigDecimal getPaid() {
        return paid;
    }

    public String getPrepaidType() {
        return prepaidType;
    }

    public String getComment() {
        return comment;
    }

    public List<CustomerOrderPositionModel> getPositionList() {
        return positionList;
    }
}

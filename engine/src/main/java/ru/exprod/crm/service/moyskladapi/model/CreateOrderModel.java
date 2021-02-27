package ru.exprod.crm.service.moyskladapi.model;

import ru.exprod.crm.service.model.CustomerOrderModel;
import ru.exprod.crm.service.model.PrepaidType;
import ru.exprod.moysklad.model.CustomerData;
import ru.exprod.moysklad.model.OrderData;
import ru.exprod.moysklad.model.PositionData;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class CreateOrderModel implements OrderData {
    private final String name;
    private final String description;
    private final List<PositionData> positions;
    private final CustomerData customerData;
    private final String managerName;
    private final PrepaidType prepaidType;
    private final BigDecimal toPay;
    private final BigDecimal deliveryCost;
    private final Integer unitId;

    public CreateOrderModel(CustomerOrderModel order) {
        this.name = String.format("%06d", order.getId());
        this.description = order.getComment();
        this.positions = order.getPositionList().stream()
                .map(CreatePositionModel::new)
                .collect(Collectors.toList());
        this.customerData = new CustomerDataModel(order.getCustomer(), order.getDeliveryType());
        this.managerName = "manager name"; //todo get from security
        this.prepaidType = order.getPrepaidType();
        this.toPay = order.getAmount().add(order.getPaid().negate());
        this.deliveryCost = order.getDeliveryCost();
        this.unitId = order.getUnit().getId();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public List<PositionData> getPositions() {
        return positions;
    }

    @Override
    public CustomerData getCustomerData() {
        return customerData;
    }

    @Override
    public String getManagerName() {
        return managerName;
    }

    @Override
    public String getPrepaidType() {
        return prepaidType.name();
    }

    @Override
    public BigDecimal getToPay() {
        return toPay;
    }

    @Override
    public BigDecimal getDeliveryCost() {
        return deliveryCost;
    }

    public Integer getUnitId() {
        return unitId;
    }
}

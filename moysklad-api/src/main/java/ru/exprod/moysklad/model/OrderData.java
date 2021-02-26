package ru.exprod.moysklad.model;


import java.math.BigDecimal;
import java.util.List;

public interface OrderData {
    String getName();
    String getDescription();

    List<PositionData> getPositions();

    CustomerData getCustomerData();

    String getManagerName();
    String getPrepaidType();
    BigDecimal getToPay();
    BigDecimal getDeliveryCost();
}

package ru.exprod.moysklad.model;

import java.util.List;

public interface OrderData {
    String getName();
    String getDescription();

    List<OrderPositionData> getPositions();

    String getManagerName();

    CustomerData getCustomerData();
}

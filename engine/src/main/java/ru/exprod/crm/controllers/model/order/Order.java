package ru.exprod.crm.controllers.model.order;

import ru.exprod.crm.service.model.CustomerOrderModel;

public class Order extends OrderCreateRequest {
    protected final Integer id;

    public Order(CustomerOrderModel orderModel) {
        this.id = orderModel.getId();
        this.customer = new Customer(orderModel.getCustomer());
    }



}

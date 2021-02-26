package ru.exprod.crm.controllers.model.order;

import ru.exprod.crm.service.model.CustomerModel;

public class Customer extends CreateCustomerRequest {
    private final Integer id;

    public Customer(CustomerModel customerModel) {
        this.id = customerModel.getId();
    }
}

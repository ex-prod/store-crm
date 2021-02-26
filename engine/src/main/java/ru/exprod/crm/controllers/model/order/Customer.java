package ru.exprod.crm.controllers.model.order;

import ru.exprod.crm.service.model.CustomerModel;

public class Customer extends CreateCustomerRequest {
    private final Integer id;

    public Customer(CustomerModel customer) {
        this.id = customer.getId();
        this.name = customer.getName();
        this.phone = customer.getPhone();
        this.username = customer.getUsername();
        this.city = customer.getCity();
        this.zip = customer.getZip();
        this.address = customer.getAddress();
    }

    public Integer getId() {
        return id;
    }
}

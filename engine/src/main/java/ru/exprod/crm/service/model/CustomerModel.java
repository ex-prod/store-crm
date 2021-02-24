package ru.exprod.crm.service.model;

import ru.exprod.crm.dao.model.CustomerEntity;

public class CustomerModel {
    private final Integer id;
    private final String name;
    private final String phone;
    private final String username;
    private final String city;
    private final String zip;
    private final String address;

    public CustomerModel(CustomerEntity entity) {
        this.id = entity.getCustomerId();
        this.name = entity.getName();
        this.phone = entity.getPhone();
        this.username = entity.getUsername();
        this.city = entity.getCity();
        this.zip = entity.getCity();
        this.address = entity.getAddress();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getUsername() {
        return username;
    }

    public String getCity() {
        return city;
    }

    public String getZip() {
        return zip;
    }

    public String getAddress() {
        return address;
    }
}

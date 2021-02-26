package ru.exprod.crm.service.moyskladapi.model;

import ru.exprod.crm.service.model.CustomerModel;
import ru.exprod.crm.service.model.DeliveryType;
import ru.exprod.moysklad.model.CustomerData;

public class CustomerDataModel implements CustomerData {

    private final String name;
    private final String phone;
    private final String username;
    private final String city;
    private final String zip;
    private final String address;
    private final String sdekAddress;

    public CustomerDataModel(CustomerModel customer, DeliveryType deliveryType) {
        this.name = customer.getName();
        this.phone = customer.getPhone();
        this.username = customer.getUsername();
        this.city = customer.getCity();
        this.zip = customer.getZip();
        if (DeliveryType.SDEK_STORAGE.equals(deliveryType)) {
            this.address = "";
            this.sdekAddress = customer.getAddress();
        } else {
            this.address = customer.getAddress();
            this.sdekAddress = "";
        }
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public String getZip() {
        return zip;
    }

    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public String getSdekAddress() {
        return sdekAddress;
    }
}

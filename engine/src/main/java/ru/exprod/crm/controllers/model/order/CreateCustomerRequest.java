package ru.exprod.crm.controllers.model.order;

public class CreateCustomerRequest {
    protected String name;
    protected String phone;
    protected String username;
    protected String city;
    protected String zip;
    protected String address;
    protected String sdekAddress;

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

    public String getSdekAddress() {
        return sdekAddress;
    }
}

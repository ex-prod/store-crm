package ru.exprod.crm.service.model;

import ru.exprod.crm.dao.model.ManagerEntity;

public class ManagerModel {
    private final Integer id;
    private final String firstname;
    private final String lastname;
    private final String email;
    private final String password;

    public ManagerModel(ManagerEntity entity) {
        this.id = entity.getManagerId();
        this.firstname = entity.getFirstname();
        this.lastname = entity.getLastname();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
    }

    public Integer getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}

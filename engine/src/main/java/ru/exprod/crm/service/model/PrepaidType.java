package ru.exprod.crm.service.model;

public enum PrepaidType {
    FULL("Полная"),
    PARTIAL("Частичная");

    private final String description;

    PrepaidType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

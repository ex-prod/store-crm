package ru.exprod.moysklad.api.model;

import static ru.exprod.moysklad.MoySkladApi.ENTITY_URL;

public enum MetaList {
    ORGANIZATION("organization"),
    COUNTERPARTY("counterparty"),
    STORAGE("store"),
    STATE("state");

    private final String type;

    MetaList(String type) {
        this.type = type;
    }

    public MetaWrapper getMeta(String id) {
        return MetaWrapper.create(new Meta(ENTITY_URL + "/" + type + "/" + id, type));
    }
}

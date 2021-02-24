package ru.exprod.moysklad.api.model;

public enum MetaList {
    ORGANIZATION("organization", "organization"),
    COUNTERPARTY("counterparty", "counterparty"),
    STORAGE("store", "store"),
    STATE("state", "state"),
    VARIANT("variant", "variant"),
    ATTRIBUTE("attributemetadata", "customerorder/metadata/attributes"),
    CUSTOMERORDER("customerorder", "customerorder");

    private final String type;
    private final String path;

    MetaList(String type, String path) {
        this.type = type;
        this.path = path;
    }

    public MetaWrapper getMetaWrapper(String id) {
        return MetaWrapper.create(getMeta(id));
    }

    public Meta getMeta(String id) {
        return Meta.create(id, type, path);
    }
}

package ru.exprod.moysklad.api.model;

public class MetaWrapper {
    public Meta meta;

    public static MetaWrapper create(Meta meta) {
        MetaWrapper wrapper = new MetaWrapper();
        wrapper.meta = meta;
        return wrapper;
    }
}

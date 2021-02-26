package ru.exprod.moysklad.api.model;

import static ru.exprod.moysklad.api.model.MetaList.ATTRIBUTE;

public class Attribute<T> {
    private Meta meta;
    private T value;

    public static<T> Attribute<T> create(T attributeValue, String attributeId) {
        Attribute<T> attribute = new Attribute<>();
        attribute.meta = ATTRIBUTE.getMeta(attributeId);
        attribute.value = attributeValue;
        return attribute;
    }

    public Meta getMeta() {
        return meta;
    }

    public T getValue() {
        return value;
    }
}

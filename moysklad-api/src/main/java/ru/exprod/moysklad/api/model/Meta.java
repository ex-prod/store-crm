package ru.exprod.moysklad.api.model;

import static ru.exprod.moysklad.MoySkladApi.ENTITY_URL;

public class Meta {
    private String href;
    private String type;
    private Integer size;

    public Meta() {
    }

    public static Meta create(String id, String type, String path) {
        Meta meta = new Meta();
        meta.href = ENTITY_URL + "/" + path + "/" + id;
        meta.type = type;
        return meta;
    }

    public String getHref() {
        return href;
    }

    public String getType() {
        return type;
    }

    public Integer getSize() {
        return size;
    }
}

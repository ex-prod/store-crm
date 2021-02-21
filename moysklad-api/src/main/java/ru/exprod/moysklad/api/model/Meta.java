package ru.exprod.moysklad.api.model;

public class Meta {
    private String href;
    private String type;
    private Integer size;

    public Meta() {
    }

    public Meta(String href, String type) {
        this.href = href;
        this.type = type;
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

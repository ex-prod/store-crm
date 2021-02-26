package ru.exprod.moysklad.api.model;

import org.springframework.util.DigestUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ru.exprod.moysklad.MoySkladApi.ENTITY_URL;

public class Meta {
    private static final Pattern ID_PATTERN
            = Pattern.compile("/([0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12})");

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

    public String extractId() {
        Matcher matcher = ID_PATTERN.matcher(href);
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "";
        }
    }
}

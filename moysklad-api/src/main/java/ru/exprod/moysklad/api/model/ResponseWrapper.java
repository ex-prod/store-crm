package ru.exprod.moysklad.api.model;

import java.util.List;

public class ResponseWrapper<T> {
    private List<T> rows;

    public List<T> getRows() {
        return rows;
    }
}

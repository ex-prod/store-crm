package ru.exprod.moysklad;

import ru.exprod.moysklad.model.Variant;

import java.util.List;

public interface MoySkladApi {
    List<Variant> getProducts();
}

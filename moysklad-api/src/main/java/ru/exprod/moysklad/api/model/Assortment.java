package ru.exprod.moysklad.api.model;

import java.util.List;

public class Assortment {
    private String id;
    private String name;
    private String description;
    private Integer stock;
    private Integer reserve;
    private Integer inTransit;
    private Integer quantity;
    private List<Price> salePrices;
    private String article;
    private String code;
    private String externalCode;
    private AssortmentMeta meta;
    private AssortmentImagesMeta images;
    private Boolean archived;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getStock() {
        return stock;
    }

    public Integer getReserve() {
        return reserve;
    }

    public Integer getInTransit() {
        return inTransit;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public List<Price> getSalePrices() {
        return salePrices;
    }

    public String getArticle() {
        return article;
    }

    public String getExternalCode() {
        return externalCode;
    }

    public String getCode() {
        return code;
    }

    public AssortmentMeta getMeta() {
        return meta;
    }

    public AssortmentImagesMeta getImages() {
        return images;
    }

    public Boolean getArchived() {
        return archived;
    }
}


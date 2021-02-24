package ru.exprod.moysklad.api.model;

import java.math.BigDecimal;
import java.util.List;

public class Assortment {
    private String id;
    private String name;
    private String description;
    private BigDecimal stock;
    private BigDecimal reserve;
    private BigDecimal inTransit;
    private BigDecimal quantity;
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

    public BigDecimal getStock() {
        return stock;
    }

    public BigDecimal getReserve() {
        return reserve;
    }

    public BigDecimal getInTransit() {
        return inTransit;
    }

    public BigDecimal getQuantity() {
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


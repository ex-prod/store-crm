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

    public Assortment setId(String id) {
        this.id = id;
        return this;
    }

    public Assortment setName(String name) {
        this.name = name;
        return this;
    }

    public Assortment setDescription(String description) {
        this.description = description;
        return this;
    }

    public Assortment setStock(BigDecimal stock) {
        this.stock = stock;
        return this;
    }

    public Assortment setReserve(BigDecimal reserve) {
        this.reserve = reserve;
        return this;
    }

    public Assortment setInTransit(BigDecimal inTransit) {
        this.inTransit = inTransit;
        return this;
    }

    public Assortment setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
        return this;
    }

    public Assortment setSalePrices(List<Price> salePrices) {
        this.salePrices = salePrices;
        return this;
    }

    public Assortment setArticle(String article) {
        this.article = article;
        return this;
    }

    public Assortment setCode(String code) {
        this.code = code;
        return this;
    }

    public Assortment setExternalCode(String externalCode) {
        this.externalCode = externalCode;
        return this;
    }

    public Assortment setMeta(AssortmentMeta meta) {
        this.meta = meta;
        return this;
    }

    public Assortment setImages(AssortmentImagesMeta images) {
        this.images = images;
        return this;
    }

    public Assortment setArchived(Boolean archived) {
        this.archived = archived;
        return this;
    }
}


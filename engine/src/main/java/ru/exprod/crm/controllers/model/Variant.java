package ru.exprod.crm.controllers.model;

import ru.exprod.crm.service.model.ImageModel;

import java.math.BigDecimal;
import java.util.List;

public class Variant {
    private Integer id;
    private String moyskladId;
    private String name;
    private String code;
    private String externalCode;
    private Boolean archived;
    private BigDecimal price;
    private BigDecimal quantity;
    private Integer imageGroupId;
    private List<ImageModel> images;

    public Variant setExternalCode(String externalCode) {
        this.externalCode = externalCode;
        return this;
    }

    public Variant setArchived(Boolean archived) {
        this.archived = archived;
        return this;
    }

    public Variant setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public Variant setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
        return this;
    }

    public Variant setImageGroupId(Integer imageGroupId) {
        this.imageGroupId = imageGroupId;
        return this;
    }

    public Variant setImages(List<ImageModel> images) {
        this.images = images;
        return this;
    }

    public Variant setCode(String code) {
        this.code = code;
        return this;
    }

    public Integer getId() {
        return id;
    }

    public Variant setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getMoyskladId() {
        return moyskladId;
    }

    public Variant setMoyskladId(String moyskladId) {
        this.moyskladId = moyskladId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Variant setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public String getExternalCode() {
        return externalCode;
    }

    public Boolean getArchived() {
        return archived;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public Integer getImageGroupId() {
        return imageGroupId;
    }

    public List<ImageModel> getImages() {
        return images;
    }

}

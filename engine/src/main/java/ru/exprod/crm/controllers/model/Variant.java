package ru.exprod.crm.controllers.model;

import ru.exprod.crm.service.model.ImageModel;
import ru.exprod.crm.service.model.VariantModel;

import java.math.BigDecimal;
import java.util.List;

public class Variant {
    private final Integer id;
    private final String moyskladId;
    private final String name;
    private final String code;
    private final String externalCode;
    private final Boolean archived;
    private final BigDecimal price;
    private final BigDecimal quantity;
    private final Integer imageGroupId;
    private final List<ImageModel> images;

    public Variant(VariantModel vm){
        this.id = vm.getId();
        this.moyskladId = vm.getMoyskladId();
        this.name = vm.getName();
        this.code = vm.getCode();
        this.externalCode = vm.getExternalCode();
        this.archived = vm.getArchived();
        this.price = vm.getPrice();
        this.quantity = vm.getQuantity();
        this.imageGroupId = vm.getImageGroupId();
        this.images = vm.getImages();
    }

    public Integer getId() {
        return id;
    }

    public String getMoyskladId() {
        return moyskladId;
    }

    public String getName() {
        return name;
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

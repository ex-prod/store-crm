package ru.exprod.crm.controllers.model;

import ru.exprod.crm.service.model.ImageModel;
import ru.exprod.crm.service.model.VariantModel;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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
    private final List<Image> images;

    public Variant(VariantModel variantModel, String imageHost) {
        this.id = variantModel.getId();
        this.moyskladId = variantModel.getMoyskladId();
        this.name = variantModel.getName();
        this.code = variantModel.getCode();
        this.externalCode = variantModel.getExternalCode();
        this.archived = variantModel.getArchived();
        this.price = variantModel.getPrice();
        this.quantity = variantModel.getQuantity();
        this.imageGroupId = variantModel.getImageGroupId();
        this.images = variantModel.getImages().stream()
                .map(image -> new Image(image, imageHost))
                .collect(Collectors.toList());
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

    public List<Image> getImages() {
        return images;
    }

}

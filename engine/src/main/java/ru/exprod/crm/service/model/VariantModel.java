package ru.exprod.crm.service.model;

import ru.exprod.crm.dao.model.VariantEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class VariantModel {
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

    public VariantModel(VariantEntity entity) {
        this.id = entity.getVariantId();
        this.moyskladId = entity.getMoyskladId();
        this.name = entity.getName();
        this.code = entity.getCode();
        this.externalCode = entity.getExternalCode();
        this.archived = entity.getArchived();
        this.price = entity.getPrice();
        this.quantity = entity.getQuantity();
        this.imageGroupId = entity.getImageGroup().getImageGroupId();
        this.images = entity.getImageGroup().getImageEntities().stream()
                .map(ImageModel::new)
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

    public List<ImageModel> getImages() {
        return images;
    }
}

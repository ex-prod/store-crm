package ru.exprod.crm.service.model;

import ru.exprod.crm.dao.model.ImageEntity;

public class ImageModel {
    private final Integer id;
    private final String moyskladUrlHash;
    private final String originalPath;
    private final String thumbnailPath;

    public ImageModel(ImageEntity entity) {
        this.id = entity.getImageId();
        this.moyskladUrlHash = entity.getMoyskladUrlHash();
        this.originalPath = entity.getOriginalPath();
        this.thumbnailPath = entity.getThumbnailPath();
    }

    public Integer getId() {
        return id;
    }

    public String getMoyskladUrlHash() {
        return moyskladUrlHash;
    }

    public String getOriginalPath() {
        return originalPath;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }
}

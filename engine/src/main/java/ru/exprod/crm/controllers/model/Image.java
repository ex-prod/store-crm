package ru.exprod.crm.controllers.model;

import ru.exprod.crm.service.model.ImageModel;

public class Image {
    private final String original;
    private final String thumbnail;

    public Image(ImageModel image, String imageHost) {
        this.original = imageHost + image.getOriginalPath();
        this.thumbnail = imageHost + image.getThumbnailPath();
    }

    public String getOriginal() {
        return original;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}

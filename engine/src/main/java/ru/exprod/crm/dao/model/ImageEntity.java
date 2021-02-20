package ru.exprod.crm.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "image")
public class ImageEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(updatable = false)
    private Integer imageId;

    @Column(updatable = false)
    private String moyskladUrlHash;

    private String originalPath;

    private String thumbnailPath;

    @JoinColumn(name = "image_group_id", updatable = false)
    @ManyToOne
    private ImageGroupEntity imageGroup;

    public Integer getImageId() {
        return imageId;
    }

    public String getMoyskladUrlHash() {
        return moyskladUrlHash;
    }

    public void setMoyskladUrlHash(String moyskladUrlHash) {
        this.moyskladUrlHash = moyskladUrlHash;
    }

    public String getOriginalPath() {
        return originalPath;
    }

    public void setOriginalPath(String originalPath) {
        this.originalPath = originalPath;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    public ImageGroupEntity getImageGroup() {
        return imageGroup;
    }

    public void setImageGroup(ImageGroupEntity imageGroup) {
        this.imageGroup = imageGroup;
    }
}

package ru.exprod.crm.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "image_group")
public class ImageGroupEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(updatable = false)
    private Integer imageGroupId;

    @OneToMany(mappedBy = "imageGroup", cascade = ALL, orphanRemoval = true)
    private final List<ImageEntity> imageEntities = new ArrayList<>();

    public Integer getImageGroupId() {
        return imageGroupId;
    }

    public List<ImageEntity> getImageEntities() {
        return imageEntities;
    }
}

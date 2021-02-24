package ru.exprod.crm.dao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "variant")
public class VariantEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(updatable = false)
    private Integer variantId;

    @JsonIgnore
    @JoinColumn(name = "unit_id", updatable = false)
    @ManyToOne
    private UnitEntity unit;

    @Column(updatable = false)
    private String moyskladId;

    private String name;

    private String code;

    private String externalCode;

    private Boolean archived;

    private BigDecimal price;

    private Integer quantity;

    @JsonIgnore
    @JoinColumn(name = "image_group_id", updatable = false)
    @ManyToOne(cascade = PERSIST)
    private ImageGroupEntity imageGroup;

    public Integer getVariantId() {
        return variantId;
    }

    public UnitEntity getUnit() {
        return unit;
    }

    public void setUnit(UnitEntity unit) {
        this.unit = unit;
    }

    public String getMoyskladId() {
        return moyskladId;
    }

    public void setMoyskladId(String moyskladId) {
        this.moyskladId = moyskladId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getExternalCode() {
        return externalCode;
    }

    public void setExternalCode(String externalCode) {
        this.externalCode = externalCode;
    }

    public Boolean getArchived() {
        return archived;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public ImageGroupEntity getImageGroup() {
        return imageGroup;
    }

    public void setImageGroup(ImageGroupEntity imageGroup) {
        this.imageGroup = imageGroup;
    }
}

package ru.exprod.crm.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "unit")
public class UnitEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(updatable = false)
    private Integer unitId;

    private String alias;

    private String moyskladToken;

    public Integer getUnitId() {
        return unitId;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getMoyskladToken() {
        return moyskladToken;
    }

    public void setMoyskladToken(String moyskladToken) {
        this.moyskladToken = moyskladToken;
    }
}

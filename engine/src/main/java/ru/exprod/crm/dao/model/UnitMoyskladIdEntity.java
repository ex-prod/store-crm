package ru.exprod.crm.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "unit_moysklad_id")
public class UnitMoyskladIdEntity {
    @Id
    @Column(updatable = false)
    private Integer unitId;

    private String orderStateNew;
    private String orderStateConfirmed;

    private String attributeName;
    private String attributeInstagramName;
    private String attributePhone;
    private String attributeZip;
    private String attributeCity;
    private String attributeAddress;
    private String attributeSdekAddress;
    private String attributePaid;
    private String attributeToPay;
    private String attributeDeliveryCost;
    private String attributeManager;

    private String defaultCounterparty;
    private String defaultOrganization;
    private String defaultStorage;

    public String getOrderStateNew() {
        return orderStateNew;
    }

    public String getOrderStateConfirmed() {
        return orderStateConfirmed;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public String getAttributeInstagramName() {
        return attributeInstagramName;
    }

    public String getAttributePhone() {
        return attributePhone;
    }

    public String getAttributeZip() {
        return attributeZip;
    }

    public String getAttributeCity() {
        return attributeCity;
    }

    public String getAttributeAddress() {
        return attributeAddress;
    }

    public String getAttributeSdekAddress() {
        return attributeSdekAddress;
    }

    public String getAttributePaid() {
        return attributePaid;
    }

    public String getAttributeToPay() {
        return attributeToPay;
    }

    public String getAttributeDeliveryCost() {
        return attributeDeliveryCost;
    }

    public String getAttributeManager() {
        return attributeManager;
    }

    public String getDefaultCounterparty() {
        return defaultCounterparty;
    }

    public String getDefaultOrganization() {
        return defaultOrganization;
    }

    public String getDefaultStorage() {
        return defaultStorage;
    }
}

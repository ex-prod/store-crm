package ru.exprod.crm.service.model;

import ru.exprod.crm.dao.model.UnitMoyskladIdEntity;
import ru.exprod.moysklad.api.FlowConfig;

public class FlowConfigModel implements FlowConfig {
    private final String orderStateNew;
    private final String orderStateConfirmed;

    private final String attributeName;
    private final String attributeInstagramName;
    private final String attributePhone;
    private final String attributeZip;
    private final String attributeCity;
    private final String attributeAddress;
    private final String attributeSdekAddress;
    private final String attributePaid;
    private final String attributeToPay;
    private final String attributeDeliveryCost;
    private final String attributeManager;

    private final String defaultCounterparty;
    private final String defaultOrganization;
    private final String defaultStorage;

    public FlowConfigModel(UnitMoyskladIdEntity unitMoyskladIdEntity) {
        this.orderStateNew = unitMoyskladIdEntity.getOrderStateNew();
        this.orderStateConfirmed = unitMoyskladIdEntity.getOrderStateConfirmed();

        this.attributeName = unitMoyskladIdEntity.getAttributeName();
        this.attributeInstagramName = unitMoyskladIdEntity.getAttributeInstagramName();
        this.attributePhone = unitMoyskladIdEntity.getAttributePhone();
        this.attributeZip = unitMoyskladIdEntity.getAttributeZip();
        this.attributeCity = unitMoyskladIdEntity.getAttributeCity();
        this.attributeAddress = unitMoyskladIdEntity.getAttributeAddress();
        this.attributeSdekAddress = unitMoyskladIdEntity.getAttributeSdekAddress();
        this.attributeToPay = unitMoyskladIdEntity.getAttributeToPay();
        this.attributePaid = unitMoyskladIdEntity.getAttributePaid();
        this.attributeDeliveryCost = unitMoyskladIdEntity.getAttributeDeliveryCost();
        this.attributeManager = unitMoyskladIdEntity.getAttributeManager();

        this.defaultCounterparty = unitMoyskladIdEntity.getDefaultCounterparty();
        this.defaultOrganization = unitMoyskladIdEntity.getDefaultOrganization();
        this.defaultStorage = unitMoyskladIdEntity.getDefaultStorage();
    }

    @Override
    public String getOrderStateNew() {
        return orderStateNew;
    }

    @Override
    public String getOrderStateConfirmed() {
        return orderStateConfirmed;
    }

    @Override
    public String getAttributeName() {
        return attributeName;
    }

    @Override
    public String getAttributeInstagramName() {
        return attributeInstagramName;
    }

    @Override
    public String getAttributePhone() {
        return attributePhone;
    }

    @Override
    public String getAttributeZip() {
        return attributeZip;
    }

    @Override
    public String getAttributeCity() {
        return attributeCity;
    }

    @Override
    public String getAttributeAddress() {
        return attributeAddress;
    }

    @Override
    public String getAttributeSdekAddress() {
        return attributeSdekAddress;
    }

    @Override
    public String getAttributePaid() {
        return attributePaid;
    }

    @Override
    public String getAttributeToPay() {
        return attributeToPay;
    }

    @Override
    public String getAttributeDeliveryCost() {
        return attributeDeliveryCost;
    }

    @Override
    public String getAttributeManager() {
        return attributeManager;
    }

    @Override
    public String getDefaultCounterparty() {
        return defaultCounterparty;
    }

    @Override
    public String getDefaultOrganization() {
        return defaultOrganization;
    }

    @Override
    public String getDefaultStorage() {
        return defaultStorage;
    }
}

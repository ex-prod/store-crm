package ru.exprod.moysklad.api;

public interface FlowConfig {
    String getOrderStateNew();
    String getOrderStateConfirmed();

    String getAttributeName();
    String getAttributeInstagramName();
    String getAttributePhone();
    String getAttributeZip();
    String getAttributeCity();
    String getAttributeAddress();
    String getAttributeSdekAddress();
    String getAttributePaid();
    String getAttributeToPay();
    String getAttributeDeliveryCost();
    String getAttributeManager();

    String getDefaultCounterparty();
    String getDefaultOrganization();
    String getDefaultStorage();
}

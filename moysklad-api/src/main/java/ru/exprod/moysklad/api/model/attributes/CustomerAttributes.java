package ru.exprod.moysklad.api.model.attributes;

import ru.exprod.moysklad.api.FlowConfig;
import ru.exprod.moysklad.api.model.Attribute;
import ru.exprod.moysklad.model.CustomerData;

import java.util.function.Function;

public enum CustomerAttributes {
    NAME(FlowConfig::getAttributeName, CustomerData::getName),
    INSTAGRAM_NAME(FlowConfig::getAttributeInstagramName, CustomerData::getUsername),
    PHONE(FlowConfig::getAttributePhone, CustomerData::getPhone),
    ZIP(FlowConfig::getAttributeZip, CustomerData::getZip),
    CITY(FlowConfig::getAttributeCity, CustomerData::getCity),
    ADDRESS(FlowConfig::getAttributeAddress, CustomerData::getAddress),
    SDEK_ADDRESS(FlowConfig::getAttributeSdekAddress, CustomerData::getSdekAddress);

    private final Function<FlowConfig, String> attributeIdExtractor;
    private final Function<CustomerData, Object> customerDataExtractor;

    CustomerAttributes(Function<FlowConfig, String> attributeIdExtractor, Function<CustomerData, Object> customerDataExtractor) {
        this.attributeIdExtractor = attributeIdExtractor;
        this.customerDataExtractor = customerDataExtractor;
    }

    public Attribute<Object> getAttribute(CustomerData data, FlowConfig config) {
        return Attribute.create(customerDataExtractor.apply(data), attributeIdExtractor.apply(config));
    }
}

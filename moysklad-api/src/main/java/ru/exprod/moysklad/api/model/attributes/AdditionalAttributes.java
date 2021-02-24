package ru.exprod.moysklad.api.model.attributes;

import ru.exprod.moysklad.api.FlowConfig;
import ru.exprod.moysklad.api.model.Attribute;
import ru.exprod.moysklad.model.OrderData;

import java.util.function.Function;

public enum AdditionalAttributes {
    PAID(FlowConfig::getAttributePaid, OrderData::getPrepaidType),
    TO_PAY(FlowConfig::getAttributeToPay, OrderData::getToPay),
    DELIVERY_COST(FlowConfig::getAttributeDeliveryCost, OrderData::getDeliveryCost),
    MANAGER(FlowConfig::getAttributeManager, OrderData::getManagerName);

    private final Function<FlowConfig, String> attributeIdExtractor;
    private final Function<OrderData, Object> customerDataExtractor;

    AdditionalAttributes(Function<FlowConfig, String> attributeIdExtractor, Function<OrderData, Object> customerDataExtractor) {
        this.attributeIdExtractor = attributeIdExtractor;
        this.customerDataExtractor = customerDataExtractor;
    }

    public Attribute<Object> getAttribute(OrderData data, FlowConfig config) {
        return Attribute.create(customerDataExtractor.apply(data), attributeIdExtractor.apply(config));
    }

}

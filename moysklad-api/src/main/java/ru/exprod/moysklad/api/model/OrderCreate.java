package ru.exprod.moysklad.api.model;

import org.w3c.dom.Attr;
import ru.exprod.moysklad.api.FlowConfig;
import ru.exprod.moysklad.api.model.attributes.AdditionalAttributes;
import ru.exprod.moysklad.api.model.attributes.CustomerAttributes;
import ru.exprod.moysklad.model.OrderData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.exprod.moysklad.api.model.MetaList.COUNTERPARTY;
import static ru.exprod.moysklad.api.model.MetaList.ORGANIZATION;
import static ru.exprod.moysklad.api.model.MetaList.STATE;
import static ru.exprod.moysklad.api.model.MetaList.STORAGE;

public class OrderCreate {
    private final String name;
    private final String description;
    private final MetaWrapper organization;
    private final MetaWrapper agent;
    private final MetaWrapper store;
    private final MetaWrapper state;
    private final List<Position> positions;
    private final List<Attribute<Object>> attributes = new ArrayList<>();

    public OrderCreate(OrderData orderData, FlowConfig flowConfig) {
        this.name = orderData.getName();
        this.description = orderData.getDescription();
        this.organization = ORGANIZATION.getMetaWrapper(flowConfig.getDefaultOrganization());
        this.agent = COUNTERPARTY.getMetaWrapper(flowConfig.getDefaultCounterparty());
        this.store = STORAGE.getMetaWrapper(flowConfig.getDefaultStorage());
        this.state = STATE.getMetaWrapper(flowConfig.getOrderStateNew());
        this.positions = orderData.getPositions().stream()
                .map(Position::create)
                .collect(Collectors.toList());

        List<Attribute<Object>> customerAttributes = Stream.of(CustomerAttributes.values())
                .map(attribute -> attribute.getAttribute(orderData.getCustomerData(), flowConfig))
                .collect(Collectors.toList());
        List<Attribute<Object>> additionalAttributes = Stream.of(AdditionalAttributes.values())
                .map(attribute -> attribute.getAttribute(orderData, flowConfig))
                .collect(Collectors.toList());
        this.attributes.addAll(customerAttributes);
        this.attributes.addAll(additionalAttributes);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public MetaWrapper getOrganization() {
        return organization;
    }

    public MetaWrapper getAgent() {
        return agent;
    }

    public MetaWrapper getStore() {
        return store;
    }

    public MetaWrapper getState() {
        return state;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public List<Attribute<Object>> getAttributes() {
        return attributes;
    }
}

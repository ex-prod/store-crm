package ru.exprod.moysklad.api.model;


import ru.exprod.moysklad.api.FlowConfig;
import ru.exprod.moysklad.model.OrderData;

import static ru.exprod.moysklad.api.model.MetaList.COUNTERPARTY;
import static ru.exprod.moysklad.api.model.MetaList.ORGANIZATION;
import static ru.exprod.moysklad.api.model.MetaList.STATE;
import static ru.exprod.moysklad.api.model.MetaList.STORAGE;

public class Order {
    private String id;
    private String name;
    private String description;
    private MetaWrapper organization;
    private MetaWrapper agent;
    private MetaWrapper store;
    private MetaWrapper state;
    private MetaWrapper positions;

    public static Order createOrder(OrderData orderData, FlowConfig flowConfig) {
        Order order = new Order();
        order.name = orderData.getName();
        order.description = orderData.getDescription();
        order.organization = ORGANIZATION.getMetaWrapper(flowConfig.getDefaultOrganization());
        order.agent = COUNTERPARTY.getMetaWrapper(flowConfig.getDefaultCounterparty());
        order.store = STORAGE.getMetaWrapper(flowConfig.getDefaultStorage());
        order.state = STATE.getMetaWrapper(flowConfig.getOrderStateNew());
        return order;
    }

    public String getId() {
        return id;
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

    public MetaWrapper getPositions() {
        return positions;
    }

}

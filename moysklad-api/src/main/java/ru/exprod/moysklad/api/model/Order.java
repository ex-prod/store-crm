package ru.exprod.moysklad.api.model;

import ru.exprod.moysklad.api.FlowConfig;
import ru.exprod.moysklad.model.OrderData;

import static ru.exprod.moysklad.api.model.MetaList.COUNTERPARTY;
import static ru.exprod.moysklad.api.model.MetaList.ORGANIZATION;
import static ru.exprod.moysklad.api.model.MetaList.STATE;
import static ru.exprod.moysklad.api.model.MetaList.STORAGE;

public class Order {
    private String name;
    private String description;
    private MetaWrapper organization;
    private MetaWrapper agent;
    private MetaWrapper store;
    private MetaWrapper state;

    public static Order createOrder(OrderData orderData, FlowConfig flowConfig) {
        Order order = new Order();
        order.name = orderData.getName();
        order.description = orderData.getDescription();
        order.organization = ORGANIZATION.getMeta(flowConfig.getDefaultOrganization());
        order.agent = COUNTERPARTY.getMeta(flowConfig.getDefaultCounterparty());
        order.store = STORAGE.getMeta(flowConfig.getDefaultStorage());
        order.state = STATE.getMeta(flowConfig.getOrderStateNew());
        return order;
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
}

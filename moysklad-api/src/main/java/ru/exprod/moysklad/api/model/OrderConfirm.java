package ru.exprod.moysklad.api.model;

import ru.exprod.moysklad.api.FlowConfig;

import static ru.exprod.moysklad.api.model.MetaList.STATE;

public class OrderConfirm {
    private final MetaWrapper state;

    public OrderConfirm(FlowConfig flowConfig) {
        this.state = STATE.getMetaWrapper(flowConfig.getOrderStateConfirmed());
    }

    public MetaWrapper getState() {
        return state;
    }
}

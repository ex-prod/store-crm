package ru.exprod.moysklad.api.model;

import ru.exprod.moysklad.api.FlowConfig;

import static ru.exprod.moysklad.api.model.MetaList.STATE;

public class OrderCancel {
    private final MetaWrapper state;

    public OrderCancel(FlowConfig flowConfig) {
        this.state = STATE.getMetaWrapper(flowConfig.getOrderStateCancelled());
    }

    public MetaWrapper getState() {
        return state;
    }
}

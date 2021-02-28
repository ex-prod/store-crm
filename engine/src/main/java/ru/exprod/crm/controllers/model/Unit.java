package ru.exprod.crm.controllers.model;

import ru.exprod.crm.service.model.UnitModel;

public class Unit {
    private final int id;
    private final String alias;


    public Unit(UnitModel unitModel) {
        this.id = unitModel.getId();
        this.alias = unitModel.getAlias();
    }

    public int getId() {
        return id;
    }

    public String getAlias() {
        return alias;
    }
}

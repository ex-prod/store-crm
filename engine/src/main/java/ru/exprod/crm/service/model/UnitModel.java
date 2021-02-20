package ru.exprod.crm.service.model;

import ru.exprod.crm.dao.model.UnitEntity;

public class UnitModel {
    private final Integer id;
    private final String alias;
    private final String token;

    public UnitModel(UnitEntity entity) {
        this.id = entity.getUnitId();
        this.alias = entity.getAlias();
        this.token = entity.getMoyskladToken();
    }

    public Integer getId() {
        return id;
    }

    public String getAlias() {
        return alias;
    }

    public String getToken() {
        return token;
    }
}

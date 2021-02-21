package ru.exprod.moysklad.api.model;

public class Order {
    private String id;
    private String name;
    private String description;
    private MetaWrapper organization;
    private MetaWrapper agent;
    private MetaWrapper store;
    private MetaWrapper state;
    private MetaWrapper positions;

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

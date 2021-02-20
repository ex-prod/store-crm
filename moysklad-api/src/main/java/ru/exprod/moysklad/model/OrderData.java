package ru.exprod.moysklad.model;

public class OrderData {
    private final String name;
    private final String ownerId;
    private final String counterPartyId;
    private final String stateId;

    public OrderData(String name, String ownerId, String counterPartyId, String stateId) {
        this.name = name;
        this.ownerId = ownerId;
        this.counterPartyId = counterPartyId;
        this.stateId = stateId;
    }
}

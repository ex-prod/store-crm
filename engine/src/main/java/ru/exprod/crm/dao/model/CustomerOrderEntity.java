package ru.exprod.crm.dao.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.math.BigDecimal;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "customer_order")
public class CustomerOrderEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(updatable = false)
    private Integer customerOrderId;

    @JoinColumn(name = "unit_id", updatable = false)
    @ManyToOne
    private UnitEntity unit;

    @Column
    private String moyskladId;

    private String name;

    private Integer positions;

    private BigDecimal amount;

    private String state;

    @JoinColumn(name = "customer_id", updatable = false)
    @ManyToOne(cascade = PERSIST)
    private CustomerEntity customer;

    @JoinColumn(name = "manager_id", updatable = false)
    @ManyToOne
    private ManagerEntity manager;

    private BigDecimal deliveryCost;

    private String deliveryType;

    private BigDecimal paid;

    private String prepaidType;

    private String comment;

    @OneToMany(mappedBy = "customerOrder", cascade = ALL, orphanRemoval = true)
    private List<CustomerOrderPositionEntity> positionList;

    public Integer getCustomerOrderId() {
        return customerOrderId;
    }

    public UnitEntity getUnit() {
        return unit;
    }

    public void setUnit(UnitEntity unit) {
        this.unit = unit;
    }

    public String getMoyskladId() {
        return moyskladId;
    }

    public void setMoyskladId(String moyskladId) {
        this.moyskladId = moyskladId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPositions() {
        return positions;
    }

    public void setPositions(Integer positions) {
        this.positions = positions;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public ManagerEntity getManager() {
        return manager;
    }

    public void setManager(ManagerEntity manager) {
        this.manager = manager;
    }

    public BigDecimal getDeliveryCost() {
        return deliveryCost;
    }

    public void setDeliveryCost(BigDecimal deliveryCost) {
        this.deliveryCost = deliveryCost;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public BigDecimal getPaid() {
        return paid;
    }

    public void setPaid(BigDecimal paid) {
        this.paid = paid;
    }

    public String getPrepaidType() {
        return prepaidType;
    }

    public void setPrepaidType(String prepaidType) {
        this.prepaidType = prepaidType;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<CustomerOrderPositionEntity> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<CustomerOrderPositionEntity> positionList) {
        this.positionList = positionList;
    }
}

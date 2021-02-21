package ru.exprod.crm.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.math.BigDecimal;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "customer_order_position")
public class CustomerOrderPositionEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(updatable = false)
    private Integer customerOrderId;

    @Column
    private String moyskladId;

    @JoinColumn(name = "customer_order_id", updatable = false)
    @ManyToOne
    private CustomerOrderEntity customerOrder;

    @JoinColumn(name = "variant_id", updatable = false)
    @ManyToOne
    private VariantEntity variant;

    private BigDecimal count;

    private BigDecimal price;

    private BigDecimal discount;

    public Integer getCustomerOrderId() {
        return customerOrderId;
    }

    public String getMoyskladId() {
        return moyskladId;
    }

    public void setMoyskladId(String moyskladId) {
        this.moyskladId = moyskladId;
    }

    public CustomerOrderEntity getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrderEntity customerOrder) {
        this.customerOrder = customerOrder;
    }

    public VariantEntity getVariant() {
        return variant;
    }

    public void setVariant(VariantEntity variant) {
        this.variant = variant;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }
}

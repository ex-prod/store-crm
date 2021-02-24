package ru.exprod.moysklad.model;

import java.math.BigDecimal;

public interface CashinData {
    String getOrderId();
    BigDecimal getPrepaidValue();
}

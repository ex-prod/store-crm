package ru.exprod.moysklad.api.model;

import java.math.BigDecimal;
import java.math.BigInteger;

public class CashinOperationLink {
    private Meta meta;
    private BigInteger linkedSum;

    public static CashinOperationLink create(Meta meta, BigDecimal linkedSum) {
        CashinOperationLink link = new CashinOperationLink();
        link.meta = meta;
        link.linkedSum = linkedSum.multiply(BigDecimal.valueOf(100)).toBigInteger();
        return link;
    }

    public Meta getMeta() {
        return meta;
    }

    public BigInteger getLinkedSum() {
        return linkedSum;
    }
}

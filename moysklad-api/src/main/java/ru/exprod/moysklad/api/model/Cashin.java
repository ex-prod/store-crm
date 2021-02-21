package ru.exprod.moysklad.api.model;

import ru.exprod.moysklad.api.FlowConfig;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

import static ru.exprod.moysklad.api.model.MetaList.COUNTERPARTY;
import static ru.exprod.moysklad.api.model.MetaList.CUSTOMERORDER;
import static ru.exprod.moysklad.api.model.MetaList.ORGANIZATION;

public class Cashin {
    private MetaWrapper organization;
    private MetaWrapper agent;
    private BigInteger sum;

    private List<CashinOperationLink> operations;

    public static Cashin create(BigDecimal value, FlowConfig flowConfig, String orderId) {
        Cashin cashin = new Cashin();
        cashin.organization = ORGANIZATION.getMetaWrapper(flowConfig.getDefaultOrganization());
        cashin.agent = COUNTERPARTY.getMetaWrapper(flowConfig.getDefaultCounterparty());
        cashin.sum = value.multiply(BigDecimal.valueOf(100)).toBigInteger();

        CashinOperationLink link = CashinOperationLink.create(CUSTOMERORDER.getMeta(orderId), value);
        cashin.operations = Collections.singletonList(link);
        return cashin;
    }

    public MetaWrapper getOrganization() {
        return organization;
    }

    public MetaWrapper getAgent() {
        return agent;
    }

    public List<CashinOperationLink> getOperations() {
        return operations;
    }

    public BigInteger getSum() {
        return sum;
    }
}

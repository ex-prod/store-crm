package ru.exprod.moysklad;

import ru.exprod.moysklad.model.CounterParty;
import ru.exprod.moysklad.model.CounterPartyData;
import ru.exprod.moysklad.model.Order;
import ru.exprod.moysklad.model.OrderData;
import ru.exprod.moysklad.model.OrderPosition;
import ru.exprod.moysklad.model.OrderPositionData;
import ru.exprod.moysklad.model.Variant;

import java.util.List;

public interface MoySkladApi {
    List<Variant> getAssortmentVariants();
    List<Order> getOrders();
    CounterParty createCounterParty(CounterPartyData data);
    Order createOrder(OrderData data);
    OrderPosition createOrderPosition(OrderPositionData data);
}

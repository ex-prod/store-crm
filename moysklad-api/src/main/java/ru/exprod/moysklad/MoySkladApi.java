package ru.exprod.moysklad;


import ru.exprod.moysklad.api.model.Cashin;
import ru.exprod.moysklad.api.model.Order;
import ru.exprod.moysklad.model.CashinData;
import ru.exprod.moysklad.model.ConfirmOrderData;
import ru.exprod.moysklad.model.OrderData;
import ru.exprod.moysklad.model.Variant;

import java.util.List;

public interface MoySkladApi {
    String ENTITY_URL = "https://online.moysklad.ru/api/remap/1.2/entity";

    List<Variant> getAssortmentVariants();

    List<Order> getOrders();

    Order createOrder(OrderData data);

    Order approveOrder(ConfirmOrderData data);

    Cashin createCashin(CashinData data);
}

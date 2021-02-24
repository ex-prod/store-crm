package ru.exprod.crm.service;

import org.springframework.stereotype.Service;
import ru.exprod.crm.controllers.model.ordercreate.OrderCreateRequest;
import ru.exprod.crm.service.db.CustomerOrderDbService;
import ru.exprod.crm.service.model.CustomerOrderModel;
import ru.exprod.crm.service.moyskladapi.MoySkladApiService;
import ru.exprod.crm.service.moyskladapi.model.CreateOrderModel;

@Service
public class CustomerOrderService {

    private final CustomerOrderDbService customerOrderDbService;
    private final MoySkladApiService moySkladApiService;

    public CustomerOrderService(CustomerOrderDbService customerOrderDbService, MoySkladApiService moySkladApiService) {
        this.customerOrderDbService = customerOrderDbService;
        this.moySkladApiService = moySkladApiService;
    }

    public void createOrder(OrderCreateRequest orderCreate, Integer unitId) {
        CustomerOrderModel order = customerOrderDbService.createOrder(orderCreate, unitId);

        moySkladApiService.createOrder(new CreateOrderModel(order));

    }
}

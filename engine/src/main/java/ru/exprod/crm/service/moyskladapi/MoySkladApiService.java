package ru.exprod.crm.service.moyskladapi;

import org.springframework.stereotype.Service;
import ru.exprod.crm.service.moyskladapi.model.CreateOrderModel;
import ru.exprod.moysklad.MoySkladApi;

import java.util.HashMap;
import java.util.Map;

@Service
public class MoySkladApiService {
    private final Map<Integer, MoySkladApi> apiInstances = new HashMap<>();

    public void createOrder(CreateOrderModel createOrderModel) {

    }
}

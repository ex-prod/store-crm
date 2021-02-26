package ru.exprod.crm.service;

import org.springframework.stereotype.Service;
import ru.exprod.crm.controllers.model.order.OrderCreateRequest;
import ru.exprod.crm.service.db.CustomerOrderDbService;
import ru.exprod.crm.service.model.CustomerOrderModel;
import ru.exprod.crm.service.model.CustomerOrderPositionModel;
import ru.exprod.crm.service.moyskladapi.MoySkladApiService;
import ru.exprod.crm.service.moyskladapi.model.CreateOrderModel;
import ru.exprod.moysklad.api.model.Order;
import ru.exprod.moysklad.api.model.Position;

import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CustomerOrderService {

    private final CustomerOrderDbService customerOrderDbService;
    private final MoySkladApiService moySkladApiService;

    public CustomerOrderService(CustomerOrderDbService customerOrderDbService, MoySkladApiService moySkladApiService) {
        this.customerOrderDbService = customerOrderDbService;
        this.moySkladApiService = moySkladApiService;
    }

    public List<CustomerOrderModel> getOrders(int unitId) {
        return customerOrderDbService.getOrders(unitId);
    }

    public CustomerOrderModel createOrder(OrderCreateRequest orderCreate, Integer unitId) {
        CustomerOrderModel order = customerOrderDbService.createOrder(orderCreate, unitId);

        Order createdMoySkladOrder = moySkladApiService.createOrder(new CreateOrderModel(order));
        List<Position> positions = moySkladApiService.getPositions(createdMoySkladOrder.getId(), unitId);
        Map<Integer, String> positionIdsMap = getPositionsIdsMap(positions, order.getPositionList());

        return customerOrderDbService.changeStatusToNew(order.getId(), createdMoySkladOrder.getId(), positionIdsMap);
    }

    public CustomerOrderModel confirmOrder(int orderId, int unitId) {
        CustomerOrderModel order = customerOrderDbService.getOrderByUnitAndId(orderId, unitId);
        if (!order.getState().equals("NEW")) {
            throw new RuntimeException("You cannot confirm order with state " + order.getState());
        }
        moySkladApiService.confirmOrder(order.getMoyskladId(), unitId);
        moySkladApiService.createCashin(order.getMoyskladId(), order.getPaid(), unitId);
        return customerOrderDbService.changeStatusToConfirm(orderId);
    }


    private Map<Integer, String> getPositionsIdsMap(List<Position> remote, List<CustomerOrderPositionModel> local) {
        Map<String, String> remoteIds = remote.stream()
                .collect(Collectors.toMap(this::getRemoteKey, Position::getId));

        Map<String, Integer> localIds = local.stream()
                .collect(Collectors.toMap(this::getLocalKey, CustomerOrderPositionModel::getId));

        Map<Integer, String> resultMap = new HashMap<>();
        localIds.forEach((k, v) -> resultMap.put(v, remoteIds.get(k)));
        return resultMap;
    }

    private String getRemoteKey(Position pos) {
        String variantId = pos.getAssortment().meta.extractId();
        return variantId + "-" +
                pos.getQuantity().setScale(2, RoundingMode.HALF_UP) + "-" +
                pos.getDiscount().setScale(2, RoundingMode.HALF_UP);
    }

    private String getLocalKey(CustomerOrderPositionModel pos) {
        String variantId = pos.getVariant().getMoyskladId();
        return variantId + "-" +
                pos.getCount().setScale(2, RoundingMode.HALF_UP) + "-" +
                pos.getDiscount().setScale(2, RoundingMode.HALF_UP);
    }

}

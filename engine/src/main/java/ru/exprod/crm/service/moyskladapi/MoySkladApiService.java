package ru.exprod.crm.service.moyskladapi;

import org.springframework.stereotype.Service;
import ru.exprod.crm.service.db.UnitService;
import ru.exprod.crm.service.model.UnitModel;
import ru.exprod.crm.service.moyskladapi.model.CreateOrderModel;
import ru.exprod.moysklad.MoySkladApi;
import ru.exprod.moysklad.api.MoySkladApiImpl;
import ru.exprod.moysklad.api.model.Order;
import ru.exprod.moysklad.api.model.Position;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MoySkladApiService {
    private final UnitService unitService;
    private final Map<Integer, MoySkladApi> apiInstances = new ConcurrentHashMap<>();

    public MoySkladApiService(UnitService unitService) {
        this.unitService = unitService;
    }

    public Order createOrder(CreateOrderModel createOrderModel) {
        return getApiInstance(createOrderModel.getUnitId()).createOrder(createOrderModel);
    }

    public List<Position> getPositions(String moyskladId, Integer unitId) {
        return getApiInstance(unitId).getPositions(moyskladId);
    }

    private MoySkladApi getApiInstance(Integer unitId) {
        MoySkladApi api = apiInstances.get(unitId);
        if (api == null) {
            api = getOrCrate(unitId);
        }
        return api;
    }

    private synchronized MoySkladApi getOrCrate(Integer unitId) {
        MoySkladApi found = apiInstances.get(unitId);
        if (found == null) {
            UnitModel unit = unitService.getById(unitId);
            found = new MoySkladApiImpl(unit.getToken(), unit.getFlowConfigModel());
            apiInstances.put(unitId, found);
        }
        return found;
    }
}

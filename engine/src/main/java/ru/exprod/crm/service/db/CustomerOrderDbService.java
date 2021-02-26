package ru.exprod.crm.service.db;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.exprod.crm.controllers.model.order.CreateCustomerRequest;
import ru.exprod.crm.controllers.model.order.OrderCreateRequest;
import ru.exprod.crm.controllers.model.order.PositionCreateRequest;
import ru.exprod.crm.dao.model.CustomerEntity;
import ru.exprod.crm.dao.model.CustomerOrderEntity;
import ru.exprod.crm.dao.model.CustomerOrderPositionEntity;
import ru.exprod.crm.dao.model.UnitEntity;
import ru.exprod.crm.dao.model.VariantEntity;
import ru.exprod.crm.dao.repo.CustomerOrderRepository;
import ru.exprod.crm.service.model.CustomerOrderModel;
import ru.exprod.crm.service.model.PrepaidType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CustomerOrderDbService {
    private final CustomerOrderRepository customerOrderRepository;
    private final UnitService unitService;
    private final VariantService variantService;

    public CustomerOrderDbService(
            CustomerOrderRepository customerOrderRepository,
            UnitService unitService,
            VariantService variantService
    ) {
        this.customerOrderRepository = customerOrderRepository;
        this.unitService = unitService;
        this.variantService = variantService;
    }

    @Transactional
    public CustomerOrderModel createOrder(OrderCreateRequest orderCreateRequest, Integer unitId) {
        CustomerOrderEntity entity = new CustomerOrderEntity();
        UnitEntity unit = unitService.byId(unitId);

        entity.setMoyskladId("");
        entity.setUnit(unit);
        entity.setName("");
        entity.setPositions(orderCreateRequest.getPositions().size());
        entity.setPositionList(createPositions(orderCreateRequest.getPositions(), entity));
        entity.setAmount(calculateAmount(orderCreateRequest.getPositions()));
        entity.setState("");
        entity.setCustomer(createCustomer(orderCreateRequest.getCustomer()));
        entity.setManager(unit.getDefaultManager()); //todo change to really creator
        entity.setDeliveryCost(orderCreateRequest.getDeliveryCost());
        entity.setDeliveryType(orderCreateRequest.getDeliveryType().name());
        entity.setComment(orderCreateRequest.getDescription());
        entity.setPrepaidType(orderCreateRequest.getPrepaidType().name());

        //todo move logic to service
        if (orderCreateRequest.getPrepaidType().equals(PrepaidType.FULL)) {
            entity.setPaid(entity.getAmount());
        } else {
            entity.setPaid(BigDecimal.valueOf(500));
        }

        customerOrderRepository.save(entity);
        return new CustomerOrderModel(entity);
    }

    @Transactional
    public CustomerOrderModel changeStatusToNew(
            Integer customerOrderId,
            String moySkladId,
            Map<Integer, String> positionsMap
    ) {
        CustomerOrderEntity entity = byId(customerOrderId);
        entity.setMoyskladId(moySkladId);
        entity.getPositionList().forEach(position ->
                position.setMoyskladId(positionsMap.get(position.getCustomerOrderPositionId())));
        entity.setState("NEW");
        customerOrderRepository.save(entity);
        return new CustomerOrderModel(entity);
    }

    private List<CustomerOrderPositionEntity> createPositions(List<PositionCreateRequest> positions, CustomerOrderEntity order) {
        return positions.stream()
                .map(pos -> this.createPosition(pos, order))
                .collect(Collectors.toList());
    }

    private CustomerOrderPositionEntity createPosition(PositionCreateRequest request, CustomerOrderEntity order) {
        VariantEntity variant = variantService.byId(request.getVariantId());
        if (variant.getQuantity().compareTo(request.getQuantity()) < 0) {
            throw new RuntimeException("Недостаточное количество товара " + variant.getName());
        }
        CustomerOrderPositionEntity entity = new CustomerOrderPositionEntity();
        entity.setPrice(request.getPrice());
        entity.setDiscount(request.getDiscount());
        entity.setCount(request.getQuantity());
        entity.setVariant(variant);
        entity.setCustomerOrder(order);
        entity.setMoyskladId("");
        return entity;
    }

    private BigDecimal calculateAmount(List<PositionCreateRequest> positions) {
        return BigDecimal.ZERO;
    }

    private CustomerEntity createCustomer(CreateCustomerRequest customer) {
        CustomerEntity entity = new CustomerEntity();
        entity.setCity(customer.getCity());
        entity.setAddress(customer.getAddress().isEmpty() ? customer.getSdekAddress() : customer.getAddress());
        entity.setName(customer.getName());
        entity.setPhone(customer.getPhone());
        entity.setUsername(customer.getUsername());
        entity.setZip(customer.getZip());
        return entity;
    }

    CustomerOrderEntity byId(Integer customerOrderId) {
        return customerOrderRepository.getOne(customerOrderId);
    }

}

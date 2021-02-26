package ru.exprod.crm.service.db;

import org.hibernate.internal.CriteriaImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.math.BigDecimal.ONE;
import static java.math.BigDecimal.ZERO;
import static java.math.RoundingMode.HALF_UP;
import static org.springframework.data.domain.Sort.Direction.DESC;

@Service
public class CustomerOrderDbService {

    private static final BigDecimal PERCENT_MULTIPLER = new BigDecimal("0.01");

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

    @Transactional(readOnly = true)
    public CustomerOrderModel getOrderByUnitAndId(int orderId, int unitId) {
        CustomerOrderEntity entity = byId(orderId);
        if (entity.getUnit().getUnitId() != unitId) {
            throw new RuntimeException("Not found orderId " + orderId);
        }
        return new CustomerOrderModel(entity);
    }

    @Transactional(readOnly = true)
    public List<CustomerOrderModel> getOrders(int unitId) {
        Pageable p = PageRequest.of(0, 30, Sort.by(DESC, "customerOrderId"));
        return customerOrderRepository.findAllByUnit_UnitId(unitId, p).stream()
                .map(CustomerOrderModel::new)
                .collect(Collectors.toList());
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
        entity.setDeliveryType(orderCreateRequest.getDeliveryType());
        entity.setComment(orderCreateRequest.getDescription());
        entity.setPrepaidType(orderCreateRequest.getPrepaidType());

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
            int customerOrderId,
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

    public CustomerOrderModel changeStatusToConfirm(int customerOrderId) {
        CustomerOrderEntity entity = byId(customerOrderId);
        entity.setState("CONFIRMED");
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
        BigDecimal total = ZERO;
        for (PositionCreateRequest pos : positions) {
            BigDecimal discountMultipler = ONE.add(pos.getDiscount().negate().multiply(PERCENT_MULTIPLER));
            BigDecimal posPrice = pos.getPrice().multiply(discountMultipler);
            total = total.add(posPrice.multiply(pos.getQuantity()));
        }
        return total.setScale(2, HALF_UP);
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

    CustomerOrderEntity byId(int customerOrderId) {
        return customerOrderRepository.getOne(customerOrderId);
    }
}

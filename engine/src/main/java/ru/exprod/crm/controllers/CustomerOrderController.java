package ru.exprod.crm.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.exprod.crm.controllers.model.order.Order;
import ru.exprod.crm.controllers.model.order.OrderCreateRequest;
import ru.exprod.crm.service.CustomerOrderService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(
        value = "/api/unit/{unitId}/customerorders",
        consumes = APPLICATION_JSON_VALUE,
        produces = APPLICATION_JSON_VALUE
)
public class CustomerOrderController {
    private final CustomerOrderService customerOrderService;

    public CustomerOrderController(CustomerOrderService customerOrderService) {
        this.customerOrderService = customerOrderService;
    }

    @PostMapping
    public Order create(@RequestBody OrderCreateRequest request, @PathVariable int unitId) {
        return new Order(customerOrderService.createOrder(request, unitId));
    }

}

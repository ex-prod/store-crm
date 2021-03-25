package ru.exprod.crm.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.exprod.crm.controllers.model.order.Order;
import ru.exprod.crm.controllers.model.order.OrderCreateRequest;
import ru.exprod.crm.service.CustomerOrderService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(
        value = "/api/units/{unitId}/customerorders",
        consumes = APPLICATION_JSON_VALUE,
        produces = APPLICATION_JSON_VALUE
)
public class CustomerOrderController {
    private final CustomerOrderService customerOrderService;

    public CustomerOrderController(CustomerOrderService customerOrderService) {
        this.customerOrderService = customerOrderService;
    }

    @GetMapping
    public List<Order> getOrders(@PathVariable int unitId) {
        return customerOrderService.getOrders(unitId).stream()
                .map(Order::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public Order create(@RequestBody OrderCreateRequest request, @PathVariable int unitId) {
        return new Order(customerOrderService.createOrder(request, unitId));
    }

    @PostMapping("/{id}/confirm")
    public Order confirm(@PathVariable int unitId, @PathVariable("id") int orderId) {
        return new Order(customerOrderService.confirmOrder(orderId, unitId));
    }

    @PostMapping("/{id}/cancel")
    public Order cancel(@PathVariable int unitId, @PathVariable("id") int orderId) {
        return new Order(customerOrderService.cancelOrder(orderId, unitId));
    }


}

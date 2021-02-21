package ru.exprod.crm.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.exprod.crm.controllers.model.ordercreate.OrderCreateRequest;

@RestController
@RequestMapping("/api/customerorders")
public class CustomerOrderController {
    @PostMapping
    public void create(OrderCreateRequest request) {

    }

}

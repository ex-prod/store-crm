package ru.exprod.crm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.exprod.crm.controllers.model.SearchRequest;
import ru.exprod.crm.controllers.model.Variant;

import java.util.List;

import static java.util.Collections.emptyList;

@RestController
@RequestMapping("/api/variants")
public class VariantController {

    @GetMapping
    public List<Variant> getOrders(@RequestParam(name = "search", required = false) SearchRequest searchRequest) {
        return emptyList();
    }
}

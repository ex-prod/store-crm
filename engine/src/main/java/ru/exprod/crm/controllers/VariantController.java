package ru.exprod.crm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.exprod.crm.controllers.model.FilterRequest;
import ru.exprod.crm.dao.model.VariantEntity;
import ru.exprod.crm.service.VariantService;
import ru.exprod.moysklad.model.Variant;

import java.util.List;

@RestController
public class VariantController {

    @Autowired
    VariantService variantService;

    @GetMapping("/api/unit/{unit_id}/variants")
    public List<VariantEntity> getOrders(@PathVariable int unit_id,
                                         @RequestParam(name = "search", required = false, defaultValue = "") String search) {

        return variantService.searchWithFilter(unit_id, search);
    }
}

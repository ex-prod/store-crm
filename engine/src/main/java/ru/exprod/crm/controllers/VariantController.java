package ru.exprod.crm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.exprod.crm.dao.model.VariantEntity;
import ru.exprod.crm.service.db.VariantService;

import java.util.List;

@RestController
public class VariantController {

    @Autowired
    VariantService variantService;

    @GetMapping("/api/units/{unitId}/variants")
    public List<VariantEntity> getOrders(@PathVariable int unitId,
                                         @RequestParam(name = "search", required = false, defaultValue = "") String search) {

        return variantService.searchWithFilter(unitId, search);
    }
}

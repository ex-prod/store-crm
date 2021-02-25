package ru.exprod.crm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.exprod.crm.service.VariantService;
import ru.exprod.moysklad.model.Variant;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/variants")
public class VariantController {

    @Autowired
    VariantService variantService;

    @GetMapping("/unit/{unit_id}/variants")
    public List<Variant> getOrders(@PathVariable int unit_id,
                                   @RequestParam(name = "search", required = false, defaultValue = "") String search) {
        variantService.searchWithFilter(unit_id, search)
        return new ArrayList<>();
    }
}

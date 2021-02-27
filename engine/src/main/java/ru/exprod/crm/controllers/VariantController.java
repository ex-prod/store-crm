package ru.exprod.crm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.exprod.crm.controllers.model.Variant;
import ru.exprod.crm.service.model.VariantModel;
import ru.exprod.crm.service.db.VariantService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class VariantController {

    @Autowired
    VariantService variantService;

    @GetMapping("/unit/{unit_id}/variants")
    public List<Variant> getOrders(@PathVariable int unit_id,
                                   @RequestParam(name = "search", required = false, defaultValue = "") String search) {
        List<VariantModel> models = variantService.searchWithFilter(unit_id, search);
        return models.stream().map(Variant::new).collect(Collectors.toList());
    }
}

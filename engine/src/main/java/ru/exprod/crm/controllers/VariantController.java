package ru.exprod.crm.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import ru.exprod.crm.controllers.model.Variant;
import ru.exprod.crm.service.model.VariantModel;
import ru.exprod.crm.service.db.VariantService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/units/{unit_id}/variants")
public class VariantController {

    private final VariantService variantService;
    private final String imageHost;
    public VariantController(
            VariantService variantService,
            @Value("${host.media.images}") String imageHost
    ) {
        this.variantService = variantService;
        this.imageHost = imageHost;
    }

    @GetMapping
    public List<Variant> getOrders(@PathVariable int unit_id,
                                   @RequestParam(name = "search", required = false, defaultValue = "") String search) {
        List<VariantModel> models = variantService.searchWithFilter(unit_id, search);
        return models.stream()
                .map(v -> new Variant(v, imageHost))
                .collect(Collectors.toList());
    }
}

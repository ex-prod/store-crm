package ru.exprod.crm.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.exprod.moysklad.MoySkladApi;
import ru.exprod.moysklad.api.MoySkladApiImpl;
import ru.exprod.moysklad.model.DownloadableImage;
import ru.exprod.moysklad.model.Variant;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/manage")
public class ManageController {
    @RequestMapping("/sync")
    public List<String> getOrders() {
        MoySkladApi api = new MoySkladApiImpl();
        List<Variant> variants = api.getProducts();
        List<Variant> filtered = variants.stream()
                .filter(p -> p.getImageCount() > 0)
                .collect(Collectors.toList());
        List<DownloadableImage> images = filtered.get(0).getImages();
        File f = images.get(0).getFile();
        System.out.println(f.getPath());
        return filtered.stream()
                .map(Variant::getId)
                .collect(Collectors.toList());
    }

}

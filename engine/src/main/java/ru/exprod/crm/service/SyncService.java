package ru.exprod.crm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import ru.exprod.crm.service.model.ImageModel;
import ru.exprod.crm.service.model.UnitModel;
import ru.exprod.crm.service.model.VariantModel;
import ru.exprod.moysklad.MoySkladApi;
import ru.exprod.moysklad.api.MoySkladApiImpl;
import ru.exprod.moysklad.model.DownloadableImage;
import ru.exprod.moysklad.model.Variant;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class SyncService {

    private static final Logger log = LoggerFactory.getLogger(SyncService.class);
    private static final Pattern HASH_PATTERN = Pattern.compile("/([0-9a-f]{8}-[0-9a-f]{4}-[0-5][0-9a-f]{3}-[089ab][0-9a-f]{3}-[0-9a-f]{12})");

    private final VariantService variantService;
    private final UnitService unitService;
    private final MediaService mediaService;

    public SyncService(VariantService variantService, UnitService unitService, MediaService mediaService) {
        this.variantService = variantService;
        this.unitService = unitService;
        this.mediaService = mediaService;
    }

    public void syncAll() {
        List<UnitModel> units = unitService.getAll();

        units.forEach(this::syncUnit);
    }

    public void syncUnit(UnitModel unit) {
        MoySkladApi api = new MoySkladApiImpl();
        List<Variant> variants = api.getProducts();

        variants.forEach(variant -> variantService.syncVariant(variant, unit.getId()));
        variants.forEach(this::syncImages);
    }

    private void syncImages(Variant variant) {
        VariantModel variantModel = variantService.getVariantByMoyskladId(variant.getId());
        Map<String, ImageModel> existedImages = variantModel.getImages().stream()
                .collect(Collectors.toMap(ImageModel::getMoyskladUrlHash, image -> image));

        Map<String, DownloadableImage> actualImages = variant.getImages().stream()
                .collect(Collectors.toMap(image -> hash(image.getOriginalDownloadHref()), image -> image));

        if (existedImages.isEmpty() && actualImages.isEmpty()) {
            return;
        }

        deleteRedundantImages(existedImages, actualImages);
        addNewImages(existedImages, actualImages, variantModel.getImageGroupId());
    }

    private void addNewImages(Map<String, ImageModel> existedImages, Map<String, DownloadableImage> actualImages, Integer imageGroupId) {
        for (Map.Entry<String, DownloadableImage> actualImage : actualImages.entrySet()) {
            if (!existedImages.containsKey(actualImage.getKey())) {
                mediaService.addMediaFile(imageGroupId, actualImage.getKey(), actualImage.getValue());
                log.info("media added " + actualImage.getKey());
            }
        }
    }

    private void deleteRedundantImages(Map<String, ImageModel> existedImages, Map<String, DownloadableImage> actualImages) {
        for (Map.Entry<String, ImageModel> existedEntry : existedImages.entrySet()) {
            if (!actualImages.containsKey(existedEntry.getKey())) {
                mediaService.deleteMediaFile(existedEntry.getValue().getId());
            }
        }
    }

    private String hash(String url) {
        Matcher matcher = HASH_PATTERN.matcher(url);
        if (matcher.find()) {
            return matcher.group(1);
        }
        try {
            return DigestUtils.md5DigestAsHex(new ByteArrayInputStream(url.getBytes()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

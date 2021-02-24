package ru.exprod.crm.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import ru.exprod.crm.service.db.UnitService;
import ru.exprod.crm.service.db.VariantService;
import ru.exprod.crm.service.model.ImageModel;
import ru.exprod.crm.service.model.UnitModel;
import ru.exprod.crm.service.model.VariantModel;
import ru.exprod.moysklad.MoySkladApi;
import ru.exprod.moysklad.api.MoySkladApiImpl;
import ru.exprod.moysklad.api.model.Order;
import ru.exprod.moysklad.model.CashinData;
import ru.exprod.moysklad.model.CustomerData;
import ru.exprod.moysklad.model.DownloadableImage;
import ru.exprod.moysklad.model.OrderData;
import ru.exprod.moysklad.model.PositionData;
import ru.exprod.moysklad.model.Variant;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
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

    public synchronized void syncAll() {
        List<UnitModel> units = unitService.getAll();

        units.forEach(this::syncUnit);
    }

    public void syncUnit(UnitModel unit) {
        MoySkladApi api = new MoySkladApiImpl(unit.getToken(), unit.getFlowConfigModel());
        List<Variant> variants = api.getAssortmentVariants();

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
                ImageModel created = mediaService.addMediaFile(imageGroupId, actualImage.getKey(), actualImage.getValue());
                log.info("media added " + created.getOriginalPath());
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

    public void test2() {
        List<UnitModel> units = unitService.getAll();
        UnitModel unit = units.get(0);
        MoySkladApi api = new MoySkladApiImpl(unit.getToken(), unit.getFlowConfigModel());

        Order order = test();

        api.approveOrder(order::getId);
        api.createCashin(new CashinData() {
            @Override
            public String getOrderId() {
                return order.getId();
            }

            @Override
            public BigDecimal getPrepaidValue() {
                return new BigDecimal(500);
            }
        });
    }

    public Order test() {
        List<UnitModel> units = unitService.getAll();
        UnitModel unit = units.get(0);
        MoySkladApi api = new MoySkladApiImpl(unit.getToken(), unit.getFlowConfigModel());

        OrderData data = new OrderData() {
            @Override
            public String getName() {
                return "name bla 1" + ThreadLocalRandom.current().nextInt();
            }

            public String getDescription() {
                return "desc order";
            }

            @Override
            public String getManagerName() {
                return "марфа";
            }

            @Override
            public String getPrepaidType() {
                return "Предоплата (500)";
            }

            @Override
            public BigDecimal getToPay() {
                return BigDecimal.valueOf(482);
            }

            @Override
            public BigDecimal getDeliveryCost() {
                return BigDecimal.valueOf(200);
            }

            @Override
            public CustomerData getCustomerData() {
                return new CustomerData() {
                    @Override
                    public String getName() {
                        return "Паулина";
                    }

                    @Override
                    public String getPhone() {
                        return "100500";
                    }

                    @Override
                    public String getUsername() {
                        return "paulina";
                    }

                    @Override
                    public String getCity() {
                        return "Куйбышев";
                    }

                    @Override
                    public String getZip() {
                        return "123654";
                    }

                    @Override
                    public String getAddress() {
                        return "Ул Аленкова, 54-1";
                    }

                    @Override
                    public String getSdekAddress() {
                        return "Ул. Аленкова, 5";
                    }
                };
            }

            @Override
            public List<PositionData> getPositions() {
                return List.of(new PositionData() {
                    @Override
                    public BigDecimal getQuantity() {
                        return new BigDecimal(1);
                    }

                    @Override
                    public BigDecimal getPrice() {
                        return new BigDecimal(670);
                    }

                    @Override
                    public BigDecimal getDiscount() {
                        return BigDecimal.valueOf(30);
                    }

                    @Override
                    public String getVariantId() {
                        return "ae9534be-72fc-11eb-0a80-05bb000f4d9b";
                    }
                }, new PositionData() {
                    @Override
                    public BigDecimal getQuantity() {
                        return new BigDecimal(1);
                    }

                    @Override
                    public BigDecimal getPrice() {
                        return new BigDecimal(570);
                    }

                    @Override
                    public BigDecimal getDiscount() {
                        return BigDecimal.valueOf(10);
                    }

                    @Override
                    public String getVariantId() {
                        return "ae88690f-72fc-11eb-0a80-05bb000f4d61";
                    }
                });
            }
        };
        return api.createOrder(data);
    }
}

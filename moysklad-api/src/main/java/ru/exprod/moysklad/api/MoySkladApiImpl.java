package ru.exprod.moysklad.api;

import ru.exprod.moysklad.MoySkladApi;
import ru.exprod.moysklad.api.model.AssortmentResponse;
import ru.exprod.moysklad.api.model.ImageMeta;
import ru.exprod.moysklad.api.model.MetaResponse;
import ru.exprod.moysklad.api.model.Order;
import ru.exprod.moysklad.api.model.OrderResponse;
import ru.exprod.moysklad.model.OrderData;
import ru.exprod.moysklad.model.OrderPosition;
import ru.exprod.moysklad.model.OrderPositionData;
import ru.exprod.moysklad.model.Variant;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;
import static java.util.Collections.emptyList;

public class MoySkladApiImpl implements MoySkladApi {

    private static final String MODIFICATION_TYPE = "variant";
    private final HttpHelper httpHelper;
    private final FlowConfig flowConfig;

    public MoySkladApiImpl(String token, FlowConfig flowConfig) {
        this.httpHelper = new HttpHelper(token);
        this.flowConfig = flowConfig;
    }

    @Override
    public List<Variant> getAssortmentVariants() {
        AssortmentResponse assortmentResponse = httpHelper.get(getAssortmentPath(), AssortmentResponse.class);
        return assortmentResponse.getRows().stream()
                .filter(assortment -> MODIFICATION_TYPE.equals(assortment.getMeta().getType()))
                .map(Variant.Builder::new)
                .map(builder -> builder.setImages(this::getVariantImages, httpHelper::downloadFile))
                .map(Variant.Builder::build)
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> getOrders() {
        return null;
    }

    @Override
    public Order createOrder(OrderData data) {
        Order requestData = Order.createOrder(data, flowConfig);
        return httpHelper.post(getOrdersPath(), requestData, Order.class);
    }

    @Override
    public OrderPosition createOrderPosition(OrderPositionData data) {
        return null;
    }

    public List<ImageMeta> getVariantImages(String productId) {
        MetaResponse metaResponse = httpHelper.get(getVariantImagesPath(productId), MetaResponse.class);
        if (metaResponse == null) {
            return emptyList();
        }
        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return metaResponse.getRows().stream()
                .map(row -> row.meta)
                .collect(Collectors.toList());
    }

    private String getVariantImagesPath(String productId) {
        return "/variant/"+ productId +"/images";
    }

    private String getAssortmentPath() {
        return "/assortment";
    }

    private String getOrdersPath() {
        return "/customerorder";
    }

}

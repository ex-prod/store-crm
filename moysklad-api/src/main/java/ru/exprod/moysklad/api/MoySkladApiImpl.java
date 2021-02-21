package ru.exprod.moysklad.api;

import ru.exprod.moysklad.MoySkladApi;
import ru.exprod.moysklad.api.model.AssortmentResponse;
import ru.exprod.moysklad.api.model.ImageMeta;
import ru.exprod.moysklad.api.model.MetaResponse;
import ru.exprod.moysklad.model.CounterParty;
import ru.exprod.moysklad.model.CounterPartyData;
import ru.exprod.moysklad.model.Order;
import ru.exprod.moysklad.model.OrderData;
import ru.exprod.moysklad.model.OrderPosition;
import ru.exprod.moysklad.model.OrderPositionData;
import ru.exprod.moysklad.model.Variant;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;
import static java.util.Collections.emptyList;
import static org.springframework.http.HttpMethod.GET;

public class MoySkladApiImpl implements MoySkladApi {

    private static final String MODIFICATION_TYPE = "variant";
    private final HttpHelper httpHelper;
    public MoySkladApiImpl(String token) {
        this.httpHelper = new HttpHelper(token);
    }

    @Override
    public List<Variant> getAssortmentVariants() {
        AssortmentResponse assortmentResponse = httpHelper.get(getAssortmentUri(), AssortmentResponse.class);
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
    public CounterParty createCounterParty(CounterPartyData data) {
        return null;
    }

    @Override
    public Order createOrder(OrderData data) {
        return null;
    }

    @Override
    public OrderPosition createOrderPosition(OrderPositionData data) {
        return null;
    }

    public List<ImageMeta> getVariantImages(String productId) {
        MetaResponse metaResponse = httpHelper.get(getVariantImagesUri(productId), MetaResponse.class);
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

    private String getVariantImagesUri(String productId) {
        return "/variant/"+ productId +"/images";
    }

    private String getAssortmentUri() {
        return "/assortment";
    }

}

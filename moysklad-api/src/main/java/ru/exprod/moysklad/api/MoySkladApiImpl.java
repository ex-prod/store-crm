package ru.exprod.moysklad.api;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;
import ru.exprod.moysklad.MoySkladApi;
import ru.exprod.moysklad.api.model.AssortmentResponse;
import ru.exprod.moysklad.api.model.ImageMeta;
import ru.exprod.moysklad.api.model.MetaResponse;
import ru.exprod.moysklad.model.Variant;
import ru.exprod.moysklad.tools.RandomString;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static java.lang.Thread.sleep;
import static java.util.Collections.emptyList;
import static org.springframework.http.HttpMethod.GET;

public class MoySkladApiImpl implements MoySkladApi {

    private static final ThreadLocal<RandomString> randomString = ThreadLocal.withInitial(
            () -> new RandomString(16, ThreadLocalRandom.current())
    );

    private static final String MODIFICATION_TYPE = "variant";
    private static final String ENTITY_URL = "https://online.moysklad.ru/api/remap/1.2/entity";
    private static final String TOKEN = "86adb04f75a493a941e356d6bc3895f42d63de7f";
    private final RestTemplate rest;
    private final HttpHeaders defaultJsonHeaders;

    public MoySkladApiImpl() {
        this.rest = new RestTemplate();
        this.defaultJsonHeaders = new HttpHeaders();
        this.defaultJsonHeaders.set("Accept", "application/json;charset=utf-8");
        setAuthorizationHeader(this.defaultJsonHeaders);
    }

    @Override
    public List<Variant> getProducts() {
        HttpEntity<AssortmentResponse> request = new HttpEntity<>(defaultJsonHeaders);
        AssortmentResponse assortmentResponse = rest.exchange(getAssortmentUri(), GET, request, AssortmentResponse.class).getBody();
        return assortmentResponse.getRows().stream()
                .filter(assortment -> MODIFICATION_TYPE.equals(assortment.getMeta().getType()))
                .map(Variant.Builder::new)
                .map(builder -> builder.setImages(this::getVariantImages, this::downloadImage))
                .map(Variant.Builder::build)
                .collect(Collectors.toList());
    }

    public List<ImageMeta> getVariantImages(String productId) {
        HttpEntity<MetaResponse> request = new HttpEntity<>(defaultJsonHeaders);
        MetaResponse metaResponse = rest.exchange(getVariantImagesUri(productId), GET, request, MetaResponse.class).getBody();
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

    private File downloadImage(String url) {
        return rest.execute(
                url,
                GET,
                httpRequest -> setAuthorizationHeader(httpRequest.getHeaders()),
                this::saveFileFromResponse
        );
    }

    private void setAuthorizationHeader(HttpHeaders httpHeaders) {
        httpHeaders.set("Authorization", "Bearer " + TOKEN);
    }

    private File saveFileFromResponse(org.springframework.http.client.ClientHttpResponse clientHttpResponse) throws IOException {
        File ret = File.createTempFile(randomString.get().nextString(), ".tmp");
        StreamUtils.copy(clientHttpResponse.getBody(), new FileOutputStream(ret));
        try {
            sleep(100);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return ret;
    }

    private URI getVariantImagesUri(String productId) {
        return getUri(ENTITY_URL + "/variant/"+ productId +"/images");
    }

    private URI getAssortmentUri() {
        return getUri(ENTITY_URL + "/assortment");
    }

    private URI getUri(String url) {
        try {
            return new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}

package ru.exprod.moysklad.api;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;
import ru.exprod.moysklad.api.model.MetaResponse;
import ru.exprod.moysklad.tools.RandomString;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

import static java.lang.Thread.sleep;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;
import static ru.exprod.moysklad.MoySkladApi.ENTITY_URL;

public class HttpHelper {
    private static final ThreadLocal<RandomString> randomString = ThreadLocal.withInitial(
            () -> new RandomString(16, ThreadLocalRandom.current())
    );

    private final RestTemplate rest;
    private final HttpHeaders defaultJsonHeaders;
    private final String token;


    HttpHelper(String token) {
        this.token = token;

        this.rest = new RestTemplate();
        this.defaultJsonHeaders = new HttpHeaders();
        this.defaultJsonHeaders.set("Accept", "application/json;charset=utf-8");
        setAuthorizationHeader(this.defaultJsonHeaders);
    }

    <T> T get(String path, Class<T> clazz) {
        HttpEntity<MetaResponse> request = new HttpEntity<>(defaultJsonHeaders);
        return rest.exchange(getAbsolutUri(path), GET, request, clazz).getBody();
    }

    <T> T post(String path, Object requestData, Class<T> clazz) {
        HttpEntity<Object> request = new HttpEntity<>(requestData, defaultJsonHeaders);
        return rest.exchange(getAbsolutUri(path), POST, request, clazz).getBody();
    }

    File downloadFile(String rawUrl) {
        return rest.execute(
                rawUrl,
                GET,
                httpRequest -> setAuthorizationHeader(httpRequest.getHeaders()),
                this::saveFileFromResponse
        );
    }

    private URI getAbsolutUri(String path) {
        return getUri(ENTITY_URL + path);
    }

    private URI getUri(String url) {
        try {
            return new URI(url);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private void setAuthorizationHeader(HttpHeaders httpHeaders) {
        httpHeaders.set("Authorization", "Bearer " + token);
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
}

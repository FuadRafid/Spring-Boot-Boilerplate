package com.fuadrafid.springboot.util;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;


@Component
public class RestRequestUtil {
    private final WebClient.Builder webClientBuilder;

    public RestRequestUtil(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    public <T> ClientResponse postSync(String url, Object reqDto, Class<T> reqDtoClass) {
        return webClientBuilder
                .build()
                .post()
                .uri(url)
                .header(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(reqDto), reqDtoClass)
                .exchange().block();
    }

    public <T> ClientResponse putSync(String url, Object reqDto, Class<T> reqDtoClass) {
        return webClientBuilder
                .build()
                .put()
                .uri(url)
                .header(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(reqDto), reqDtoClass)
                .exchange().block();
    }

    public ClientResponse getSync(String url) {
        return this.getSync(url, new HashMap<>());
    }

    public ClientResponse getSync(String url, Map<String, String> queryParams) {
        return webClientBuilder
                .baseUrl(url)
                .build()
                .get()
                .uri(uriBuilder -> {
                    queryParams.forEach(uriBuilder::queryParam);
                    return uriBuilder.build();
                })
                .header(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchange().block();
    }

    public ClientResponse deleteSync(String url) {
        return webClientBuilder
                .baseUrl(url)
                .build()
                .delete()
                .header(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .exchange().block();
    }

    public <T> ClientResponse patchSync(String url, Object reqDto, Class<T> reqDtoClass) {
        return webClientBuilder
                .build()
                .patch()
                .uri(url)
                .header(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(reqDto), reqDtoClass)
                .exchange().block();
    }
}

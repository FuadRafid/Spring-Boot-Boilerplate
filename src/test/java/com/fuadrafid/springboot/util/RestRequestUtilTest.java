package com.fuadrafid.springboot.util;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.reactive.function.client.ClientResponse;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestRequestUtilTest {
    private static final MockWebServer mockWebServer = new MockWebServer();
    private static final String JSON_RESPONSE = "{ \"text\":\"some text here\" }";
    @Autowired
    private RestRequestUtil restRequestUtil;

    @AfterAll
    static void close() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void test_postSync_shouldReturnCorrectResponse() {
        mockWebServer.enqueue(
                new MockResponse()
                        .setResponseCode(200)
                        .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .setBody(JSON_RESPONSE));
        ClientResponse response = restRequestUtil.postSync(mockWebServer.url("/").toString(), new ReqDto("some req"), ReqDto.class);
        assertThat(
                response.statusCode()
        ).isEqualTo(HttpStatus.OK);

        assertThat(response.bodyToMono(String.class).block()).isEqualTo(JSON_RESPONSE);
    }

    @Test
    void test_getSync_shouldReturnCorrectResponse() {
        mockWebServer.enqueue(
                new MockResponse()
                        .setResponseCode(200)
                        .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .setBody(JSON_RESPONSE));

        ClientResponse response = restRequestUtil.getSync(mockWebServer.url("/").toString());
        assertThat(
                response.statusCode()
        ).isEqualTo(HttpStatus.OK);

        assertThat(response.bodyToMono(String.class).block()).isEqualTo(JSON_RESPONSE);    }

    @Test
    void test_deleteSync_shouldReturnCorrectResponse() {
        mockWebServer.enqueue(
                new MockResponse()
                        .setResponseCode(200)
                        .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .setBody(JSON_RESPONSE));

        ClientResponse response = restRequestUtil.deleteSync(mockWebServer.url("/").toString());
        assertThat(
                response.statusCode()
        ).isEqualTo(HttpStatus.OK);

        assertThat(response.bodyToMono(String.class).block()).isEqualTo(JSON_RESPONSE);    }

    @Test
    void test_patchSync_shouldReturnCorrectResponse() {
        mockWebServer.enqueue(
                new MockResponse()
                        .setResponseCode(200)
                        .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .setBody(JSON_RESPONSE));

        ClientResponse response = restRequestUtil.patchSync(mockWebServer.url("/").toString(), new ReqDto("some req"), ReqDto.class);
        assertThat(
                response.statusCode()
        ).isEqualTo(HttpStatus.OK);

        assertThat(response.bodyToMono(String.class).block()).isEqualTo(JSON_RESPONSE);    }

    @Test
    void test_putSync_shouldReturnCorrectResponse() {
        mockWebServer.enqueue(
                new MockResponse()
                        .setResponseCode(200)
                        .setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .setBody(JSON_RESPONSE));

        ClientResponse response = restRequestUtil.putSync(mockWebServer.url("/").toString(), new ReqDto("some req"), ReqDto.class);
        assertThat(
                response.statusCode()
        ).isEqualTo(HttpStatus.OK);

        assertThat(response.bodyToMono(String.class).block()).isEqualTo(JSON_RESPONSE);
    }
}

class ReqDto {
    private String request;

    public ReqDto(String request) {
        this.request = request;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }
}
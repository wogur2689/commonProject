package com.example.commonproject.common.util;

import org.springframework.http.*;
import org.springframework.web.client.RestClient;

import java.net.URI;

public class RestApiUtil<T> {

    /**
     * get
     */
    public ResponseEntity<?> RestApiGetCall(URI uri, T returnEntity) {

        RestClient restClient = RestClient.create();

        return restClient.get()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(returnEntity.getClass());
    }

    /**
     * post
     */
    public ResponseEntity<?> RestApiPostCall(URI uri, Object body, T returnEntity) {

        RestClient restClient = RestClient.create();

        return restClient.post()
                .uri(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body)
                .retrieve()
                .toEntity(returnEntity.getClass());
    }

    /**
     * put
     */
    public ResponseEntity<?> RestApiPutCall(URI uri, Object body, T returnEntity) {

        RestClient restClient = RestClient.create();

        return restClient.put()
                .uri(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .body(body)
                .retrieve()
                .toEntity(returnEntity.getClass());
    }

    /**
     * delete
     */
    public ResponseEntity<?> RestApiDeleteCall(URI uri, T returnEntity) {

        RestClient restClient = RestClient.create();

        return restClient.delete()
                .uri(uri)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .toEntity(returnEntity.getClass());
    }
}

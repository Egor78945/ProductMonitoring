package com.example.authentication_service.service.web;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class WebClientServiceManager implements WebClientService {
    private final RestTemplate restTemplate;

    public WebClientServiceManager(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public <R, E> ResponseEntity<R> post(URI url, Class<R> responseType, HttpEntity<E> httpEntity) {
        return restTemplate.postForEntity(url, httpEntity, responseType);
    }
}

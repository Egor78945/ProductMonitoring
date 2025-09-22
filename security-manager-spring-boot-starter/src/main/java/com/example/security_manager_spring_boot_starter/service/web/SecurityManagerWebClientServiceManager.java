package com.example.security_manager_spring_boot_starter.service.web;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class SecurityManagerWebClientServiceManager implements SecurityManagerWebClientService {
    private final RestTemplate restTemplate;

    public SecurityManagerWebClientServiceManager(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public <RES, REQ> ResponseEntity<RES> get(URI uri, Class<RES> responseType) {
        return restTemplate.getForEntity(uri, responseType);
    }
}

package com.example.security_manager_spring_boot_starter.service.web;

import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;

public interface SecurityManagerWebClientService {
    <RES, REQ> ResponseEntity<RES> get(URI uri, Class<RES> responseType);
}

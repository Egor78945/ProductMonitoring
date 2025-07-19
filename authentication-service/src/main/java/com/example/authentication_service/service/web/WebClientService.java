package com.example.authentication_service.service.web;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.net.URI;

public interface WebClientService {
    <R, E> ResponseEntity<R> post(URI url, Class<R> responseType, HttpEntity<E> httpEntity);
}

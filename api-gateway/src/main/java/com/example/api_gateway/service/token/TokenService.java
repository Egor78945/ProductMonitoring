package com.example.api_gateway.service.token;

import java.util.List;
import java.util.Map;

public interface TokenService<T> {
    Map<String, Object> extractClaims(T token);
    List<String> extractAuthorities(T token);
    String extractSubject(T token);
}

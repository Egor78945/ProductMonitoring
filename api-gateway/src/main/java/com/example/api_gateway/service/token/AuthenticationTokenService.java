package com.example.api_gateway.service.token;

import com.example.jwt_spring_boot_starter.service.jwt.JwtService;

import java.util.List;
import java.util.Map;

public abstract class AuthenticationTokenService implements TokenService<String> {
    protected final JwtService jwtService;

    public AuthenticationTokenService(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public Map<String, Object> extractClaims(String token) {
        return jwtService.getClaims(token);
    }

    @Override
    public List<String> extractAuthorities(String token) {
        return (List<String>) extractClaims(token).get("roles");
    }

    @Override
    public String extractSubject(String token) {
        return extractClaims(token).get("sub").toString();
    }
}

package com.example.jwt_spring_boot_starter.service.jwt;

import com.example.jwt_spring_boot_starter.model.JwtSecret;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.util.Date;
import java.util.Map;

public abstract class JwtService {
    protected final JwtSecret jwtSecret;

    public JwtService(JwtSecret jwtSecret) {
        this.jwtSecret = jwtSecret;
    }

    public String generate(String subject, Map<String, Object> claims) {
        return Jwts
                .builder()
                .claims(claims)
                .subject(subject)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtSecret.getLifetime()))
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getSecret()))
                .compact();
    }

    public Map<String, Object> getClaims(String token) {
        return getJwtParser()
                .parseSignedClaims(token)
                .getPayload();
    }

    public JwtParser getJwtParser() {
        return Jwts
                .parser()
                .verifyWith(Keys.hmacShaKeyFor(jwtSecret.getSecret()))
                .build();
    }
}

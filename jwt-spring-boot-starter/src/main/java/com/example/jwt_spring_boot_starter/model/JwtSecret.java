package com.example.jwt_spring_boot_starter.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.Base64;

@ConfigurationProperties(prefix = "jwt.starter")
@Validated
public class JwtSecret {
    private final byte[] secret;
    private final int lifetime;
    private final Base64.Decoder decoder;

    public JwtSecret(String secret, int lifetime) {
        this.secret = secret.getBytes();
        this.lifetime = lifetime;
        decoder = Base64.getDecoder();
    }

    public byte[] getSecret() {
        return decoder.decode(secret);
    }

    public int getLifetime() {
        return lifetime;
    }
}

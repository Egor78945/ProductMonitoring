package com.example.security_manager_spring_boot_starter.service.keycloak.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.security_manager_spring_boot_starter.service.keycloak.SecurityManagerKeycloakService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.security.interfaces.RSAPublicKey;

@Service
public class SecurityManagerKeycloakTokenParserManager implements SecurityManagerKeycloakTokenParser{
    private final SecurityManagerKeycloakService keycloakService;

    public SecurityManagerKeycloakTokenParserManager(@Qualifier("securityManagerKeycloakServiceManager") SecurityManagerKeycloakService keycloakService) {
        this.keycloakService = keycloakService;
    }

    @Override
    public String validateAndGetAttribute(String token, String attribute) {
        RSAPublicKey publicKey = keycloakService.getPublicKey();

        DecodedJWT decodedJWT = JWT.require(Algorithm.RSA256(publicKey, null))
                .build()
                .verify(token);

        return decodedJWT.getClaim(attribute).asString();
    }
}

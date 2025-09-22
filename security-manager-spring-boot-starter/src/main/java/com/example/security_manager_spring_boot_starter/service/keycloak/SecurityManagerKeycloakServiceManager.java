package com.example.security_manager_spring_boot_starter.service.keycloak;

import com.example.security_manager_spring_boot_starter.configuration.security.keycloak.environment.SecurityManagerKeycloakEnvironment;
import com.example.security_manager_spring_boot_starter.service.web.SecurityManagerWebClientService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

@Service
public class SecurityManagerKeycloakServiceManager extends SecurityManagerKeycloakService {
    private final SecurityManagerWebClientService webClientService;
    private JsonNode jwks;

    public SecurityManagerKeycloakServiceManager(SecurityManagerKeycloakEnvironment keycloakEnvironment, @Qualifier("securityManagerWebClientServiceManager") SecurityManagerWebClientService webClientService) {
        super(keycloakEnvironment);
        this.webClientService = webClientService;
    }

    @Override
    public void reloadJwks() {
        jwks = webClientService.get(URI.create(keycloakUrl), JsonNode.class).getBody();
    }

    @Override
    public RSAPublicKey getPublicKey() {
        if (jwks == null) {
            jwks = webClientService.get(URI.create(keycloakUrl), JsonNode.class).getBody();
        }
        JsonNode key = jwks.get("keys").get(0);

        try {
            return createPublicKey(key.get("n").asText(), key.get("e").asText());
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    private RSAPublicKey createPublicKey(String modulus, String exponent) throws NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] modBytes = Base64.getUrlDecoder().decode(modulus);
        byte[] expBytes = Base64.getUrlDecoder().decode(exponent);

        java.math.BigInteger mod = new java.math.BigInteger(1, modBytes);
        java.math.BigInteger exp = new java.math.BigInteger(1, expBytes);

        java.security.spec.RSAPublicKeySpec keySpec =
                new java.security.spec.RSAPublicKeySpec(mod, exp);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return (RSAPublicKey) keyFactory.generatePublic(keySpec);
    }
}

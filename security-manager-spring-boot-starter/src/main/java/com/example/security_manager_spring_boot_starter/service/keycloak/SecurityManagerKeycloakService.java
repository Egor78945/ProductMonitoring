package com.example.security_manager_spring_boot_starter.service.keycloak;

import com.example.security_manager_spring_boot_starter.configuration.security.keycloak.environment.SecurityManagerKeycloakEnvironment;

import java.security.interfaces.RSAPublicKey;

public abstract class SecurityManagerKeycloakService {
    protected final String keycloakUrl;

    public SecurityManagerKeycloakService(SecurityManagerKeycloakEnvironment keycloakEnvironment) {
        this.keycloakUrl = String.format("http://%s:%s/realms/%s/protocol/openid-connect/certs", keycloakEnvironment.getHost(), keycloakEnvironment.getPort(), keycloakEnvironment.getRealm());
    }

    public abstract void reloadJwks();
    public abstract RSAPublicKey getPublicKey();
}

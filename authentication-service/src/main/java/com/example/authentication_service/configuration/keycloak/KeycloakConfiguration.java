package com.example.authentication_service.configuration.keycloak;

import com.example.authentication_service.configuration.keycloak.environment.KeycloakEnvironment;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.*;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KeycloakConfiguration {
    private final KeycloakEnvironment keycloakEnvironment;
    private final Keycloak keycloak;

    public KeycloakConfiguration(KeycloakEnvironment keycloakEnvironment) {
        this.keycloakEnvironment = keycloakEnvironment;
        keycloak = KeycloakBuilder
                .builder()
                .serverUrl(String.format("http://%s:%s", keycloakEnvironment.getKeycloakServerHost(), keycloakEnvironment.getKeycloakServerPort()))
                .realm(keycloakEnvironment.getKeycloakRealmMasterName())
                .username(keycloakEnvironment.getKeycloakAdminName())
                .password(keycloakEnvironment.getKeycloakAdminPassword())
                .clientId(keycloakEnvironment.getKeycloakRealmMasterClientAdminCliId())
                .grantType(OAuth2Constants.PASSWORD)
                .build();
    }

    public Keycloak getKeycloak() {
        return keycloak;
    }
}

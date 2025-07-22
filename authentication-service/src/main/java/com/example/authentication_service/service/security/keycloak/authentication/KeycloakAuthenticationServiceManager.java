package com.example.authentication_service.service.security.keycloak.authentication;

import com.example.authentication_service.configuration.keycloak.environment.KeycloakEnvironment;
import com.example.authentication_service.service.security.keycloak.KeycloakService;
import com.example.authentication_service.service.security.keycloak.builder.KeycloakItemBuilder;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeycloakAuthenticationServiceManager implements KeycloakAuthenticationService<String> {
    private final KeycloakService keycloakService;
    private final KeycloakEnvironment keycloakEnvironment;

    public KeycloakAuthenticationServiceManager(KeycloakService keycloakService, KeycloakEnvironment keycloakEnvironment) {
        this.keycloakService = keycloakService;
        this.keycloakEnvironment = keycloakEnvironment;
    }

    @Override
    public void register(String username, String password) {
        String userId = keycloakService.createUser(KeycloakItemBuilder.buildUserRepresentation(username), keycloakEnvironment.getKeycloakRealmAuthenticationName());
        keycloakService.resetPassword(keycloakEnvironment.getKeycloakRealmAuthenticationName(), userId, password);
        keycloakService.joinGroup(keycloakEnvironment.getKeycloakRealmAuthenticationName(), userId, keycloakEnvironment.getKeycloakRealmAuthenticationGroupUserRolesName());
    }

    @Override
    public String login(String username, String password) {
        return keycloakService.getToken(username, password, keycloakEnvironment.getKeycloakRealmAuthenticationName(), keycloakEnvironment.getKeycloakRealmAuthenticationClientAuthenticationServiceId());
    }
}

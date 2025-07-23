package com.example.authentication_service.service.security.keycloak.authentication;

import com.example.authentication_service.configuration.keycloak.environment.KeycloakEnvironment;
import com.example.authentication_service.service.security.keycloak.KeycloakService;
import com.example.authentication_service.service.security.keycloak.builder.KeycloakItemBuilder;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

@Service
public class KeycloakAuthenticationRealmAuthenticationServiceManager implements KeycloakAuthenticationService<String> {
    private final KeycloakService keycloakService;
    private final KeycloakEnvironment keycloakEnvironment;

    public KeycloakAuthenticationRealmAuthenticationServiceManager(KeycloakService keycloakService, KeycloakEnvironment keycloakEnvironment) {
        this.keycloakService = keycloakService;
        this.keycloakEnvironment = keycloakEnvironment;
    }

    @Override
    public String register(String username, String password) {
        String userId = keycloakService.createUser(keycloakEnvironment.getKeycloakRealmAuthenticationName(), KeycloakItemBuilder.buildUserRepresentation(username));
        keycloakService.resetPassword(keycloakEnvironment.getKeycloakRealmAuthenticationName(), userId, password);
        keycloakService.joinGroup(keycloakEnvironment.getKeycloakRealmAuthenticationName(), userId, keycloakEnvironment.getKeycloakRealmAuthenticationGroupUserRolesName());
        return userId;
    }

    @Override
    public String login(String username, String password) {
        return keycloakService.getToken(username, password, keycloakEnvironment.getKeycloakRealmAuthenticationName(), keycloakEnvironment.getKeycloakRealmAuthenticationClientAuthenticationServiceId());
    }

    @Override
    public void delete(String username) {
        UserRepresentation user = keycloakService.getUserByUsername(keycloakEnvironment.getKeycloakRealmAuthenticationName(), username);
        keycloakService.deleteUser(keycloakEnvironment.getKeycloakRealmAuthenticationName(), user.getId());
    }
}

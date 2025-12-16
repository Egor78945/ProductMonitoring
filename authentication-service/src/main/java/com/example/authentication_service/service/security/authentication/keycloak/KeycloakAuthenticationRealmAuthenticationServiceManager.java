package com.example.authentication_service.service.security.authentication.keycloak;

import com.example.authentication_service.configuration.keycloak.environment.KeycloakEnvironment;
import com.example.authentication_service.model.security.KeycloakRegistrationModel;
import com.example.authentication_service.service.security.keycloak.KeycloakService;
import com.example.authentication_service.service.security.keycloak.builder.KeycloakItemBuilder;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class KeycloakAuthenticationRealmAuthenticationServiceManager implements KeycloakAuthenticationService<String, KeycloakRegistrationModel> {
    private final KeycloakService keycloakService;
    private final KeycloakEnvironment keycloakEnvironment;

    public KeycloakAuthenticationRealmAuthenticationServiceManager(KeycloakService keycloakService, KeycloakEnvironment keycloakEnvironment) {
        this.keycloakService = keycloakService;
        this.keycloakEnvironment = keycloakEnvironment;
    }

    @Override
    public void register(KeycloakRegistrationModel keycloakRegistrationModel) {
        String userId = keycloakService.createUser(keycloakEnvironment.getKeycloakRealmAuthenticationName(), KeycloakItemBuilder.buildUserRepresentation(keycloakRegistrationModel.getUsername(),keycloakRegistrationModel.getEmail(), null));
        keycloakService.resetPassword(keycloakEnvironment.getKeycloakRealmAuthenticationName(), userId, keycloakRegistrationModel.getPassword());
        keycloakService.joinGroup(keycloakEnvironment.getKeycloakRealmAuthenticationName(), userId, keycloakEnvironment.getKeycloakRealmAuthenticationGroupUserRolesName());
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

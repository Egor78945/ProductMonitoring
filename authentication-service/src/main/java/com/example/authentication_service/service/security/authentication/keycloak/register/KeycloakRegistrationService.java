package com.example.authentication_service.service.security.authentication.keycloak.register;

import com.example.authentication_service.configuration.keycloak.environment.KeycloakEnvironment;
import com.example.authentication_service.model.security.KeycloakRegistrationModel;
import com.example.authentication_service.service.security.authentication.RegistrationService;
import com.example.authentication_service.service.security.keycloak.KeycloakService;
import com.example.authentication_service.service.security.keycloak.builder.KeycloakItemBuilder;

public abstract class KeycloakRegistrationService<T extends KeycloakRegistrationModel> implements RegistrationService<T, String> {
    protected final KeycloakService keycloakService;
    protected final KeycloakEnvironment keycloakEnvironment;

    public KeycloakRegistrationService(KeycloakService keycloakService, KeycloakEnvironment keycloakEnvironment) {
        this.keycloakService = keycloakService;
        this.keycloakEnvironment = keycloakEnvironment;
    }

    @Override
    public String register(T keycloakRegistrationModel) {
        String userId = keycloakService.createUser(keycloakEnvironment.getKeycloakRealmAuthenticationName(), KeycloakItemBuilder.buildUserRepresentation(keycloakRegistrationModel.getUsername(),keycloakRegistrationModel.getEmail(), null));
        keycloakService.resetPassword(keycloakEnvironment.getKeycloakRealmAuthenticationName(), userId, keycloakRegistrationModel.getPassword());
        keycloakService.joinGroup(keycloakEnvironment.getKeycloakRealmAuthenticationName(), userId, keycloakEnvironment.getKeycloakRealmAuthenticationGroupUserRolesName());
        return userId;
    }
}

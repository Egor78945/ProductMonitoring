package com.example.authentication_service.service.security.authentication.keycloak.login;

import com.example.authentication_service.configuration.keycloak.environment.KeycloakEnvironment;
import com.example.authentication_service.model.security.UserAuthenticationModel;
import com.example.authentication_service.service.security.authentication.LoginService;
import com.example.authentication_service.service.security.keycloak.KeycloakService;

public abstract class KeycloakLoginService<T extends UserAuthenticationModel> implements LoginService<T, String> {
    protected final KeycloakService keycloakService;
    protected final KeycloakEnvironment keycloakEnvironment;

    public KeycloakLoginService(KeycloakService keycloakService, KeycloakEnvironment keycloakEnvironment) {
        this.keycloakService = keycloakService;
        this.keycloakEnvironment = keycloakEnvironment;
    }

    @Override
    public String login(T loginModel) {
        return keycloakService.getToken(loginModel.getUsername(), loginModel.getPassword(), keycloakEnvironment.getKeycloakRealmAuthenticationName(), keycloakEnvironment.getKeycloakRealmAuthenticationClientAuthenticationServiceId());
    }
}

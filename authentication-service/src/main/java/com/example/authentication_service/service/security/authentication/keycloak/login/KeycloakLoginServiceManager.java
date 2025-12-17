package com.example.authentication_service.service.security.authentication.keycloak.login;

import com.example.authentication_service.configuration.keycloak.environment.KeycloakEnvironment;
import com.example.authentication_service.model.security.UserAuthenticationModel;
import com.example.authentication_service.service.security.keycloak.KeycloakService;
import org.springframework.stereotype.Service;

@Service
public class KeycloakLoginServiceManager extends KeycloakLoginService<UserAuthenticationModel> {
    public KeycloakLoginServiceManager(KeycloakService keycloakService, KeycloakEnvironment keycloakEnvironment) {
        super(keycloakService, keycloakEnvironment);
    }
}

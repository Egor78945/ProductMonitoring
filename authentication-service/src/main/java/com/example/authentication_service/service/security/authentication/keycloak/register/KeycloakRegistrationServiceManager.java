package com.example.authentication_service.service.security.authentication.keycloak.register;

import com.example.authentication_service.configuration.keycloak.environment.KeycloakEnvironment;
import com.example.authentication_service.model.security.KeycloakRegistrationModel;
import com.example.authentication_service.service.security.keycloak.KeycloakService;
import org.springframework.stereotype.Service;

@Service
public class KeycloakRegistrationServiceManager extends KeycloakRegistrationService<KeycloakRegistrationModel> {
    public KeycloakRegistrationServiceManager(KeycloakService keycloakService, KeycloakEnvironment keycloakEnvironment) {
        super(keycloakService, keycloakEnvironment);
    }
}

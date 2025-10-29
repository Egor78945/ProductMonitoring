package com.example.authentication_service.service.security.authentication.keycloak;

import com.example.authentication_service.service.security.authentication.AuthenticationService;

public interface KeycloakAuthenticationService<T, R> extends AuthenticationService<T, R> {
    void delete(String user);
}

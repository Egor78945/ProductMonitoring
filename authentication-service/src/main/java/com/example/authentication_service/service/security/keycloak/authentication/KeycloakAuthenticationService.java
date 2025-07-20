package com.example.authentication_service.service.security.keycloak.authentication;

public interface KeycloakAuthenticationService<T> {
    void register(String username, String password);
    T login(String username, String password);
}

package com.example.authentication_service.service.security.keycloak.authentication;

public interface KeycloakAuthenticationService<T> {
    String register(String username, String password);
    T login(String username, String password);
    void delete(String user);
}

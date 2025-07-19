package com.example.authentication_service.service.security.keycloak.authentication;

import org.keycloak.representations.idm.UserRepresentation;

public interface KeycloakAuthenticationService {
    void createUser(UserRepresentation user);
    void resetPassword(String realmName, String userId, String password);
    void joinGroup(String realmName, String userId, String groupName);
    String getToken(String username, String password);
}

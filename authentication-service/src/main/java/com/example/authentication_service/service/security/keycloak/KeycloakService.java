package com.example.authentication_service.service.security.keycloak;

import org.keycloak.representations.idm.UserRepresentation;

public interface KeycloakService {
    void createUser(UserRepresentation user, String realmName);
    void resetPassword(String realmName, String userId, String password);
    void joinGroup(String realmName, String userId, String groupName);
    String getToken(String username, String password, String realmName, String clientId);
}

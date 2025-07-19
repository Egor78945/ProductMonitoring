package com.example.authentication_service.service.security.keycloak.builder;

import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

public class KeycloakItemBuilder {
    public static UserRepresentation buildUserRepresentation(String username) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(username);
        userRepresentation.setEmail(username);
        userRepresentation.setEmailVerified(true);
        userRepresentation.setEnabled(true);
        return userRepresentation;
    }

    public static CredentialRepresentation buildCredentialRepresentation(String credential, String credentialType) {
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setType(credentialType);
        credentialRepresentation.setValue(credential);
        credentialRepresentation.setTemporary(false);
        return credentialRepresentation;
    }
}

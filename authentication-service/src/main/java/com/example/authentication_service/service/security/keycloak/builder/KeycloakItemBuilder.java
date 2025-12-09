package com.example.authentication_service.service.security.keycloak.builder;

import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;
import java.util.Map;

public class KeycloakItemBuilder {
    public static UserRepresentation buildUserRepresentation(String username, String email, Map<String, List<String>> attributes) {
        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(username);
        userRepresentation.setEmail(email);
        userRepresentation.setEmailVerified(true);
        userRepresentation.setEnabled(true);
        userRepresentation.setAttributes(attributes);
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

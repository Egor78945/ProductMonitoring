package com.example.authentication_service.service.security.keycloak;

import com.example.authentication_service.configuration.keycloak.KeycloakConfiguration;
import org.keycloak.admin.client.resource.ClientsResource;
import org.keycloak.admin.client.resource.GroupsResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.springframework.stereotype.Service;

@Service
public class KeycloakServiceManager implements KeycloakService {
    private final KeycloakConfiguration keycloakConfiguration;

    public KeycloakServiceManager(KeycloakConfiguration keycloakConfiguration) {
        this.keycloakConfiguration = keycloakConfiguration;
    }

    public UsersResource getUsersResource(String realmName) {
        return keycloakConfiguration.getKeycloak().realm(realmName).users();
    }

    public RolesResource getRolesResource(String realmName) {
        return keycloakConfiguration.getKeycloak().realm(realmName).roles();
    }

    public GroupsResource getGroupsResource(String realmName) {
        return keycloakConfiguration.getKeycloak().realm(realmName).groups();
    }

    public ClientsResource getClientsResource(String realmName) {
        return keycloakConfiguration.getKeycloak().realm(realmName).clients();
    }
}

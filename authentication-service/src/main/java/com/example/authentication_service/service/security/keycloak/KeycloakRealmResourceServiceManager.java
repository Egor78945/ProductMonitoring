package com.example.authentication_service.service.security.keycloak;

import com.example.authentication_service.configuration.keycloak.KeycloakConfiguration;
import com.example.authentication_service.exception.KeycloakException;
import com.example.authentication_service.exception.message.ExceptionMessage;
import org.keycloak.admin.client.resource.ClientsResource;
import org.keycloak.admin.client.resource.GroupsResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.GroupRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KeycloakRealmResourceServiceManager implements KeycloakRealmResourceService {
    private final KeycloakConfiguration keycloakConfiguration;

    public KeycloakRealmResourceServiceManager(KeycloakConfiguration keycloakConfiguration) {
        this.keycloakConfiguration = keycloakConfiguration;
    }

    @Override
    public UsersResource getUsersResource(String realmName) {
        return keycloakConfiguration.getKeycloak().realm(realmName).users();
    }

    @Override
    public RolesResource getRolesResource(String realmName) {
        return keycloakConfiguration.getKeycloak().realm(realmName).roles();
    }

    @Override
    public GroupsResource getGroupsResource(String realmName) {
        return keycloakConfiguration.getKeycloak().realm(realmName).groups();
    }

    @Override
    public ClientsResource getClientsResource(String realmName) {
        return keycloakConfiguration.getKeycloak().realm(realmName).clients();
    }

    @Override
    public List<GroupRepresentation> getGroupRepresentation(String realmName, String groupName) {
        return getGroupsResource(realmName)
                .groups()
                .stream()
                .filter(g -> g.getName().equals(groupName))
                .collect(Collectors.toList());
    }
}

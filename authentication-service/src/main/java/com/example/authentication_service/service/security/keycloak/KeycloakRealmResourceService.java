package com.example.authentication_service.service.security.keycloak;

import org.keycloak.admin.client.resource.ClientsResource;
import org.keycloak.admin.client.resource.GroupsResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UsersResource;

public interface KeycloakRealmResourceService {
    UsersResource getUsersResource(String realmName);

    RolesResource getRolesResource(String realmName);

    GroupsResource getGroupsResource(String realmName);

    ClientsResource getClientsResource(String realmName);
}

package com.example.authentication_service.configuration.keycloak.environment;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KeycloakEnvironment {
    private final String keycloakServerHost;
    private final int keycloakServerPort;
    private final String keycloakRealmAuthenticationName;
    private final String keycloakRealmAuthenticationClientAuthenticationServiceId;
    private final String keycloakRealmMasterName;
    private final String keycloakRealmAuthenticationGroupUserRolesName;
    private final String keycloakRealmAuthenticationGroupAdminRolesName;
    private final String keycloakAdminName;
    private final String keycloakRealmMasterClientAdminCliId;

    public KeycloakEnvironment(@Value("${keycloak.server.host}") String keycloakServerHost,
                               @Value("${keycloak.server.port}") int keycloakServerPort,
                               @Value("${keycloak.realm.authentication.name}") String keycloakRealmAuthenticationName,
                               @Value("${keycloak.realm.authentication.client.authentication-service.id}") String keycloakRealmAuthenticationClientAuthenticationServiceId,
                               @Value("${keycloak.realm.master.client.admin.id}") String keycloakRealmMasterClientAdminCliId,
                               @Value("${keycloak.realm.authentication.group.user-roles.name}") String keycloakRealmAuthenticationGroupUserRolesName,
                               @Value("${keycloak.realm.authentication.group.admin-roles.name}") String keycloakRealmAuthenticationGroupAdminRolesName,
                               @Value("${keycloak.realm.master.name}") String keycloakRealmMasterName,
                               @Value("${keycloak.admin.name}") String keycloakAdminName) {
        this.keycloakServerHost = keycloakServerHost;
        this.keycloakServerPort = keycloakServerPort;
        this.keycloakRealmAuthenticationName = keycloakRealmAuthenticationName;
        this.keycloakRealmAuthenticationClientAuthenticationServiceId = keycloakRealmAuthenticationClientAuthenticationServiceId;
        this.keycloakRealmMasterClientAdminCliId = keycloakRealmMasterClientAdminCliId;
        this.keycloakRealmMasterName = keycloakRealmMasterName;
        this.keycloakRealmAuthenticationGroupUserRolesName = keycloakRealmAuthenticationGroupUserRolesName;
        this.keycloakRealmAuthenticationGroupAdminRolesName = keycloakRealmAuthenticationGroupAdminRolesName;
        this.keycloakAdminName = keycloakAdminName;
    }

    public String getKeycloakServerHost() {
        return keycloakServerHost;
    }

    public int getKeycloakServerPort() {
        return keycloakServerPort;
    }

    public String getKeycloakRealmAuthenticationName() {
        return keycloakRealmAuthenticationName;
    }

    public String getKeycloakRealmAuthenticationClientAuthenticationServiceId() {
        return keycloakRealmAuthenticationClientAuthenticationServiceId;
    }

    public String getKeycloakRealmMasterClientAdminCliId() {
        return keycloakRealmMasterClientAdminCliId;
    }

    public String getKeycloakRealmMasterName() {
        return keycloakRealmMasterName;
    }

    public String getKeycloakRealmAuthenticationGroupUserRolesName() {
        return keycloakRealmAuthenticationGroupUserRolesName;
    }

    public String getKeycloakRealmAuthenticationGroupAdminRolesName() {
        return keycloakRealmAuthenticationGroupAdminRolesName;
    }

    public String getKeycloakAdminName() {
        return keycloakAdminName;
    }

    public String getKeycloakAdminPassword() {
        return System.getenv("KEYCLOAK_ADMIN_PASSWORD");
    }
}

package com.example.security_manager_spring_boot_starter.configuration.security.keycloak.environment;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "keycloak")
public class SecurityManagerKeycloakEnvironment {
    private String serverHost;
    private int serverPort;
    private String realmAuthenticationName;

    public String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public String getRealmAuthenticationName() {
        return realmAuthenticationName;
    }

    public void setRealmAuthenticationName(String realmAuthenticationName) {
        this.realmAuthenticationName = realmAuthenticationName;
    }
}

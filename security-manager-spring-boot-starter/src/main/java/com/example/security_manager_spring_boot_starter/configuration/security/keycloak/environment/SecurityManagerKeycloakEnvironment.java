package com.example.security_manager_spring_boot_starter.configuration.security.keycloak.environment;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Objects;

@ConfigurationProperties("keycloak")
public class SecurityManagerKeycloakEnvironment {
    private String realm;
    private String host;
    private int port;

    public String getRealm() {
        return realm;
    }

    public void setRealm(String realm) {
        this.realm = realm;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        SecurityManagerKeycloakEnvironment that = (SecurityManagerKeycloakEnvironment) o;
        return port == that.port && Objects.equals(realm, that.realm) && Objects.equals(host, that.host);
    }

    @Override
    public int hashCode() {
        return Objects.hash(realm, host, port);
    }

    @Override
    public String toString() {
        return "SecurityManagerKeycloakEnvironment{" +
                "realm='" + realm + '\'' +
                ", host='" + host + '\'' +
                ", port=" + port +
                '}';
    }
}

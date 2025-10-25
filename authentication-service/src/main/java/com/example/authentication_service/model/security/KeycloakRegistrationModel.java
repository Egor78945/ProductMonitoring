package com.example.authentication_service.model.security;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class KeycloakRegistrationModel {
    private String username;
    private String email;
    private String password;
    private Map<String, List<String>> attributes;

    public KeycloakRegistrationModel(String username, String email, String password, Map<String, List<String>> attributes) {
        this.username = username.toLowerCase();
        this.email = email.toLowerCase();
        this.password = password;
        this.attributes = attributes;
    }

    public Map<String, List<String>> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, List<String>> attributes) {
        this.attributes = attributes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        KeycloakRegistrationModel that = (KeycloakRegistrationModel) o;
        return Objects.equals(username, that.username) && Objects.equals(email, that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email);
    }

    @Override
    public String toString() {
        return "KeycloakRegistrationModel{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", attributes=" + attributes +
                '}';
    }
}

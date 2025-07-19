package com.example.authentication_service.service.security.keycloak.authentication;

import com.example.authentication_service.configuration.keycloak.environment.KeycloakEnvironment;
import com.example.authentication_service.exception.KeycloakException;
import com.example.authentication_service.exception.message.ExceptionMessage;
import com.example.authentication_service.service.security.keycloak.KeycloakService;
import com.example.authentication_service.service.security.keycloak.builder.KeycloakItemBuilder;
import com.example.authentication_service.service.web.WebClientService;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.GroupResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class KeycloakAuthenticationServiceManager implements KeycloakAuthenticationService {
    private final KeycloakService keycloakService;
    private final KeycloakEnvironment keycloakEnvironment;
    private final WebClientService webClientService;

    public KeycloakAuthenticationServiceManager(KeycloakService keycloakService, KeycloakEnvironment keycloakEnvironment, WebClientService webClientService) {
        this.keycloakService = keycloakService;
        this.keycloakEnvironment = keycloakEnvironment;
        this.webClientService = webClientService;
    }

    @Override
    public void createUser(UserRepresentation user) {
        try (Response response = keycloakService.getUsersResource(keycloakEnvironment.getKeycloakRealmAuthenticationName()).create(user)){
            if(response.getStatus()/100 != 2) {
                throw new KeycloakException(ExceptionMessage.FAILED_TO_CREATE.getMessage());
            }
        }
    }

    @Override
    public void resetPassword(String realmName, String userId, String password) {
        keycloakService.getUsersResource(realmName).get(userId).resetPassword(KeycloakItemBuilder.buildCredentialRepresentation(password, CredentialRepresentation.PASSWORD));
    }

    @Override
    public void joinGroup(String realmName, String userId, String groupName) {
        GroupResource groupResource = keycloakService.getGroupsResource(groupName).group(groupName);
        if(groupResource != null) {
            keycloakService.getUsersResource(realmName).get(userId).joinGroup(groupName);
        } else {
            throw new KeycloakException(ExceptionMessage.NOT_FOUND.getMessage());
        }
    }

    @Override
    public String getToken(String username, String password) {
        URI url = URI.create(String.format("http://%s:%s/realms/%s/protocol/openid-connect/token", keycloakEnvironment.getKeycloakServerHost(), keycloakEnvironment.getKeycloakServerPort(), keycloakEnvironment.getKeycloakRealmAuthenticationName()));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> httpBody = new LinkedMultiValueMap<>();
        httpBody.add("grant_type", "password");
        httpBody.add("username", username);
        httpBody.add("password", password);
        httpBody.add("client_secret", getClientSecret(keycloakEnvironment.getKeycloakRealmAuthenticationName(), keycloakEnvironment.getKeycloakRealmAuthenticationClientAuthenticationServiceId()));
        httpBody.add("client_id", keycloakEnvironment.getKeycloakRealmAuthenticationClientAuthenticationServiceId());

        ResponseEntity<Map> responseEntity = webClientService.post(url, Map.class, new HttpEntity<>(httpBody, httpHeaders));

        if(responseEntity.getStatusCode() == HttpStatus.OK && responseEntity.getBody() != null) {
            Object token = responseEntity.getBody().get("access_token");
            if(token != null) {
                return token.toString();
            }
        }
        throw new KeycloakException(responseEntity.getBody().toString());
    }

    private String getClientSecret(String realmName, String clientId) {
        List<ClientRepresentation> clientRepresentation = keycloakService.getClientsResource(realmName).findByClientId(clientId);
        if(!clientRepresentation.isEmpty()) {
            return clientRepresentation.get(0).getSecret();
        }
        throw new KeycloakException(ExceptionMessage.NOT_FOUND.getMessage());
    }
}

package com.example.authentication_service.service.security.keycloak;

import com.example.authentication_service.configuration.keycloak.environment.KeycloakEnvironment;
import com.example.authentication_service.exception.KeycloakException;
import com.example.authentication_service.exception.message.ExceptionMessage;
import com.example.authentication_service.service.security.keycloak.builder.KeycloakItemBuilder;
import com.example.authentication_service.service.web.WebClientService;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.resource.*;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;
import java.util.List;
import java.util.Map;

@Service
public class KeycloakServiceManager implements KeycloakService {
    private final KeycloakRealmResourceService keycloakRealmResourceService;
    private final KeycloakEnvironment keycloakEnvironment;
    private final WebClientService webClientService;

    public KeycloakServiceManager(KeycloakRealmResourceService keycloakRealmResourceService, KeycloakEnvironment keycloakEnvironment, WebClientService webClientService) {
        this.keycloakRealmResourceService = keycloakRealmResourceService;
        this.keycloakEnvironment = keycloakEnvironment;
        this.webClientService = webClientService;
    }

    @Override
    public String createUser(UserRepresentation user, String realmName) {
        try (Response response = keycloakRealmResourceService.getUsersResource(realmName).create(user)) {
            if (response.getStatus() / 100 != 2) {
                throw new KeycloakException(ExceptionMessage.FAILED_TO_CREATE.getMessage());
            } else {
                return response.getLocation().getPath().replaceAll(".*/([^/]+)$", "$1");
            }
        }
    }

    @Override
    public void resetPassword(String realmName, String userId, String password) {
        keycloakRealmResourceService.getUsersResource(realmName).get(userId).resetPassword(KeycloakItemBuilder.buildCredentialRepresentation(password, CredentialRepresentation.PASSWORD));
    }

    @Override
    public void joinGroup(String realmName, String userId, String groupName) {
        GroupRepresentation groupRepresentation = keycloakRealmResourceService
                .getGroupsResource(realmName)
                        .groups()
                                .stream()
                                        .filter(g -> g.getName().equals(keycloakEnvironment.getKeycloakRealmAuthenticationGroupUserRolesName()))
                                                .findFirst()
                                                        .get();
        System.out.println(groupRepresentation);
        if (groupRepresentation != null) {
            keycloakRealmResourceService.getUsersResource(realmName).get(userId).joinGroup(groupRepresentation.getId());
        } else {
            throw new KeycloakException(ExceptionMessage.NOT_FOUND.getMessage());
        }
    }

    @Override
    public String getToken(String username, String password, String realmName, String clientId) {
        URI uri = URI.create(String.format("http://%s:%s/realms/%s/protocol/openid-connect/token", keycloakEnvironment.getKeycloakServerHost(), keycloakEnvironment.getKeycloakServerPort(), realmName));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> httpBody = new LinkedMultiValueMap<>();
        httpBody.add("grant_type", "password");
        httpBody.add("username", username);
        httpBody.add("password", password);
        httpBody.add("client_secret", getClientSecret(realmName, clientId));
        httpBody.add("client_id", clientId);

        System.out.println("HTTP BODY = " + httpBody);

        ResponseEntity<Map> responseEntity = webClientService.post(uri, Map.class, new HttpEntity<>(httpBody, httpHeaders));

        if (responseEntity.getStatusCode() == HttpStatus.OK && responseEntity.getBody() != null) {
            Object token = responseEntity.getBody().get("access_token");
            if (token != null) {
                return token.toString();
            }
        }
        throw new KeycloakException(responseEntity.getBody().toString());
    }
    private String getClientSecret(String realmName, String clientId) {
        List<ClientRepresentation> clientRepresentation = keycloakRealmResourceService.getClientsResource(realmName).findByClientId(clientId);
        if (!clientRepresentation.isEmpty()) {
            return clientRepresentation.get(0).getSecret();
        }
        throw new KeycloakException(ExceptionMessage.NOT_FOUND.getMessage());
    }
}

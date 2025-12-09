package com.example.authentication_service.service.security.authentication.user;

import com.example.authentication_service.model.account.status.AccountStatusEnumeration;
import com.example.authentication_service.model.security.KeycloakRegistrationModel;
import com.example.authentication_service.model.security.UserRegistrationModel;
import com.example.authentication_service.model.user.role.UserRoleEnumeration;
import com.example.authentication_service.model.user.status.UserStatusEnumeration;
import com.example.authentication_service.service.grpc.builder.GrpcMessageBuilder;
import com.example.authentication_service.service.security.authentication.AuthenticationService;
import com.example.authentication_service.service.security.authentication.grpc.AuthenticationGrpcClientService;
import com.example.authentication_service.service.security.authentication.keycloak.KeycloakAuthenticationService;
import com.example.grpc.user.UserProtoConfiguration;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.example.async_spring_boot_starter.service.concurrency.AsyncStarterAsyncTaskExecutorService;

@Service
public class UserAuthenticationServiceManager implements AuthenticationService<String, UserRegistrationModel> {
    private final AuthenticationGrpcClientService authenticationGrpcClientService;
    private final KeycloakAuthenticationService<String, KeycloakRegistrationModel> keycloakAuthenticationService;
    private final AsyncStarterAsyncTaskExecutorService asyncTaskExecutorService;

    public UserAuthenticationServiceManager(AuthenticationGrpcClientService authenticationGrpcClientService, KeycloakAuthenticationService<String, KeycloakRegistrationModel> keycloakAuthenticationService, AsyncStarterAsyncTaskExecutorService asyncTaskExecutorService) {
        this.authenticationGrpcClientService = authenticationGrpcClientService;
        this.keycloakAuthenticationService = keycloakAuthenticationService;
        this.asyncTaskExecutorService = asyncTaskExecutorService;
    }

    @Override
    public String login(String name, String password) {
        return keycloakAuthenticationService.login(name, password);
    }

    @Override
    public void register(UserRegistrationModel registerModel) {
        UserProtoConfiguration.UserMessage userMessage = GrpcMessageBuilder.buildFrom(registerModel.getEmail(), UserStatusEnumeration.STATUS_ACTIVE, List.of(UserRoleEnumeration.ROLE_USER.getId()));
        UserProtoConfiguration.AccountMessage accountMessage = GrpcMessageBuilder.buildFrom(registerModel.getName(), AccountStatusEnumeration.STATUS_ACTIVE, true);
        UserProtoConfiguration.UserRegistrationMessage userRegistrationResult = authenticationGrpcClientService.register(GrpcMessageBuilder.buildFrom(userMessage, accountMessage));
        try {
            keycloakAuthenticationService.register(new KeycloakRegistrationModel(userRegistrationResult.getAccount().getName(), registerModel.getEmail(), registerModel.getPassword(), Map.of("account_uuid", List.of(userRegistrationResult.getAccount().getUuid()))));
        } catch (Exception e) {
            System.out.println("sending");
            asyncTaskExecutorService.run(() -> authenticationGrpcClientService.delete(userRegistrationResult));
        }
    }


}

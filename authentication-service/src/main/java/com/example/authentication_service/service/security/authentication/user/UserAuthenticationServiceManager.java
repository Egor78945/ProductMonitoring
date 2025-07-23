package com.example.authentication_service.service.security.authentication.user;

import com.example.authentication_service.exception.AuthenticationException;
import com.example.authentication_service.exception.message.ExceptionMessage;
import com.example.authentication_service.model.account.status.AccountStatusEnumeration;
import com.example.authentication_service.model.security.UserRegistrationModel;
import com.example.authentication_service.model.user.role.UserRoleEnumeration;
import com.example.authentication_service.model.user.status.UserStatusEnumeration;
import com.example.authentication_service.service.grpc.builder.GrpcMessageBuilder;
import com.example.authentication_service.service.security.authentication.AuthenticationService;
import com.example.authentication_service.service.security.keycloak.KeycloakService;
import com.example.authentication_service.service.security.keycloak.authentication.KeycloakAuthenticationService;
import com.example.authentication_service.service.user.UserService;
import com.example.authentication_service.service.user.grpc.client.UserGrpcClientService;
import com.example.grpc.user.UserProtoConfiguration;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAuthenticationServiceManager implements AuthenticationService<String, UserRegistrationModel> {
    private final UserService userService;
    private final UserGrpcClientService userGrpcClientService;
    private final KeycloakAuthenticationService<String> keycloakAuthenticationService;

    public UserAuthenticationServiceManager(UserService userService, UserGrpcClientService userGrpcClientService, KeycloakAuthenticationService<String> keycloakAuthenticationService) {
        this.userService = userService;
        this.userGrpcClientService = userGrpcClientService;
        this.keycloakAuthenticationService = keycloakAuthenticationService;
    }

    @Override
    public String login(String email, String password) {
        return keycloakAuthenticationService.login(email, password);
    }

    @Override
    public void register(UserRegistrationModel registerModel) {
        if (!userService.existsByEmail(registerModel.getEmail())) {
            UserProtoConfiguration.UserMessage userMessage = GrpcMessageBuilder.buildFrom(registerModel.getEmail(), UserStatusEnumeration.STATUS_ACTIVE, List.of(UserRoleEnumeration.ROLE_USER.getId()));
            UserProtoConfiguration.AccountMessage accountMessage = GrpcMessageBuilder.buildFrom(AccountStatusEnumeration.STATUS_ACTIVE);

            keycloakAuthenticationService.register(registerModel.getEmail(), registerModel.getPassword());
            try {
                userGrpcClientService.registerUser(GrpcMessageBuilder.buildFrom(userMessage, accountMessage));
            } catch (Exception e) {
                keycloakAuthenticationService.delete(registerModel.getEmail());
            }
        } else {
            throw new AuthenticationException(ExceptionMessage.buildMessage(ExceptionMessage.ALREADY_EXISTS, registerModel.getEmail()));
        }
    }
}

package com.example.authentication_service.service.security.authentication.user;

import com.example.async_spring_boot_starter.service.concurrency.AsyncStarterAsyncTaskExecutorService;
import com.example.authentication_service.model.account.status.AccountStatusEnumeration;
import com.example.authentication_service.model.security.KeycloakRegistrationModel;
import com.example.authentication_service.model.security.UserRegistrationModel;
import com.example.authentication_service.model.user.role.UserRoleEnumeration;
import com.example.authentication_service.model.user.status.UserStatusEnumeration;
import com.example.authentication_service.service.account.AccountService;
import com.example.authentication_service.service.grpc.builder.GrpcMessageBuilder;
import com.example.authentication_service.service.security.authentication.AuthenticationService;
import com.example.authentication_service.service.security.authentication.grpc.AuthenticationGrpcClientService;
import com.example.authentication_service.service.security.authentication.keycloak.KeycloakAuthenticationService;
import com.example.authentication_service.service.user.UserService;
import com.example.grpc.user.UserProtoConfiguration;
import com.example.jwt_spring_boot_starter.service.jwt.JwtService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserAuthenticationServiceManager implements AuthenticationService<String, UserRegistrationModel> {
    private final AuthenticationGrpcClientService authenticationGrpcClientService;
    private final KeycloakAuthenticationService<String, KeycloakRegistrationModel> keycloakAuthenticationService;
    private final UserService<UserProtoConfiguration.UserMessage> userService;
    private final AccountService<UserProtoConfiguration.AccountMessage> accountService;
    private final JwtService jwtService;
    private final AsyncStarterAsyncTaskExecutorService asyncTaskExecutorService;

    public UserAuthenticationServiceManager(AuthenticationGrpcClientService authenticationGrpcClientService, KeycloakAuthenticationService<String, KeycloakRegistrationModel> keycloakAuthenticationService, UserService<UserProtoConfiguration.UserMessage> userService, AccountService<UserProtoConfiguration.AccountMessage> accountService, JwtService jwtService, AsyncStarterAsyncTaskExecutorService asyncTaskExecutorService) {
        this.authenticationGrpcClientService = authenticationGrpcClientService;
        this.keycloakAuthenticationService = keycloakAuthenticationService;
        this.userService = userService;
        this.accountService = accountService;
        this.jwtService = jwtService;
        this.asyncTaskExecutorService = asyncTaskExecutorService;
    }

    @Override
    public String login(String name, String password) {
        boolean dog = name.contains("@");
        UserProtoConfiguration.UserMessage userByAccountName = dog ? userService.getByEmail(name) : userService.getByAccountName(name);
        keycloakAuthenticationService.login(userByAccountName.getEmail(), password);
        UserProtoConfiguration.AccountMessage accountByUserUuid = dog ? accountService.getMainByUserUuid(UUID.fromString(userByAccountName.getUuid())) : accountService.getByName(name);

        return jwtService.generate(userByAccountName.getEmail(), Map.of("account_uuid", accountByUserUuid.getUuid()));
    }

    @Override
    public void register(UserRegistrationModel registerModel) {
        UserProtoConfiguration.UserMessage userMessage = GrpcMessageBuilder.buildFrom(registerModel.getEmail(), UserStatusEnumeration.STATUS_ACTIVE, List.of(UserRoleEnumeration.ROLE_USER.getId()));
        UserProtoConfiguration.AccountMessage accountMessage = GrpcMessageBuilder.buildFrom(registerModel.getName(), AccountStatusEnumeration.STATUS_ACTIVE, true);
        UserProtoConfiguration.UserRegistrationMessage userRegistrationResult = authenticationGrpcClientService.register(GrpcMessageBuilder.buildFrom(userMessage, accountMessage));
        if (userRegistrationResult.getFirst()) {
            try {
                keycloakAuthenticationService.register(new KeycloakRegistrationModel(registerModel.getEmail(), registerModel.getEmail(), registerModel.getPassword(), null));
            } catch (Exception e) {
                System.out.println("sending");
                asyncTaskExecutorService.run(() -> authenticationGrpcClientService.deleteByEmail(GrpcMessageBuilder.buildFrom(registerModel.getEmail())));
            }
        }
    }


}

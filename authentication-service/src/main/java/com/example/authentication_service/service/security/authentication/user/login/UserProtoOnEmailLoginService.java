package com.example.authentication_service.service.security.authentication.user.login;

import com.example.authentication_service.model.security.UserAuthenticationModel;
import com.example.authentication_service.service.account.AccountService;
import com.example.authentication_service.service.security.authentication.LoginService;
import com.example.authentication_service.service.security.authentication.keycloak.login.KeycloakLoginService;
import com.example.authentication_service.service.user.UserService;
import com.example.grpc.user.UserProtoConfiguration;
import com.example.jwt_spring_boot_starter.service.jwt.JwtService;

import java.util.Map;

public abstract class UserProtoOnEmailLoginService<T extends UserAuthenticationModel> implements LoginService<T, String> {
    protected final JwtService jwtService;
    protected final UserService<UserProtoConfiguration.UserMessage> userService;
    protected final AccountService<UserProtoConfiguration.AccountMessage> accountService;
    protected final KeycloakLoginService<T> keycloakLoginService;

    public UserProtoOnEmailLoginService(JwtService jwtService, UserService<UserProtoConfiguration.UserMessage> userService, AccountService<UserProtoConfiguration.AccountMessage> accountService, KeycloakLoginService<T> keycloakLoginService) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.accountService = accountService;
        this.keycloakLoginService = keycloakLoginService;
    }

    @Override
    public String login(T loginModel) {
        keycloakLoginService.login(loginModel);
        UserProtoConfiguration.AccountMessage accountByUserUuid = accountService.getMainByUserEmail(loginModel.getUsername());

        return jwtService.generate(loginModel.getUsername(), Map.of("account_uuid", accountByUserUuid.getUuid()));
    }
}

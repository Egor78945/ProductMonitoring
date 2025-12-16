package com.example.authentication_service.service.security.authentication.login;

import com.example.authentication_service.model.security.KeycloakRegistrationModel;
import com.example.authentication_service.model.security.UserAuthenticationModel;
import com.example.authentication_service.service.account.AccountService;
import com.example.authentication_service.service.security.authentication.keycloak.KeycloakAuthenticationService;
import com.example.authentication_service.service.user.UserService;
import com.example.grpc.user.UserProtoConfiguration;
import com.example.jwt_spring_boot_starter.service.jwt.JwtService;

import java.util.Map;

public abstract class UserProtoOnAccountNameLoginService<T extends UserAuthenticationModel> implements LoginService<T, String> {
    protected final JwtService jwtService;
    protected final UserService<UserProtoConfiguration.UserMessage> userService;
    protected final AccountService<UserProtoConfiguration.AccountMessage> accountService;
    protected final KeycloakAuthenticationService<String, KeycloakRegistrationModel> keycloakAuthenticationService;

    protected UserProtoOnAccountNameLoginService(JwtService jwtService, UserService<UserProtoConfiguration.UserMessage> userService, AccountService<UserProtoConfiguration.AccountMessage> accountService, KeycloakAuthenticationService<String, KeycloakRegistrationModel> keycloakAuthenticationService) {
        this.jwtService = jwtService;
        this.userService = userService;
        this.accountService = accountService;
        this.keycloakAuthenticationService = keycloakAuthenticationService;
    }

    @Override
    public String login(T loginModel) {
        UserProtoConfiguration.UserMessage userByAccountName = userService.getByAccountName(loginModel.getUsername());
        keycloakAuthenticationService.login(userByAccountName.getEmail(), loginModel.getPassword());
        UserProtoConfiguration.AccountMessage accountByUserUuid = accountService.getByName(loginModel.getUsername());

        return jwtService.generate(userByAccountName.getEmail(), Map.of("account_uuid", accountByUserUuid.getUuid()));
    }
}

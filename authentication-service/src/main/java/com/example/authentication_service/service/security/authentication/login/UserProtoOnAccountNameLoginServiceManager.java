package com.example.authentication_service.service.security.authentication.login;

import com.example.authentication_service.model.security.KeycloakRegistrationModel;
import com.example.authentication_service.model.security.UserAuthenticationModel;
import com.example.authentication_service.service.account.AccountService;
import com.example.authentication_service.service.security.authentication.keycloak.KeycloakAuthenticationService;
import com.example.authentication_service.service.user.UserService;
import com.example.grpc.user.UserProtoConfiguration;
import com.example.jwt_spring_boot_starter.service.jwt.JwtService;
import org.springframework.stereotype.Service;

@Service
public class UserProtoOnAccountNameLoginServiceManager extends UserProtoOnAccountNameLoginService<UserAuthenticationModel>{
    protected UserProtoOnAccountNameLoginServiceManager(JwtService jwtService, UserService<UserProtoConfiguration.UserMessage> userService, AccountService<UserProtoConfiguration.AccountMessage> accountService, KeycloakAuthenticationService<String, KeycloakRegistrationModel> keycloakAuthenticationService) {
        super(jwtService, userService, accountService, keycloakAuthenticationService);
    }
}

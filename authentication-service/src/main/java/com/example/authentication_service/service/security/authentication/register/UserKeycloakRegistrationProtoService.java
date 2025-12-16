package com.example.authentication_service.service.security.authentication.register;

import com.example.authentication_service.model.security.KeycloakRegistrationModel;
import com.example.authentication_service.model.security.UserRegistrationModel;
import com.example.authentication_service.service.grpc.builder.GrpcMessageBuilder;
import com.example.authentication_service.service.security.authentication.grpc.AuthenticationGrpcClientService;
import com.example.authentication_service.service.security.authentication.keycloak.KeycloakAuthenticationService;
import com.example.grpc.user.UserProtoConfiguration;

public abstract class UserKeycloakRegistrationProtoService extends UserRegistrationProtoService {
    protected final KeycloakAuthenticationService<String, KeycloakRegistrationModel> keycloakAuthenticationService;

    protected UserKeycloakRegistrationProtoService(AuthenticationGrpcClientService authenticationGrpcClientService, KeycloakAuthenticationService<String, KeycloakRegistrationModel> keycloakAuthenticationService) {
        super(authenticationGrpcClientService);
        this.keycloakAuthenticationService = keycloakAuthenticationService;
    }

    @Override
    public UserProtoConfiguration.UserRegistrationMessage register(UserRegistrationModel registerModel) {
        UserProtoConfiguration.UserRegistrationMessage userRegistrationResult = super.register(registerModel);
        if (userRegistrationResult.getFirst()) {
            try {
                keycloakAuthenticationService.register(new KeycloakRegistrationModel(registerModel.getEmail(), registerModel.getEmail(), registerModel.getPassword(), null));
            } catch (Exception e) {
                System.out.println("sending");
                rollback(registerModel);
            }
        }
        return userRegistrationResult;
    }

    protected void rollback(UserRegistrationModel registerModel) {
        authenticationGrpcClientService.deleteByEmail(GrpcMessageBuilder.buildFrom(registerModel.getEmail()));
    }
}

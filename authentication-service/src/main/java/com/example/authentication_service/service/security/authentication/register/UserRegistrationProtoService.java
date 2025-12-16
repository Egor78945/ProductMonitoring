package com.example.authentication_service.service.security.authentication.register;

import com.example.authentication_service.model.account.status.AccountStatusEnumeration;
import com.example.authentication_service.model.security.UserRegistrationModel;
import com.example.authentication_service.model.user.role.UserRoleEnumeration;
import com.example.authentication_service.model.user.status.UserStatusEnumeration;
import com.example.authentication_service.service.grpc.builder.GrpcMessageBuilder;
import com.example.authentication_service.service.security.authentication.grpc.AuthenticationGrpcClientService;
import com.example.grpc.user.UserProtoConfiguration;

import java.util.List;

public abstract class UserRegistrationProtoService implements RegistrationService<UserRegistrationModel, UserProtoConfiguration.UserRegistrationMessage> {
    protected final AuthenticationGrpcClientService authenticationGrpcClientService;

    protected UserRegistrationProtoService(AuthenticationGrpcClientService authenticationGrpcClientService) {
        this.authenticationGrpcClientService = authenticationGrpcClientService;
    }

    @Override
    public UserProtoConfiguration.UserRegistrationMessage register(UserRegistrationModel registerModel) {
        UserProtoConfiguration.UserMessage userMessage = GrpcMessageBuilder.buildFrom(registerModel.getEmail(), UserStatusEnumeration.STATUS_ACTIVE, List.of(UserRoleEnumeration.ROLE_USER.getId()));
        UserProtoConfiguration.AccountMessage accountMessage = GrpcMessageBuilder.buildFrom(registerModel.getName(), AccountStatusEnumeration.STATUS_ACTIVE, true);
        return authenticationGrpcClientService.register(GrpcMessageBuilder.buildFrom(userMessage, accountMessage));
    }
}

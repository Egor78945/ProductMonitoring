package com.example.authentication_service.service.security.authentication.user.register;

import com.example.async_spring_boot_starter.service.concurrency.AsyncStarterAsyncTaskExecutorService;
import com.example.authentication_service.model.security.KeycloakRegistrationModel;
import com.example.authentication_service.model.security.UserRegistrationModel;
import com.example.authentication_service.service.security.authentication.grpc.AuthenticationGrpcClientService;
import com.example.authentication_service.service.security.authentication.keycloak.register.KeycloakRegistrationService;
import org.springframework.stereotype.Service;

@Service
public class AsyncUserKeycloakRegistrationProtoService extends UserKeycloakRegistrationProtoService {
    protected final AsyncStarterAsyncTaskExecutorService asyncStarterAsyncTaskExecutorService;

    public AsyncUserKeycloakRegistrationProtoService(AuthenticationGrpcClientService authenticationGrpcClientService, KeycloakRegistrationService<KeycloakRegistrationModel> keycloakRegistrationService, AsyncStarterAsyncTaskExecutorService asyncStarterAsyncTaskExecutorService) {
        super(authenticationGrpcClientService, keycloakRegistrationService);
        this.asyncStarterAsyncTaskExecutorService = asyncStarterAsyncTaskExecutorService;
    }

    @Override
    protected void rollback(UserRegistrationModel registerModel) {
        asyncStarterAsyncTaskExecutorService.run(() -> super.rollback(registerModel));
    }
}

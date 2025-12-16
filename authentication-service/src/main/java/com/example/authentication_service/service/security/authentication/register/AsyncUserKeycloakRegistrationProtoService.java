package com.example.authentication_service.service.security.authentication.register;

import com.example.async_spring_boot_starter.service.concurrency.AsyncStarterAsyncTaskExecutorService;
import com.example.authentication_service.model.security.KeycloakRegistrationModel;
import com.example.authentication_service.model.security.UserRegistrationModel;
import com.example.authentication_service.service.security.authentication.grpc.AuthenticationGrpcClientService;
import com.example.authentication_service.service.security.authentication.keycloak.KeycloakAuthenticationService;
import org.springframework.stereotype.Service;

@Service
public class AsyncUserKeycloakRegistrationProtoService extends UserKeycloakRegistrationProtoService {
    protected final AsyncStarterAsyncTaskExecutorService asyncStarterAsyncTaskExecutorService;

    public AsyncUserKeycloakRegistrationProtoService(AuthenticationGrpcClientService authenticationGrpcClientService, KeycloakAuthenticationService<String, KeycloakRegistrationModel> keycloakAuthenticationService, AsyncStarterAsyncTaskExecutorService asyncStarterAsyncTaskExecutorService) {
        super(authenticationGrpcClientService, keycloakAuthenticationService);
        this.asyncStarterAsyncTaskExecutorService = asyncStarterAsyncTaskExecutorService;
    }

    @Override
    protected void rollback(UserRegistrationModel registerModel) {
        asyncStarterAsyncTaskExecutorService.run(() -> super.rollback(registerModel));
    }
}

package com.example.authentication_service.controller.authentication;

import com.example.async_spring_boot_starter.service.concurrency.AsyncStarterAsyncTaskExecutorService;
import com.example.authentication_service.controller.advice.handler.CommonControllerExceptionHandler;
import com.example.authentication_service.controller.authentication.advice.handler.AuthenticationControllerExceptionHandler;
import com.example.authentication_service.model.security.UserAuthenticationModel;
import com.example.authentication_service.model.security.UserRegistrationModel;
import com.example.authentication_service.service.security.authentication.user.login.router.LoginServiceRouter;
import com.example.authentication_service.service.security.authentication.RegistrationService;
import com.example.grpc.user.UserProtoConfiguration;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/auth")
@CommonControllerExceptionHandler
@AuthenticationControllerExceptionHandler
public class AuthenticationController {
    private final AsyncStarterAsyncTaskExecutorService asyncTaskExecutorService;
    private final RegistrationService<UserRegistrationModel, UserProtoConfiguration.UserRegistrationMessage> registrationService;
    private final LoginServiceRouter<UserAuthenticationModel> loginServiceRouter;


    public AuthenticationController(AsyncStarterAsyncTaskExecutorService asyncTaskExecutorService, RegistrationService<UserRegistrationModel, UserProtoConfiguration.UserRegistrationMessage> registrationService, LoginServiceRouter<UserAuthenticationModel> loginServiceRouter) {
        this.asyncTaskExecutorService = asyncTaskExecutorService;
        this.registrationService = registrationService;
        this.loginServiceRouter = loginServiceRouter;
    }

    @PostMapping
    public CompletableFuture<Void> register(@Valid @RequestBody UserRegistrationModel userRegistrationModel) {
        return asyncTaskExecutorService.run(() -> registrationService.register(userRegistrationModel));
    }

    @GetMapping
    public CompletableFuture<ResponseEntity<String>> login(@Valid @RequestBody UserAuthenticationModel userAuthenticationModel) {
        return asyncTaskExecutorService.submit(() ->
                ResponseEntity.ok(loginServiceRouter.onUsername(userAuthenticationModel.getUsername().contains("@") ? "email" : "name").login(userAuthenticationModel)));
    }
}

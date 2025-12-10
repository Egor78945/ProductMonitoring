package com.example.authentication_service.controller.authentication;

import com.example.async_spring_boot_starter.service.concurrency.AsyncStarterAsyncTaskExecutorService;
import com.example.authentication_service.controller.advice.handler.CommonControllerExceptionHandler;
import com.example.authentication_service.controller.authentication.advice.handler.AuthenticationControllerExceptionHandler;
import com.example.authentication_service.model.security.UserRegistrationModel;
import com.example.authentication_service.service.security.authentication.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/auth")
@CommonControllerExceptionHandler
@AuthenticationControllerExceptionHandler
public class AuthenticationController {
    private final AuthenticationService<String, UserRegistrationModel> authenticationService;
    private final AsyncStarterAsyncTaskExecutorService asyncTaskExecutorService;

    public AuthenticationController(AuthenticationService<String, UserRegistrationModel> authenticationService, AsyncStarterAsyncTaskExecutorService asyncTaskExecutorService) {
        this.authenticationService = authenticationService;
        this.asyncTaskExecutorService = asyncTaskExecutorService;
    }

    @PostMapping
    public CompletableFuture<Void> register(@Valid @RequestBody UserRegistrationModel userRegistrationModel) {
        return asyncTaskExecutorService.run(() -> authenticationService.register(userRegistrationModel));
    }

    @GetMapping
    public CompletableFuture<ResponseEntity<String>> login(@RequestParam("username") String username, @RequestParam("password") String password) {
        return asyncTaskExecutorService.submit(() -> ResponseEntity.ok(authenticationService.login(username, password)));
    }
}

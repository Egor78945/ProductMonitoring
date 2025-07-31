package com.example.authentication_service.controller.authentication;

import com.example.authentication_service.controller.advice.handler.CommonControllerExceptionHandler;
import com.example.authentication_service.controller.authentication.advice.handler.AuthenticationControllerExceptionHandler;
import com.example.authentication_service.model.security.UserRegistrationModel;
import com.example.authentication_service.service.concurrency.AsyncTaskExecutorService;
import com.example.authentication_service.service.security.authentication.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/api/v1/auth")
@CommonControllerExceptionHandler
@AuthenticationControllerExceptionHandler
public class AuthenticationController {
    private final AuthenticationService<String, UserRegistrationModel> authenticationService;
    private final AsyncTaskExecutorService asyncTaskExecutorService;

    public AuthenticationController(AuthenticationService<String, UserRegistrationModel> authenticationService, AsyncTaskExecutorService asyncTaskExecutorService) {
        this.authenticationService = authenticationService;
        this.asyncTaskExecutorService = asyncTaskExecutorService;
    }

    @PostMapping
    public CompletableFuture<Void> register(@Valid @RequestBody UserRegistrationModel userRegistrationModel) {
        return asyncTaskExecutorService.run(() -> authenticationService.register(userRegistrationModel));
    }

    @GetMapping
    public CompletableFuture<ResponseEntity<String>> login(@RequestParam("email") String email, @RequestParam("password") String password) {
        return asyncTaskExecutorService.submit(() -> ResponseEntity.ok(authenticationService.login(email, password)));
    }
}

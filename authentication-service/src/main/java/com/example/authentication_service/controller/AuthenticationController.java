package com.example.authentication_service.controller;

import com.example.authentication_service.model.security.UserRegistrationModel;
import com.example.authentication_service.service.security.authentication.AuthenticationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final AuthenticationService<String, UserRegistrationModel> authenticationService;

    public AuthenticationController(AuthenticationService<String, UserRegistrationModel> authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping
    public void register(@Valid @RequestBody UserRegistrationModel userRegistrationModel) {
        authenticationService.register(userRegistrationModel);
    }

    @GetMapping
    public ResponseEntity<String> login(@RequestParam("email") String email, @RequestParam("password") String password) {
        return ResponseEntity.ok(authenticationService.login(email, password));
    }
}

package com.example.authentication_service.service.security.authentication.user;

import com.example.authentication_service.service.security.authentication.AuthenticationService;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationServiceManager implements AuthenticationService<String, String> {
    @Override
    public String login(String email, String password) {
        return "";
    }

    @Override
    public void register(String registerModel) {

    }
}

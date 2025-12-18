package com.example.api_gateway.service.token;

import com.example.jwt_spring_boot_starter.service.jwt.JwtService;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationTokenServiceManger extends AuthenticationTokenService{
    public AuthenticationTokenServiceManger(JwtService jwtService) {
        super(jwtService);
    }
}

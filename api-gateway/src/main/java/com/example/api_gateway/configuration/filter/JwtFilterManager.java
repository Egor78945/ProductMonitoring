package com.example.api_gateway.configuration.filter;

import com.example.api_gateway.service.token.TokenService;
import org.springframework.stereotype.Component;

@Component
public class JwtFilterManager extends JwtFilter {
    public JwtFilterManager(TokenService<String> tokenService) {
        super(tokenService);
    }
}

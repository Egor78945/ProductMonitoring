package com.example.security_manager_spring_boot_starter.configuration.security.filter;

import com.example.security_manager_spring_boot_starter.service.keycloak.token.SecurityManagerKeycloakTokenParser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityManagerJwtEmailExtractRequestFilter extends OncePerRequestFilter {
    private final SecurityManagerKeycloakTokenParser securityManagerKeycloakTokenParser;

    public SecurityManagerJwtEmailExtractRequestFilter(SecurityManagerKeycloakTokenParser securityManagerKeycloakTokenParser) {
        this.securityManagerKeycloakTokenParser = securityManagerKeycloakTokenParser;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getHeader("X-User-Email") != null && !request.getHeader("X-User-Email").equals("anonymousUser") && SecurityContextHolder.getContext().getAuthentication() == null) {
            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(request.getHeader("X-User-Email"), null));
        }
        filterChain.doFilter(request, response);
    }
}

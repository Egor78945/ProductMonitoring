package com.example.security_manager_spring_boot_starter.configuration.security.filter;

import com.example.security_manager_spring_boot_starter.service.keycloak.token.SecurityManagerKeycloakTokenParser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class SecurityManagerJwtEmailExtractRequestFilter extends OncePerRequestFilter {
    private final SecurityManagerKeycloakTokenParser securityManagerKeycloakTokenParser;

    public SecurityManagerJwtEmailExtractRequestFilter(SecurityManagerKeycloakTokenParser securityManagerKeycloakTokenParser) {
        this.securityManagerKeycloakTokenParser = securityManagerKeycloakTokenParser;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            try {
                String email = securityManagerKeycloakTokenParser.validateAndGetAttribute(token, "email");

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(email, null, List.of());

                SecurityContextHolder.getContext().setAuthentication(authentication);

                request.setAttribute("userEmail", email);

            } catch (Exception e) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("invalid token: " + e.getMessage());
                return;
            }
        }

        filterChain.doFilter(request, response);
    }
}

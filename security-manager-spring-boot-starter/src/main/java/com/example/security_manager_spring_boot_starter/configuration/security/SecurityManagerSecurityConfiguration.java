package com.example.security_manager_spring_boot_starter.configuration.security;

import com.example.security_manager_spring_boot_starter.configuration.security.filter.SecurityManagerJwtEmailExtractRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityManagerSecurityConfiguration {
    private final SecurityManagerJwtEmailExtractRequestFilter securityManagerJwtEmailExtractRequestFilter;

    public SecurityManagerSecurityConfiguration(SecurityManagerJwtEmailExtractRequestFilter securityManagerJwtEmailExtractRequestFilter) {
        this.securityManagerJwtEmailExtractRequestFilter = securityManagerJwtEmailExtractRequestFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(CsrfConfigurer::disable)
                .authorizeHttpRequests(authz -> authz
                        .anyRequest().permitAll() // или .authenticated()
                )
                .addFilterBefore(securityManagerJwtEmailExtractRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

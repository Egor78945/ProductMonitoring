package com.example.api_gateway.configuration.security;

import com.example.api_gateway.configuration.security.converter.JwtAuthoritiesConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

@Configuration
@EnableWebFluxSecurity
public class WebSecurityConfiguration {
    @Bean
    public SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http, Converter<Jwt, Mono<AbstractAuthenticationToken>> authenticationConverter) {
        return http.authorizeExchange(exchange ->
                        exchange.pathMatchers("/api/v1/auth/test").authenticated())
                .authorizeExchange(exchange ->
                        exchange.pathMatchers("/api/v1/auth/**").permitAll()
                                .anyExchange().authenticated())
                .oauth2ResourceServer(oa2 ->
                        oa2.jwt(jwt -> jwt.jwtAuthenticationConverter(authenticationConverter)))
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .cors(ServerHttpSecurity.CorsSpec::disable)
                .build();
    }

    @Bean
    public Converter<Jwt, Mono<AbstractAuthenticationToken>> keycloakJwtConverter() {
        var converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(new JwtAuthoritiesConverter());
        return new ReactiveJwtAuthenticationConverterAdapter(converter);
    }
}

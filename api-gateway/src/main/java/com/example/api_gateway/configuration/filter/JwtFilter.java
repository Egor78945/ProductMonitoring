package com.example.api_gateway.configuration.filter;

import com.example.api_gateway.service.token.TokenService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

public abstract class JwtFilter implements WebFilter {
    protected final TokenService<String> tokenService;

    public JwtFilter(TokenService<String> tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        try {
            List<String> auth = exchange.getRequest().getHeaders().get("Authorization");
            if (auth != null && !auth.isEmpty() && auth.get(0).startsWith("Bearer ")) {
                String token = auth.get(0).substring("Bearer ".length());
                Map<String, Object> claims = tokenService.extractClaims(token);

                List<SimpleGrantedAuthority> authorities = tokenService.extractAuthorities(token).stream().filter(r -> r.startsWith("ROLE_")).map(SimpleGrantedAuthority::new).toList();
                if (tokenService.extractSubject(token) != null && claims.get("account_uuid") != null && claims.get("roles") != null) {
                    exchange.getRequest().getHeaders().set("X-Account-Uuid", claims.get("account_uuid").toString());
                    return chain.filter(exchange).contextWrite(ReactiveSecurityContextHolder.withAuthentication(new UsernamePasswordAuthenticationToken(claims.get("account_uuid").toString(), null, authorities)));
                }
            }
        } catch (Exception e) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }
}

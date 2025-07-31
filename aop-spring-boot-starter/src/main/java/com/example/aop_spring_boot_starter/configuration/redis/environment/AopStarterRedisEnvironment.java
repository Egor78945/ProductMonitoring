package com.example.aop_spring_boot_starter.configuration.redis.environment;

import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "spring.data.redis")
@Validated
public class AopStarterRedisEnvironment {
    @NotNull(message = "redis host is null")
    private String host;
    private int port;
    private long lifetime;
    @NotNull(message = "redis password is null")
    private String password;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public long getLifetime() {
        return lifetime;
    }

    public void setLifetime(long lifetime) {
        this.lifetime = lifetime;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

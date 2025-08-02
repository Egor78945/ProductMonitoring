package com.example.async_spring_boot_starter.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import java.util.Objects;

@ConfigurationProperties(prefix = "async.starter")
@Validated
public class AsyncStarterEnvironment {
    private int threadPoolSize;

    public AsyncStarterEnvironment(int threadPoolSize) {
        this.threadPoolSize = threadPoolSize > 0 ? threadPoolSize : 1;
    }

    public int getThreadPoolSize() {
        return threadPoolSize;
    }

    public void setThreadPoolSize(int threadPoolSize) {
        this.threadPoolSize = threadPoolSize;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        AsyncStarterEnvironment that = (AsyncStarterEnvironment) o;
        return threadPoolSize == that.threadPoolSize;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(threadPoolSize);
    }

    @Override
    public String toString() {
        return "AsyncStarterEnvironment{" +
                "threadPoolSize=" + threadPoolSize +
                '}';
    }
}

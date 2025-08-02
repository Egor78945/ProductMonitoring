package com.example.async_spring_boot_starter.configuration.executor;

import com.example.async_spring_boot_starter.configuration.AsyncStarterEnvironment;
import com.example.async_spring_boot_starter.service.concurrency.AsyncStarterAsyncTaskExecutorService;
import com.example.async_spring_boot_starter.service.concurrency.AsyncStarterAsyncTaskExecutorServiceManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
@EnableConfigurationProperties(AsyncStarterEnvironment.class)
public class AsyncStarterExecutorConfiguration {
    private final AsyncStarterEnvironment asyncStarterEnvironment;

    public AsyncStarterExecutorConfiguration(AsyncStarterEnvironment asyncStarterEnvironment) {
        this.asyncStarterEnvironment = asyncStarterEnvironment;
    }

    @Bean
    public ExecutorService asyncStarterFixedThreadPoolExecutor() {
        return Executors.newFixedThreadPool(asyncStarterEnvironment.getThreadPoolSize(), r -> new Thread(() -> {
            Thread.currentThread().setContextClassLoader(getMainClassLoader());
            r.run();
        }));
    }

    @Bean
    public AsyncStarterAsyncTaskExecutorService asyncStarterAsyncTaskExecutorServiceManager(@Qualifier("asyncStarterFixedThreadPoolExecutor") ExecutorService executorService) {
        return new AsyncStarterAsyncTaskExecutorServiceManager(executorService);
    }

    private ClassLoader getMainClassLoader() {
        try {
            Class<?> springApplicationClass = Class.forName("org.springframework.boot.SpringApplication");
            Method getMainAppMethod = springApplicationClass.getDeclaredMethod("getMainApplicationClass");
            Class<?> mainApplicationClass = (Class<?>) getMainAppMethod.invoke(null);
            return mainApplicationClass.getClassLoader();
        } catch (Exception e) {
            return getClass().getClassLoader();
        }
    }
}

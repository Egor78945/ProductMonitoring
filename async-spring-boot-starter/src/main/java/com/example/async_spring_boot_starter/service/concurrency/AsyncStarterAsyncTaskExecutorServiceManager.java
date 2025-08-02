package com.example.async_spring_boot_starter.service.concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.function.Supplier;

public class AsyncStarterAsyncTaskExecutorServiceManager implements AsyncStarterAsyncTaskExecutorService {
    private final ExecutorService executorService;

    public AsyncStarterAsyncTaskExecutorServiceManager(ExecutorService executorService) {
        this.executorService = executorService;
    }

    @Override
    public <T> CompletableFuture<T> submit(Supplier<T> task) {
        return CompletableFuture.supplyAsync(task, executorService);
    }

    @Override
    public CompletableFuture<Void> run(Runnable task) {
        return CompletableFuture.runAsync(task, executorService);
    }
}

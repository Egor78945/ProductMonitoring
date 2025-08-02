package com.example.async_spring_boot_starter.service.concurrency;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public interface AsyncStarterAsyncTaskExecutorService {
    <T> CompletableFuture<T> submit(Supplier<T> task);

    CompletableFuture<Void> run(Runnable task);
}

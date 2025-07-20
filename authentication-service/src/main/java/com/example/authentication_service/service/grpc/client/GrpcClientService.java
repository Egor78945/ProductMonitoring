package com.example.authentication_service.service.grpc.client;

import io.grpc.stub.AbstractBlockingStub;

public abstract class GrpcClientService<T extends AbstractBlockingStub<T>> {
    protected final T stub;

    public GrpcClientService(T stub) {
        this.stub = stub;
    }
}

package com.example.product_processor_service.service.common.grpc.client;

import io.grpc.stub.AbstractBlockingStub;

public abstract class GrpcClientService<T extends AbstractBlockingStub<T>> {
    protected final T stub;

    public GrpcClientService(T stub) {
        this.stub = stub;
    }
}

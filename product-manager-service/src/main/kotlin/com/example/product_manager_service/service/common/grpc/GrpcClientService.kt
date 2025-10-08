package com.example.product_manager_service.service.common.grpc

import io.grpc.stub.AbstractBlockingStub

abstract class GrpcClientService<T : AbstractBlockingStub<T>>(protected val stub: T)

package com.example.product_manager_service.service.product.grpc

import com.example.grpc.user.ProductProtoServiceGrpc
import org.springframework.stereotype.Service

@Service
class ProductGrpcClientServiceManager(stub: ProductProtoServiceGrpc.ProductProtoServiceBlockingStub) : ProductGrpcClientService(stub) {
}
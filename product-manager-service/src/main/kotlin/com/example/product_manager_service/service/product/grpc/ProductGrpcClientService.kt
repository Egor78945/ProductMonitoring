package com.example.product_manager_service.service.product.grpc

import com.example.grpc.user.ProductProtoServiceGrpc
import com.example.grpc.user.UserProtoConfiguration
import com.example.product_manager_service.service.common.grpc.GrpcClientService

abstract class ProductGrpcClientService(stub: ProductProtoServiceGrpc.ProductProtoServiceBlockingStub) :
    GrpcClientService<ProductProtoServiceGrpc.ProductProtoServiceBlockingStub>(stub) {

    fun getAllByAccountUuid(message: UserProtoConfiguration.StringIntMessage): UserProtoConfiguration.ProductListMessage =
        stub.getAllByAccountUuidAndPage(message)

}
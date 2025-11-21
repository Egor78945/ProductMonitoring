package com.example.product_manager_service.service.product

import com.example.product_manager_service.service.product.grpc.ProductGrpcClientService
import com.example.product_manager_service.service.security.AuthenticationContextService
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
class ProductProtoEntityServiceManager(
    authContextService: AuthenticationContextService<Authentication>,
    productGrpcClientService: ProductGrpcClientService) : ProductProtoEntityService(authContextService, productGrpcClientService) {

}

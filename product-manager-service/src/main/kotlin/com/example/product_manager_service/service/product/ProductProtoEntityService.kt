package com.example.product_manager_service.service.product

import com.example.product_manager_service.model.product.dto.ProductDto
import com.example.product_manager_service.service.product.grpc.ProductGrpcClientService
import com.example.product_manager_service.service.security.AuthenticationContextService
import com.example.product_manager_service.util.grpc.mapper.GrpcMapper
import org.springframework.security.core.Authentication

abstract class ProductProtoEntityService(
    authContextService: AuthenticationContextService<Authentication>,
    protected val productGrpcClientService: ProductGrpcClientService
) : ProductEntityService(authContextService) {
    override fun getByAccountUuid(page: Int): List<ProductDto> =
        GrpcMapper.mapTo(
            productGrpcClientService.getAllByAccountUuid(
                GrpcMapper.mapTo(
                    authContextService.getCurrentAuthentication().principal.toString(), page
                )
            ).productsList
        )
}
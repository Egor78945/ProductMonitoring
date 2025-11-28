package com.example.product_manager_service.service.product

import com.example.product_manager_service.model.product.dto.ProductDto
import com.example.product_manager_service.service.kafka.producter.RoutedKafkaProducerService
import com.example.product_manager_service.service.product.grpc.ProductGrpcClientService
import com.example.product_manager_service.service.security.AuthenticationContextService
import com.example.product_manager_service.util.grpc.mapper.GrpcMapper
import org.springframework.security.core.Authentication
import java.net.URI
import java.util.UUID

abstract class ProductProtoEntityService(
    protected val productGrpcClientService: ProductGrpcClientService,
) : ProductEntityService() {
    override fun getByAccountUuid(accountUuid: UUID, page: Int): List<ProductDto> =
        GrpcMapper.mapTo(
            productGrpcClientService.getAllByAccountUuid(
                GrpcMapper.mapTo(
                    accountUuid.toString(), page
                )
            ).productsList
        )
}
package com.example.product_manager_service.util.grpc.mapper

import com.example.grpc.user.UserProtoConfiguration
import com.example.grpc.user.UserProtoConfiguration.ProductMessage
import com.example.product_manager_service.model.product.dto.ProductDto
import java.net.URI
import java.util.Date

class GrpcMapper {
    companion object {
        fun mapTo(string: String, int: Int) = UserProtoConfiguration.StringIntMessage
            .newBuilder()
            .setString(string)
            .setInt(int)
            .build()

        fun mapTo(uri: URI) = UserProtoConfiguration.ProductMessage
            .newBuilder()
            .setUrl(uri.toString())
            .build()

        fun mapTo(productMessage: UserProtoConfiguration.ProductMessage) = ProductDto(
            URI.create(productMessage.url),
            productMessage.name,
            productMessage.actualPrice,
            productMessage.pastPrice,
            Date(productMessage.updatedAt)
        )

        fun mapTo(productMessageList: List<UserProtoConfiguration.ProductMessage>) = productMessageList
            .map{ mapTo(it) }
            .toList()
    }
}
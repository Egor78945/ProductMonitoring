package com.example.product_manager_service.service.common.grpc.mapper

import com.example.grpc.product.ProductServiceProtoConfiguration

class GrpcMapperService {
    companion object {
        fun mapTo(booleanMessage: ProductServiceProtoConfiguration.BooleanMessage) = booleanMessage.boolean
        fun mapTo(string: String) =
            ProductServiceProtoConfiguration.StringMessage.newBuilder().setString(string).build()

        fun mapTo(stringList: ProductServiceProtoConfiguration.StringListMessage) = stringList.stringsList.toSet()
    }
}
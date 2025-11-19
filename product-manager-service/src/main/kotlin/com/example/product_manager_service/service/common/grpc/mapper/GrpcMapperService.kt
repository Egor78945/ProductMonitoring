package com.example.product_manager_service.service.common.grpc.mapper

import com.example.grpc.user.UserProtoConfiguration;

class GrpcMapperService {
    companion object {
        fun mapTo(booleanMessage: UserProtoConfiguration.BooleanMessage) = booleanMessage.boolean
        fun mapTo(string: String) =
            UserProtoConfiguration.StringMessage.newBuilder().setString(string).build()
    }
}
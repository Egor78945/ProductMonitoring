package com.example.user_database_manager_service.service.common.grpc.mapper;

import com.example.grpc.user.UserProtoConfiguration;

public class GrpcMapper {
    public static UserProtoConfiguration.BooleanMessage mapTo(boolean bool){
        return UserProtoConfiguration.BooleanMessage
                .newBuilder()
                .setBoolean(bool)
                .build();
    }

    public static UserProtoConfiguration.StringMessage mapTo(String string){
        return UserProtoConfiguration.StringMessage
                .newBuilder()
                .setString(string)
                .build();
    }

    public static UserProtoConfiguration.EmptyMessage mapTo(){
        return UserProtoConfiguration.EmptyMessage.newBuilder().build();
    }
}

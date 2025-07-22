package com.example.user_database_manager_service.service.grpc.builder;

import com.example.grpc.user.UserProtoConfiguration;

public class GrpcItemBuilder {
    public static UserProtoConfiguration.BooleanMessage buildFrom(boolean bool){
        return UserProtoConfiguration.BooleanMessage
                .newBuilder()
                .setBoolean(bool)
                .build();
    }

    public static UserProtoConfiguration.StringMessage buildFrom(String string){
        return UserProtoConfiguration.StringMessage
                .newBuilder()
                .setString(string)
                .build();
    }

    public static UserProtoConfiguration.EmptyMessage buildEmpty(){
        return UserProtoConfiguration.EmptyMessage.newBuilder().build();
    }
}

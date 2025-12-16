package com.example.user_database_manager_service.service.user.notification.grpc;

import com.example.grpc.user.UserNotificationProtoServiceGrpc;
import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.exception.AlreadyExistsException;
import com.example.user_database_manager_service.exception.NotFoundException;
import com.example.user_database_manager_service.exception.message.ExceptionMessage;
import com.example.user_database_manager_service.service.common.grpc.mapper.GrpcMapper;
import com.example.user_database_manager_service.service.user.notification.UserNotificationService;
import com.example.user_database_manager_service.service.user.notification.common.CommonUserNotificationService;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import java.util.UUID;

@GrpcService
public class UserNotificationServiceGrpc extends UserNotificationProtoServiceGrpc.UserNotificationProtoServiceImplBase {
    private final UserNotificationService<UserProtoConfiguration.UserNotificationMessage> userNotificationService;
    private final CommonUserNotificationService commonUserNotificationService;

    public UserNotificationServiceGrpc(UserNotificationService<UserProtoConfiguration.UserNotificationMessage> userNotificationService, CommonUserNotificationService commonUserNotificationService) {
        this.userNotificationService = userNotificationService;
        this.commonUserNotificationService = commonUserNotificationService;
    }

    @Override
    public void save(UserProtoConfiguration.UserNotificationMessage request, StreamObserver<UserProtoConfiguration.EmptyMessage> responseObserver) {
        try {
            userNotificationService.save(request);
            responseObserver.onNext(GrpcMapper.mapTo());
            responseObserver.onCompleted();
        } catch (AlreadyExistsException e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void update(UserProtoConfiguration.UserNotificationMessage request, StreamObserver<UserProtoConfiguration.EmptyMessage> responseObserver) {
        try {
            userNotificationService.update(request);
            responseObserver.onNext(GrpcMapper.mapTo());
            responseObserver.onCompleted();
        } catch (NotFoundException e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void findByUserUuidAndNotificationTypeId(UserProtoConfiguration.StringLongMessage request, StreamObserver<UserProtoConfiguration.UserNotificationMessage> responseObserver) {
        try {
            responseObserver.onNext(userNotificationService.findByUserUuidAndNotificationTypeId(UUID.fromString(request.getString()), request.getLong())
                    .orElseThrow(() -> new NotFoundException(ExceptionMessage.NOT_FOUND.getMessage())));
            responseObserver.onCompleted();
        } catch (NotFoundException e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void existsByUserUuidAndNotificationTypeId(UserProtoConfiguration.StringLongMessage request, StreamObserver<UserProtoConfiguration.BooleanMessage> responseObserver) {
        responseObserver.onNext(GrpcMapper.mapTo(commonUserNotificationService.existsBy(UUID.fromString(request.getString()), request.getLong())));
        responseObserver.onCompleted();
    }
}

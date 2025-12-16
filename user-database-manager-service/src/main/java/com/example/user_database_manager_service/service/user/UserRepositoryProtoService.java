package com.example.user_database_manager_service.service.user;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.repository.user.UserRepository;

public abstract class UserRepositoryProtoService extends UserRepositoryService<UserProtoConfiguration.UserMessage> {
    public UserRepositoryProtoService(UserRepository<UserProtoConfiguration.UserMessage> userRepository) {
        super(userRepository);
    }
}

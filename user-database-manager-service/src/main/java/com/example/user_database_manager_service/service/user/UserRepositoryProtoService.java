package com.example.user_database_manager_service.service.user;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.exception.AlreadyExistsException;
import com.example.user_database_manager_service.exception.NotFoundException;
import com.example.user_database_manager_service.repository.user.UserRepository;
import org.springframework.stereotype.Service;

public abstract class UserRepositoryProtoService extends UserRepositoryService<UserProtoConfiguration.UserMessage> {
    public UserRepositoryProtoService(UserRepository<UserProtoConfiguration.UserMessage> userProtoRepository) {
        super(userProtoRepository);
    }

    @Override
    public UserProtoConfiguration.UserMessage save(UserProtoConfiguration.UserMessage user) {
        if (!existsByEmail(user.getEmail())) {
            return super.save(user);
        }
        throw new AlreadyExistsException(String.format("User is already exists by email: %s", user));
    }

    @Override
    public UserProtoConfiguration.UserMessage update(UserProtoConfiguration.UserMessage user) {
        if (existsByEmail(user.getEmail())) {
            return super.update(user);
        }
        throw new NotFoundException(String.format("User is not found by user email: %s", user));
    }
}

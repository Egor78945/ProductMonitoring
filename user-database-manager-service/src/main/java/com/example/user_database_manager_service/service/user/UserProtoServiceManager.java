package com.example.user_database_manager_service.service.user;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.exception.AlreadyExistsException;
import com.example.user_database_manager_service.exception.NotFoundException;
import com.example.user_database_manager_service.exception.ProcessingException;
import com.example.user_database_manager_service.exception.message.ExceptionMessage;
import com.example.user_database_manager_service.repository.user.JooqUserRepository;
import com.example.user_database_manager_service.repository.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserProtoServiceManager extends UserProtoService {
    public UserProtoServiceManager(UserRepository<UserProtoConfiguration.UserMessage> userProtoRepository) {
        super(userProtoRepository);
    }

    @Override
    public UserProtoConfiguration.UserMessage save(UserProtoConfiguration.UserMessage user) {
        if (!existsByEmail(user.getEmail())) {
            return super.save(user);
        }
        throw new AlreadyExistsException(ExceptionMessage.ALREADY_EXISTS.getMessage());
    }

    @Override
    public UserProtoConfiguration.UserMessage update(UserProtoConfiguration.UserMessage user) {
        if (existsByEmail(user.getEmail())) {
            return super.update(user);
        }
        throw new NotFoundException(ExceptionMessage.NOT_FOUND.getMessage());
    }
}

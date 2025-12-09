package com.example.user_database_manager_service.service.user;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.repository.user.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UserRepositoryProtoServiceManager extends UserRepositoryProtoService{
    public UserRepositoryProtoServiceManager(UserRepository<UserProtoConfiguration.UserMessage> userProtoRepository) {
        super(userProtoRepository);
    }
}

package com.example.user_database_manager_service.service.user;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRepositoryProtoTransactionalServiceManager extends UserRepositoryProtoService{
    public UserRepositoryProtoTransactionalServiceManager(UserRepository<UserProtoConfiguration.UserMessage> userProtoRepository) {
        super(userProtoRepository);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public UserProtoConfiguration.UserMessage save(UserProtoConfiguration.UserMessage user) {
        return super.save(user);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public UserProtoConfiguration.UserMessage update(UserProtoConfiguration.UserMessage user) {
        return super.update(user);
    }
}

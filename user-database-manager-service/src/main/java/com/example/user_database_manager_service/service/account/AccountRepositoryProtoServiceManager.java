package com.example.user_database_manager_service.service.account;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.repository.account.AccountRepository;
import com.example.user_database_manager_service.repository.user.UserRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class AccountRepositoryProtoServiceManager extends AccountRepositoryProtoService{
    public AccountRepositoryProtoServiceManager(AccountRepository<UserProtoConfiguration.AccountMessage> accountProtoRepository, UserRepository<?> userRepository) {
        super(accountProtoRepository, userRepository);
    }
}

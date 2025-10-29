package com.example.user_database_manager_service.service.account;

import com.example.user_database_manager_service.repository.account.JooqAccountRepository;
import com.example.user_database_manager_service.repository.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountProtoServiceManager extends AccountProtoService {
    public AccountProtoServiceManager(JooqAccountRepository accountProtoRepository, UserRepository<?> userRepository) {
        super(accountProtoRepository, userRepository);
    }
}

package com.example.user_database_manager_service.service.account;

import com.example.user_database_manager_service.repository.account.AccountProtoRepository;
import com.example.user_database_manager_service.repository.user.UserProtoRepository;
import com.example.user_database_manager_service.repository.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountProtoServiceManager extends AccountProtoService {
    public AccountProtoServiceManager(AccountProtoRepository accountProtoRepository, UserRepository<?> userRepository) {
        super(accountProtoRepository, userRepository);
    }
}

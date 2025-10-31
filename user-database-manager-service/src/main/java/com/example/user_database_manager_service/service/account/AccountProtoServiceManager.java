package com.example.user_database_manager_service.service.account;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.exception.ProcessingException;
import com.example.user_database_manager_service.exception.message.ExceptionMessage;
import com.example.user_database_manager_service.repository.account.AccountRepository;
import com.example.user_database_manager_service.repository.account.JooqAccountRepository;
import com.example.user_database_manager_service.repository.user.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccountProtoServiceManager extends AccountProtoService {
    public AccountProtoServiceManager(AccountRepository<UserProtoConfiguration.AccountMessage> accountProtoRepository, UserRepository<?> userRepository) {
        super(accountProtoRepository, userRepository);
    }

    @Override
    public UserProtoConfiguration.AccountMessage save(UserProtoConfiguration.AccountMessage account) {
        if (!existsByName(account.getName()) && userRepository.existsByUUID(UUID.fromString(account.getUserUuid()))) {
            return accountProtoRepository.save(account);
        }
        throw new ProcessingException(ExceptionMessage.ALREADY_EXISTS.getMessage());
    }
}

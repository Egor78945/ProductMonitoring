package com.example.user_database_manager_service.service.account;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.exception.AlreadyExistsException;
import com.example.user_database_manager_service.exception.NotFoundException;
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
            return super.save(account);
        }
        throw new AlreadyExistsException(ExceptionMessage.ALREADY_EXISTS.getMessage());
    }

    @Override
    public UserProtoConfiguration.AccountMessage update(UserProtoConfiguration.AccountMessage account) {
        if (existsByName(account.getName()) && userRepository.existsByUUID(UUID.fromString(account.getUserUuid()))) {
            return super.update(account);
        }
        throw new NotFoundException(ExceptionMessage.NOT_FOUND.getMessage());
    }
}

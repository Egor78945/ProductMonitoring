package com.example.user_database_manager_service.service.account;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.repository.account.AccountRepository;
import com.example.user_database_manager_service.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountRepositoryProtoTransactionalServiceManager extends AccountRepositoryProtoService{
    public AccountRepositoryProtoTransactionalServiceManager(AccountRepository<UserProtoConfiguration.AccountMessage> accountProtoRepository, UserRepository<?> userRepository) {
        super(accountProtoRepository, userRepository);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public UserProtoConfiguration.AccountMessage save(UserProtoConfiguration.AccountMessage account) {
        return super.save(account);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public UserProtoConfiguration.AccountMessage update(UserProtoConfiguration.AccountMessage account) {
        return super.update(account);
    }
}

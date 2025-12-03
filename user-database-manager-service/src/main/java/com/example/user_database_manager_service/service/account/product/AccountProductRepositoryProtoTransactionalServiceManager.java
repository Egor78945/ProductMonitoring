package com.example.user_database_manager_service.service.account.product;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.repository.account.product.AccountProductRepository;
import com.example.user_database_manager_service.service.account.AccountService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountProductRepositoryProtoTransactionalServiceManager extends AccountProductRepositoryProtoService{
    public AccountProductRepositoryProtoTransactionalServiceManager(AccountProductRepository<UserProtoConfiguration.AccountUuidProductUriMessage> accountProductRepository, AccountService<?> accountService) {
        super(accountProductRepository, accountService);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public UserProtoConfiguration.AccountUuidProductUriMessage save(UserProtoConfiguration.AccountUuidProductUriMessage entity) {
        return super.save(entity);
    }

    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public UserProtoConfiguration.AccountUuidProductUriMessage update(UserProtoConfiguration.AccountUuidProductUriMessage entity) {
        return super.update(entity);
    }
}

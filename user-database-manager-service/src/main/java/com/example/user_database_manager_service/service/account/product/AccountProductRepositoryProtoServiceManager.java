package com.example.user_database_manager_service.service.account.product;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.repository.account.product.AccountProductRepository;
import com.example.user_database_manager_service.service.account.AccountService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class AccountProductRepositoryProtoServiceManager extends AccountProductRepositoryProtoService {
    public AccountProductRepositoryProtoServiceManager(AccountProductRepository<UserProtoConfiguration.AccountUuidProductUriMessage> accountProductRepository, AccountService<?> accountService) {
        super(accountProductRepository, accountService);
    }
}

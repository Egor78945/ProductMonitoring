package com.example.user_database_manager_service.service.account.product;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.exception.AlreadyExistsException;
import com.example.user_database_manager_service.exception.NotFoundException;
import com.example.user_database_manager_service.repository.account.product.AccountProductRepository;
import com.example.user_database_manager_service.service.account.AccountService;
import com.example.user_database_manager_service.service.account.common.CommonAccountService;
import com.example.user_database_manager_service.service.account.product.common.CommonAccountProductService;

import java.net.URI;
import java.util.UUID;

public abstract class AccountProductRepositoryProtoService extends AccountProductRepositoryService<UserProtoConfiguration.AccountUuidProductUriMessage> {
    protected final CommonAccountService commonAccountService;
    protected final CommonAccountProductService commonAccountProductService;

    public AccountProductRepositoryProtoService(AccountProductRepository<UserProtoConfiguration.AccountUuidProductUriMessage> accountProductRepository, CommonAccountService commonAccountService, CommonAccountProductService commonAccountProductService) {
        super(accountProductRepository);
        this.commonAccountService = commonAccountService;
        this.commonAccountProductService = commonAccountProductService;
    }

    @Override
    public UserProtoConfiguration.AccountUuidProductUriMessage save(UserProtoConfiguration.AccountUuidProductUriMessage entity) {
        if (commonAccountService.existsByUUID(UUID.fromString(entity.getAccountUuid())) && !commonAccountProductService.existsBy(UUID.fromString(entity.getAccountUuid()), URI.create(entity.getProductUri()))) {
            return super.save(entity);
        }
        throw new AlreadyExistsException(String.format("AccountProduct by account uuid and product url is already exists: %s", entity));
    }

    @Override
    public UserProtoConfiguration.AccountUuidProductUriMessage update(UserProtoConfiguration.AccountUuidProductUriMessage entity) {
        if (commonAccountService.existsByUUID(UUID.fromString(entity.getAccountUuid())) && commonAccountProductService.existsBy(UUID.fromString(entity.getAccountUuid()), URI.create(entity.getProductUri()))) {
            return super.update(entity);
        }
        throw new NotFoundException(String.format("AccountProduct is not found by account uuid and product url: %s", entity));
    }
}

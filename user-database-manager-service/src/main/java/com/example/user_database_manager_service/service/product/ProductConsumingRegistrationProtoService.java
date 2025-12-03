package com.example.user_database_manager_service.service.product;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.exception.NotFoundException;
import com.example.user_database_manager_service.service.account.AccountService;
import com.example.user_database_manager_service.service.account.product.AccountProductService;
import com.example.user_database_manager_service.service.account.product.mapper.AccountProductMapper;

import java.net.URI;
import java.util.UUID;

public abstract class ProductConsumingRegistrationProtoService implements ProductConsumingRegistrationService<UserProtoConfiguration.ProductRegistrationMessage> {
    protected final ProductService<UserProtoConfiguration.ProductMessage> productService;
    protected final AccountProductService<UserProtoConfiguration.AccountUuidProductUriMessage> accountProductService;
    protected final AccountService<?> accountService;

    protected ProductConsumingRegistrationProtoService(ProductService<UserProtoConfiguration.ProductMessage> productService, AccountProductService<UserProtoConfiguration.AccountUuidProductUriMessage> accountProductService, AccountService<?> accountService) {
        this.productService = productService;
        this.accountProductService = accountProductService;
        this.accountService = accountService;
    }

    @Override
    public void register(UserProtoConfiguration.ProductRegistrationMessage subject) {
        if (accountService.existsByUUID(UUID.fromString(subject.getAccountUuid()))) {
            if (!productService.existsByUrl(URI.create(subject.getProduct().getUrl()))) {
                productService.save(subject.getProduct());
            }
            if (!accountProductService.existsBy(UUID.fromString(subject.getAccountUuid()), URI.create(subject.getProduct().getUrl()))) {
                accountProductService.save(AccountProductMapper.mapTo(subject.getAccountUuid(), subject.getProduct().getUrl()));
            }
        } else {
            throw new NotFoundException(String.format("account not found: account uuid = %s", subject.getAccountUuid()));
        }
    }
}

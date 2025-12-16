package com.example.user_database_manager_service.service.product;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.exception.NotFoundException;
import com.example.user_database_manager_service.service.account.common.CommonAccountService;
import com.example.user_database_manager_service.service.account.product.AccountProductService;
import com.example.user_database_manager_service.service.account.product.common.CommonAccountProductService;
import com.example.user_database_manager_service.service.account.product.mapper.AccountProductMapper;
import com.example.user_database_manager_service.service.product.common.CommonProductService;

import java.net.URI;
import java.util.UUID;

public abstract class ProductConsumingRegistrationProtoService implements ProductConsumingRegistrationService<UserProtoConfiguration.ProductRegistrationMessage> {
    protected final ProductService<UserProtoConfiguration.ProductMessage> productService;
    protected final CommonProductService commonProductService;
    protected final AccountProductService<UserProtoConfiguration.AccountUuidProductUriMessage> accountProductService;
    protected final CommonAccountProductService commonAccountProductService;
    protected final CommonAccountService commonAccountService;

    public ProductConsumingRegistrationProtoService(ProductService<UserProtoConfiguration.ProductMessage> productService, CommonProductService commonProductService, AccountProductService<UserProtoConfiguration.AccountUuidProductUriMessage> accountProductService, CommonAccountProductService commonAccountProductService, CommonAccountService commonAccountService) {
        this.productService = productService;
        this.commonProductService = commonProductService;
        this.accountProductService = accountProductService;
        this.commonAccountProductService = commonAccountProductService;
        this.commonAccountService = commonAccountService;
    }

    @Override
    public void register(UserProtoConfiguration.ProductRegistrationMessage subject) {
        if (commonAccountService.existsByUUID(UUID.fromString(subject.getAccountUuid()))) {
            if (!commonProductService.existsByUrl(URI.create(subject.getProduct().getUrl()))) {
                productService.save(subject.getProduct());
            }
            if (!commonAccountProductService.existsBy(UUID.fromString(subject.getAccountUuid()), URI.create(subject.getProduct().getUrl()))) {
                accountProductService.save(AccountProductMapper.mapTo(subject.getAccountUuid(), subject.getProduct().getUrl()));
            }
        } else {
            throw new NotFoundException(String.format("account not found: account uuid = %s", subject.getAccountUuid()));
        }
    }
}

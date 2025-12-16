package com.example.user_database_manager_service.service.account;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.exception.NotFoundException;
import com.example.user_database_manager_service.exception.ProcessingException;
import com.example.user_database_manager_service.repository.account.AccountRepository;
import com.example.user_database_manager_service.repository.user.UserRepository;
import com.example.user_database_manager_service.service.account.common.CommonAccountService;
import com.example.user_database_manager_service.service.user.common.CommonUserService;

import java.util.UUID;

public abstract class AccountRepositoryProtoService extends AccountRepositoryService<UserProtoConfiguration.AccountMessage> {
    protected final CommonUserService commonUserService;
    protected final CommonAccountService commonAccountService;

    public AccountRepositoryProtoService(AccountRepository<UserProtoConfiguration.AccountMessage> accountProtoRepository, CommonUserService commonUserService, CommonAccountService commonAccountService) {
        super(accountProtoRepository);
        this.commonUserService = commonUserService;
        this.commonAccountService = commonAccountService;
    }

    @Override
    public UserProtoConfiguration.AccountMessage save(UserProtoConfiguration.AccountMessage account) {
        if (commonUserService.existsByUUID(UUID.fromString(account.getUserUuid())) && !commonAccountService.existsByName(account.getName()) && commonAccountService.getCountOfAccountsOfUserByUserUUID(UUID.fromString(account.getUserUuid())) < 3) {
            return super.save(account);
        }
        throw new ProcessingException(String.format("User not found or count of accounts of user is exceeded: %s", account));
    }

    @Override
    public UserProtoConfiguration.AccountMessage update(UserProtoConfiguration.AccountMessage account) {
        if (commonAccountService.existsByUUID(UUID.fromString(account.getUuid()))) {
            return super.update(account);
        }
        throw new NotFoundException(String.format("Account is not found by uuid: %s", account.getUuid()));
    }
}

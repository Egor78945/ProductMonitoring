package com.example.user_database_manager_service.service.account.product;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.exception.AlreadyExistsException;
import com.example.user_database_manager_service.exception.NotFoundException;
import com.example.user_database_manager_service.exception.ProcessingException;
import com.example.user_database_manager_service.exception.message.ExceptionMessage;
import com.example.user_database_manager_service.repository.account.product.AccountProductRepository;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.UUID;

@Service
public class AccountProductProtoServiceManager extends AccountProductProtoService {
    public AccountProductProtoServiceManager(AccountProductRepository<UserProtoConfiguration.AccountProductMessage> accountProductRepository) {
        super(accountProductRepository);
    }

    @Override
    public UserProtoConfiguration.AccountProductMessage save(UserProtoConfiguration.AccountProductMessage entity) {
        if (!existsBy(UUID.fromString(entity.getAccountUuid()), URI.create(entity.getProductUrl()))) {
            return super.save(entity);
        }
        throw new AlreadyExistsException(ExceptionMessage.ALREADY_EXISTS.getMessage());
    }

    @Override
    public UserProtoConfiguration.AccountProductMessage update(UserProtoConfiguration.AccountProductMessage entity) {
        if (existsBy(UUID.fromString(entity.getAccountUuid()), URI.create(entity.getProductUrl()))) {
            return super.update(entity);
        }
        throw new NotFoundException(ExceptionMessage.NOT_FOUND.getMessage());
    }
}

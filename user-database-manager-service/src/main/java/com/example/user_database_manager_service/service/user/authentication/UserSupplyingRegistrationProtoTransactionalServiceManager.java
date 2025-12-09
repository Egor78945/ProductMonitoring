package com.example.user_database_manager_service.service.user.authentication;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.service.account.AccountService;
import com.example.user_database_manager_service.service.account.product.AccountProductService;
import com.example.user_database_manager_service.service.user.UserService;
import com.example.user_database_manager_service.service.user.notification.UserNotificationService;
import com.example.user_database_manager_service.service.user.role.UserRoleService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserSupplyingRegistrationProtoTransactionalServiceManager extends UserSupplyingRegistrationProtoService {
    public UserSupplyingRegistrationProtoTransactionalServiceManager(@Qualifier("userRepositoryProtoTransactionalServiceManager") UserService<UserProtoConfiguration.UserMessage> userService,
                                                                     @Qualifier("accountRepositoryProtoTransactionalServiceManager") AccountService<UserProtoConfiguration.AccountMessage> accountService,
                                                                     @Qualifier("userRoleRepositoryProtoTransactionalServiceManager") UserRoleService<UserProtoConfiguration.UserRoleMessage> userRoleService,
                                                                     @Qualifier("userNotificationRepositoryProtoTransactionalServiceManager") UserNotificationService<UserProtoConfiguration.UserNotificationMessage> userNotificationService,
                                                                     @Qualifier("accountProductRepositoryProtoTransactionalServiceManager") AccountProductService<UserProtoConfiguration.AccountUuidProductUriMessage> accountProductService) {
        super(userService, accountService, userRoleService, userNotificationService, accountProductService);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public UserProtoConfiguration.UserRegistrationMessage register(UserProtoConfiguration.UserRegistrationMessage registerModel) {
        System.out.println("USER TRANSACTIONAL REGISTER");
        return super.register(registerModel);
    }

    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void rollback(UserProtoConfiguration.UserRegistrationMessage registerModel) {
        super.rollback(registerModel);
    }
}

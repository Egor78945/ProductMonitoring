package com.example.user_database_manager_service.service.user.authentication;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.service.account.AccountService;
import com.example.user_database_manager_service.service.account.product.AccountProductService;
import com.example.user_database_manager_service.service.user.UserService;
import com.example.user_database_manager_service.service.user.notification.UserNotificationService;
import com.example.user_database_manager_service.service.user.role.UserRoleService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class UserSupplyingRegistrationProtoServiceManager extends UserSupplyingRegistrationProtoService {
    public UserSupplyingRegistrationProtoServiceManager(UserService<UserProtoConfiguration.UserMessage> userService, AccountService<UserProtoConfiguration.AccountMessage> accountService, UserRoleService<UserProtoConfiguration.UserRoleMessage> userRoleService, UserNotificationService<UserProtoConfiguration.UserNotificationMessage> userNotificationService, AccountProductService<UserProtoConfiguration.AccountUuidProductUriMessage> accountProductService) {
        super(userService, accountService, userRoleService, userNotificationService, accountProductService);
    }
}

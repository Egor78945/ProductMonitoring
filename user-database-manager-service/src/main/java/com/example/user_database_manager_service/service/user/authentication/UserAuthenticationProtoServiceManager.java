package com.example.user_database_manager_service.service.user.authentication;

import com.example.grpc.user.UserProtoConfiguration;
import com.example.user_database_manager_service.model.account.product.entity.AccountProduct;
import com.example.user_database_manager_service.model.user.notification.entity.UserNotification;
import com.example.user_database_manager_service.service.account.AccountService;
import com.example.user_database_manager_service.service.account.product.AccountProductService;
import com.example.user_database_manager_service.service.user.UserService;
import com.example.user_database_manager_service.service.user.notification.UserNotificationService;
import com.example.user_database_manager_service.service.user.role.UserRoleService;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationProtoServiceManager extends UserAuthenticationProtoService {
    public UserAuthenticationProtoServiceManager(UserService<UserProtoConfiguration.UserMessage> userService, AccountService<UserProtoConfiguration.AccountMessage> accountService, UserRoleService<UserProtoConfiguration.UserRoleMessage> userRoleService, UserNotificationService<UserNotification> userNotificationService, AccountProductService<AccountProduct> accountProductService) {
        super(userService, accountService, userRoleService, userNotificationService, accountProductService);
    }
}

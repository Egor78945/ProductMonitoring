package com.example.user_database_manager_service.service.user.operation.delete;

import com.example.user_database_manager_service.repository.user.common.CommonUserRepository;
import com.example.user_database_manager_service.service.account.common.CommonAccountService;
import com.example.user_database_manager_service.service.account.product.common.CommonAccountProductService;
import com.example.user_database_manager_service.service.user.notification.common.CommonUserNotificationService;
import com.example.user_database_manager_service.service.user.role.common.CommonUserRoleService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public class SafetyDeleteUserRepositoryServiceManager extends SafetyDeleteUserRepositoryService{
    public SafetyDeleteUserRepositoryServiceManager(CommonUserRepository commonUserRepository, CommonAccountProductService commonAccountProductService, CommonAccountService commonAccountService, CommonUserNotificationService commonUserNotificationService, CommonUserRoleService commonUserRoleService) {
        super(commonUserRepository, commonAccountProductService, commonAccountService, commonUserNotificationService, commonUserRoleService);
    }
}

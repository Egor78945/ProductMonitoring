package com.example.user_database_manager_service.service.account.common;

import com.example.user_database_manager_service.repository.account.common.CommonAccountRepository;
import org.springframework.stereotype.Service;

@Service
public class CommonAccountRepositoryServiceManager extends CommonAccountRepositoryService{
    public CommonAccountRepositoryServiceManager(CommonAccountRepository commonAccountRepository) {
        super(commonAccountRepository);
    }
}

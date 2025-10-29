package com.example.user_database_manager_service.service.user;

import com.example.user_database_manager_service.repository.user.JooqUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserProtoServiceManager extends UserProtoService {
    public UserProtoServiceManager(JooqUserRepository userProtoRepository) {
        super(userProtoRepository);
    }
}

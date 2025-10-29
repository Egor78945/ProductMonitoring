package com.example.product_processor_service.service.user;

import com.example.product_processor_service.model.user.entity.User;
import com.example.product_processor_service.repository.user.UserRepository;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;

@Service
public class UserServiceManager implements UserService<User> {
    private final UserRepository<User> userRepository;

    public UserServiceManager(UserRepository<User> userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllByProductUrl(URI uri) {
        return userRepository.findAllByProductUrl(uri.toString());
    }
}

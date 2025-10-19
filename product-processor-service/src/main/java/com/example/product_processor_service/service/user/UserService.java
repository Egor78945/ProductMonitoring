package com.example.product_processor_service.service.user;

import com.example.product_processor_service.model.user.entity.User;

import java.net.URI;
import java.util.List;

public interface UserService<U extends User> {
    List<U> getAllByProductUrl(URI uri);
}

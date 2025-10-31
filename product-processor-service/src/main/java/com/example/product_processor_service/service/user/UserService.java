package com.example.product_processor_service.service.user;


import java.net.URI;
import java.util.List;

public interface UserService<U> {
    List<U> getAllByProductUrl(URI uri);
}

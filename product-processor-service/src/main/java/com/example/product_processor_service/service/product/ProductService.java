package com.example.product_processor_service.service.product;

import com.example.product_processor_service.model.product.entity.Product;

import java.util.List;

public interface ProductService<P extends Product> {
    void save(P product);
    P getByUrl(String url);
    List<P> getAllExpired(int limit);
    boolean existsByUrl(String url);
}

package com.example.product_processor_service.service.account.product;

import com.example.product_processor_service.model.account.product.entity.AccountProduct;
import com.example.product_processor_service.model.product.entity.Product;

import java.util.List;
import java.util.UUID;

public interface AccountProductService <P extends AccountProduct>{
    void save(P product);
}
